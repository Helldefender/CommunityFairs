package com.helldefender.enjoylife.data.server;

import android.content.Context;

import com.helldefender.enjoylife.data.server.progress.ProgressCancelListener;
import com.helldefender.enjoylife.data.server.progress.ProgressDialogHandler;

import rx.Subscriber;

/**
 * Created by Helldefender on 2016/12/10.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private Context mContext;

    private boolean isShowWaitDialog;

    private ProgressDialogHandler progressDialogHandler;

    public ProgressSubscriber(){
        progressDialogHandler = new ProgressDialogHandler(this, true, mContext);
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setShowWaitDialog(boolean showWaitDialog) {
        isShowWaitDialog = showWaitDialog;
    }

    private void showProgressDialog() {
        if (progressDialogHandler != null) {
            progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (progressDialogHandler != null) {
            progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
        }
    }

    @Override
    public void onStart() {
        if (isShowWaitDialog) {
            showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        if (isShowWaitDialog) {
            dismissProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {
//        if (isShowWaitDialog) {
//           dismissProgressDialog();
//        }
//        Throwable throwable = e;
//        if (throwable.getCause() != null) {
//            e = throwable;
//            throwable = throwable.getCause();
//        }
//        if (e instanceof HttpException) {
//            HttpException httpException = (HttpException) e;
//            if (TextUtils.isEmpty(httpException.getMessage())) {
//
//            } else {
//                String errorMsg = httpException.getMessage();
//                if (TextUtils.isEmpty(errorMsg)) {
//                    Toast.makeText(mContext, "°ÑÎÒÐ´µ½×ÊÔ´ÎÄ¼þÖÐÈ¥", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
//                }
//            }
//        } else if (e instanceof ApiException) {//·þÎñÆ÷·µ»ØµÄ´íÎó
//            onResultError((ApiException) e);
//        } else if (e instanceof JsonParseException
//                || e instanceof JSONException
//                || e instanceof ParseException) {//½âÎöÒì³£
//            Toast.makeText(mContext, "°ÑÎÒÐ´µ½×ÊÔ´ÎÄ¼þÖÐÈ¥", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof UnknownHostException) {
//            Toast.makeText(mContext, "°ÑÎÒÐ´µ½×ÊÔ´ÎÄ¼þÖÐÈ¥", Toast.LENGTH_SHORT).show();
//        } else if (e instanceof SocketTimeoutException) {
//            Toast.makeText(mContext, "°ÑÎÒÐ´µ½×ÊÔ´ÎÄ¼þÖÐÈ¥", Toast.LENGTH_SHORT).show();
//        } else {
//            e.printStackTrace();
//            Toast.makeText(mContext, "°ÑÎÒÐ´µ½×ÊÔ´ÎÄ¼þÖÐÈ¥", Toast.LENGTH_SHORT).show();
//        }
    }

    protected void onResultError(HttpException httpException) {
        switch (httpException.getCode()) {
//            case :
//            break;
            default:
                String msg = httpException.getMessage();
//                if (TextUtils.isEmpty(msg)) {
//                    Toast.makeText(mContext, "°ÑÎÒÐ´µ½×ÊÔ´ÎÄ¼þÖÐÈ¥", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
//                }
        }
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

}
