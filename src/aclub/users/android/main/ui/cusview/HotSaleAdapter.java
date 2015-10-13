/**
 * 
 */
package aclub.users.android.main.ui.cusview;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.main.models.HotSaleItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class HotSaleAdapter extends BaseAdapter {

	private ArrayList<HotSaleItem> listHotSales;
	private Context context;

	/**
	 * 
	 */
	public HotSaleAdapter(Context context, ArrayList<HotSaleItem> list) {
		this.context = context;
		this.listHotSales = list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		if (listHotSales != null) {
			return listHotSales.size();
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
		if (listHotSales != null && listHotSales.size() > position) {
			return listHotSales.get(position);
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
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.hot_sale_item_layout, parent,
					false);
			holder.image = (ImageView) view
					.findViewById(R.id.hot_sale_item_avatar_iv);
			holder.itemTitle = (TextView) view
					.findViewById(R.id.hot_sale_item_title_tv);
			holder.itemRes = (TextView) view
					.findViewById(R.id.hot_sale_item_restaurance_tv);
			holder.itemPrice = (TextView) view
					.findViewById(R.id.hot_sale_item_price_tv);
			holder.itemConfirm = (Button) view
					.findViewById(R.id.hot_sale_item_confirm_btn);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		HotSaleItem item = listHotSales.get(position);
		holder.itemPrice.setText(item.getHoteSalePrice());
		holder.itemRes.setText(item.getHoteSaleRestauranceName());
		holder.itemTitle.setText(item.getHotSaleTitle());
		holder.image.setImageDrawable(view.getResources()
				.getDrawable(item.getHoteSaleIv()));
		return view;
	}

	class ViewHolder {
		ImageView image;
		TextView itemTitle;
		TextView itemPrice;
		TextView itemRes;
		Button itemConfirm;
	}

}
