/**
 * 
 */
package aclub.users.android.login.ui.activities;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.db.SPConstants;
import aclub.users.android.db.SpManager;
import aclub.users.android.httpservices.ErrorMessage;
import aclub.users.android.httpservices.ResponseHandler;
import aclub.users.android.httpservices.RestHelper;
import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.httpservices.models.User;
import aclub.users.android.log.DLog;
import aclub.users.android.main.ui.activities.AclubMainActivity;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.StringUtils;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class VerifyOTPPassActivity extends BaseActivity {

	private RelativeLayout actionBarVerifyOtpLayout;
	private TextView resentPassTv;
	private TextView phoneNumTv;
	private EditText otpPassEdt;
	private Button confirmBtn;
	private String numberPhone;
	private Editor edt;
	// private String verifyNum;
	private boolean fromLoginMode = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verify_otp_pass_layout);
		initActionBar();
		Intent intent = getIntent();
		if (intent != null) {
			numberPhone = intent.getStringExtra("numberphone");
			String loginMode = intent.getStringExtra("fromLogin");
			if (!StringUtils.isEmpty(loginMode)) {
				fromLoginMode = true;
			}
			edt = SpManager.getInstances(VerifyOTPPassActivity.this).edit();
			edt.putString(SPConstants.NUM_PHONE, numberPhone);
			edt.commit();
		}
		initUI();
	}

	@SuppressLint("DefaultLocale")
	private void initActionBar() {
		actionBarVerifyOtpLayout = (RelativeLayout) findViewById(R.id.action_bar_verify_otp_layout);
		super.initActionBarLayout(actionBarVerifyOtpLayout);
		titleActionBarTv.setText(getString(R.string.register_title)
				.toUpperCase());
	}

	private void initUI() {

		resentPassTv = (TextView) findViewById(R.id.resent_otp_tv);
		confirmBtn = (Button) findViewById(R.id.verify_otp_buton);
		confirmBtn.setOnClickListener(this);
		phoneNumTv = (TextView) findViewById(R.id.phone_num_verify_otp_pass_tv);
		otpPassEdt = (EditText) findViewById(R.id.verify_otp_pass_tv);
		phoneNumTv.setText(numberPhone);

		// resentPassTv
		// .setText(Html
		// .fromHtml("<a href=\"https://play.google.com/store/search?q=dinostudio8891&c=apps\">"
		// + getString(R.string.send_otp_pass_again)
		// + "</a>"));
		// Linkify.addLinks(resentPassTv, Linkify.ALL);
		// resentPassTv.setMovementMethod(LinkMovementMethod.getInstance());

		resentPassTv.setOnClickListener(this);

		otpPassEdt.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (!StringUtils.isEmpty(s.toString())) {
					confirmBtn.setClickable(true);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#onClick(android.view
	 * .View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.verify_otp_buton:
			doVerifyNum();
			break;
		case R.id.back_action_bar_iv:
			finish();
			break;
		case R.id.resent_otp_tv:
			doResentOTPPass();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

	private void doResentOTPPass() {
		showDialogLoading();
		RestHelper.getInstance().resendOTPPass(VerifyOTPPassActivity.this,
				numberPhone, new ResponseHandler() {

					@Override
					public void onSuccess(BaseResponse response) {
						hideDialogLoading();
						DLog.d("Resent OTP Ok");
						displayDialogNotify(
								getString(R.string.otp_verify_resent_success),
								VerifyOTPPassActivity.this);
					}

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("OK" + responses.size());

					}

					@Override
					public void onError(ErrorMessage error) {
						hideDialogLoading();
						displayDialogNotify(
								getString(R.string.email_verify_resent_error),
								VerifyOTPPassActivity.this);
					}
				});
	}

	private void doVerifyNum() {
		String otpPass = otpPassEdt.getText().toString();
		if (!StringUtils.isEmpty(otpPass)) {
			showDialogLoading();
			RestHelper.getInstance().verifyPhoneNum(VerifyOTPPassActivity.this,
					numberPhone, otpPass, new ResponseHandler() {

						@Override
						public void onSuccess(BaseResponse response) {
							DLog.d("Verify OK");
							hideDialogLoading();
							User user = (User) response;
							edt = SpManager.getInstances(
									VerifyOTPPassActivity.this).edit();
							edt.putString(SPConstants.NUM_TOKEN,
									user.getTokenNum());
							edt.putInt(SPConstants.USER_ID, user.getId());

							if (fromLoginMode) {
								Intent intent = new Intent(
										VerifyOTPPassActivity.this,
										AclubMainActivity.class);
								startActivity(intent);
							} else {
								Intent intent = new Intent(
										VerifyOTPPassActivity.this,
										UpdateProfileActivity.class);
								startActivity(intent);
							}
							edt.putBoolean(SPConstants.LOGINED, true);
							edt.commit();
							finish();
						}

						@Override
						public void onSuccess(
								ArrayList<BaseResponse> responses,
								boolean isJSONArrayFB) {
							DLog.d("OK" + responses.size());

						}

						@Override
						public void onError(ErrorMessage error) {
							DLog.d("Verify error");
							hideDialogLoading();
							displayErrorDialog(error,
									VerifyOTPPassActivity.this);
						}
					});
		} else {
			otpPassEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(VerifyOTPPassActivity.this, otpPassEdt);
		}
	}

	BroadcastReceiver smsReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			Bundle bundle = intent.getExtras();
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				String address = messages[i].getOriginatingAddress();
				Toast.makeText(VerifyOTPPassActivity.this, address,
						Toast.LENGTH_SHORT).show();
				String bodySms = messages[i].getDisplayMessageBody();
				if (!StringUtils.isEmpty(address) && address.contains("ACLUB")) {
					otpPassEdt.setText(getOTPPassFromSms(bodySms));
					edt.putString(SPConstants.VERIFICATION_TOKEN,
							getOTPPassFromSms(bodySms));
					edt.commit();
				}
			}
		}
	};

	/**
	 * Get OTP Pass from Sms body
	 *
	 * @param string
	 *            body
	 */
	public String getOTPPassFromSms(String body) {
		String pass = null;
		if (body != null) {
			int location = body.lastIndexOf(":");
			if (location != -1) {
				pass = body.substring(location + 2, body.length());
			}
		}
		return pass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(smsReceiver, new IntentFilter(
				"android.provider.Telephony.SMS_RECEIVED"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(smsReceiver);
	}
}
