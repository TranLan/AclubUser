/**
 * 
 */
package aclub.users.android.main.ui.cusview;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.log.DLog;
import aclub.users.android.main.models.MemberItems;
import aclub.users.android.main.ui.cusview.HotSaleAdapter.ViewHolder;
import aclub.users.android.ui.cusimageview.RoundedImageView;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author ntdong2012
 *
 */
public class HAdapter extends BaseAdapter {

	private ArrayList<MemberItems> list;
	private Context context;

	public HAdapter(ArrayList<MemberItems> list, Context context) {
		super();
		this.context = context;
		this.list = list;
		DLog.d("HAdapter: listSize " + list.size());
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
	 * @see android.widget.Adapter#getCount()
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
		return 0;
	}

	@SuppressWarnings("deprecation")
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(R.layout.active_member_layout, parent,
					false);
			holder.avatar = (RoundedImageView) view
					.findViewById(R.id.member_avatar_iv);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		MemberItems member = list.get(position);
		holder.avatar.setImageDrawable(context.getResources().getDrawable(
				member.getAvaResourceId()));
		return view;
	}

	class ViewHolder {
		RoundedImageView avatar;
	}

};
