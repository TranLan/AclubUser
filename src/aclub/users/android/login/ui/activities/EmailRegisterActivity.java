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
import aclub.users.android.utils.Tools;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class EmailRegisterActivity extends BaseActivity {

	private RelativeLayout actionBarEmailRegisterLayout;

	private EditText emailEdt, passwordEdt;
	private Button mEmReConfirm, mEmReLater;
	private SharedPreferences spf;
	private Editor edt;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#onCreate(android.os
	 * .Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_register_layout);
		initActionBarTitle(getString(R.string.register_email_action_bar_title));
		spf = SpManager.getInstances(EmailRegisterActivity.this);
		edt = spf.edit();
		initUI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * aclub.users.android.abstractactivity.BaseActivity#initActionBar(java.
	 * lang.String)
	 */
	@Override
	protected void initActionBarTitle(String titleActionBar) {
		actionBarEmailRegisterLayout = (RelativeLayout) findViewById(R.id.action_bar_email_register_layout);
		initActionBarLayout(actionBarEmailRegisterLayout);
		super.initActionBarTitle(titleActionBar);
	}

	protected void initUI() {
		emailEdt = (EditText) findViewById(R.id.email_register_edt);
		passwordEdt = (EditText) findViewById(R.id.password_email_rergistere_dt);

		mEmReConfirm = (Button) findViewById(R.id.register_email_btn);
		mEmReLater = (Button) findViewById(R.id.register_email_later_btn);

		mEmReConfirm.setOnClickListener(this);
		mEmReLater.setOnClickListener(this);
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
		case R.id.register_email_btn:
			doRegisterEmail();
			break;
		case R.id.register_email_later_btn:
			doRegisterEmailLater();
			break;
		default:
			super.onClick(v);
			break;

		}
	}

	private boolean verifyEmailAndPassword(String email, String password) {
		if (StringUtils.isEmpty(email) || !Tools.isEmail(email)) {
			emailEdt.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(EmailRegisterActivity.this, emailEdt);
			return false;
		}
		if (!Tools.isPassword(password)) {
			passwordEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(EmailRegisterActivity.this, passwordEdt);
			return false;
		}
		return true;
	}

	private void doRegisterEmail() {
		final String email = emailEdt.getText().toString();
		String password = passwordEdt.getText().toString();
		DLog.d("Password: " + password);
		if (verifyEmailAndPassword(email, password)) {
			showDialogLoading();
			RestHelper.getInstance().registerEmail(EmailRegisterActivity.this,
					email, password, spf.getInt(SPConstants.USER_ID, 0),
					new ResponseHandler() {
						@Override
						public void onSuccess(
								ArrayList<BaseResponse> responses,
								boolean isJSONArrayFB) {
							DLog.d("OK" + responses.size());

						}

						@Override
						public void onSuccess(BaseResponse response) {
							hideDialogLoading();
							DLog.d("OK Register email");
							User user = (User) response;
							DLog.d(user.getEmail_verification_token());
							edt.putString(SPConstants.USER_EMAIL_UNVERIFY,
									user.getUnVerifyEmail());
							edt.putString(SPConstants.USER_EMAIL_TOKEN,
									user.getEmail_verification_token());
							edt.commit();
							doGoToVerifyEmail(email);
						}

						@Override
						public void onError(ErrorMessage error) {
							hideDialogLoading();
							displayErrorDialog(error,
									EmailRegisterActivity.this);
						}
					});
		}
	}

	private void doRegisterEmailLater() {
		Intent intent = new Intent(EmailRegisterActivity.this,
				AclubMainActivity.class);
		startActivity(intent);
		finish();
	}

	private void doGoToVerifyEmail(String email) {
		Intent intent = new Intent(EmailRegisterActivity.this,
				VerifyEmailTokenActivity.class);
		intent.putExtra("email_user", email);
		startActivity(intent);
		finish();
	}
}
