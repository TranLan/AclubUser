package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author ntdong2012
 *
 */
public class CouponsImage extends SerializableClass {

	@SerializedName(value = "thumb")
	private String thumbLink;
	@SerializedName(value = "origin")
	private String originLink;

	/**
	 * @param thumbLink
	 * @param originLink
	 */
	public CouponsImage(String thumbLink, String originLink) {
		super();
		this.thumbLink = thumbLink;
		this.originLink = originLink;
	}

	/**
	 * @return the thumbLink
	 */
	public String getThumbLink() {
		return thumbLink;
	}

	/**
	 * @param thumbLink
	 *            the thumbLink to set
	 */
	public void setThumbLink(String thumbLink) {
		this.thumbLink = thumbLink;
	}

	/**
	 * @return the originLink
	 */
	public String getOriginLink() {
		return originLink;
	}

	/**
	 * @param originLink
	 *            the originLink to set
	 */
	public void setOriginLink(String originLink) {
		this.originLink = originLink;
	}

}
