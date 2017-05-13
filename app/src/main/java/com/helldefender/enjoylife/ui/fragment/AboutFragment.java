package com.helldefender.enjoylife.ui.fragment;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.ui.fragment.base.BaseFragment;

/**
 * Created by Helldefender on 2017/2/24.
 */

public class AboutFragment extends BaseFragment {

    private ImageView backBtn;

    private TextView aboutText;

    private TextView titleText;

//    @Override
//    protected void handleView(View view, Bundle savedInstanceState) {
//        setTopPanel(view);
//
//        setAboutText(view);
//    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    private void setTopPanel(View view) {
        titleText = (TextView) view.findViewById(R.id.top_panel_text);
        backBtn = (ImageView) view.findViewById(R.id.top_panel_back_image);
        titleText.setText("关于");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
    }

    private void setAboutText(View view) {
        aboutText = (TextView) view.findViewById(R.id.about_text);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.about));
        //StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(getHoldingActivity(),R.style.about_text_style), 0, 2, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        aboutText.setText(spannableStringBuilder);

    }

    public static AboutFragment getInstance() {
        return new AboutFragment();
    }
}
