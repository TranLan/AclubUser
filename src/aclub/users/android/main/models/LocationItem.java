/**
 * 
 */
package aclub.users.android.main.models;

import android.graphics.Bitmap;

/**
 * @author ntdong2012
 *
 */
public class LocationItem {

	private String locationName;
	private String locationDes;
	private String locationRate;
	private int locationAvaId; // for testing
	private Bitmap locationAva;

	public LocationItem(String locationName, String locationDes,
			String locationRate, Bitmap locationAva) {
		super();
		this.locationName = locationName;
		this.locationDes = locationDes;
		this.locationRate = locationRate;
		this.locationAva = locationAva;
	}

	public LocationItem(String locationName, String locationDes,
			String locationRate, int locationAvaId) {
		super();
		this.locationName = locationName;
		this.locationDes = locationDes;
		this.locationRate = locationRate;
		this.locationAvaId = locationAvaId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationDes() {
		return locationDes;
	}

	public void setLocationDes(String locationDes) {
		this.locationDes = locationDes;
	}

	public String getLocationRate() {
		return locationRate;
	}

	public void setLocationRate(String locationRate) {
		this.locationRate = locationRate;
	}

	public int getLocationAvaId() {
		return locationAvaId;
	}

	public void setLocationAvaId(int locationAvaId) {
		this.locationAvaId = locationAvaId;
	}

	public Bitmap getLocationAva() {
		return locationAva;
	}

	public void setLocationAva(Bitmap locationAva) {
		this.locationAva = locationAva;
	}

}
