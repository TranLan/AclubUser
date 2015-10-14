/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VoucherResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;
	@SerializedName(value = "id")
	private int id;
	@SerializedName(value = "code")
	private String code;
	@SerializedName(value = "start_date")
	private String startDate;
	@SerializedName(value = "end_date")
	private String endDate;
	@SerializedName(value = "description")
	private String description;
	@SerializedName(value = "short_description")
	private String shortDesc;
	@SerializedName(value = "required_minimum_invitees")
	private int requiredMinimumInvitees;
	@SerializedName(value = "quantity")
	private int quantity;
	@SerializedName(value = "venue")
	private VoucherVenueResponse venue;
	@SerializedName(value = "image")
	private CouponsImage images;

	/**
	 * @param id
	 * @param code
	 * @param startDate
	 * @param endDate
	 * @param description
	 * @param shortDesc
	 * @param requiredMinimumInvitees
	 * @param quantity
	 * @param venue
	 * @param images
	 */
	public VoucherResponse(int id, String code, String startDate, String endDate, String description, String shortDesc,
			int requiredMinimumInvitees, int quantity, VoucherVenueResponse venue, CouponsImage images) {
		super();
		this.id = id;
		this.code = code;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.shortDesc = shortDesc;
		this.requiredMinimumInvitees = requiredMinimumInvitees;
		this.quantity = quantity;
		this.venue = venue;
		this.images = images;
	}

	public VoucherResponse(int id, String code, String startDate, String endDate, String description, String shortDesc,
			int requiredMinimumInvitees, int quantity, VoucherVenueResponse venue) {
		super();
		this.id = id;
		this.code = code;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.shortDesc = shortDesc;
		this.requiredMinimumInvitees = requiredMinimumInvitees;
		this.quantity = quantity;
		this.venue = venue;
	}

	/**
	 * @return the images
	 */
	public CouponsImage getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(CouponsImage images) {
		this.images = images;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getDescription() {
		return description;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public int getRequiredMinimumInvitees() {
		return requiredMinimumInvitees;
	}

	public int getQuantity() {
		return quantity;
	}

	public VoucherVenueResponse getVenue() {
		return venue;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public void setRequiredMinimumInvitees(int requiredMinimumInvitees) {
		this.requiredMinimumInvitees = requiredMinimumInvitees;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setVenue(VoucherVenueResponse venue) {
		this.venue = venue;
	}

}
