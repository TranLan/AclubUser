/**
 * 
 */
package aclub.users.android.httpservices;

/**
 * @author ntdong2012
 *
 */
public class Constants {

	interface HttpConst {

		// Login path
		String LOGIN_API = "/users/login";
		String REGISTER_EMAIL_ONE = "users";
		String REGISTER_EMAIL_TWO = "email_registration";
		String VERIFY_EMAIL = "email_verification";
		String RESENT_EMAIL_VERIFY = "resend_email_verification";
		String LOGIN_PHONE = "sessions/";
		String RESENT_OTP_PASS = "sessions/request_phone_verification_token";
		String NEAR_BY_RESTAURANTS = "venues";
		String VOUCHER = "coupons";
	}

	long TIME_REQUEST_SERVER = 30 * 1000; // 30 minutes request to server to get
											// todoList;

	interface ApiConst {
		int API_SAMPLE = 0;
		int NEAR_BY_RESTAURANTS = 1;
		int DETAIL_RESTAURANT = 2;
		int RESTAURANTS_BY_NAME = 3;
		int VOUCHER = 4;
		int PHONE_REGISTRAION = 7;
		int PHONE_VERIFICATION_TOKEN = 8;
		int PHONE_UPDATE_EMAIL = 9;
		int EMAIL_VERIFY = 10;
		int EMAIL_RESENT = 11;
		int LOGIN_PHONE = 12;
		int LOGIN_EMAIL = 13;
		int OTP_PASS_RESENT = 14;
		int UPDATE_PROFILE = 15;
	}

	interface ErrorCode {
		// Success
		int ERR_SUCCESS = 0;
		// MyAQUA ID Incorrect
		int ERR_INCORRECT_ID = 10;
		// Incorrect password
		int ERR_INCORRECT_PWD = 11;
		// Incorrect token
		int ERR_INCORRECT_TOKEN = 12;
		// Incorrect digi ID
		int ERR_INCORRECT_DIGI_ID = 13;
		// Incorrect parameter
		int ERR_INCORRECT_PARAM = 14;
		// No data(There is no data which can return)
		int ERR_NO_DATA = 15;
	}

}
