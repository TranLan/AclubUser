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
import aclub.users.android.httpservices.models.VoucherResponse;
import aclub.users.android.log.DLog;
import aclub.users.android.main.models.VoucherItem;
import aclub.users.android.main.ui.cusview.VoucherAdapter;
import aclub.users.android.utils.CommonValues;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
	private ArrayList<VoucherResponse> listVoucher;
	private VoucherAdapter voucherAdapter;
	private RelativeLayout voucherHeaderLayout;
	private EditText searchVoucherEdt;
	private Button searchVoucherBtn;
	private TextView searchVoucherResultTv;

	public interface BookInterface {
		void onBookVoucher(VoucherResponse item);
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
		listVoucher = new ArrayList<VoucherResponse>();
		CommonValues.showDialogLoading(getActivity());
		RestHelper.getInstance().getVoucher(getActivity(),
				new ResponseHandler() {

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						listVoucher.clear();
						for (int i = 0; i < responses.size(); i++) {
							VoucherResponse vr = (VoucherResponse) responses
									.get(i);
							listVoucher.add(vr);
						}
						voucherAdapter = new VoucherAdapter(listVoucher,
								getActivity(), new BookInterface() {

									@Override
									public void onBookVoucher(
											VoucherResponse item) {
										FragmentManager fm = getActivity()
												.getSupportFragmentManager();
										FragmentTransaction ft = fm
												.beginTransaction();
										ft.replace(R.id.main_content,
												VoucherDetailFragment
														.newInstance(0));
										ft.addToBackStack(null);
										ft.commit();
									}
								});
						voucherListView.setAdapter(voucherAdapter);
						CommonValues.hideDialogLoading();
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

		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		// listVoucher
		// .add(new VoucherItem("Mỳ hoành thánh, 4 phần cho gia đình",
		// "300,000 VND/kg", "Nhà hàng Trung hoa",
		// R.drawable.hot_sale_one));
		//
		// voucherAdapter = new VoucherAdapter(listVoucher, getActivity(),
		// new BookInterface() {
		//
		// @Override
		// public void onBookVoucher(VoucherItem item) {
		// DLog.d("onClick : " + item.getVoucherName());
		// }
		// });
		// voucherListView.setAdapter(voucherAdapter);
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
