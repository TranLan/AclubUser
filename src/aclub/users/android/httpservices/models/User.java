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
public class User extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SerializedName(value = "id")
	private int id;

	@SerializedName(value = "name")
	private String name;

	@SerializedName(value = "phone")
	private String phone;

	@SerializedName(value = "verification_token")
	private String verificationToken;

	@SerializedName(value = "email")
	private String email;

	@SerializedName(value = "password")
	private String password;

	@SerializedName(value = "avatar")
	private File avatar;

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	@SerializedName(value = "unverified_email")
	private String unVerifyEmail;

	public String getUnVerifyEmail() {
		return unVerifyEmail;
	}

	public void setUnVerifyEmail(String unVerifyEmail) {
		this.unVerifyEmail = unVerifyEmail;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail_verification_token() {
		return email_verification_token;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail_verification_token(String email_verification_token) {
		this.email_verification_token = email_verification_token;
	}

	@SerializedName(value = "token")
	private String tokenNum;

	public String getTokenNum() {
		return tokenNum;
	}

	public void setTokenNum(String tokenNum) {
		this.tokenNum = tokenNum;
	}

	@SerializedName(value = "email_verification_token")
	private String email_verification_token;

	/**
	 * 
	 */
	public User(String phone) {
		this.phone = phone;
	}

	public User(String name, File file, String password) {
		this.name = name;
		this.avatar = file;
		this.password = password;
	}

	public User(String phone, String verifycationToken) {
		this.phone = phone;
		this.verificationToken = verifycationToken;
	}

	public User(String email, String password, int id) {
		this.email = email;
		this.id = id;
		this.password = password;
	}

	public User(int id, String emailTk) {
		this.id = id;
		this.email_verification_token = emailTk;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}

}
