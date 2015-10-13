/**
 * 
 */
package aclub.users.android.httpservices;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aclub.users.android.httpservices.models.NearByRestaurantsResponse;
import aclub.users.android.httpservices.models.SerializableClass;
import aclub.users.android.httpservices.models.UserRegister;
import aclub.users.android.log.DLog;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author ntdong2012
 *
 */
public class JsonHelper {

	/**
	 * This method serializes the specified object into its equivalent Json
	 * representation
	 *
	 * @param src
	 *            the object for which Json representation is to be created
	 *            setting for Gson
	 * @return Json representation of src
	 */
	public static String toJson(Object src) {
		Gson gson = new Gson();
		try {
			return gson.toJson(src);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method deserializes the specified Json into an object of the
	 * specified class.
	 *
	 * @param json
	 *            the string from which the object is to be deserialized
	 * @param classOfT
	 *            the class of T
	 * @param <T>
	 *            class which is serializable
	 * @return an object of type T from the string
	 */
	public static <T extends SerializableClass> T fromJson(String json,
			Class<T> classOfT) {
		Gson gson = new Gson();
		try {
			return gson.fromJson(json, classOfT);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public static ArrayList<T extends SerializableClass>  hrJsonHelper(JSONArray jsonArray, Class<T> classOfT) {
//		ArrayList<NearByRestaurantsResponse> listData = new ArrayList<NearByRestaurantsResponse>();
//
//		Gson gson = new Gson();
//		for (int i = 0; i < jsonArray.length(); i++) {
//			JSONObject obj = null;
//			try {
//				obj = (JSONObject) jsonArray.get(i);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//
//			DLog.d(obj.toString());
//			String ojbStr = obj.toString();
//			// DLog.d(ojbStr);
//			// int first = ojbStr.indexOf("[");
//			// DLog.d(first);
//			// int last = ojbStr.lastIndexOf("]");
//			// DLog.d(last);
//			// DLog.d(ojbStr.length());
//			//
//			// ojbStr = ojbStr.substring(first, last);
//			DLog.d(ojbStr);
//			NearByRestaurantsResponse cse = gson.fromJson(ojbStr,
//					NearByRestaurantsResponse.class);
//			listData.add(cse);
//		}
//		return listData;
//	}

	public static String toJson(UserRegister user) {
		try {

			JSONObject jsonObj = new JSONObject();
			JSONObject jsonAdd = new JSONObject();
			jsonAdd.put("phone", user.user.getPhone());
			jsonObj.put("user", jsonAdd);

			return jsonObj.toString();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	public static String toJsonFromVerifyNumToken(UserRegister user) {
		try {
			JSONObject jsonObj = new JSONObject();
			JSONObject jsonAdd = new JSONObject();
			jsonAdd.put("phone", user.user.getPhone());
			jsonAdd.put("verification_token", user.user.getVerificationToken());
			jsonObj.put("user", jsonAdd);
			return jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toJsonAvatar(UserRegister user) {
		try {
			JSONObject jsonObj = new JSONObject();
			JSONObject jsonAdd = new JSONObject();
			jsonAdd.put("name", user.user.getName());
			if (user.user.getAvatar() != null) {
				jsonAdd.put("avatar", user.user.getAvatar());
			}
			// jsonAdd.put("password", user.user.getPassword());
			jsonObj.put("user", jsonAdd);
			return jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toJsonFromEmailUpdate(UserRegister user) {
		try {
			JSONObject jsonObj = new JSONObject();
			JSONObject jsonAdd = new JSONObject();
			jsonAdd.put("email", user.user.getEmail());
			jsonAdd.put("password", user.user.getPassword());
			jsonObj.put("user", jsonAdd);
			return jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toJsonFromEmailVerify(UserRegister user) {
		try {
			JSONObject jsonObj = new JSONObject();
			JSONObject jsonAdd = new JSONObject();
			jsonAdd.put("email_token", user.user.getEmail_verification_token());
			jsonAdd.put("password", user.user.getPassword());
			jsonObj.put("user", jsonAdd);
			return jsonObj.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
