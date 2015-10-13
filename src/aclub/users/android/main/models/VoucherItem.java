/**
 * 
 */
package aclub.users.android.main.models;

import android.graphics.Bitmap;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VoucherItem {

	private Bitmap voucherAvatar;
	private String voucherName;
	private String voucherPrice;
	private String voucherRestaurance;
	private int voucherRes;

	public VoucherItem(Bitmap voucherAvatar, String voucherName,
			String voucherPrice, String voucherRestaurance) {
		super();
		this.voucherAvatar = voucherAvatar;
		this.voucherName = voucherName;
		this.voucherPrice = voucherPrice;
		this.voucherRestaurance = voucherRestaurance;
	}

	public VoucherItem(String voucherName, String voucherPrice,
			String voucherRestaurance, int voucherRes) {
		super();
		this.voucherName = voucherName;
		this.voucherPrice = voucherPrice;
		this.voucherRestaurance = voucherRestaurance;
		this.voucherRes = voucherRes;
	}

	public Bitmap getVoucherAvatar() {
		return voucherAvatar;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public String getVoucherPrice() {
		return voucherPrice;
	}

	public String getVoucherRestaurance() {
		return voucherRestaurance;
	}

	public int getVoucherRes() {
		return voucherRes;
	}

	public void setVoucherAvatar(Bitmap voucherAvatar) {
		this.voucherAvatar = voucherAvatar;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public void setVoucherPrice(String voucherPrice) {
		this.voucherPrice = voucherPrice;
	}

	public void setVoucherRestaurance(String voucherRestaurance) {
		this.voucherRestaurance = voucherRestaurance;
	}

	public void setVoucherRes(int voucherRes) {
		this.voucherRes = voucherRes;
	}

}
