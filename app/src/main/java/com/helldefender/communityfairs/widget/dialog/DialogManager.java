package com.helldefender.communityfairs.widget.dialog;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;

/**
 * Created by Helldefender on 2017/2/22.
 */

public class DialogManager {

	public static EasyDialog progressDialog;

    public static EasyDialog showProgressDialog(Context context, String message) {
        return showProgressDialog(context, null, message, true, null);
    }

	public static EasyDialog showProgressDialog(Context context, String message, boolean cancelable) {
		return showProgressDialog(context, null, message, cancelable, null);
	}
	
	@Deprecated
	public static EasyDialog showProgressDialog(Context context,
                                                String title, String message, boolean canCancelable, OnCancelListener listener) {

		if (progressDialog == null) {
			progressDialog = new EasyDialog(context, message);
		} else if (progressDialog.getContext() != context) {
			dismissProgressDialog();
			progressDialog = new EasyDialog(context, message);
		}

		progressDialog.setCancelable(canCancelable);
		progressDialog.setOnCancelListener(listener);

		progressDialog.show();

		return progressDialog;
	}
	
	public static void dismissProgressDialog() {
		if (null == progressDialog) {
			return;
		}
		if (progressDialog.isShowing()) {
			try {
				progressDialog.dismiss();
				progressDialog = null;
			} catch (Exception e) {
			}

		}

	}
	
	public static void setMessage(String message) {
		if (null != progressDialog && progressDialog.isShowing()
				&& !TextUtils.isEmpty(message)) {
			progressDialog.setMessage(message);
		}
	}

	public static void updateLoadingMessage(String message) {
		if (null != progressDialog && progressDialog.isShowing()
				&& !TextUtils.isEmpty(message)) {
			progressDialog.updateLoadingMessage(message);
		}
	}
	
	public static boolean isShowing() {
		return (progressDialog != null && progressDialog.isShowing());
	}
}
