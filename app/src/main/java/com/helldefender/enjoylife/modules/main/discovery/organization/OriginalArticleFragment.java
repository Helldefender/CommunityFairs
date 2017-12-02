package com.helldefender.enjoylife.modules.main.discovery.organization;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.app.BaseFragment;

/**
 * Created by Helldefender on 2017/2/28.
 */

public class OriginalArticleFragment extends BaseFragment {

    private static String ORIGINAL_ARTICLE_FRAGMENT_FLAG="OriginalArticleFragment";

    private ImageView topPanelImage;

    private TextView topPanelText;

    private String type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (String) getArguments().getSerializable(ORIGINAL_ARTICLE_FRAGMENT_FLAG);
        }
    }

//    @Override
//    protected void handleView(View view, Bundle savedInstanceState) {
//        setTopPanel(view);
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
        return R.layout.fragment_original_article;
    }

    @Override
    protected int getEmptyLayoutId() {
        return 0;
    }

    private void setTopPanel(View view) {
        topPanelImage = (ImageView) view.findViewById(R.id.top_panel_back_image);
        topPanelText = (TextView) view.findViewById(R.id.top_panel_text);
        topPanelText.setText(type);

        topPanelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
    }

    public static OriginalArticleFragment getInstance(String type) {
        OriginalArticleFragment originalArticleFragment = new OriginalArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ORIGINAL_ARTICLE_FRAGMENT_FLAG, type);
        originalArticleFragment.setArguments(bundle);
        return originalArticleFragment;
    }
}
