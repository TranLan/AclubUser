/**
 * 
 */
package aclub.users.android.httpservices;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import aclub.users.android.httpservices.models.User;
import aclub.users.android.httpservices.models.UserRegister;
import aclub.users.android.log.DLog;
import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author ntdong2012
 *
 */
public class RestHelper {

    private final String LOG_TAG = "RestHelper";
    private static final boolean MOCKUP_MODE = false;
    private static RestHelper instance;

    protected RestHelper() {
    }

    public static RestHelper getInstance() {
        if (instance == null) {
            if (MOCKUP_MODE) {
                instance = null;//new RestHelperMockup();
            } else {
                instance = new RestHelper();
            }
        }

        return instance;
    }

    /**
     * Cancel current request
     *
     * @param context context that associated with request
     */
    public void cancelRequest(Context context) {
        RestClient.cancelRequest(context);
    }

    /**
     * Request asynchronously for login API
     *
     * @param context context that associated with request
     * @param params params sent with request
     * @param responseHandler handler for response
     */
//    public void login(Context context, RequestParams params, final ResponseHandler responseHandler) {
//        String apiUrl = Constants.HttpConst.LOGIN_API;
//        // If need to pass parameter then need to convert it into JSON format as below
//        String jsonParam = JsonHelper.toJson(params);
//        JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.API_LOGIN);
//        RestClient.postWithoutHeader(context, apiUrl, jsonParam, handler);
//    }

    /**
     * Get To-Do list
     * @param context context that associated with request
     * @param params request parameters
     * @param responseHandler handler for response
     */
//    public void getTodoList(Context context, RequestParams params, final ResponseHandler responseHandler) {
//        String apiUrl = Constants.HttpConst.GET_TODO_LIST_API;
//        JsonHttpResponseHandler handler = (JsonHttpResponseHandler) new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.GET_TODO_LIST_API);
//        RestClient.post(context, apiUrl, params, handler);
//    }

    /**
     * Get Memo List
     * @param context context that associated with request
     * @param params request parameters
     * @param responseHandler handler for response
     */
//    public  void getMemoList(Context context, RequestParams params, final ResponseHandler responseHandler) {
//        String apiUrl = Constants.HttpConst.GET_MEMO_LIST_API;
////        String jsonParam = "";
////        if(params != null) {
////            jsonParam = JsonHelper.toJson(params);
////        }
//        JsonHttpResponseHandler handler = (JsonHttpResponseHandler) new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.GET_MEMO_LIST_API);
//        RestClient.post(context, apiUrl, params, handler);
//    }


    /**
     * Delete To-Do item
     * Sample for calling
     * {@code
     *      DeleteTodoParam param = new DeleteTodoParam;
     *      param.digiId = "digi9999";
     *      param.todoId = "todo0001";
     *      deleteTodoItem(context, param, new ResponseHandler() {});
     * }
     * @param context context that associated with request
     * @param params request parameters
     * @param responseHandler handler for response
     */
//    public void deleteTodoItem(Context context, RequestParams params, final ResponseHandler responseHandler) {
//        String apiUrl = Constants.HttpConst.DELETE_TODO_ITEM_API;
//        JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.DEL_TODO_ITEM);
//        RestClient.post(context, apiUrl, params, handler);
//    }

    /**
     * Get To-Do list
     * @param context context that associated with request
     * @param responseHandler handler for response
     */
//    public void getCurWeatherInfo(Context context, final ResponseHandler responseHandler) {
//        String apiUrl = Constants.HttpConst.OPEN_WEATHER_URL;
//        JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.GET_CUR_WEATHER_INFO_API);
//        RestClient.get(context, apiUrl, null, handler);
//    }

    /**
     * Get To-Do list
     * @param context context that associated with request
     * @param params request parameters
     * @param responseHandler handler for response
     */
//    public void getServiceInfo(Context context, RequestParams params, final ResponseHandler responseHandler) {
//        String apiUrl = Constants.HttpConst.GET_SERVICE_INFO_API;
//        JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.GET_SERVICE_INFO_API);
//        RestClient.post(context, apiUrl, params, handler);
//    }

    /**
     * Sample request
     *
     * @param context context that associated with request
     * @param responseHandler handler for response
     */
    public void requestSample(Context context, final ResponseHandler responseHandler) {
        String apiUrl = "/rating";
        JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.API_SAMPLE);
        RestClient.get(context, apiUrl, null, handler, false);
    }
    
    public void registerPhoneNum(Context context, String  number, final ResponseHandler responseHandler) throws JSONException {
    	String apiUrl = "registrations";
    	JSONObject jsonParams = new JSONObject();
    	jsonParams.put("phone", number);
    	
    	String params = JsonHelper.toJson(new UserRegister(new User(number)));
    	DLog.d(params);
//    	String params = JsonHelper.toJson(jsonParams);
    	JsonHelper.fromJson(params, UserRegister.class);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.PHONE_REGISTRAION);
    	RestClient.post(context, apiUrl, params, handler, false);
    }
    
    public void verifyPhoneNum(Context context, String number, String verifyCode, final ResponseHandler responseHandler) {
    	String apiUrl = "/sessions/";
    	String params = JsonHelper.toJsonFromVerifyNumToken(new UserRegister(new User(number, verifyCode)));
    	DLog.d(params);
    	JsonHelper.fromJson(params, UserRegister.class);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.PHONE_VERIFICATION_TOKEN);
    	RestClient.post(context, apiUrl, params, handler, false);
    }
    
    public void registerEmail(Context context, String email, String password, int id,  final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.REGISTER_EMAIL_ONE + "/"+id+"/"+Constants.HttpConst.REGISTER_EMAIL_TWO +"?email="+email;
//    	String params = JsonHelper.toJsonFromEmailUpdate(new UserRegister(new User(email,password, id)));
    	String params = "{\"user\" : {\"email\" : \"" + email +"\", \"password\" : \"" + password+"\"}}";
        
    	DLog.d(params);
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.PHONE_UPDATE_EMAIL);
    	RestClient.post(context, apiUrl, params, handler, true);
    }
    
    public void verifyEmail(Context context, int id, String emailTk, final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.REGISTER_EMAIL_ONE + "/"+id+"/"+Constants.HttpConst.VERIFY_EMAIL;
    	String params = "{\"email_token\" : "+"\""+emailTk+"\"}";
    	DLog.d(params);
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.EMAIL_VERIFY);
    	RestClient.post(context, apiUrl, params, handler, true);
    }
    
    public void resentVerifyEmail(Context context, int id, final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.REGISTER_EMAIL_ONE + "/"+id+"/"+Constants.HttpConst.RESENT_EMAIL_VERIFY;
    	String params = "{}";
    	DLog.d(params);
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.EMAIL_RESENT);
    	RestClient.post(context, apiUrl, params, handler, true);
    }
    
    public void loginByPhone(Context context, String numPhone, String verificationToken, final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.LOGIN_PHONE;
    	String params = "{\"user\" : {\"phone\" : \"" + numPhone +"\", \"verification_token\" : \"" + verificationToken+"\"}}";
    	DLog.d(params);
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.LOGIN_PHONE);
    	RestClient.post(context, apiUrl, params, handler, true);
    }
    
    public void loginByEmail(Context context, String email, String pass, final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.LOGIN_PHONE;
    	String params = "{\"user\" : {\"username\" : \"" + email +"\", \"password\" : \"" + pass+"\"}}";
    	DLog.d(params);
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.LOGIN_EMAIL);
    	RestClient.post(context, apiUrl, params, handler, false);
    }
    
    public void resendOTPPass(Context context, String number, final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.RESENT_OTP_PASS;
    	String params = "{\"user\" : {\"phone\" : \"" + number +"\"}}";
    	DLog.d(params);
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.OTP_PASS_RESENT);
    	RestClient.post(context, apiUrl, params, handler, false);
    }
    
    public void updateProfile(Context context, int id, String name, File avatar, String password,RequestParams paramsRP,  final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.REGISTER_EMAIL_ONE + "/" + id;
    	RequestParams params = new RequestParams();
		try {
			params.put("[user][avatar]", avatar);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.put("[user][name]", name);
		params.put("[user][password]", password);
    	DLog.d(apiUrl);
    	DLog.d(params);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.UPDATE_PROFILE);
    	RestClient.put(context, apiUrl, params, "", handler, true);
    }
    
    public void getNearByRestaurants(Context context, String latitude, String longtitude, int radius, int paging, final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.NEAR_BY_RESTAURANTS + "?latitude="+latitude+"&longitude="+longtitude+"&radius="+radius+"&page="+paging;
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.NEAR_BY_RESTAURANTS);
    	RestClient.get(context, apiUrl, null, handler, true);	
    }
    
    public void getRestaurantByName(Context context, String latitude, String longtitude, int radius, int paging, String name, final ResponseHandler responseHandler){
    	String apiUrl = Constants.HttpConst.NEAR_BY_RESTAURANTS + "?latitude="+latitude+"&longitude="+longtitude+"&radius="+radius+"&name='"+name+"'"+"&page="+paging;
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.RESTAURANTS_BY_NAME);
    	RestClient.get(context, apiUrl, null, handler, true);	
    }
    public void getDetailRestaurant(Context context, int resId, final ResponseHandler responseHandler) {
    	String apiUrl = Constants.HttpConst.NEAR_BY_RESTAURANTS + "/"+resId;
    	DLog.d(apiUrl);
    	JsonHttpResponseHandler handler = new CommonJsonHttpResponseHandler(responseHandler, Constants.ApiConst.DETAIL_RESTAURANT);
    	RestClient.get(context, apiUrl, null, handler, true);
    }
    
    
    
    
}
