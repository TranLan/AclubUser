/**
 * 
 */
package aclub.users.android.main.models;

import android.graphics.Bitmap;

/**
 * @author ntdong2012
 *
 */
public class MemberItems {

	private Bitmap avatarMember;

	/**
	 * 
	 */
	public MemberItems(Bitmap avatarMember) {
		this.avatarMember = avatarMember;
	}

	/**
	 * @return the avatarMember
	 */
	public Bitmap getAvatarMember() {
		return avatarMember;
	}

	/**
	 * @param avatarMember
	 *            the avatarMember to set
	 */
	public void setAvatarMember(Bitmap avatarMember) {
		this.avatarMember = avatarMember;
	}

	// ///////////////////////////// For testing
	private int avaResourceId;

	/**
	 * @return the avaResourceId
	 */
	public int getAvaResourceId() {
		return avaResourceId;
	}

	/**
	 * @param avaResourceId
	 *            the avaResourceId to set
	 */
	public void setAvaResourceId(int avaResourceId) {
		this.avaResourceId = avaResourceId;
	}

	/**
	 * 
	 */
	public MemberItems(int avaReId) {
		this.avaResourceId = avaReId;
	}
}
