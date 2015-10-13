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
import aclub.users.android.main.ui.cusview.SearchLocationAdapter;
import aclub.users.android.utils.CommonValues;
import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class NearByRestaurantsFragment extends Fragment implements
		LocationListener, OnClickListener {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private EditText searchResEdt;

	/*************
	 * Location manager
	 */
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected boolean gps_enabled, network_enabled;
	protected String latitude, longitude;
	protected int radius;
	protected int paging;

	private ListView lvSearchResutl;
	private ArrayList<NearByRestaurantsResponse> listLocaResutl;
	private SearchLocationAdapter locaAdapter;
	private View rootView;
	private Button searchResBtn;

	private ArrayList<NearByRestaurantsResponse> listNearByRest;

	public static NearByRestaurantsFragment newInstance(int sectionNumber) {
		NearByRestaurantsFragment fragment = new NearByRestaurantsFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public NearByRestaurantsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.search_location_fragment,
				container, false);
		initUI(rootView);
		getNearByRestaurants();
		return rootView;
	}

	private void getNearByRestaurants() {
		showDialogLoading();
		if (CommonValues.TEST) {
			latitude = "21.062592";
			longitude = "105.852392";
		}
		paging = 1;
		radius = 100;
		listNearByRest = new ArrayList<NearByRestaurantsResponse>();
		RestHelper.getInstance().getNearByRestaurants(getActivity(), latitude,
				longitude, radius, paging, new ResponseHandler() {

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("OK" + responses.size());
						for (int i = 0; i < responses.size(); i++) {
							NearByRestaurantsResponse nearby = (NearByRestaurantsResponse) responses
									.get(i);
							listNearByRest.add(nearby);
						}
						hideDialogLoading();
						initDataFromServer();
					}

					@Override
					public void onSuccess(BaseResponse response) {

					}

					@Override
					public void onError(ErrorMessage error) {
						hideDialogLoading();
						DLog.d("ERROR L ");
						;
					}
				});
	}

	private void initDataFromServer() {
		locaAdapter = new SearchLocationAdapter(listNearByRest, getActivity());
		lvSearchResutl.setAdapter(locaAdapter);
		lvSearchResutl.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				NearByRestaurantsResponse item = (NearByRestaurantsResponse) ((SearchLocationAdapter) adapter
						.getAdapter()).getItem(position);
				final FragmentTransaction ft = getActivity()
						.getSupportFragmentManager().beginTransaction();
				ft.replace(R.id.main_content,
						DetailRestaurantFragment.newInstance(1),
						"NewFragmentTag");
				ft.addToBackStack(null);
				ft.commit();

			}

		});
	}

	protected ProgressDialog dialog;

	protected void showDialogLoading() {
		if (dialog == null) {
			dialog = new ProgressDialog(getActivity());
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Loading. Please wait...");
			dialog.setIndeterminate(true);
			dialog.setCanceledOnTouchOutside(false);
		}
		if (dialog != null && !dialog.isShowing()) {
			dialog.show();
		}
	}

	public void hideDialogLoading() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	private void initUI(View view) {
		searchResEdt = (EditText) view
				.findViewById(R.id.search_restaurance_edt);
		searchResEdt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (locaAdapter != null && searchResEdt != null
						&& searchResEdt.getText() != null) {
					listLocaResutl = locaAdapter.search(searchResEdt.getText()
							.toString());
					locaAdapter.notifyDataSetChanged();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (locaAdapter != null && searchResEdt != null
						&& searchResEdt.getText() != null) {
					listLocaResutl = locaAdapter.search(searchResEdt.getText()
							.toString());
					locaAdapter.notifyDataSetChanged();
				}
			}
		});
		searchResBtn = (Button) view.findViewById(R.id.search_restaurance_btn);
		searchResBtn.setOnClickListener(this);
		lvSearchResutl = (ListView) view
				.findViewById(R.id.search_loca_listview);
	}

	private void initListLocaResut() {
		// listLocaResutl = new ArrayList<SearchLocaItemResult>();
		// listLocaResutl
		// .add(new SearchLocaItemResult("BBQ",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// listLocaResutl
		// .add(new SearchLocaItemResult("Pho ngon 37",
		// "126 Trieu Viet Vuong hai ba trung ha noi",
		// R.drawable.home_15));
		// locaAdapter = new SearchLocationAdapter(listLocaResutl,
		// getActivity());
		// lvSearchResutl.setAdapter(locaAdapter);
		// lvSearchResutl.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> adapter, View view,
		// int position, long arg3) {
		// SearchLocaItemResult item = (SearchLocaItemResult)
		// ((SearchLocationAdapter) adapter
		// .getAdapter()).getItem(position);
		// final FragmentTransaction ft = getActivity()
		// .getSupportFragmentManager().beginTransaction();
		// ft.replace(R.id.main_content,
		// DetailLocationFragment.newInstance(1), "NewFragmentTag");
		// ft.addToBackStack(null);
		// ft.commit();
		//
		// }
		//
		// });
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.location.LocationListener#onLocationChanged(android.location.
	 * Location)
	 */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude() + "";
		longitude = location.getLongitude() + "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.location.LocationListener#onStatusChanged(java.lang.String,
	 * int, android.os.Bundle)
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.location.LocationListener#onProviderEnabled(java.lang.String)
	 */
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.location.LocationListener#onProviderDisabled(java.lang.String)
	 */
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.search_restaurance_btn:
			RestHelper.getInstance().getRestaurantByName(getActivity(),
					latitude, longitude, radius, paging, "beer",
					new ResponseHandler() {

						@Override
						public void onSuccess(
								ArrayList<BaseResponse> responses,
								boolean isJSONArrayFB) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onSuccess(BaseResponse response) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onError(ErrorMessage error) {
							// TODO Auto-generated method stub

						}
					});
		default:
			break;
		}
	}

}
