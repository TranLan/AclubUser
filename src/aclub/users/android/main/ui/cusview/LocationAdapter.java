/**
 * 
 */
package aclub.users.android.main.ui.cusview;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.main.models.LocationItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author ntdong2012
 *
 */
public class LocationAdapter extends BaseAdapter {

	private ArrayList<LocationItem> list;
	private Context context;

	/**
	 * 
	 */
	public LocationAdapter(Context context, ArrayList<LocationItem> list) {
		this.list = list;
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
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
		if (list != null && list.size() > position) {
			return list.get(position);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;

		if (view == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.location_item_layout, parent,
					false);
			holder.avatar = (ImageView) view.findViewById(R.id.location_ava_iv);
			holder.des = (TextView) view.findViewById(R.id.location_des_tv);
			holder.name = (TextView) view.findViewById(R.id.location_name_tv);
			holder.rateStar = (ImageView) view
					.findViewById(R.id.location_star_iv);
			holder.num = (TextView) view.findViewById(R.id.location_num_tv);
			holder.rate = (TextView) view.findViewById(R.id.location_rate_tv);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		LocationItem item = (LocationItem) list.get(position);

		holder.avatar.setImageDrawable(context.getResources().getDrawable(
				item.getLocationAvaId()));
		holder.des.setText(item.getLocationDes());
		holder.name.setText(item.getLocationName());
		holder.num.setText((position + 1) + "");
		holder.rate.setText(item.getLocationRate());

		return view;
	}

	class ViewHolder {
		TextView num;
		TextView name;
		TextView des;
		TextView rate;
		ImageView rateStar;
		ImageView avatar;
	}

}
