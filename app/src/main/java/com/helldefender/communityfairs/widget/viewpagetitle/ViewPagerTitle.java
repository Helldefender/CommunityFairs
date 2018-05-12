package com.helldefender.communityfairs.widget.viewpagetitle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.utils.DisplayUtil;

import java.util.ArrayList;


/**
 * Created by Helldefender on 2018/4/8 for Mirroe.
 * Function:
 * Description:
 */
public class ViewPagerTitle extends HorizontalScrollView {

    private String[] titles;

    private ArrayList<TextView> textViews = new ArrayList<>();

    private Context mContext;

    private OnTextViewClick onTextViewClick;

    private DynamicLine dynamicLine;
    private ViewPager viewPager;
    private OnPageChangeListener onPageChangeListener;
    private int margin;
    private LinearLayout.LayoutParams rootContentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private LinearLayout.LayoutParams textContentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    private float defaultTextSize;
    private float selectedTextSize;
    private int defaultTextColor;
    private int selectedTextColor;
    private int backgroundColor;
    private int allTextViewLength;

    private static final int DEF_BACKGROUND_COLOR = Color.parseColor("#1F1F1F");
    private static final int DEF_DEFAULT_TEXT_COLOR = Color.GRAY;
    private static final int DEF_SELECT_TEXT_COLOR = Color.WHITE;
    private static final int DEF_DEFAULT_TEXT_SIZE = 12;
    private static final int DEF_SELECT_TEXT_SIZE = 12;

    public ViewPagerTitle(Context context) {
        this(context, null);
    }

    public ViewPagerTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        getAttribute(attrs);
    }

    private void getAttribute(AttributeSet attributeSet) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.ViewPagerTitle);
        if (typedArray != null) {
            try {
                backgroundColor = typedArray.getColor(R.styleable.ViewPagerTitle_backgroundColor, DEF_BACKGROUND_COLOR);
                defaultTextColor = typedArray.getColor(R.styleable.ViewPagerTitle_defaultTextColor, DEF_DEFAULT_TEXT_COLOR);
                selectedTextColor = typedArray.getColor(R.styleable.ViewPagerTitle_selectedTextColor, DEF_SELECT_TEXT_COLOR);
                defaultTextSize = (int) typedArray.getDimension(R.styleable.ViewPagerTitle_defaultTextSize, DEF_DEFAULT_TEXT_SIZE);
                selectedTextSize = (int) typedArray.getDimension(R.styleable.ViewPagerTitle_selectedTextSize, DEF_SELECT_TEXT_SIZE);

                defaultTextSize = DisplayUtil.px2sp(defaultTextSize);
                selectedTextSize = DisplayUtil.px2sp(selectedTextSize);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                typedArray.recycle();
            }
        }
    }

    public void initData(String[] titles, ViewPager viewPager, int defaultIndex) {
        this.titles = titles;
        this.viewPager = viewPager;
        createDynamicLine();
        createTextViews(titles);

        int fixLeftDis = getFixLeftDis();
        onPageChangeListener = new OnPageChangeListener(getContext(), viewPager, dynamicLine, this, allTextViewLength, margin, fixLeftDis);
        setDefaultIndex(defaultIndex);

        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    private int getFixLeftDis() {
        TextView textView = new TextView(getContext());
        // getDimension是获取某个dimen的值,如果是dp或sp的单位,将其乘以density,如果是px,则不乘;其结果返回的都是px值。
        //setTextSize方法默认传入的单位是sp。
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultTextSize);
        textView.setTextSize(defaultTextSize);
        textView.setText(titles[0]);
        float defaultTextSize = DisplayUtil.getTextViewLength(textView);
        textView.setTextSize(selectedTextSize);
        float selectTextSize = DisplayUtil.getTextViewLength(textView);
        return (int) (selectTextSize - defaultTextSize) / 2;
    }

    public ArrayList<TextView> getTextView() {
        return textViews;
    }


    private void createDynamicLine() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dynamicLine = new DynamicLine(getContext());
        dynamicLine.setLayoutParams(params);
    }


    private void createTextViews(String[] titles) {
        LinearLayout contentLl = new LinearLayout(getContext());
        contentLl.setBackgroundColor(backgroundColor);
        contentLl.setLayoutParams(rootContentParams);
        contentLl.setOrientation(LinearLayout.VERTICAL);
        addView(contentLl);


        LinearLayout textViewLl = new LinearLayout(getContext());
        textViewLl.setLayoutParams(textContentParams);
        textViewLl.setOrientation(LinearLayout.HORIZONTAL);

        margin = getTextViewMargins(titles);

        textViewParams.setMargins(margin, 16, margin, 16);

        for (int i = 0; i < titles.length; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(titles[i]);
            textView.setTextColor(Color.GRAY);
            textView.setTextSize(defaultTextSize);
            textView.setLayoutParams(textViewParams);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setOnClickListener(onClickListener);
            textView.setTag(i);
            textViews.add(textView);
            textViewLl.addView(textView);
        }
        contentLl.addView(textViewLl);
        contentLl.addView(dynamicLine);
    }

    private int getTextViewMargins(String[] titles) {
        int defaultMargins = 30;
        float countLength = 0;
        TextView textView = new TextView(getContext());
        textView.setTextSize(defaultTextSize);
        TextPaint paint = textView.getPaint();


        for (int i = 0; i < titles.length; i++) {
            countLength = countLength + defaultMargins + paint.measureText(titles[i]) + defaultMargins;
        }
        int screenWidth = DisplayUtil.getScreenWidth(getContext());

        if (countLength <= screenWidth) {
            allTextViewLength = screenWidth;
            return (screenWidth / titles.length - (int) paint.measureText(titles[0])) / 2;
        } else {
            allTextViewLength = (int) countLength;
            return defaultMargins;
        }
    }


    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            setCurrentItem((int) v.getTag());
            viewPager.setCurrentItem((int) v.getTag());
            if (onTextViewClick != null) {
                onTextViewClick.textViewClick((TextView) v, (int) v.getTag());
            }

        }
    };

    public void setDefaultIndex(int index) {
        setCurrentItem(index);
    }

    public void setCurrentItem(int index) {
        for (int i = 0; i < textViews.size(); i++) {
            if (i == index) {
                textViews.get(i).setTextColor(selectedTextColor);
                textViews.get(i).setTextSize(selectedTextSize);
            } else {
                textViews.get(i).setTextColor(defaultTextColor);
                textViews.get(i).setTextSize(defaultTextSize);
            }
        }
    }

    public interface OnTextViewClick {
        void textViewClick(TextView textView, int index);
    }

    public void setOnTextViewClickListener(OnTextViewClick onTextViewClick) {
        this.onTextViewClick = onTextViewClick;
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewPager.removeOnPageChangeListener(onPageChangeListener);
    }
}