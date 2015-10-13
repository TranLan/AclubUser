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
import aclub.users.android.log.DLog;
import aclub.users.android.main.ui.activities.AclubActivity;
import aclub.users.android.main.ui.activities.AclubMainActivity;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.StringUtils;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
public class VerifyEmailTokenActivity extends BaseActivity {

	private RelativeLayout mActionBarRl;
	private EditText mEmailTkEdt;
	private Button mConfirmEmailBtn;
	private Button mRegisterLaterBtn;
	private TextView mResentTkTv, mSendEmailTv;
	private SharedPreferences spf;
	private Editor edt;
	private String emailUser;
	private TextView emailUserTv;

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
		setContentView(R.layout.email_verify_layout);
		Intent intent = getIntent();
		if (intent != null) {
			emailUser = intent.getStringExtra("email_user");
		}
		initUI();

	}

	private void initUI() {
		mActionBarRl = (RelativeLayout) findViewById(R.id.action_bar_email_verify_layout);
		initActionBarLayout(mActionBarRl);
		initActionBarTitle(getString(R.string.register_email_action_bar_title));
		mEmailTkEdt = (EditText) findViewById(R.id.email_verify_edt);
		mConfirmEmailBtn = (Button) findViewById(R.id.email_verify_btn);
		mRegisterLaterBtn = (Button) findViewById(R.id.email_verify_later_btn);
		mResentTkTv = (TextView) findViewById(R.id.email_verify_resent_token_tv);
		mSendEmailTv = (TextView) findViewById(R.id.email_verify_send_email_tv);
		spf = SpManager.getInstances(VerifyEmailTokenActivity.this);
		edt = spf.edit();
		mConfirmEmailBtn.setOnClickListener(this);
		mResentTkTv.setOnClickListener(this);
		mSendEmailTv.setOnClickListener(this);
		emailUserTv = (TextView) findViewById(R.id.email_user_tv);
		emailUserTv.setText(emailUser);
		mRegisterLaterBtn.setOnClickListener(this);
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
		case R.id.email_verify_btn:
			doVerifyEmail();
			break;
		case R.id.email_verify_resent_token_tv:
			doResentEmail();
			break;
		case R.id.email_verify_later_btn:
			doVerifyEmailLater();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

	private void doVerifyEmailLater() {
		Intent intent = new Intent(VerifyEmailTokenActivity.this,
				AclubMainActivity.class);
		startActivity(intent);
		finish();
	}

	private void doResentEmail() {
		showDialogLoading();
		RestHelper.getInstance().resentVerifyEmail(
				VerifyEmailTokenActivity.this,
				spf.getInt(SPConstants.USER_ID, 0), new ResponseHandler() {

					@Override
					public void onSuccess(BaseResponse response) {
						DLog.d("doResentEmail ok");
						hideDialogLoading();
						displayDialogNotify(
								getString(R.string.email_verify_resent_success),
								VerifyEmailTokenActivity.this);
					}

					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("OK" + responses.size());

					}

					@Override
					public void onError(ErrorMessage error) {
						DLog.d("doResentEmail not ok");
						hideDialogLoading();
						displayDialogNotify(
								getString(R.string.email_verify_resent_error),
								VerifyEmailTokenActivity.this);

					}
				});
	}

	// private void displayDialogNotify(String content) {
	// final CusDialogNotify dialog = new CusDialogNotify(
	// VerifyEmailTokenActivity.this, content, "");
	// dialog.setEvendialog(new EventDialog() {
	//
	// @Override
	// public void onSubmit(String value) {
	// dialog.dismiss();
	// }
	//
	// @Override
	// public void onDismiss(int value) {
	//
	// }
	//
	// @Override
	// public void onCancel() {
	// dialog.dismiss();
	//
	// }
	// });
	// dialog.show();
	// }

	private void doVerifyEmail() {
		String emailTk = mEmailTkEdt.getText().toString();
		if (StringUtils.isEmpty(emailTk)) {
			mEmailTkEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(VerifyEmailTokenActivity.this, mEmailTkEdt);
			return;
		}

		showDialogLoading();
		RestHelper.getInstance().verifyEmail(VerifyEmailTokenActivity.this,
				spf.getInt(SPConstants.USER_ID, 0),
				spf.getString(SPConstants.USER_EMAIL_TOKEN, ""),
				new ResponseHandler() {
					@Override
					public void onSuccess(ArrayList<BaseResponse> responses,
							boolean isJSONArrayFB) {
						DLog.d("OK" + responses.size());

					}

					@Override
					public void onSuccess(BaseResponse response) {
						hideDialogLoading();
						DLog.d("doVerifyEmail ok");
						doGoToMainApp();
					}

					@Override
					public void onError(ErrorMessage error) {
						hideDialogLoading();
						DLog.d("doVerifyEmail not ok");
					}
				});

	}

	private void doGoToMainApp() {
		Intent intent = new Intent(VerifyEmailTokenActivity.this,
				AclubMainActivity.class);
		startActivity(intent);
		finish();
	}
}
