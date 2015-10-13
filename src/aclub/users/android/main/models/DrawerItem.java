/**
 * 
 */
package aclub.users.android.main.models;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class DrawerItem {
	private String mDrawerItemName;
	private int imgResID;
	private String mTitleDrawerItem;
	private boolean isEdtDrawerItem;

	/**
	 * 
	 */
	public DrawerItem() {
	}

	public DrawerItem(String itemName, int imgResID) {
		mDrawerItemName = itemName;
		this.imgResID = imgResID;
	}

	public DrawerItem(boolean isSpinner) {
		this(null, 0);
		this.isEdtDrawerItem = isSpinner;
	}

	public DrawerItem(String title) {
		this(null, 0);
		this.mTitleDrawerItem = title;
	}

	public String getDrawerItemName() {
		return mDrawerItemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public String getTitleDrawerItem() {
		return mTitleDrawerItem;
	}

	public boolean isEdtDrawerItem() {
		return isEdtDrawerItem;
	}

	public void setmDrawerItemName(String mDrawerItemName) {
		this.mDrawerItemName = mDrawerItemName;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}

	public void setmTitleDrawerItem(String mTitleDrawerItem) {
		this.mTitleDrawerItem = mTitleDrawerItem;
	}

	public void setEdtDrawerItem(boolean isEdtDrawerItem) {
		this.isEdtDrawerItem = isEdtDrawerItem;
	}

}
