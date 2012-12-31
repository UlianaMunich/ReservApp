package tudresden.mobile.reserverest;

import java.util.ArrayList;
import java.util.List;

import tudresden.mobile.reserverest.R;
import tudresden.mobile.reserverest.backend.Dish;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DishArrayAdapter extends ArrayAdapter<Dish> {
	private TextView dishName;
	private TextView dishPrice;
	private TextView dishDesc;
	private List<Dish> dishes = new ArrayList<Dish>();

	public DishArrayAdapter(Context context, int textViewResourceId,
			List<Dish> objects) {
		super(context, textViewResourceId, objects);
		this.dishes = objects;
	}

	public int getCount() {
		return this.dishes.size();
	}

	public Dish getItem(int index) {
		return this.dishes.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.listitem_dishes, parent, false);
		}

		// Get item
		Dish dish = getItem(position);
		
		dishName = (TextView) row.findViewById(R.id.dish_name);
		dishPrice = (TextView) row.findViewById(R.id.dish_price);
		dishDesc = (TextView) row.findViewById(R.id.dish_desc);

		dishName.setText(dish.getName_en());
		dishPrice.setText(Double.toString(dish.getPrice()) + "\u20ac");
		dishDesc.setText(dish.getDesc_en());
		
		return row;
	}
}
