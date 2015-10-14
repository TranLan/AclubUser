/**
 * 
 */
package aclub.users.android.main.ui.fragments;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.httpservices.ErrorMessage;
import aclub.users.android.httpservices.ResponseHandler;
import aclub.users.android.httpservices.RestHelper;
import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.httpservices.models.NearByRestaurantsResponse;
import aclub.users.android.log.DLog;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * @author ntdong2012
 *
 */
public class DetailRestaurantFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "section_number";
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	private View rootView;
	private TextView resName;
	private TextView resInfo;
	private TextView resAddress;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static DetailRestaurantFragment newInstance(int sectionNumber) {
		DetailRestaurantFragment fragment = new DetailRestaurantFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public DetailRestaurantFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.search_location_detail_fragment,
				container, false);
		RestHelper.getInstance().getDetailRestaurant(getActivity(),
				NearByRestaurantsFragment.currentRestaurantId,
				new ResponseHandler() {

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("getDetailRestaurant OK");

					}

					@Override
					public void onSuccess(BaseResponse response) {
						NearByRestaurantsResponse result = (NearByRestaurantsResponse) response;
						DLog.d("getDetailRestaurant O2K");
						displayRestaurant(result);
					}

					@Override
					public void onError(ErrorMessage error) {

					}
				});
		return rootView;
	}

	private void displayRestaurant(NearByRestaurantsResponse item) {
		initUI(item);
	}

	private void setMapDefualtProp() {
		map.setMyLocationEnabled(true);
		map.setTrafficEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.getUiSettings().setRotateGesturesEnabled(true);
		map.getUiSettings().setCompassEnabled(true);
		map.getUiSettings().setZoomGesturesEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(true);
	}

	private void initUI(NearByRestaurantsResponse res) {
		resName = (TextView) rootView
				.findViewById(R.id.detail_restaturance_info_name_textview);
		resInfo = (TextView) rootView
				.findViewById(R.id.detail_restaturance_info_address_textview);
		resName.setText(res.getName());
		resInfo.setText(res.getFullAddress());
		
		if (map == null) {
			map = ((SupportMapFragment) getChildFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			map.setMyLocationEnabled(true);
			if (map == null) {
				Toast.makeText(getActivity().getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			} else {
				setMapDefualtProp();

			}
		} else {
			setMapDefualtProp();
		}
		LatLng RES_LOCATION = new LatLng(Double.parseDouble(res.getLatitude()),
				Double.parseDouble(res.getLogitude()));
		Marker hamburg = map.addMarker(new MarkerOptions().position(
				RES_LOCATION).title(res.getName()));
		// Marker kiel = map.addMarker(new MarkerOptions()
		// .position(KIEL)
		// .title("Kiel")
		// .snippet("Kiel is cool")
		// .icon(BitmapDescriptorFactory
		// .fromResource(R.drawable.ic_launcher)));

		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(RES_LOCATION, 15));

		// Zoom in, animating the camera.
		// map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}
}
