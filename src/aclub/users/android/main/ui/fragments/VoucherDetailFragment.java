/**
 * 
 */
package aclub.users.android.main.ui.fragments;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import aclub.users.android.R;
import aclub.users.android.httpservices.ErrorMessage;
import aclub.users.android.httpservices.ResponseHandler;
import aclub.users.android.httpservices.RestHelper;
import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.httpservices.models.VoucherResponse;
import aclub.users.android.utils.CommonValues;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VoucherDetailFragment extends Fragment {

	private View rootView;
	private ImageView vcAvIv;
	private TextView vcNameTv, vcTimeTv, vcNumTv, vcInviteFriendTv, vcDetailTv;
	private Button vcCodeBtn, vcReleaseBtn, vcHotSaleBtn, vcShareBtn;

	private static final String ARG_SECTION_NUMBER = "section_number";

	public static VoucherDetailFragment newInstance(int sectionNumber) {
		VoucherDetailFragment fragment = new VoucherDetailFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public VoucherDetailFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.voucher_detail_layout, container, false);
		initUI();
		getVoucherDetailFromServer();
		return rootView;
	}

	private void initUI() {
		vcAvIv = (ImageView) rootView.findViewById(R.id.voucher_avatar_iv);
		vcNameTv = (TextView) rootView.findViewById(R.id.voucher_name_tv);
		vcTimeTv = (TextView) rootView.findViewById(R.id.voucher_time_tv);
		vcNumTv = (TextView) rootView.findViewById(R.id.voucher_num_tv);
		vcInviteFriendTv = (TextView) rootView.findViewById(R.id.voucher_invite_friend_textview);
		vcDetailTv = (TextView) rootView.findViewById(R.id.voucher_detail_guide_time_title_tv);
		vcCodeBtn = (Button) rootView.findViewById(R.id.voucher_detail_code_button);
		vcReleaseBtn = (Button) rootView.findViewById(R.id.voucher_button_nhaphathanh_btn);
		vcHotSaleBtn = (Button) rootView.findViewById(R.id.voucher_button_giamgia_btn);
		vcShareBtn = (Button) rootView.findViewById(R.id.voucher_button_share_btn);

	}

	private void displayVoucherInfo(VoucherResponse voucher) {
		Picasso.with(getActivity()).load(voucher.getImages().getThumbLink()).placeholder(R.drawable.hot_sale_one)
				.into(vcAvIv);
		vcNameTv.setText(voucher.getVenue().getName());
		vcDetailTv.setText(voucher.getDescription());
		vcCodeBtn.setText("Ma voucher cua ban la " + voucher.getCode());
		vcNumTv.setText(voucher.getQuantity() + " voucher");
		vcInviteFriendTv.setText("Moi "+voucher.getRequiredMinimumInvitees() + " ban nhan voucher");
	}

	private void getVoucherDetailFromServer() {
		CommonValues.showDialogLoading(getActivity());
		RestHelper.getInstance().getVoucherDetail(getActivity(), VoucherBoxFragment.voucherSelectedId,
				new ResponseHandler() {

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses, boolean isJSONArrayFB) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(BaseResponse response) {
						VoucherResponse result = (VoucherResponse) response;
						CommonValues.hideDialogLoading();
						displayVoucherInfo(result);
					}

					@Override
					public void onError(ErrorMessage error) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}
}
