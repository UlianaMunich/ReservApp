package tudresden.mobile.reserverest.backend;

import android.content.Context;
import android.location.Location;
import tudresden.mobile.reserverest.backend.LocationFinder.LocationResult;

/* implemented as the simplest skeleton, who cares about threads... */
public class MyLocation {
	static MyLocation singleton = null;
	
	Context mContext;
	Location location;
	 
    private MyLocation(Context mContext){
    	this.mContext = mContext; 
    }
 
    // I need mContext to pass to LocationFinder (to find LocationService)
    // TODO: change this ugly implementation with argument
    public static MyLocation getInstance(Context mContext){
        if (singleton == null) {
        	singleton = new MyLocation(mContext);
        	
        	// TODO: that's a mock to test distances; delete this later
	        Location mockLocation = new Location("foobar");
	        // these are coordinates of TU Dresden Faculty of Informatics building
	        mockLocation.setLatitude(51.025204); 
	        mockLocation.setLongitude(13.723426);
        	singleton.location = mockLocation;
        }
        return singleton;
    }
    
    public Location getLocation() {
		return location;
	}

	public void updateLocation(){
    	LocationResult locationResult = new LocationResult(){
    	    @Override
    	    public void gotLocation(Location location){
    	        //Got the location!
    	    	if (location != null)
    	    		MyLocation.this.location = location;
    	    }
    	};
    	LocationFinder finder = new LocationFinder();
    	finder.getLocation(mContext, locationResult);
    }
}
