/**
 * 
 */
package aclub.users.android.login.ui.activities;

import java.util.ArrayList;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.httpservices.ErrorMessage;
import aclub.users.android.httpservices.ResponseHandler;
import aclub.users.android.httpservices.RestHelper;
import aclub.users.android.httpservices.models.BaseResponse;
import aclub.users.android.log.DLog;
import aclub.users.android.main.ui.activities.AclubActivity;
import aclub.users.android.main.ui.activities.AclubMainActivity;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.Tools;
import android.content.Intent;
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
public class EmailLoginActivity extends BaseActivity {

	private RelativeLayout actionBarRl;
	private EditText mEmailRgEdt, mPassRgEdt;
	private TextView mForgotPassTv;
	private Button mEmailLgBtn, mEmailNotRegisBtn;

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
		facebookLogin(savedInstanceState);
		setContentView(R.layout.email_login);
		initUI();
	}

	private void initUI() {
		mEmailLgBtn = (Button) findViewById(R.id.login_register_email_btn);
		mEmailLgBtn.setOnClickListener(this);
		mEmailNotRegisBtn = (Button) findViewById(R.id.not_yet_register_email_btn);
		mEmailNotRegisBtn.setOnClickListener(this);
		mEmailRgEdt = (EditText) findViewById(R.id.email_edt);
		mPassRgEdt = (EditText) findViewById(R.id.password_edt);
		mForgotPassTv = (TextView) findViewById(R.id.forgot_pass_tv);
		mForgotPassTv.setOnClickListener(this);
		actionBarRl = (RelativeLayout) findViewById(R.id.action_bar_email_login_layout);
		initActionBarLayout(actionBarRl);
		initActionBarTitle(getString(R.string.login_action_bar_title));
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
		case R.id.login_register_email_btn:
			doLoginEmail();
			break;
		case R.id.not_yet_register_email_btn:
			doGoToRegisterEmail();
			break;
		case R.id.forgot_pass_tv:
			doForgotPass();
			break;
		case R.id.back_action_bar_iv:
			finish();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

	private void doForgotPass() {
		Intent intent = new Intent(EmailLoginActivity.this,
				ForgotEmailActivity.class);
		startActivity(intent);
		finish();
	}

	private void doGoToRegisterEmail() {
		Intent intent = new Intent(EmailLoginActivity.this,
				EmailRegisterActivity.class);
		startActivity(intent);
		finish();
	}

	private void doLoginEmail() {
		String email = mEmailRgEdt.getText().toString();
		String pass = mPassRgEdt.getText().toString();
		if (!Tools.isEmail(email)) {
			mEmailRgEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(EmailLoginActivity.this, mEmailRgEdt);
			return;
		}

		// if (!Tools.isPassword(pass)) {
		// mPassRgEdt
		// .setError(getString(R.string.error_register_infor_message));
		// AnimationUtils.shake(EmailLoginActivity.this, mPassRgEdt);
		// return;
		// }

		showDialogLoading();

		RestHelper.getInstance().loginByEmail(EmailLoginActivity.this, email,
				pass, new ResponseHandler() {
					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("OK" + responses.size());

					}

					@Override
					public void onSuccess(BaseResponse response) {
						hideDialogLoading();
						DLog.d("Login Email Ok");
						doGoToMainApp();
					}

					@Override
					public void onError(ErrorMessage error) {
						hideDialogLoading();
						DLog.d("Login Email Not Ok");
						displayErrorDialog(error, EmailLoginActivity.this);
					}
				});
	}

	private void doGoToMainApp() {
		Intent intent = new Intent(EmailLoginActivity.this,
				AclubMainActivity.class);
		startActivity(intent);
		finish();
	}
}
