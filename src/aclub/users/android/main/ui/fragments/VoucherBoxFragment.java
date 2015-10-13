/**
 * 
 */
package aclub.users.android.main.ui.fragments;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.log.DLog;
import aclub.users.android.main.models.VoucherItem;
import aclub.users.android.main.ui.cusview.VoucherAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VoucherBoxFragment extends Fragment implements
		View.OnClickListener {

	private static final String ARG_SECTION_NUMBER = "section_number";

	private ListView voucherListView;
	private ArrayList<VoucherItem> listVoucher;
	private VoucherAdapter voucherAdapter;
	private RelativeLayout voucherHeaderLayout;
	private EditText searchVoucherEdt;
	private Button searchVoucherBtn;
	private TextView searchVoucherResultTv;

	public interface BookInterface {
		void onBookVoucher(VoucherItem item);
	}

	private BookInterface bookListener;

	public void setListener(BookInterface listener) {
		this.bookListener = listener;
	}

	public static VoucherBoxFragment newInstance(int sectionNumber) {
		VoucherBoxFragment fragment = new VoucherBoxFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public VoucherBoxFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.voucher_fragment_layout,
				container, false);
		initHeader(rootView);
		initListView(rootView);
		return rootView;
	}

	private void initListView(View view) {
		voucherListView = (ListView) view.findViewById(R.id.voucher_listview);
		listVoucher = new ArrayList<VoucherItem>();

		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));
		listVoucher
				.add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
						"300,000 VND/kg", "Nhà hàng Trung hoa",
						R.drawable.hot_sale_one));

		voucherAdapter = new VoucherAdapter(listVoucher, getActivity(),
				new BookInterface() {

					@Override
					public void onBookVoucher(VoucherItem item) {
						DLog.d("onClick : " + item.getVoucherName());
					}
				});
		voucherListView.setAdapter(voucherAdapter);
	}

	private void initHeader(View view) {
		voucherHeaderLayout = (RelativeLayout) view
				.findViewById(R.id.voucher_header_layout);
		searchVoucherEdt = (EditText) voucherHeaderLayout
				.findViewById(R.id.search_restaurance_edt);
		searchVoucherEdt.setHint(getString(R.string.search_by_city));
		searchVoucherBtn = (Button) voucherHeaderLayout
				.findViewById(R.id.search_restaurance_btn);
		searchVoucherResultTv = (TextView) voucherHeaderLayout
				.findViewById(R.id.search_result_total_tv);
		searchVoucherResultTv
				.setText(getString(R.string.voucher_total_result_label));
		searchVoucherBtn.setOnClickListener(this);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {

	}
}
