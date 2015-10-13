/**
 * 
 */
package aclub.users.android.httpservices.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class NearByResCategory extends SerializableClass {

	@SerializedName(value = "id")
	private int id;

	@SerializedName(value = "name")
	private String name;

	@SerializedName(value = "foursquare_id")
	private String foursquareId;

	@SerializedName(value = "parent_id")
	private String parentId;

	public NearByResCategory() {
		super();
	}

	public NearByResCategory(int id, String name, String foursquareId,
			String parentId) {
		super();
		this.id = id;
		this.name = name;
		this.foursquareId = foursquareId;
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFoursquareId() {
		return foursquareId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFoursquareId(String foursquareId) {
		this.foursquareId = foursquareId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
