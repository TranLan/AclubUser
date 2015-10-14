/**
 * 
 */
package aclub.users.android.main.ui.fragments;

import java.util.ArrayList;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import aclub.users.android.R;
import aclub.users.android.httpservices.ErrorMessage;
import aclub.users.android.httpservices.ResponseHandler;
import aclub.users.android.httpservices.RestHelper;
import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.httpservices.models.NearByRestaurantsResponse;
import aclub.users.android.log.DLog;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author ntdong2012
 *
 */
public class DetailRestaurantFragment extends Fragment implements OnMapReadyCallback {

	private static final String ARG_SECTION_NUMBER = "section_number";
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;
	private View rootView;
	private TextView resName;
	private TextView resInfo;
	private TextView resAddress;
	private LatLng RES_LOCATION;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.search_location_detail_fragment, container, false);
		RestHelper.getInstance().getDetailRestaurant(getActivity(), NearByRestaurantsFragment.currentRestaurantId,
				new ResponseHandler() {

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses, boolean isJSONArrayFB) {
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

	private SupportMapFragment mSupportMapFragment;

	private void initUI(NearByRestaurantsResponse res) {
		resName = (TextView) rootView.findViewById(R.id.detail_restaturance_info_name_textview);
		resInfo = (TextView) rootView.findViewById(R.id.detail_restaturance_info_address_textview);
		resName.setText(res.getName());
		resInfo.setText(res.getFullAddress());
		RES_LOCATION = new LatLng(Double.parseDouble(res.getLatitude()), Double.parseDouble(res.getLogitude()));
		if (map == null) {
			map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
			map.setMyLocationEnabled(true);
			if (map == null) {
				Toast.makeText(getActivity().getApplicationContext(), "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			} else {
				setMapDefualtProp();

			}
		} else {
			setMapDefualtProp();
		}

		Marker hamburg = map.addMarker(new MarkerOptions().position(RES_LOCATION).title(res.getName()));
		// Marker kiel = map.addMarker(new MarkerOptions()
		// .position(KIEL)
		// .title("Kiel")
		// .snippet("Kiel is cool")
		// .icon(BitmapDescriptorFactory
		// .fromResource(R.drawable.ic_launcher)));

		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(RES_LOCATION, 15));
		Location currentLocation = map.getMyLocation();
		if (currentLocation != null) {
			LatLng current = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
			map.addPolyline(new PolylineOptions().geodesic(true).add(current).add(RES_LOCATION));
		}

		// Zoom in, animating the camera.
		// map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		// RES_LOCATION = new LatLng(Double.parseDouble(res.getLatitude()),
		// Double.parseDouble(res.getLogitude()));
		// mSupportMapFragment = (SupportMapFragment)
		// getChildFragmentManager().findFragmentById(R.id.map);
		// if (mSupportMapFragment == null) {
		// FragmentManager fragmentManager = getFragmentManager();
		// FragmentTransaction fragmentTransaction =
		// fragmentManager.beginTransaction();
		// mSupportMapFragment = SupportMapFragment.newInstance();
		// fragmentTransaction.replace(R.id.map, mSupportMapFragment).commit();
		// }
		//
		// if (mSupportMapFragment != null) {
		// mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
		// @Override
		// public void onMapReady(GoogleMap googleMap) {
		// if (googleMap != null) {
		//
		// googleMap.getUiSettings().setAllGesturesEnabled(true);
		//
		// CameraPosition cameraPosition = new
		// CameraPosition.Builder().target(RES_LOCATION).zoom(15.0f)
		// .build();
		// CameraUpdate cameraUpdate =
		// CameraUpdateFactory.newCameraPosition(cameraPosition);
		// googleMap.moveCamera(cameraUpdate);
		// googleMap.setMyLocationEnabled(true);
		// googleMap.setTrafficEnabled(true);
		// googleMap.getUiSettings().setMyLocationButtonEnabled(true);
		// googleMap.getUiSettings().setRotateGesturesEnabled(true);
		// googleMap.getUiSettings().setCompassEnabled(true);
		// googleMap.getUiSettings().setZoomGesturesEnabled(true);
		// googleMap.getUiSettings().setZoomControlsEnabled(true);
		// Location currentLocation = googleMap.getMyLocation();
		// LatLng current = new LatLng(currentLocation.getLatitude(),
		// currentLocation.getLongitude());
		// googleMap.addPolyline(new
		// PolylineOptions().geodesic(true).add(current).add(RES_LOCATION));
		// }
		//
		// }
		// });
		// }
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.android.gms.maps.OnMapReadyCallback#onMapReady(com.google.
	 * android.gms.maps.GoogleMap)
	 */
	@Override
	public void onMapReady(GoogleMap googleMap) {
		DLog.d("onMapReady");
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(RES_LOCATION));
		Location currentLocation = googleMap.getMyLocation();
		LatLng current = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
		googleMap.addPolyline(new PolylineOptions().geodesic(true).add(current).add(RES_LOCATION));
	}
}
