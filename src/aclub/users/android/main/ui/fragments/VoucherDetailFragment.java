/**
 * 
 */
package aclub.users.android.main.ui.fragments;

import aclub.users.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VoucherDetailFragment extends Fragment {

	private View rootView;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.voucher_detail_layout, container,
				false);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}
}
