/**
 * 
 */
package aclub.users.android.main.ui.cusview;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import aclub.users.android.R;
import aclub.users.android.httpservices.models.VoucherResponse;
import aclub.users.android.main.models.HotSaleItem;
import aclub.users.android.main.models.VoucherItem;
import aclub.users.android.main.ui.cusview.HotSaleAdapter.ViewHolder;
import aclub.users.android.main.ui.fragments.VoucherBoxFragment;
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
public class VoucherAdapter extends BaseAdapter {

	private ArrayList<VoucherResponse> originalList;
	private ArrayList<VoucherResponse> searchList;
	private Context context;
	private VoucherBoxFragment.BookInterface handler;

	public VoucherAdapter(ArrayList<VoucherResponse> list, Context context,
			VoucherBoxFragment.BookInterface handler) {
		this.originalList = list;
		this.searchList = list;
		this.context = context;
		this.handler = handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		if (searchList != null) {
			return searchList.size();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int arg0) {
		if (searchList != null && searchList.size() > arg0) {
			return searchList.get(arg0);
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
		return 0;
	}

	class ViewHolder {
		ImageView image;
		TextView itemTitle;
		TextView itemPrice;
		TextView itemRes;
		Button itemConfirm;
	}

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
		final VoucherResponse item = searchList.get(position);
		holder.itemPrice.setText(item.getCode());
		holder.itemRes.setText(item.getShortDesc());
		holder.itemTitle.setText(item.getVenue().getName());
		
		Picasso.with(context).load(item.getImages().getThumbLink()).placeholder(R.drawable.hot_sale_one).into(holder.image);
//		holder.image.setImageDrawable(view.getResources().getDrawable(
//				R.drawable.hot_sale_one));
		holder.itemConfirm.setText(context
				.getString(R.string.voucher_book_lable));
		holder.itemConfirm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (handler != null) {
					handler.onBookVoucher(item);
				}
			}
		});
		return view;
	}

}
