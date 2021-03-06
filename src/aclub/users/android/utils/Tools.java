/**
 * 
 */
package aclub.users.android.utils;

import java.util.regex.Pattern;

import aclub.users.android.log.DLog;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author dinostudio8891@gmail.com
 * Lan test 
 * Dong test 
 */
public class Tools {

	private final static Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
					+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");

	public static boolean isEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}

	public static void showInputMethod(View view, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, 0);
	}

	public static void closeKeyBoard(View v, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static boolean isPassword(String pass) {
		if (StringUtils.isEmpty(pass)) {
			return false;
		}
		if (pass.length() >= CommonValues.MIN_PASS_LENGTH
				&& pass.length() <= CommonValues.MAX_PASS_LENGTH) {
			return true;
		}
		return false;
	}

	public static String getCurrentPhoneNum(Context context) {
		TelephonyManager phoneManager = (TelephonyManager) context
				.getApplicationContext().getSystemService(
						Context.TELEPHONY_SERVICE);
		String phoneNumber = phoneManager.getLine1Number();
		DLog.d(phoneNumber);
		return phoneNumber;
	}
}
