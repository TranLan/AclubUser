package aclub.users.android.main.ui.fragments;

import aclub.users.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * @author ntdong2012
 *
 */
public class FriendFragment extends Fragment implements OnClickListener {

	private static final String ARG_SECTION_NUMBER = "section_number";
	private View rootView;
	private RelativeLayout inviteSmsRl, inviteEmailRl;
	private ListView contactListView;
	private String content = "Co ung dung nay hay. Bam vao link nay de tai ung dung Aclub: http://abc.xyz";

	public static FriendFragment newInstance(int sectionNumber) {
		FriendFragment fragment = new FriendFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public FriendFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.friend_fragment_layout, container, false);
		initUI();
		return rootView;
	}

	private void initUI() {
		inviteSmsRl = (RelativeLayout) rootView.findViewById(R.id.add_friend_sms_relative_layout);
		inviteEmailRl = (RelativeLayout) rootView.findViewById(R.id.add_friend_email_relative_layout);
		inviteEmailRl.setOnClickListener(this);
		inviteSmsRl.setOnClickListener(this);
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
		switch (v.getId()) {
		case R.id.add_friend_email_relative_layout:
			doAddFriendByEmail();
			break;
		case R.id.add_friend_sms_relative_layout:
			doAddFriendBySms();
			break;
		default:
			break;
		}
	}

	private void doAddFriendBySms() {
		Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
		smsIntent.setType("vnd.android-dir/mms-sms");
		smsIntent.putExtra("sms_body", content);
		smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(smsIntent);
	}

	private void doAddFriendByEmail() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Moi ban su dung ung dung Aclub");
		intent.putExtra(Intent.EXTRA_TEXT, content);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
