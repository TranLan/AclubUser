/**
 * 
 */
package aclub.users.android.main.models;

import android.graphics.Bitmap;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class SearchLocaItemResult {

	private Bitmap itemAvatar;
	private String itemName;
	private String itemAddress;
	private int itemRes;

	public SearchLocaItemResult(Bitmap itemAvatar, String itemName,
			String itemAddress) {
		super();
		this.itemAvatar = itemAvatar;
		this.itemName = itemName;
		this.itemAddress = itemAddress;
	}

	public SearchLocaItemResult(String itemName, String itemAddress, int itemRes) {
		super();
		this.itemName = itemName;
		this.itemAddress = itemAddress;
		this.itemRes = itemRes;
	}

	public Bitmap getItemAvatar() {
		return itemAvatar;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemAddress() {
		return itemAddress;
	}

	public int getItemRes() {
		return itemRes;
	}

	public void setItemAvatar(Bitmap itemAvatar) {
		this.itemAvatar = itemAvatar;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemAddress(String itemAddress) {
		this.itemAddress = itemAddress;
	}

	public void setItemRes(int itemRes) {
		this.itemRes = itemRes;
	}

}
