/**
 * 
 */
package aclub.users.android.main.ui.cusview;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.httpservices.models.NearByRestaurantsResponse;
import aclub.users.android.main.models.SearchLocaItemResult;
import aclub.users.android.ui.cusimageview.RoundedImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class SearchLocationAdapter extends BaseAdapter {

	private ArrayList<NearByRestaurantsResponse> listOrginal;
	private ArrayList<NearByRestaurantsResponse> listSearch;
	private Context context;

	public SearchLocationAdapter(ArrayList<NearByRestaurantsResponse> list,
			Context context) {
		this.listOrginal = list;
		this.listSearch = list;
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		if (listSearch != null) {
			return listSearch.size();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		if (listSearch != null && listSearch.size() > position) {
			return listSearch.get(position);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	class ViewHolder {
		RoundedImageView avatar;
		TextView name;
		TextView address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertview, ViewGroup arg2) {
		View view = convertview;
		ViewHolder holder;
		if (view == null) {
			view = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.search_location_item_layout, null);
			holder = new ViewHolder();
			holder.address = (TextView) view
					.findViewById(R.id.search_loca_item_result_infor_address_textview);
			holder.name = (TextView) view
					.findViewById(R.id.search_loca_item_result_infor_name_textview);
			holder.avatar = (RoundedImageView) view
					.findViewById(R.id.search_loca_item_result_avatar_iv);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		NearByRestaurantsResponse item = listSearch.get(position);
		holder.address.setText(item.getFullAddress());
		holder.name.setText(item.getName());
		holder.avatar.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.home_15));
//		holder.avatar.setImageDrawable(context.getResources().getDrawable(
//				item.getItemRes()));
		return view;
	}

	public ArrayList<NearByRestaurantsResponse> search(String key) {
		ArrayList<NearByRestaurantsResponse> result = new ArrayList<NearByRestaurantsResponse>();
		if (key != null && key.length() > 0) {
			key = key.toLowerCase();
			for (int i = 0; i < listOrginal.size(); i++) {
				String appName = (String) listOrginal.get(i).getName().toLowerCase();
				if (appName.contains(key)) {
					result.add(listOrginal.get(i));
				}
			}
		} else {
			result = listOrginal;
		}
		listSearch = result;
		return listSearch;
	}
}
