/**
 * 
 */
package aclub.users.android.httpservices.models;

import java.util.ArrayList;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class NearByRestaurantsList {

	private ArrayList<NearByRestaurantsResponse> listNearByRestaurances;

	public ArrayList<NearByRestaurantsResponse> getListNearByRestaurances() {
		return listNearByRestaurances;
	}

	public void setListNearByRestaurances(ArrayList<NearByRestaurantsResponse> listNearByRestaurances) {
		this.listNearByRestaurances = listNearByRestaurances;
	}
	
	
}
