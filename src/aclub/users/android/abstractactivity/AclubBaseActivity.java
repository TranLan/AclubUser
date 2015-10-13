/**
 * 
 */
package aclub.users.android.abstractactivity;

import aclub.users.android.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

/**
 * @author ntdong2012
 *
 */
public class AclubBaseActivity extends FragmentActivity implements
		OnClickListener {

	protected ImageView mSearchAbIv, mDrawerNaviAbIv;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	protected void initActionBar(View view) {
//		mCamIv = (ImageView) findViewById(R.id.action_bar_cam_iv);
		mSearchAbIv = (ImageView) findViewById(R.id.action_bar_search_iv);
		mDrawerNaviAbIv = (ImageView) findViewById(R.id.action_bar_drawer_iv);
//		mCamIv.setOnClickListener(this);
		mSearchAbIv.setOnClickListener(this);
		mDrawerNaviAbIv.setOnClickListener(this);
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
