package com.helldefender.communityfairs.modules.community.detail.comment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.app.BaseActivity;
import com.helldefender.communityfairs.app.ViewModelFactory;
import com.helldefender.communityfairs.databinding.ActivityCommentBinding;

/**
 * Created by Helldefender on 2017/2/24.
 */

public class CommentActivity extends BaseActivity<ActivityCommentBinding, CommentViewModel> {

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isEnable = binding.etComment.getText().length() > 0;
            updateReleaseBtn(binding.btnComment, isEnable);
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected int getVariableId() {
        return BR.viewModel;
    }

    @Override
    protected CommentViewModel getViewModel() {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(CommentViewModel.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpStatusBar(android.R.color.transparent);

        setUpContentEdit();

        setUpReleaseBtn();

        setPlaceHolderLayout();
    }

    private void setUpContentEdit() {
        binding.etComment.addTextChangedListener(textWatcher);
    }

    private void setUpReleaseBtn() {
        binding.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setPlaceHolderLayout() {
//        placeHolderLayout = (LinearLayout) findViewById(R.id.post_comment_place_holder_layout);
//
//        placeHolderLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

//    private void initCommentPostListener() {
//        commentPostListener = new SubscriberOnNextListener<CommentBean>() {
//            @Override
//            public void onNext(CommentBean commentBean) {
//                //判断是否成功，刷新数据（for result）
//            }
//        };
//    }

    private void updateReleaseBtn(TextView releaseBtn, boolean isEnable) {
        releaseBtn.setEnabled(isEnable);
        //releaseBtn.setTextColor(isEnable ? getResources().getColor(R.color.colorAccentBlue) : getResources().getColor(R.color.colorPrimaryDark));
    }
}
