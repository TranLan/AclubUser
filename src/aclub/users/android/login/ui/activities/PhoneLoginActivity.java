/**
 * 
 */
package aclub.users.android.login.ui.activities;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.db.SpManager;
import aclub.users.android.httpservices.ErrorMessage;
import aclub.users.android.httpservices.ResponseHandler;
import aclub.users.android.httpservices.RestHelper;
import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.log.DLog;
import aclub.users.android.main.ui.activities.AclubMainActivity;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.StringUtils;
import aclub.users.android.utils.Tools;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class PhoneLoginActivity extends BaseActivity {

	private EditText mNumPhonEdt;
	private Button mConfirmBtn;
	private TextView mEmailLgTv;
	private RelativeLayout mActionBarLayout;
	private SharedPreferences spf;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#onCreate(android.os
	 * .Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_login_activity);
		initUI();
		spf = SpManager.getInstances(PhoneLoginActivity.this);
	}

	private void initUI() {
		mNumPhonEdt = (EditText) findViewById(R.id.put_your_num_edt);
		mConfirmBtn = (Button) findViewById(R.id.login_confirm_btn);
		mEmailLgTv = (TextView) findViewById(R.id.login_by_email_tv);
		mConfirmBtn.setOnClickListener(this);
		mEmailLgTv.setOnClickListener(this);
		mActionBarLayout = (RelativeLayout) findViewById(R.id.action_bar_phone_login_layout);
		initActionBarLayout(mActionBarLayout);
		initActionBarTitle(getString(R.string.login_title));
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
		case R.id.back_action_bar_iv:
			finish();
			break;
		case R.id.login_by_email_tv:
			doLoginByEmail();
			break;
		case R.id.login_confirm_btn:
			doLogin();
			break;
		default:
			break;
		}
	}

	private void doLogin() {
		final String num = mNumPhonEdt.getText().toString();

		if (StringUtils.isEmpty(num)) {
			mNumPhonEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(PhoneLoginActivity.this, mNumPhonEdt);
			return;
		}

		Tools.closeKeyBoard(mNumPhonEdt, PhoneLoginActivity.this);
		showDialogLoading();

		// Comment for wrong flow
		// RestHelper.getInstance().loginByPhone(PhoneLoginActivity.this, num,
		// spf.getString(SPConstants.VERIFICATION_TOKEN, ""),
		// new ResponseHandler() {
		//
		// @Override
		// public void onSuccess(BaseResponse response) {
		// DLog.d("Login successed");
		// hideDialogLoading();
		// doGoToMainApp();
		// }
		//
		// @Override
		// public void onError(ErrorMessage error) {
		// DLog.d("Login fail");
		// hideDialogLoading();
		// displayErrorDialog(error, PhoneLoginActivity.this);
		// }
		// });

		RestHelper.getInstance().resendOTPPass(PhoneLoginActivity.this, num,
				new ResponseHandler() {
					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("OK" + responses.size());

					}

					@Override
					public void onSuccess(BaseResponse response) {
						hideDialogLoading();
						DLog.d("Send OTP Pass for Login");
						doGoToVerifySmsCode(num);
					}

					@Override
					public void onError(ErrorMessage error) {
						hideDialogLoading();
						DLog.d("Send OTP Pass for Login Error");
						displayDialogNotify(getString(R.string.error_title),
								PhoneLoginActivity.this);
					}
				});
	}

	private void doGoToVerifySmsCode(String num) {
		Intent intent = new Intent(PhoneLoginActivity.this,
				VerifyOTPPassActivity.class);
		intent.putExtra("numberphone", num);
		intent.putExtra("fromLogin", "login");
		startActivity(intent);
		finish();
	}

	private void doLoginByEmail() {
		Intent emailLgIntent = new Intent(PhoneLoginActivity.this,
				EmailLoginActivity.class);
		startActivity(emailLgIntent);
		finish();
	}

	private void doGoToMainApp() {
		Intent intent = new Intent(PhoneLoginActivity.this,
				AclubMainActivity.class);
		startActivity(intent);
		finish();
	}
}
