/**
 * 
 */
package aclub.users.android.httpservices.models;

import java.io.File;

import com.google.gson.annotations.SerializedName;

/**
 * @author ntdong2012
 *
 */
public class NearByRestaurantsResponse extends BaseResponse {

	@SerializedName(value = "id")
	private int id;
	@SerializedName(value = "name")
	private String name;

	@SerializedName(value = "description")
	private String description;

	@SerializedName(value = "longitude")
	private String logitude;

	@SerializedName(value = "latitude")
	private String latitude;

	@SerializedName(value = "full_address")
	private String fullAddress;

	@SerializedName(value = "categories")
	private NearByResCategory[] arrayCategory;

	// @SerializedName(value = "images")
	// private File images;

	public NearByResCategory[] getArrayCategory() {
		return arrayCategory;
	}

	public NearByRestaurantsResponse(int id, String name, String description,
			String logitude, String latitude, String fullAddress,
			NearByResCategory[] arrayCategory) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.logitude = logitude;
		this.latitude = latitude;
		this.fullAddress = fullAddress;
		this.arrayCategory = arrayCategory;
	}

	public void setArrayCategory(NearByResCategory[] arrayCategory) {
		this.arrayCategory = arrayCategory;
	}

	public NearByRestaurantsResponse(int id, String name, String description,
			String logitude, String latitude, String fullAddress/* , File images */) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.logitude = logitude;
		this.latitude = latitude;
		this.fullAddress = fullAddress;
		// this.images = images;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogitude() {
		return logitude;
	}

	public void setLogitude(String logitude) {
		this.logitude = logitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	// public File getImages() {
	// return images;
	// }
	//
	// public void setImages(File images) {
	// this.images = images;
	// }

}
