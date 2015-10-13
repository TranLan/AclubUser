/**
 * 
 */
package aclub.users.android.login.ui.activities;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.Tools;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class ForgotEmailActivity extends BaseActivity {

	private RelativeLayout actionBarRl;
	private EditText mEmailEdt;
	private Button mConfirmFogotEmailBtn;

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
		setContentView(R.layout.forgot_email_pass_layout);
		initUI();
	}

	private void initUI() {
		actionBarRl = (RelativeLayout) findViewById(R.id.action_bar_forgot_pass_layout);
		initActionBarLayout(actionBarRl);
		initActionBarTitle(getString(R.string.forgot_email_password));
		helpActionBarIv.setImageResource(R.drawable.exit_iv_actionbar);
		backActionBarIv.setImageResource(R.drawable.back_iv_actionbar);
		mEmailEdt = (EditText) findViewById(R.id.email_forgot_pass_edt);
		mConfirmFogotEmailBtn = (Button) findViewById(R.id.email_forgot_pass_btn);
		mConfirmFogotEmailBtn.setOnClickListener(this);
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
		case R.id.help_action_bar_iv:
			finish();
			break;
		case R.id.email_forgot_pass_btn:
			doForgotEmailPassword();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

	private void doForgotEmailPassword() {
		String email = mEmailEdt.getText().toString();
		if (!Tools.isEmail(email)) {
			mEmailEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(ForgotEmailActivity.this, mEmailEdt);
			return;
		}
		Toast.makeText(ForgotEmailActivity.this, "Lack of API : Will be updated", Toast.LENGTH_SHORT).show();
		// TODO  fogot email password
	}
}
