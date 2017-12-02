package com.helldefender.enjoylife.modules.main.messsage;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.app.BaseAdapter;
import com.helldefender.enjoylife.app.BaseViewHolder;
import com.helldefender.enjoylife.widget.BadgeView;

import java.util.List;

/**
 * Created by Helldefender on 2017/5/6.
 */

public class MessageRVAdapter extends BaseAdapter {

    public MessageRVAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    public void onBind(BaseViewHolder holder, Object o, int position, int viewType) {
        ImageView imageView = holder.getView(R.id.img_item_message_avatar);
        BadgeView badgeView = new BadgeView(context);
        badgeView.setTargetView(imageView);
        badgeView.setBadgeCount(2);
        //badgeView.setBackground(12, Color.parseColor("#F4733D"));
        //badgeView.setBackgroundResource(R.drawable.bg_message_text);
        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.BOTTOM);
    }
}
