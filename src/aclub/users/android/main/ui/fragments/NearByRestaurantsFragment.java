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
import aclub.users.android.utils.AnimationUtils;
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
	public static int currentRestaurantId = 0;

	private ListView resultListView;
	// private ArrayList<NearByRestaurantsResponse> listLocaResutl;
	private SearchLocationAdapter adapter;
	private View rootView;
	private Button searchResBtn;

	private ArrayList<NearByRestaurantsResponse> listDb;

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
		CommonValues.showDialogLoading(getActivity());
		if (CommonValues.TEST) {
			latitude = "21.022592";
			longitude = "105.852392";
		}
		paging = 1;
		radius = 100;
		listDb = new ArrayList<NearByRestaurantsResponse>();
		RestHelper.getInstance().getNearByRestaurants(getActivity(), latitude,
				longitude, radius, paging, new ResponseHandler() {

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("OK" + responses.size());
						listDb.clear();
						ArrayList<NearByRestaurantsResponse> list = new ArrayList<NearByRestaurantsResponse>();
						for (int i = 0; i < responses.size(); i++) {
							NearByRestaurantsResponse nearby = (NearByRestaurantsResponse) responses
									.get(i);
							list.add(nearby);
						}
						listDb = list;
						CommonValues.hideDialogLoading();
						initDataFromServer();
					}

					@Override
					public void onSuccess(BaseResponse response) {

					}

					@Override
					public void onError(ErrorMessage error) {
						CommonValues.hideDialogLoading();
						DLog.d("ERROR L ");
					}
				});
	}

	private void initDataFromServer() {
		adapter = new SearchLocationAdapter(listDb, getActivity());
		resultListView.setAdapter(adapter);
		resultListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg3) {
				NearByRestaurantsResponse item = (NearByRestaurantsResponse) ((SearchLocationAdapter) adapter
						.getAdapter()).getItem(position);
				currentRestaurantId = item.getId();
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

	private void initUI(View view) {
		searchResEdt = (EditText) view
				.findViewById(R.id.search_restaurance_edt);
		// searchResEdt.addTextChangedListener(new TextWatcher() {
		//
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before,
		// int count) {
		// if (adapter != null && searchResEdt != null
		// && searchResEdt.getText() != null) {
		// listDb = adapter.search(searchResEdt.getText().toString());
		// adapter.notifyDataSetChanged();
		// }
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		//
		// }
		//
		// @Override
		// public void afterTextChanged(Editable s) {
		// if (adapter != null && searchResEdt != null
		// && searchResEdt.getText() != null) {
		// listDb = adapter.search(searchResEdt.getText().toString());
		// adapter.notifyDataSetChanged();
		// }
		// }
		// });
		searchResBtn = (Button) view.findViewById(R.id.search_restaurance_btn);
		searchResBtn.setOnClickListener(this);
		resultListView = (ListView) view
				.findViewById(R.id.search_loca_listview);
		listDb = new ArrayList<NearByRestaurantsResponse>();
		adapter = new SearchLocationAdapter(listDb, getActivity());
		resultListView.setAdapter(adapter);
		resultListView.setOnItemClickListener(new OnItemClickListener() {

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
			String name = searchResEdt.getText().toString();
			if (name != null && name.length() > 0) {
				CommonValues.showDialogLoading(getActivity());
				if (CommonValues.TEST) {
					name = "cafe";
					latitude = "21.022592";
					longitude = "105.852392";
					paging = 1;
					radius = 100;
				}
				RestHelper.getInstance().getRestaurantByName(getActivity(),
						latitude, longitude, radius, paging, name,
						new ResponseHandler() {

							@Override
							public void onSuccess(
									ArrayList<BaseResponse> responses,
									boolean isJSONArrayFB) {
								CommonValues.hideDialogLoading();
								listDb.clear();
								ArrayList<NearByRestaurantsResponse> list = new ArrayList<NearByRestaurantsResponse>();
								for (int i = 0; i < responses.size(); i++) {
									NearByRestaurantsResponse nearby = (NearByRestaurantsResponse) responses
											.get(i);
									list.add(nearby);
								}
								listDb = list;
								CommonValues.hideDialogLoading();
								initDataFromServer();
							}

							@Override
							public void onSuccess(BaseResponse response) {

							}

							@Override
							public void onError(ErrorMessage error) {
								DLog.d(error.getMessage());
								CommonValues.hideDialogLoading();
							}
						});
			} else {
				searchResEdt
						.setError(getString(R.string.error_register_infor_message));
				AnimationUtils.shake(getActivity(), searchResEdt);
			}

		default:
			break;
		}
	}

}
