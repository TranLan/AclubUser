/**
 * 
 */
package aclub.users.android.httpservices;

import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.httpservices.models.NearByRestaurantsResponse;
import aclub.users.android.httpservices.models.SampleResponse;
import aclub.users.android.httpservices.models.User;
import aclub.users.android.httpservices.models.UserRegister;
import aclub.users.android.log.DLog;
import aclub.users.android.utils.StringUtils;

import com.google.gson.JsonArray;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Response handler for HTTP request
 * 
 * @author ntdong2012
 *
 */
public class CommonJsonHttpResponseHandler extends JsonHttpResponseHandler {
	private final String LOG_TAG = "CommonJsonHttpResponseHandler";

	private ResponseHandler _responseHandler;
	private int _requestType;

	public CommonJsonHttpResponseHandler(ResponseHandler responseHandler,
			int requestType) {
		this._responseHandler = responseHandler;
		this._requestType = requestType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(int,
	 * org.apache.http.Header[], org.json.JSONObject)
	 */
	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
		handleSuccess(statusCode, headers,
				response != null ? response.toString() : "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(int,
	 * org.apache.http.Header[], java.lang.String)
	 */
	@Override
	public void onSuccess(int statusCode, Header[] headers,
			String responseString) {
		handleSuccess(statusCode, headers, responseString);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.loopj.android.http.JsonHttpResponseHandler#onSuccess(int,
	 * org.apache.http.Header[], org.json.JSONArray)
	 */
	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
		super.onSuccess(statusCode, headers, response);
		writeLogSuccess("onSuccess", statusCode, "JSONArray is not implemented");
		handleSuccess(statusCode, headers, response);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.loopj.android.http.JsonHttpResponseHandler#onFailure(int,
	 * org.apache.http.Header[], java.lang.Throwable, org.json.JSONObject)
	 */
	@Override
	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, JSONObject errorResponse) {
		handleFailure(statusCode, headers,
				errorResponse != null ? errorResponse.toString() : "",
				throwable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.loopj.android.http.JsonHttpResponseHandler#onFailure(int,
	 * org.apache.http.Header[], java.lang.Throwable, org.json.JSONArray)
	 */
	@Override
	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, JSONArray errorResponse) {
		writeLogFailure("onFailure", statusCode,
				"JSONArray is not implemented", throwable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.loopj.android.http.JsonHttpResponseHandler#onFailure(int,
	 * org.apache.http.Header[], java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void onFailure(int statusCode, Header[] headers,
			String responseString, Throwable throwable) {
		handleFailure(statusCode, headers, responseString, throwable);
	}

	private void writeLogSuccess(String function, int statusCode,
			String responseBody) {
		DLog.i(LOG_TAG, "RestApi::" + function + "::onSuccess");
		DLog.i(LOG_TAG, "    statusCode: " + statusCode);
		DLog.i(LOG_TAG, "    responseBody: " + responseBody);
	}

	private void writeLogErrSuccess(String function, int errCode,
			String responseBody) {
		DLog.i(LOG_TAG, "RestApi::" + function + "::onSuccess");
		DLog.i(LOG_TAG, "    errCode: " + errCode);
		DLog.i(LOG_TAG, "    responseBody: " + responseBody);
	}

	private void writeLogFailure(String function, int statusCode,
			String responseBody, Throwable e) {
		DLog.d("RestApi::" + function + "::onFailure");
		DLog.d(LOG_TAG, "    statusCode: " + statusCode);
		DLog.d(LOG_TAG, "    errorResponse: " + responseBody);
		DLog.d(LOG_TAG, "    error: " + e.getMessage());
	}

	private void handleSuccess(int statusCode, Header[] headers,
			JSONArray responJson) {
		DLog.d("handleSuccess");
		switch (_requestType) {
		case Constants.ApiConst.NEAR_BY_RESTAURANTS:
			responseSuccess("NEAR_BY_RESTAURANTS", statusCode, responJson,
					NearByRestaurantsResponse.class);
			break;
		case Constants.ApiConst.RESTAURANTS_BY_NAME:
			responseSuccess("RESTAURANTS_BY_NAME", statusCode, responJson,
					NearByRestaurantsResponse.class);
			break;
		default:
			break;
		}
	}

	private void handleSuccess(int statusCode, Header[] headers,
			String responseBody) {
		DLog.i(LOG_TAG, "responseString -> " + responseBody);
		// if (!StringUtils.isEmpty(responseString)) {
		// BaseResponse response = (BaseResponse)
		// JsonUtils.fromJson(responseString, BaseResponse.class);
		// if
		// (response.errorCode.equals(Constants.ErrorCode.ERROR_CODE_AUTH_FAILED)
		// ||
		// response.errorCode.equals(Constants.ErrorCode.ERROR_CODE_INTERNAL_SERVER_ERROR)||
		// response.errorCode.equals(Constants.ErrorCode.ERROR_CODE_INTERNAL_SERVER_NOT_FOUND))
		// {
		// try{
		// Util.tokenTimeOut(PosApplication.mActivity);
		// }catch(Exception e){
		// DLog.i(LOG_TAG, "No activity : "+e.getMessage());
		// }
		// return;
		// }
		// }
		switch (_requestType) {
		case Constants.ApiConst.API_SAMPLE:
			responseSuccess("requestSample", statusCode, responseBody,
					SampleResponse.class);
			break;
		case Constants.ApiConst.PHONE_REGISTRAION:
			responseSuccess("registerNumberPhone", statusCode, responseBody,
					User.class);
			break;
		case Constants.ApiConst.PHONE_VERIFICATION_TOKEN:
			responseSuccess("verifyNumberPhone", statusCode, responseBody,
					User.class);
			break;
		case Constants.ApiConst.PHONE_UPDATE_EMAIL:
			responseSuccess("updateEmail", statusCode, responseBody, User.class);
			break;
		case Constants.ApiConst.EMAIL_VERIFY:
			responseSuccess("verifyEmail", statusCode, responseBody, User.class);
			break;
		case Constants.ApiConst.EMAIL_RESENT:
			responseSuccess("EMAIL_RESENT", statusCode, responseBody,
					User.class);
			break;
		case Constants.ApiConst.LOGIN_PHONE:
			responseSuccess("LOGIN_PHONE", statusCode, responseBody, User.class);
			break;
		case Constants.ApiConst.LOGIN_EMAIL:
			responseSuccess("LOGIN_EMAIL", statusCode, responseBody, User.class);
			break;
		case Constants.ApiConst.UPDATE_PROFILE:
			responseSuccess("UPDATE_PROFILE", statusCode, responseBody,
					User.class);
			break;
		case Constants.ApiConst.NEAR_BY_RESTAURANTS:
			responseSuccess("NEAR_BY_RESTAURANTS", statusCode, responseBody,
					NearByRestaurantsResponse.class);
		case Constants.ApiConst.DETAIL_RESTAURANT:
			responseSuccess("DETAIL_RESTAURANT", statusCode, responseBody,
					NearByRestaurantsResponse.class);
			break;
		// Handle for login request
		// case Constants.ApiConst.API_LOGIN:
		// responseSuccess("Login", statusCode, responseBody,
		// LoginResponse.class);
		// break;
		// case Constants.ApiConst.GET_TODO_LIST_API:
		// responseSuccess("getTodoList", statusCode, responseBody,
		// GetToDoListResponse.class);
		// break;
		// case Constants.ApiConst.GET_CUR_WEATHER_INFO_API:
		// responseSuccess("getCurrentWeatherInfo", statusCode, responseBody,
		// WeatherInfoResponse.class);
		// break;
		// case Constants.ApiConst.GET_MEMO_LIST_API:
		// responseSuccess("getMemoList", statusCode, responseBody,
		// GetMemoListResponse.class);
		// break;
		// case Constants.ApiConst.DEL_TODO_ITEM:
		// responseSuccess("delTodoItem", statusCode, responseBody,
		// BaseResponse.class);
		// break;
		// case Constants.ApiConst.GET_SERVICE_INFO_API:
		// responseSuccess("getServiceInfo", statusCode, responseBody,
		// GetServiceInfoResponse.class);
		// break;
		default:
			writeLogSuccess("handleSuccess:default", statusCode, responseBody);
			break;
		}
	}

	/**
	 * Response to caller in success case
	 *
	 * @param function
	 *            Function name for logging
	 * @param statusCode
	 *            Status code
	 * @param responseBody
	 *            Response body string
	 * @param classOfT
	 *            Class of response object want to be casted
	 * @param <T>
	 *            Class which extends from {@code BaseResponse}
	 */
	private <T extends BaseResponse> void responseSuccess(String function,
			int statusCode, String responseBody, Class<T> classOfT) {
		// Write success log
		writeLogSuccess(function, statusCode, responseBody);

		if (function.contains("EMAIL_RESENT") && statusCode == 200) {
			_responseHandler.onSuccess(null);
			return;
		}

		if (function.contains("OTP_PASS_RESENT") && statusCode == 200) {
			_responseHandler.onSuccess(null);
			return;
		}

		// Response success to caller
		// T response = JsonHelper.fromJson(responseBody, classOfT);
		T response = JsonHelper.fromJson(responseBody, classOfT);
		// _responseHandler.onSuccess(response); // Dong testing
		if (Constants.ErrorCode.ERR_SUCCESS == response.result) {
			_responseHandler.onSuccess(response);
		} else {
			// Handle error in success case
			writeLogErrSuccess(function, response.result, responseBody);
			handleErrInSuccess(response.result);
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends BaseResponse> void responseSuccess(String function,
			int statusCode, JSONArray result, Class<T> classOfT) {
		writeLogSuccess(function, statusCode, "");
		ArrayList<T> listData = new ArrayList<T>();
		for (int i = 0; i < result.length(); i++) {
			JSONObject obj = null;
			try {
				obj = (JSONObject) result.get(i);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			T t = JsonHelper.fromJson(obj.toString(), classOfT);

			listData.add(t);
		}
		_responseHandler.onSuccess((ArrayList<BaseResponse>) listData, true);
	}

	/**
	 * Handle error in success case
	 * 
	 * @param errCode
	 *            The error code that define by server
	 */
	private void handleErrInSuccess(int errCode) {
		// Consider process with error here (display message...)
		// switch (errCode) {
		// case Constants.ErrorCode.ERR_INCORRECT_ID:
		// case Constants.ErrorCode.ERR_INCORRECT_PWD:
		// case Constants.ErrorCode.ERR_INCORRECT_TOKEN:
		// case Constants.ErrorCode.ERR_INCORRECT_DIGI_ID:
		// case Constants.ErrorCode.ERR_INCORRECT_PARAM:
		// case Constants.ErrorCode.ERR_NO_DATA:
		// break;
		// default:
		// break;
		// }
		// Response error to caller
		ErrorMessage errMsg = new ErrorMessage();
		errMsg.setResultCode(errCode);
		errMsg.setMessage("");
		_responseHandler.onError(errMsg);
	}

	private void handleFailure(int statusCode, Header[] headers,
			String responseBody, Throwable throwable) {
		switch (_requestType) {
		case Constants.ApiConst.API_SAMPLE:
			responseFailure("requestSample", statusCode, responseBody,
					throwable);
			break;
		case Constants.ApiConst.PHONE_REGISTRAION:
			responseFailure("registerNum", statusCode, responseBody, throwable);
			break;
		case Constants.ApiConst.PHONE_VERIFICATION_TOKEN:
			responseFailure("verifyNum", statusCode, responseBody, throwable);
			break;
		case Constants.ApiConst.PHONE_UPDATE_EMAIL:
			responseFailure("updateEmail", statusCode, responseBody, throwable);
			break;
		case Constants.ApiConst.EMAIL_VERIFY:
			responseFailure("EMAIL_VERIFY", statusCode, responseBody, throwable);
			break;
		case Constants.ApiConst.LOGIN_PHONE:
			responseFailure("LOGIN_PHONE", statusCode, responseBody, throwable);
			break;
		case Constants.ApiConst.LOGIN_EMAIL:
			responseFailure("LOGIN_EMAIL", statusCode, responseBody, throwable);
			break;
		case Constants.ApiConst.UPDATE_PROFILE:
			responseFailure("UPDATE_PROFILE", statusCode, responseBody,
					throwable);
			break;
		case Constants.ApiConst.EMAIL_RESENT:
			if (statusCode == 200) {
				responseSuccess("EMAIL_RESENT", statusCode, "", null);
			} else {
				responseFailure("EMAIL_RESENT", statusCode, responseBody,
						throwable);
			}
			break;
		case Constants.ApiConst.OTP_PASS_RESENT:
			if (statusCode == 200) {
				responseSuccess("OTP_PASS_RESENT", statusCode, "", null);
			} else {
				responseFailure("OTP_PASS_RESENT", statusCode, responseBody,
						throwable);
			}
			break;
		case Constants.ApiConst.NEAR_BY_RESTAURANTS:
			responseFailure("NEAR_BY_RESTAURANTS", statusCode, responseBody,
					throwable);
			break;
		default:
			writeLogFailure("handleFailure:default", statusCode, responseBody,
					throwable);
			break;
		}
	}

	/**
	 * Response to caller in failure case
	 *
	 * @param function
	 *            Function name for logging
	 * @param statusCode
	 *            Status code
	 * @param responseBody
	 *            Response body string
	 * @param throwable
	 *            error that thrown by HTTP response
	 */
	private void responseFailure(String function, int statusCode,
			String responseBody, Throwable throwable) {
		// Write error log
		writeLogFailure(function, statusCode, responseBody, throwable);

		// Response error to caller
		ErrorMessage errMsg = new ErrorMessage();
		errMsg.setStatusCode(statusCode);
		errMsg.setMessage(throwable.getMessage());
		errMsg.setErrorBody(responseBody);
		_responseHandler.onError(errMsg);
	}
}
