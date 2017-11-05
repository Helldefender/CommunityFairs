package com.helldefender.enjoylife.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Event;
import com.helldefender.enjoylife.modules.LabFinanceActivity;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Helldefender on 2017/5/11.
 */

public class PublishRVAdapter extends BaseAdapter<Event> {

    public PublishRVAdapter(Context context, int layoutResId, List<Event> data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, Event event, int position, int viewType) {
        ImageView imageView1 = holder.getView(R.id.item_publish_img_left);
        LinearLayout imageView2 = holder.getView(R.id.ll_publish_detail);
        TextView textView = holder.getView(R.id.item_publish_title);
        TextView textView1 = holder.getView(R.id.item_publish_status);
        TextView textView2 = holder.getView(R.id.item_publish_num);

        if (event.getStatus() == 1) {
            imageView1.setImageResource(R.drawable.indicator_1);
            imageView2.setBackgroundResource(R.drawable.publish_1);
            textView.setText(event.getName());
            textView1.setText("审核中");
        } else if (event.getStatus() == 2) {
            imageView1.setImageResource(R.drawable.indicator_2);
            imageView2.setBackgroundResource(R.drawable.publish_2);
            textView.setText(event.getName());
            textView1.setText("报名中");
            textView2.setText("报名人数：11人");
        } else if (event.getStatus() == 3) {
            imageView1.setImageResource(R.drawable.indicator_3);
            imageView2.setBackgroundResource(R.drawable.publish_3);
            textView.setText(event.getName());
            textView1.setText("已结束");
            textView2.setText("查看详情");
        }

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, LabFinanceActivity.class));
            }
        });
    }
}
