package com.helldefender.communityfairs.model.data.server.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.helldefender.communityfairs.R;


/**
 * Created by Helldefender on 2016/12/10.
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 0;
    public static final int DISMISS_PROGRESS_DIALOG = 1;

    private boolean cancelable;
    private Context mContext;
    private ProgressCancelListener progressCancelListener;
    private ProgressDialog progressDialog;

    public ProgressDialogHandler(ProgressCancelListener progressCancelListener, boolean cancelable, Context mContext) {
        this.progressCancelListener = progressCancelListener;
        this.cancelable = cancelable;
        this.mContext = mContext;
    }

    public void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("loading");
            progressDialog.setIcon(R.mipmap.ic_launcher);
            progressDialog.setCancelable(cancelable);
            if (cancelable) {
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressCancelListener.onCancelProgress();
                    }
                });
            }
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
