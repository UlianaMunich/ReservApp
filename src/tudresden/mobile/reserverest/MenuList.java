package tudresden.mobile.reserverest;

import java.util.ArrayList;
import java.util.List;

import tudresden.mobile.reserverest.R;
import tudresden.mobile.reserverest.backend.Dish;
import tudresden.mobile.reserverest.backend.RestaurantManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MenuList extends FragmentActivity {
	static final String[] dishtypes = {"main", "soup", "salad"};

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_list);
		
		Bundle b = getIntent().getExtras();
		
		setTitle(b.getString("rest_name"));

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu_list, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			case 2:
				return getString(R.string.title_section3).toUpperCase();
			}
			return null;
		}
	}

	public class DummySectionFragment extends ListFragment {

//		ListView lvDishes;
		List<Dish> dishesList = new ArrayList<Dish>();
		int type_id = 0;
		
		/* dimakuv: 
		 *   Background Task for fetching list of dishes from our backend server into Spinner
		 */
		public class GetDishesTask extends AsyncTask<String, Void, DishArrayAdapter> {
			
			@Override
			protected DishArrayAdapter doInBackground(String... types) {
				DishArrayAdapter aa = null;

				dishesList = RestaurantManager.getDishes(types[0]);		

				if (dishesList != null) {
					aa = new DishArrayAdapter(
							getActivity(), R.layout.listitem_restaurants, dishesList);
				}
		        
				return aa;
			}
			
			@Override
			protected void onPostExecute(DishArrayAdapter aa) {			
				// NOTE: this method is executed in main, UI thread
				if (aa != null) {
					setListAdapter(aa);			
				} else {
	        	   	Toast.makeText(getActivity(), "WARNING: Didn't retrieve dishes!", Toast.LENGTH_SHORT).show();		        	   
	           }
			}
		}
		
		
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Bundle args = getArguments();
			type_id = args.getInt(ARG_SECTION_NUMBER);
			
	        new GetDishesTask().execute(dishtypes[type_id]);
	        
            return super.onCreateView(inflater, container, savedInstanceState);	        
		}
		
		@Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            // Create a customized ArrayAdapter
			// empty in the beginning
			DishArrayAdapter adapter = new DishArrayAdapter(
					getActivity(), R.layout.listitem_dishes, dishesList);

			setListAdapter(adapter);
			registerForContextMenu(getListView());
        }
		
		@Override
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    	  super.onCreateContextMenu(menu, v, menuInfo);
	    	  MenuInflater inflater = getMenuInflater();
	    	  inflater.inflate(R.menu.menu_context, menu);
	    	}
		
		@Override
		public boolean onContextItemSelected(MenuItem item) {
			AdapterView.AdapterContextMenuInfo info = null;
			try {
			    info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
			} catch (ClassCastException e) {
				/* nothing to do */
			}

			List<Dish> basketDishes = RestaurantManager.getBasketDishes();
			Dish dish = (Dish) getListAdapter().getItem(info.position);
			
			switch (item.getItemId()) {
				case R.id.Select:
					// add this dish to basket
					if (!basketDishes.contains(dish)) {
						basketDishes.add(dish);
						Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getActivity(), "Already in the basket", Toast.LENGTH_SHORT).show();					
					}
					break;
			
				case R.id.Remove:
					if (basketDishes.contains(dish)) {
						basketDishes.remove(dish);
						Toast.makeText(getActivity(), "Removed", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getActivity(), "Wasn't in the basket", Toast.LENGTH_SHORT).show();					
					}
					break;
					
				case R.id.ShowBasket:
					Intent intent = new Intent(getActivity(), BasketActivity.class);
			    	startActivity(intent);
					break;
			
				case R.id.Cancel:
					return false;
			}
			
			return true;
		}
		
		@Override
		public void onListItemClick(ListView l, View v, final int position, long id) {
//			super.onListItemClick(l, v, position, id);
			List<Dish> basketDishes = RestaurantManager.getBasketDishes();
			Dish dish = (Dish) getListAdapter().getItem(position);
			
			if (!basketDishes.contains(dish)) {
				basketDishes.add(dish);
				Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getActivity(), "Already in the basket", Toast.LENGTH_SHORT).show();					
			}
	    }
		
	}

}
