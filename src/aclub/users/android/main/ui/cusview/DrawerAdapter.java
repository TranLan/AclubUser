/**
 * 
 */
package aclub.users.android.main.ui.cusview;

import java.util.List;

import aclub.users.android.R;
import aclub.users.android.main.models.DrawerItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

	private List<DrawerItem> drawerItemList;
	private Context context;
	private int layoutResID;

	/**
	 * 
	 */
	public DrawerAdapter(Context context, int layoutResourceID,
			List<DrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.drawerItemList = listItems;
		this.context = context;
		this.layoutResID = layoutResourceID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		if (drawerItemList != null) {
			return drawerItemList.size();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	private static class DrawerItemHolder {
		TextView drawerItemName;
		ImageView drawerItemIcon;
		LinearLayout itemLayout;
		EditText searchItem;
		View separateView;
		TextView addFriendTv;
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
		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.drawerItemName = (TextView) view
					.findViewById(R.id.drawer_itemName);
			drawerHolder.drawerItemIcon = (ImageView) view
					.findViewById(R.id.drawer_icon);
			drawerHolder.searchItem = (EditText) view
					.findViewById(R.id.drawer_edt);
			drawerHolder.itemLayout = (LinearLayout) view
					.findViewById(R.id.itemLayout);
			drawerHolder.separateView = (View) view
					.findViewById(R.id.drawer_view);
			drawerHolder.addFriendTv = (TextView) view
					.findViewById(R.id.drawer_addfriend_tv);
			view.setTag(drawerHolder);
		} else {
			drawerHolder = (DrawerItemHolder) view.getTag();
		}

		DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

		if (dItem.isEdtDrawerItem()) {
			drawerHolder.itemLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.searchItem.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.separateView.setVisibility(View.GONE);
			drawerHolder.addFriendTv.setVisibility(View.GONE);
		} else if (dItem.getDrawerItemName() == null) {
			drawerHolder.itemLayout.setVisibility(View.GONE);
			drawerHolder.searchItem.setVisibility(LinearLayout.GONE);
			drawerHolder.separateView.setVisibility(View.VISIBLE);
			drawerHolder.addFriendTv.setVisibility(View.GONE);
		} else {
			drawerHolder.separateView.setVisibility(View.GONE);
			drawerHolder.searchItem.setVisibility(LinearLayout.GONE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.addFriendTv.setVisibility(View.GONE);
			try {
				drawerHolder.drawerItemIcon.setImageDrawable(context
						.getResources().getDrawable(dItem.getImgResID()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			drawerHolder.drawerItemName.setText(dItem.getDrawerItemName());
		}
		if (position == 8) {
			drawerHolder.addFriendTv.setVisibility(View.VISIBLE);
		}
		return view;
	}
}
