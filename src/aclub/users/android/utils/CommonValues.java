/**
 * 
 */
package aclub.users.android.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import com.facebook.Profile;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class CommonValues {

	public static final boolean DEBUG_MODE = true;
	public static final boolean isShowLog = true;
	public static final int MIN_PASS_LENGTH = 6;
	public static final int MAX_PASS_LENGTH = 20;
	public static Profile currentFbProflie;

	public static final int HOME_DRAWER = 1;
	public static final int VOUCHER_BOX_DRAWER = 2;
	public static final int DEFAULT_DRAWER = HOME_DRAWER;
	public static final int SETTING_DRAWER = 11;
	public static final int FRIEND_DRAWER = 8;

	public static final boolean TEST = true;
	public static ProgressDialog dialog;

	public static void showDialogLoading(Activity activity) {
		if (dialog == null) {
			dialog = new ProgressDialog(activity);
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Loading. Please wait...");
			dialog.setIndeterminate(true);
			dialog.setCanceledOnTouchOutside(false);
		}
		if (dialog != null && !dialog.isShowing()) {
			dialog.show();
		}
	}

	public static void hideDialogLoading() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
