package com.helldefender.communityfairs.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Helldefender on 2018/4/24 for Mirroe.
 * Function:
 * Description:
 */
public class NiceSpinnerAdapter<T> extends BaseAdapter {
    private final SimpleSpinnerTextFormatter simpleSpinnerTextFormatter;

    private List<T> items;

    private int textColor;
    private int backgroundSelector;
    private int selectedIndex;

    public NiceSpinnerAdapter(Context context, List<T> items, int textColor, int backgroundSelector,
                              SimpleSpinnerTextFormatter spinnerTextFormatter) {
        this.simpleSpinnerTextFormatter = spinnerTextFormatter;
        this.backgroundSelector = backgroundSelector;
        this.textColor = textColor;
        this.items = items;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        TextView textView;

        if (convertView == null) {
            //convertView = View.inflate(context, R.layout.layout_spinner_item, null);
            //textView = convertView.findViewById(R.id.tv_spinner_item);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                //textView.setBackground(ContextCompat.getDrawable(context, backgroundSelector));
            }
            //convertView.setTag(new ViewHolder(textView));
        } else {
            textView = ((ViewHolder) convertView.getTag()).textView;
        }

        //textView.setText(simpleSpinnerTextFormatter.format(getItem(position).toString()));
        //textView.setTextColor(textColor);
        return convertView;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    void setSelectedIndex(int index) {
        selectedIndex = index;
    }

    public T getItemInDataset(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size() - 1;
    }

    @Override
    public T getItem(int position) {
        if (position >= selectedIndex) {
            return items.get(position + 1);
        } else {
            return items.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        TextView textView;

        ViewHolder(TextView textView) {
            this.textView = textView;
        }
    }
}
