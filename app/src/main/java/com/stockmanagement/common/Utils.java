package com.stockmanagement.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.StrictMode;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.stockmanagement.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Utils
{
	public static String BackFlag="";
	public static AlertDialog alert;
	private static String TAG = Utils.class.getSimpleName();
	public static double latitude; // latitude
	public static double longitude; // longitude
	static String Response;
	public static ProgressDialog dialog;

	public static Typeface SetCustomFont(String fontName, Context context) {
		return Typeface.createFromAsset(context.getAssets(), fontName);
	}

	public static Spanned SetErrorHtml(String msg) {
		return Html.fromHtml("<font color='red'>" + msg + "</font>");
	}

	public static void ShowTost(Context context, String ToastMessage) {
		Toast.makeText(context, ToastMessage, Toast.LENGTH_SHORT).show();
	}

	public final static boolean isValidEmail(CharSequence target) {
		if (TextUtils.isEmpty(target))
			return false;
		else
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}


	public static void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size=1024;
		try
		{
			byte[] bytes=new byte[buffer_size];
			for(;;)
			{
				int count=is.read(bytes, 0, buffer_size);
				if(count==-1)
					break;
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex){}
	}

	public static void hideKeyBoard(View view, Activity mActivity) {
		InputMethodManager imm = (InputMethodManager) mActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static void showKeyBoard(View v, Activity mActivity) {
		InputMethodManager imm = (InputMethodManager) mActivity
				.getSystemService(Service.INPUT_METHOD_SERVICE);
		imm.showSoftInput(v, 0);
	}

	public static boolean isEmpty(CharSequence charSequence) {
		return TextUtils.isEmpty(charSequence)
				|| charSequence.toString().equalsIgnoreCase("null");
	}

	public static boolean isNetworkAvailable(final Context context, boolean canShowErrorDialogOnFail, final boolean isFinish)
	{
		boolean isNetAvailable = false;
		if (context != null)
		{
			final ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (mConnectivityManager != null)
			{
				boolean mobileNetwork = false;
				boolean wifiNetwork = false;
				boolean mobileNetworkConnecetd = false;
				boolean wifiNetworkConnecetd = false;

				final NetworkInfo mobileInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				final NetworkInfo wifiInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

				if (mobileInfo != null) {
					mobileNetwork = mobileInfo.isAvailable();
				}

				if (wifiInfo != null) {
					wifiNetwork = wifiInfo.isAvailable();
				}

				if (wifiNetwork || mobileNetwork) {
					if (mobileInfo != null)
						mobileNetworkConnecetd = mobileInfo
								.isConnectedOrConnecting();
					wifiNetworkConnecetd = wifiInfo.isConnectedOrConnecting();
				}

				isNetAvailable = (mobileNetworkConnecetd || wifiNetworkConnecetd);
			}
			context.setTheme(R.style.AppTheme);
			if (!isNetAvailable && canShowErrorDialogOnFail) {
				Log.v("TAG", "context : " + context.toString());
				if (context instanceof Activity) {
					((Activity) context).runOnUiThread(new Runnable() {

						@Override
						public void run() {
							showAlertWithFinish((Activity) context, context.getString(R.string.app_name), context.getString(R.string.network_alert), isFinish);
						}
					});
				}
			}
		}

		return isNetAvailable;
	}

	public static void ShowProgressDialog(Activity activity, String message){
		if(dialog != null){
			dialog.dismiss();
		}
		try{
			dialog = new ProgressDialog(activity);
			dialog.setMessage(message);
			dialog.setCancelable(false);
			dialog.show();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void hideProgressDialog(){
		if(dialog != null && dialog.isShowing())
			dialog.dismiss();
	}

	public static void showAlertWithFinish(final Activity activity, String title, String message, final boolean isFinish) {
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (isFinish) {
					dialog.dismiss();
					activity.finish();
				} else {
					dialog.dismiss();
				}
			}
		}).show();
	}

	public static void showAlert(final Activity activity, String title, String message) {
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(activity.getString(R.string.ok), null);
		builder.show();
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public static String getAddressData(Activity activity, String url1) {
		if (Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		StringBuilder result = new StringBuilder();
		HttpURLConnection urlConnection = null;
		try {

			URL url = new URL(url1);
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

		}catch( Exception e) {
			e.printStackTrace();
		}
		finally {
			urlConnection.disconnect();
		}

		Response = result.toString();
		return Response;
	}

	public static String sendContact(String url, String fromEmail, String sendEmail, String subject, String question, String delivery) {
		String response = null;
		try {
			BufferedReader reader=null;
			try {

				// Create data variable for sent values to server

				String data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(subject, "UTF-8");
				data += "&" + URLEncoder.encode("buyerEmail", "UTF-8") + "="+ URLEncoder.encode(fromEmail, "UTF-8");
				data += "&" + URLEncoder.encode("sellerEmail", "UTF-8") + "="+ URLEncoder.encode(sendEmail, "UTF-8");
				data += "&" + URLEncoder.encode("question", "UTF-8") + "="+ URLEncoder.encode(question, "UTF-8");
				data += "&" + URLEncoder.encode("delivery", "UTF-8") + "="+ URLEncoder.encode(delivery, "UTF-8");

				Log.e("my webservice", "registration : " + data);

				// Send POST data request

				URL url1 = new URL(url);

				URLConnection conn =url1.openConnection();
				conn.setDoOutput(true);
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(data);
				wr.flush();

				// Get the server response

				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;

				// Read Server Response
				while((line = reader.readLine()) != null)
				{
					// Append server response in string
					sb.append(line + "\n");
				}
				response = sb.toString();
			}
			catch(Exception ex)
			{

			}
			finally
			{
				try
				{

					reader.close();
				}

				catch(Exception ex) {}
			}
		} catch (Exception e) {
			Log.e("Error : ", "IO Exception");

		}
		return response;
	}

	public static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;
	}
}
