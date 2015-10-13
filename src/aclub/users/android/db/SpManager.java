/**
 * 
 */
package aclub.users.android.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class SpManager {

	public static SharedPreferences instances;

	public static SharedPreferences getInstances(Context context) {
		if (instances == null) {
			instances = PreferenceManager.getDefaultSharedPreferences(context);
		}
		return instances;
	}
}
