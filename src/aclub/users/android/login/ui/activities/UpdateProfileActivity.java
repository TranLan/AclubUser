/**
 * 
 */
package aclub.users.android.login.ui.activities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;

import aclub.users.android.R;
import aclub.users.android.abstractactivity.BaseActivity;
import aclub.users.android.db.SPConstants;
import aclub.users.android.db.SpManager;
import aclub.users.android.log.DLog;
import aclub.users.android.ui.cusimageview.RoundedImageView;
import aclub.users.android.utils.AnimationUtils;
import aclub.users.android.utils.CommonValues;
import aclub.users.android.utils.StringUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * @author dinostudio8891@gmail.com
 *
 */
public class UpdateProfileActivity extends BaseActivity {

	private RelativeLayout actionBarLayout;
	private RoundedImageView avatarProfile;
	private final int FROM_CAMERA = 1;
	private final int FROM_GALARY = 2;
	private static Bitmap bm;
	private static String avatarFilePath;
	private CheckBox mAutoAddFriendCb;
	private CheckBox mCanAddFriendsCb;
	private TextView mAutoAddFriendTv;
	private TextView mCanAddFriendTv;
	private SharedPreferences spf;
	private EditText mFullNameEdt;
	private Button mUpdateProfileBtn;
	private ProfilePictureView facebookAvatar;

	private static String currentAvatar;
	private File avatarFb;

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
		setContentView(R.layout.update_profile_activity);
		initActionBarTitle(getString(R.string.register_title));
		initUI();
	}

	private void initUI() {
		avatarProfile = (RoundedImageView) findViewById(R.id.avatar_profile);
		avatarProfile.setOnClickListener(this);
		facebookAvatar = (ProfilePictureView) findViewById(R.id.profile_facebook_picture);
		spf = SpManager.getInstances(UpdateProfileActivity.this);
		if (bm != null) {
			avatarProfile.setImageBitmap(bm);
		}
		mFullNameEdt = (EditText) findViewById(R.id.full_name_edt);
		if (CommonValues.currentFbProflie != null) {
			avatarProfile.setVisibility(View.GONE);
			facebookAvatar.setVisibility(View.VISIBLE);
			mFullNameEdt.setText(CommonValues.currentFbProflie.getName());
			facebookAvatar.setProfileId(CommonValues.currentFbProflie.getId());
			facebookAvatar.setDrawingCacheEnabled(true);
			// facebookAvatar.setDr
			// avatarFb = convertBitmapToFile(facebookAvatar.getDrawingCache());
		} else {
			avatarProfile.setVisibility(View.VISIBLE);
			facebookAvatar.setVisibility(View.GONE);
			facebookAvatar.setProfileId(null);
		}

		mAutoAddFriendCb = (CheckBox) findViewById(R.id.auto_add_friend_cb);
		mAutoAddFriendCb.setChecked(spf.getBoolean(SPConstants.AUTO_ADD_FRIEND,
				true));
		mAutoAddFriendTv = (TextView) findViewById(R.id.auto_add_friend_tv);
		mCanAddFriendsCb = (CheckBox) findViewById(R.id.allow_add_friend_cb);
		mCanAddFriendsCb.setChecked(spf.getBoolean(SPConstants.CAN_ADD_FRIEND,
				false));
		mCanAddFriendTv = (TextView) findViewById(R.id.allow_add_friend_tv);
		mUpdateProfileBtn = (Button) findViewById(R.id.update_profile_button);
		mUpdateProfileBtn.setOnClickListener(this);

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
		actionBarLayout = (RelativeLayout) findViewById(R.id.action_bar_update_profile_layout);
		super.initActionBarLayout(actionBarLayout);
		super.initActionBarTitle(titleActionBar);
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
		case R.id.avatar_profile:
			selectImageToAvatar();
			break;
		case R.id.back_action_bar_iv:
			finish();
			break;
		case R.id.update_profile_button:
			try {
				doUpdateProfile();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void updateProfile(File file, String name) {
		RequestParams params = new RequestParams();
		try {
			params.put("[user][avatar]", file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		params.put("[user][name]", name);

		String phone = spf.getString(SPConstants.NUM_PHONE, "");
		String token = spf.getString(SPConstants.NUM_TOKEN, "");

		DLog.d(phone);
		DLog.d(token);

		AsyncHttpClient client = new AsyncHttpClient();
		client.addHeader("application_token",
				"b0bb724a0c6a6654742a6b667de939998ef70c6b2569516054a4ee");
		client.addHeader("phone", phone);
		client.addHeader("TOKEN", token);

		client.put(UpdateProfileActivity.this, "http://aclub.vn/api/v1/users/"
				+ spf.getInt(SPConstants.USER_ID, 0), params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						DLog.d("Upload OK");
						doGoToVerifyEmail();
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						DLog.d("Upload faOK");
						DLog.d(arg3);
						DLog.d(arg2.toString());
						displayDialogNotify(getString(R.string.error_title),
								UpdateProfileActivity.this);
					}
				});
	}

	private void doUpdateProfile() throws FileNotFoundException {
		String name = mFullNameEdt.getText().toString();
		if (StringUtils.isEmpty(name)) {
			mFullNameEdt
					.setError(getString(R.string.error_register_infor_message));
			AnimationUtils.shake(UpdateProfileActivity.this, mFullNameEdt);
			return;
		}
		DLog.d(currentAvatar);
		if (facebookAvatar.getVisibility() == View.VISIBLE) {
			ImageView fbImage = ((ImageView) facebookAvatar.getChildAt(0));
			Bitmap bitmap = ((BitmapDrawable) fbImage.getDrawable())
					.getBitmap();
			avatarFb = convertBitmapToFile(bitmap);
			updateProfile(avatarFb, name);
		} else {
			File avatarFile = null;
			if (currentAvatar != null && currentAvatar.length() > 0) {
				avatarFile = new File(currentAvatar);
			}
			updateProfile(avatarFile, name);
		}
	}

	private void doGoToVerifyEmail() {
		Intent intent = new Intent(UpdateProfileActivity.this,
				EmailRegisterActivity.class);
		startActivity(intent);
		finish();
	}

	private void selectImageToAvatar() {
		final CharSequence[] items = { getString(R.string.take_foto),
				getString(R.string.from_library),
				getString(R.string.cancel_aclub_label) };
		AlertDialog.Builder builder = new AlertDialog.Builder(
				UpdateProfileActivity.this);
		builder.setTitle(getString(R.string.add_foto));
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if (items[item].equals(getString(R.string.take_foto))) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, 1);
				} else if (items[item].equals(getString(R.string.from_library))) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(Intent.createChooser(intent,
							getString(R.string.select_file)), 2);
				} else if (items[item]
						.equals(getString(R.string.cancel_aclub_label))) {
					dialog.dismiss();
				}
			}
		});
		builder.show();
	}

	private File convertBitmapToFile(Bitmap bm) {
		String fileavatarFilePath = android.os.Environment
				.getExternalStorageDirectory()
				+ File.separator
				+ "Aclub"
				+ File.separator + "avatar";
		DLog.d(fileavatarFilePath);

		OutputStream fOut = null;
		File file = new File(fileavatarFilePath, String.valueOf(System
				.currentTimeMillis()) + ".jpg");
		file.getParentFile().mkdirs();
		if (file != null && file.canExecute()) {
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fOut = new FileOutputStream(file);
			bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
			fOut.flush();
			fOut.close();
		} catch (FileNotFoundException e) {
			DLog.d(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			DLog.d(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			DLog.d(e.getMessage());
			e.printStackTrace();
		}
		return file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (requestCode == FROM_CAMERA) {
				try {
					bm = (Bitmap) data.getExtras().get("data");
					avatarProfile.setImageBitmap(bm);

					avatarFilePath = android.os.Environment
							.getExternalStorageDirectory()
							+ File.separator
							+ "Aclub" + File.separator + "avatar";
					DLog.d(avatarFilePath);

					OutputStream fOut = null;
					File file = new File(avatarFilePath, String.valueOf(System
							.currentTimeMillis()) + ".jpg");
					file.getParentFile().mkdirs();
					currentAvatar = file.getAbsolutePath();
					if (file != null && file.canExecute()) {
					} else {
						file.createNewFile();
					}
					try {
						fOut = new FileOutputStream(file);
						bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
						fOut.flush();
						fOut.close();
					} catch (FileNotFoundException e) {
						DLog.d(e.getMessage());
						e.printStackTrace();
					} catch (IOException e) {
						DLog.d(e.getMessage());
						e.printStackTrace();
					} catch (Exception e) {
						DLog.d(e.getMessage());
						e.printStackTrace();
					}
				} catch (Exception e) {
					DLog.d(e.getMessage());
					e.printStackTrace();
				}
			} else if (requestCode == FROM_GALARY) {
				Uri selectedImageUri = data.getData();

				currentAvatar = getPath(selectedImageUri,
						UpdateProfileActivity.this);

				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				bm = BitmapFactory.decodeFile(currentAvatar, btmapOptions);
				avatarProfile.setImageBitmap(bm);
			}
		}
	}

	public String getPath(Uri uri, Activity activity) {
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = activity
				.managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}
