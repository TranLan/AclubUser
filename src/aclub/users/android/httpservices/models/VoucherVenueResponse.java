/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VoucherVenueResponse extends SerializableClass {

	private static final long serialVersionUID = 1L;
	@SerializedName(value = "id")
	private int id;
	@SerializedName(value = "name")
	private String name;
	@SerializedName(value = "description")
	private String description;
	@SerializedName(value = "longitude")
	private String longitude;
	@SerializedName(value = "latitude")
	private String latitude;
	@SerializedName(value = "full_address")
	private String fullAddress;

	public VoucherVenueResponse(int id, String name, String description,
			String longitude, String latitude, String fullAddress) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
		this.fullAddress = fullAddress;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

}
