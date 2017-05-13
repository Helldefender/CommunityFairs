package com.helldefender.enjoylife.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.delete.server.entity.CommentBean;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.ui.adapter.CommentRvAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/2/22.
 */

public class CommentActivity extends BaseActivity {

    private ImageView backBtn;

    private ImageView avatarImage;

    private TextView commentBtn;

    private RecyclerView mRecyclerView;

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Intent extras) {
        Intent intent = new Intent();
        intent.setClass(context, CommentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public int getEmptyLayoutId() {
        return 0;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        handleStatusBar();

        setRecyclerView();

        setBackBtn();

        setAvatarImage();

        setCommentPost();
    }

    private void setRecyclerView() {
        List<CommentBean> data = new ArrayList<CommentBean>();
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());
        data.add(new CommentBean());

        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CommentRvAdapter(data,this));
    }

    private void setBackBtn() {
        backBtn = (ImageView) findViewById(R.id.comment_back_image);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setAvatarImage() {
        avatarImage = (ImageView) findViewById(R.id.comment_avatar_image);
    }

    private void setCommentPost() {
        commentBtn = (TextView) findViewById(R.id.comment_post_text);

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, CommentPostActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {

                }
        }
    }
}
