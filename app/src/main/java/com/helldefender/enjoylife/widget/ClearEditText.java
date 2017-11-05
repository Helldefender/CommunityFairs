package com.helldefender.enjoylife.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;

import com.helldefender.enjoylife.R;

/**
 * Created by Helldefender on 2017/2/21.
 */

public class ClearEditText extends EditText implements OnTouchListener, TextWatcher {

	Drawable deleteImage = getResources().getDrawable(R.drawable.delete_orange);

	Drawable icon;

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ClearEditText(Context context) {
		super(context);
		init();
	}

	private void init() {
		ClearEditText.this.setOnTouchListener(this);
		ClearEditText.this.addTextChangedListener(this);
		deleteImage.setBounds(0, 0, deleteImage.getIntrinsicWidth(), deleteImage.getIntrinsicHeight());
		manageClearButton();
	}

	public void setIconResource(int id) {
		icon = getResources().getDrawable(id);
		icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
		manageClearButton();
	}

    public void setDeleteImage(int id) {
        deleteImage = getResources().getDrawable(id);
        deleteImage.setBounds(0, 0, deleteImage.getIntrinsicWidth(), deleteImage.getIntrinsicHeight());
        manageClearButton();
    }

	void manageClearButton() {
		if (this.getText().toString().equals(""))
			removeClearButton();
		else
			addClearButton();
	}

	void removeClearButton() {
		this.setCompoundDrawables(this.icon, this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
	}

	void addClearButton() {
		this.setCompoundDrawables(this.icon, this.getCompoundDrawables()[1], deleteImage,
				this.getCompoundDrawables()[3]);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ClearEditText et = ClearEditText.this;

		if (et.getCompoundDrawables()[2] == null)
			return false;
		if (event.getAction() != MotionEvent.ACTION_UP)
			return false;
		if (event.getX() > et.getWidth() - et.getPaddingRight() - deleteImage.getIntrinsicWidth()) {
			et.setText("");
			ClearEditText.this.removeClearButton();
		}
		return false;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		ClearEditText.this.manageClearButton();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void afterTextChanged(Editable s) {

	}

}
