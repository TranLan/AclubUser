/**
 * 
 */
package aclub.users.android.main.models;

import android.graphics.Bitmap;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class HotSaleItem {

	private int hoteSaleIv;
	private String hotSaleTitle;
	private String hoteSalePrice;
	private String hoteSaleRestauranceName;

	public HotSaleItem(int hoteSaleIv, String hotSaleTitle,
			String hoteSalePrice, String hoteSaleRestauranceName) {
		super();
		this.hoteSaleIv = hoteSaleIv;
		this.hotSaleTitle = hotSaleTitle;
		this.hoteSalePrice = hoteSalePrice;
		this.hoteSaleRestauranceName = hoteSaleRestauranceName;
	}

	public int getHoteSaleIv() {
		return hoteSaleIv;
	}

	public String getHotSaleTitle() {
		return hotSaleTitle;
	}

	public String getHoteSalePrice() {
		return hoteSalePrice;
	}

	public String getHoteSaleRestauranceName() {
		return hoteSaleRestauranceName;
	}

	public void setHoteSaleIv(int hoteSaleIv) {
		this.hoteSaleIv = hoteSaleIv;
	}

	public void setHotSaleTitle(String hotSaleTitle) {
		this.hotSaleTitle = hotSaleTitle;
	}

	public void setHoteSalePrice(String hoteSalePrice) {
		this.hoteSalePrice = hoteSalePrice;
	}

	public void setHoteSaleRestauranceName(String hoteSaleRestauranceName) {
		this.hoteSaleRestauranceName = hoteSaleRestauranceName;
	}

}
