package aclub.users.android.main.ui.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import aclub.users.android.R;
import aclub.users.android.main.models.HotSaleItem;
import aclub.users.android.main.models.LocationItem;
import aclub.users.android.main.models.MemberItems;
import aclub.users.android.main.ui.cusview.HAdapter;
import aclub.users.android.main.ui.cusview.HorizontalListView;
import aclub.users.android.main.ui.cusview.HotSaleAdapter;
import aclub.users.android.main.ui.cusview.LocationAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

public class AclubFirstFragament extends Fragment implements
		BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,
		OnClickListener {

	private View rootView;
	private SliderLayout newProSliderShow;
	private ListView hostSaleListView;
	private ArrayList<HotSaleItem> listHotSales;
	private HotSaleAdapter adapter;

	private ArrayList<MemberItems> listActiveMembers;
	private HAdapter activeMemberAdatper;
	private HorizontalListView activeMemberListview;

	private ArrayList<MemberItems> listNewMembers;
	private HAdapter newMemberAdapter;
	private HorizontalListView newMemListView;

	private ListView locationListView;
	private ArrayList<LocationItem> listLocations;
	private LocationAdapter locationAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.acub_first_fragment, container,
				false);
		initUI(rootView);
		return rootView;
	}

	private void initUI(View view) {
		initNewPromosionBlock(view);
		initListHotSaleBlock(view);
		initMostActiveMemberBlock(view);
		initNewMembersBlock(view);
		initLocationBlock(view);
		initNewsBlock(view);
	}

	private void initNewsBlock(View view) {
		RelativeLayout newsOne = (RelativeLayout) view
				.findViewById(R.id.news_one_layout_inclue);
		TextView titleOne = (TextView) newsOne.findViewById(R.id.news_title_tv);
		String str = "<big>Những món mì nhất định phải thử khi đến Sài Gòn</big><small> 08:28 ngày 16/9/2015</small>";
		titleOne.setText(Html.fromHtml(str));

		RelativeLayout newsTwo = (RelativeLayout) view
				.findViewById(R.id.news_two_layout_inclue);
		TextView titleTwo = (TextView) newsTwo.findViewById(R.id.news_title_tv);
		String strTwo = "<big>Các quán khó có thể bỏ qua tại Hanoi Creative Citty</big><small> 08:28 ngày 16/9/2015</small>";
		titleTwo.setText(Html.fromHtml(strTwo));

		ImageView newIvTwo = (ImageView) newsTwo.findViewById(R.id.news_iv);
		newIvTwo.setImageDrawable(getResources().getDrawable(R.drawable.news2));

		view.findViewById(R.id.news_next_button).setOnClickListener(this);
		newsOne.setOnClickListener(this);
		newsTwo.setOnClickListener(this);
	}

	private void initLocationBlock(View view) {
		// listLocations = new ArrayList<LocationItem>();
		// listLocations.add(new LocationItem("Nhà hàng chả cá lã vọng",
		// "Buffet Hải sản tươi sống", "9.32", R.drawable.fvr1));
		// listLocations.add(new LocationItem("Nhà hàng chả cá lã vọng",
		// "Buffet Hải sản tươi sống", "9.32", R.drawable.fvr2));
		// listLocations.add(new LocationItem("Nhà hàng chả cá lã vọng",
		// "Buffet Hải sản tươi sống", "9.32", R.drawable.fvr3));
		// listLocations.add(new LocationItem("Nhà hàng chả cá lã vọng",
		// "Buffet Hải sản tươi sống", "9.32", R.drawable.fvr4));
		// listLocations.add(new LocationItem("Nhà hàng chả cá lã vọng",
		// "Buffet Hải sản tươi sống", "9.32", R.drawable.fvr5));
		//
		// locationAdapter = new LocationAdapter(getActivity(), listLocations);
		// // locationListView = (ListView) view.findViewById(R.id.location_lv);
		// locationListView.setAdapter(locationAdapter);

		RelativeLayout locaOne = (RelativeLayout) view
				.findViewById(R.id.location_one);
		TextView numOne = (TextView) locaOne.findViewById(R.id.location_num_tv);
		numOne.setText("1");
		ImageView avaOne = (ImageView) locaOne
				.findViewById(R.id.location_ava_iv);
		avaOne.setImageDrawable(getResources().getDrawable(R.drawable.fvr1));
		locaOne.setOnClickListener(this);

		RelativeLayout locaTwo = (RelativeLayout) view
				.findViewById(R.id.location_two);
		TextView numTwo = (TextView) locaTwo.findViewById(R.id.location_num_tv);
		numTwo.setText("2");
		ImageView avaTwo = (ImageView) locaTwo
				.findViewById(R.id.location_ava_iv);
		avaTwo.setImageDrawable(getResources().getDrawable(R.drawable.fvr2));
		locaTwo.setOnClickListener(this);

		RelativeLayout locaThree = (RelativeLayout) view
				.findViewById(R.id.location_three);
		TextView numThree = (TextView) locaThree
				.findViewById(R.id.location_num_tv);
		numThree.setText("3");
		ImageView avaThree = (ImageView) locaThree
				.findViewById(R.id.location_ava_iv);
		avaThree.setImageDrawable(getResources().getDrawable(R.drawable.fvr3));
		locaThree.setOnClickListener(this);

		RelativeLayout locaFour = (RelativeLayout) view
				.findViewById(R.id.location_four);
		TextView numFour = (TextView) locaFour
				.findViewById(R.id.location_num_tv);
		numFour.setText("4");
		ImageView avaFour = (ImageView) locaFour
				.findViewById(R.id.location_ava_iv);
		avaFour.setImageDrawable(getResources().getDrawable(R.drawable.fvr4));
		locaOne.setOnClickListener(this);

		RelativeLayout locaFive = (RelativeLayout) view
				.findViewById(R.id.location_five);
		TextView numFive = (TextView) locaFive
				.findViewById(R.id.location_num_tv);
		numFive.setText("5");
		ImageView avaFive = (ImageView) locaFive
				.findViewById(R.id.location_ava_iv);
		avaFive.setImageDrawable(getResources().getDrawable(R.drawable.fvr5));
		locaFive.setOnClickListener(this);
	}

	private void initMostActiveMemberBlock(View view) {
		listActiveMembers = new ArrayList<MemberItems>();
		listActiveMembers.add(new MemberItems(R.drawable.home_26));
		listActiveMembers.add(new MemberItems(R.drawable.home_20));
		listActiveMembers.add(new MemberItems(R.drawable.home_22));
		listActiveMembers.add(new MemberItems(R.drawable.home_26));
		listActiveMembers.add(new MemberItems(R.drawable.home_28));
		listActiveMembers.add(new MemberItems(R.drawable.home_30));
		listActiveMembers.add(new MemberItems(R.drawable.home_31));
		listActiveMembers.add(new MemberItems(R.drawable.home_20));
		listActiveMembers.add(new MemberItems(R.drawable.home_22));
		listActiveMembers.add(new MemberItems(R.drawable.home_26));
		listActiveMembers.add(new MemberItems(R.drawable.home_28));

		activeMemberAdatper = new HAdapter(listActiveMembers, getActivity());
		activeMemberListview = (HorizontalListView) view
				.findViewById(R.id.most_active_member_horizontalListView);
		activeMemberListview.setAdapter(activeMemberAdatper);
		activeMemberListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						"Go to detail member - define later",
						Toast.LENGTH_SHORT).show();
			}

		});
		view.findViewById(R.id.most_active_member_next_button)
		.setOnClickListener(this);
	}

	private void initNewMembersBlock(View view) {
		listNewMembers = new ArrayList<MemberItems>();
		listNewMembers.add(new MemberItems(R.drawable.home_30));
		listNewMembers.add(new MemberItems(R.drawable.home_31));
		listNewMembers.add(new MemberItems(R.drawable.home_20));
		listNewMembers.add(new MemberItems(R.drawable.home_22));
		listNewMembers.add(new MemberItems(R.drawable.home_26));
		listNewMembers.add(new MemberItems(R.drawable.home_28));
		listNewMembers.add(new MemberItems(R.drawable.home_26));
		listNewMembers.add(new MemberItems(R.drawable.home_20));
		listNewMembers.add(new MemberItems(R.drawable.home_22));
		listNewMembers.add(new MemberItems(R.drawable.home_26));
		listNewMembers.add(new MemberItems(R.drawable.home_28));

		newMemberAdapter = new HAdapter(listNewMembers, getActivity());
		newMemListView = (HorizontalListView) view
				.findViewById(R.id.new_member_horizontalListView);
		newMemListView.setAdapter(newMemberAdapter);
		newMemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(),
						"Go to detail member - define later",
						Toast.LENGTH_SHORT).show();
			}
		});
		view.findViewById(R.id.new_member_next_button).setOnClickListener(this);
	}

	private void initListHotSaleBlock(View view) {
		// hostSaleListView = (ListView) view
		// .findViewById(R.id.hot_sale_item_list_view);
		// listHotSales = new ArrayList<HotSaleItem>();
//		 listHotSales.add(new HotSaleItem(R.drawable.hot_sale_one,
//		 "Mỳ hoành thánh, 4 phần cho gia đình ", "300,000 VND/kg",
//		 "Nhà hàng Trung hoa"));
//		 listHotSales.add(new HotSaleItem(R.drawable.hot_sale_two,
//		 "Mỳ hoành thánh, 4 phần cho gia đình ", "300,000 VND/kg",
//		 "Nhà hàng Trung hoa"));
//		 listHotSales.add(new HotSaleItem(R.drawable.hot_sale_three,
//		 "Mỳ hoành thánh, 4 phần cho gia đình ", "300,000 VND/kg",
//		 "Nhà hàng Trung hoa"));
		// adapter = new HotSaleAdapter(getActivity(), listHotSales);
		// hostSaleListView.setAdapter(adapter);
	}

	private void initNewPromosionBlock(View view) {
		newProSliderShow = (SliderLayout) view
				.findViewById(R.id.new_promotion_block_slider);
		HashMap<String, Integer> fileMaps = new HashMap<String, Integer>();
		fileMaps.put("one", R.drawable.new_pro1);
		fileMaps.put("two", R.drawable.new_promosion_slider_one);
		for (String name : fileMaps.keySet()) {
			TextSliderView textSliderView = new TextSliderView(getActivity());

			textSliderView.description(name).image(fileMaps.get(name))
					.setScaleType(BaseSliderView.ScaleType.Fit)
					.setOnSliderClickListener(this);

			// add your extra information
			textSliderView.bundle(new Bundle());
			textSliderView.getBundle().putString("extra", name);

			newProSliderShow.addSlider(textSliderView);
		}
		newProSliderShow.setPresetTransformer(SliderLayout.Transformer.Default);
		newProSliderShow.setCustomAnimation(new DescriptionAnimation());
		newProSliderShow.setDuration(10000);
		newProSliderShow.addOnPageChangeListener(this);
		newProSliderShow.setCustomIndicator((PagerIndicator) view
				.findViewById(R.id.ew_promotion_block_custom_indicator));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daimajia.slider.library.Tricks.ViewPagerEx.OnPageChangeListener#
	 * onPageScrolled(int, float, int)
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onStop()
	 */
	@Override
	public void onStop() {
		newProSliderShow.stopAutoCycle();
		super.onStop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daimajia.slider.library.Tricks.ViewPagerEx.OnPageChangeListener#
	 * onPageSelected(int)
	 */
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daimajia.slider.library.Tricks.ViewPagerEx.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.daimajia.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener
	 * #onSliderClick(com.daimajia.slider.library.SliderTypes.BaseSliderView)
	 */
	@Override
	public void onSliderClick(BaseSliderView slider) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.most_active_member_next_button:
		case R.id.new_member_next_button:
		case R.id.location_one:
		case R.id.location_two:
		case R.id.location_three:
		case R.id.location_four:
		case R.id.location_five:
		case R.id.news_next_button:
			Toast.makeText(getActivity(), "Not define", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.news_one_layout_inclue:
		case R.id.news_two_layout_inclue:
			Toast.makeText(getActivity(), "Link not available",
					Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

}
