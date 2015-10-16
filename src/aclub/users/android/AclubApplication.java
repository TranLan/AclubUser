/**
 * 
 */
package aclub.users.android;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * @author ntdong2012
 * @tranlan
 *
 */
public class AclubApplication extends Application {

	private static Context context;
	private final String TAG = getClass().getSimpleName();
	private boolean isUTMode = true;
	private Thread.UncaughtExceptionHandler uncaughtHandler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		if (context == null) {
			context = getApplicationContext();
		}
//		uncaughtHandler = Thread.getDefaultUncaughtExceptionHandler();
//		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerApplication());
	}

	private String getDeviceSuperInfo() {
		try {
			String s = "Debug-infos:";
			s += "\n OS Version: " + System.getProperty("os.version") + "("
					+ android.os.Build.VERSION.INCREMENTAL + ")";
			s += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
			s += "\n Device: " + android.os.Build.DEVICE;
			s += "\n Model (and Product): " + android.os.Build.MODEL + " ("
					+ android.os.Build.PRODUCT + ")";
			s += "\n DISPLAY: " + android.os.Build.DISPLAY;
			s += "\n UNKNOWN: " + android.os.Build.UNKNOWN;
			s += "\n MANUFACTURER: " + android.os.Build.MANUFACTURER;
			s += "\n SERIAL: " + android.os.Build.SERIAL;
			s += "\n USER: " + android.os.Build.USER;
			s += "\n HOST: " + android.os.Build.HOST;
			s += "++++++++++++++++++++++++++++++++++++++\n";
			return s;
		} catch (Exception e) {
			return "";
		}

	}

	public static Context getContext() {
		return context;
	}

	private String getStackTrace(Throwable t) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		Throwable cause = t;

		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();

		}
		final String stackTrace = result.toString();
		printWriter.close();
		return stackTrace;
	}

	class UncaughtExceptionHandlerApplication implements
			Thread.UncaughtExceptionHandler {

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			String stackString = getStackTrace(ex);
			stackString = getDeviceSuperInfo() + stackString;
			if (isUTMode) {
				String[] mails = { "dinostudio8891@gmail.com",
						"tronglong88@gmail.com" };
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("plain/text");
				intent.putExtra(Intent.EXTRA_EMAIL, mails);
				intent.putExtra(Intent.EXTRA_SUBJECT,
						"[VOC for Wifi Password] FC_Log");
				intent.putExtra(Intent.EXTRA_TEXT, stackString);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				int pid = android.os.Process.myPid();
				try {
					android.os.Process.killProcess(pid);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				uncaughtHandler.uncaughtException(thread, ex);
			}
		}
	}

}
