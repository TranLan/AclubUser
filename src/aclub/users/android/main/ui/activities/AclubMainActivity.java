/**
 * 
 */
package aclub.users.android.main.ui.activities;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.AclubBaseActivity;
import aclub.users.android.log.DLog;
import aclub.users.android.main.models.DrawerItem;
import aclub.users.android.main.ui.cusview.DrawerAdapter;
import aclub.users.android.main.ui.fragments.AclubFirstFragament;
import aclub.users.android.main.ui.fragments.FragmentSecond;
import aclub.users.android.main.ui.fragments.NearByRestaurantsFragment;
import aclub.users.android.main.ui.fragments.VoucherBoxFragment;
import aclub.users.android.utils.CommonValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * @author ntdong2012
 *
 */
public class AclubMainActivity extends AclubBaseActivity implements
		OnItemClickListener {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private RelativeLayout mAbLayout;
	private ArrayList<DrawerItem> drawerList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.AclubBaseActivity#onCreate(android
	 * .os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aclub_main_layout);
		moveDrawerToTop();
		initDrawer();
		onItemClick(null, null, 0, 0);
		initActionBar();
	}

	private void initActionBar() {
		mAbLayout = (RelativeLayout) findViewById(R.id.action_bar_aclub_main_layout);
		initActionBar(mAbLayout);
	}

	private void initDrawer() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.drawer_linear_layout);
		mDrawerLayout.setDrawerListener(createDrawerToggle());

		drawerList = new ArrayList<DrawerItem>();
		drawerList.add(new DrawerItem(true));

		drawerList.add(new DrawerItem(getString(R.string.drawer_item_one),
				R.drawable.drawer_home));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_two),
				R.drawable.drawer_voucher_box));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_three),
				R.drawable.ic_action_gamepad));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_four),
				R.drawable.drawer_checkin));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_five),
				R.drawable.drawer_news_feed));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_six),
				R.drawable.drawer_rewards));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_sevend),
				R.drawable.drawer_brands));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_eight),
				R.drawable.drawer_friends));
		drawerList.add(new DrawerItem());
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_night),
				R.drawable.drawer_profile));
		drawerList.add(new DrawerItem(getString(R.string.drawer_item_ten),
				R.drawable.drawer_settings));
		DrawerAdapter adapter = new DrawerAdapter(this,
				R.layout.custom_list_drawer_layout, drawerList);

		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(this);
	}

	@Override
	public void onBackPressed() {
		int count = getSupportFragmentManager().getBackStackEntryCount();
		if (count == 1 || count == 0) {
			super.onBackPressed();
			finish();
		} else {
			getSupportFragmentManager().popBackStack();
		}
	}

	private DrawerListener createDrawerToggle() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close) {

			@Override
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerStateChanged(int state) {
			}
		};
		return mDrawerToggle;
	}

	private void moveDrawerToTop() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		DrawerLayout drawer = (DrawerLayout) inflater.inflate(
				R.layout.drawer_navigator_layout, null); // "null" is important.

		// HACK: "steal" the first child of decor view
		ViewGroup decor = (ViewGroup) getWindow().getDecorView();
		View child = decor.getChildAt(0);
		decor.removeView(child);
		LinearLayout container = (LinearLayout) drawer
				.findViewById(R.id.drawer_content); // This is the container we
													// defined just now.
		container.addView(child, 0);
		drawer.findViewById(R.id.drawer_linear_layout).setPadding(0,
				getStatusBarHeight(), 0, 0);

		// Make the drawer replace the first child
		decor.addView(drawer);
	}

	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	private int getContentIdResource() {
		return getResources().getIdentifier("content", "id", "android");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mDrawerToggle.syncState();
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		int id = item.getItemId();
		if (id == R.id.action_search) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mDrawerLayout.closeDrawer(mDrawerList);
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ftx = fragmentManager.beginTransaction();

		switch (position) {
		case CommonValues.HOME_DRAWER:
			ftx.replace(R.id.main_content, new AclubFirstFragament(), "ONE");
			ftx.addToBackStack(null);
			break;
		case CommonValues.VOUCHER_BOX_DRAWER:
			ftx.replace(R.id.main_content,
					VoucherBoxFragment.newInstance(position + 1), "ONE");
			ftx.addToBackStack(null);
			break;
		default:
			ftx.replace(R.id.main_content, new AclubFirstFragament(), "ONE");
			ftx.addToBackStack(null);
			break;
		}
		ftx.commit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.AclubBaseActivity#onClick(android
	 * .view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.action_bar_drawer_iv:
			mDrawerLayout.openDrawer(Gravity.START);
			break;
		case R.id.action_bar_search_iv:
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.main_content, NearByRestaurantsFragment.newInstance(0));
			ft.addToBackStack(null);
			ft.commit();
			break;
		default:
			super.onClick(v);
			break;
		}
	}
}
