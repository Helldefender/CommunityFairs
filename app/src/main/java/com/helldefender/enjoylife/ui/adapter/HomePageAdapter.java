package com.helldefender.enjoylife.ui.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.modules.ConstellationAdapter;
import com.helldefender.enjoylife.modules.GirdDropDownAdapter;
import com.helldefender.enjoylife.modules.ListDropDownAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseAdapter;
import com.helldefender.enjoylife.ui.adapter.base.BaseViewHolder;
import com.helldefender.enjoylife.ui.adapter.base.MultiViewType;
import com.helldefender.enjoylife.widget.BannerView;
import com.helldefender.enjoylife.widget.FilterMenu;
import com.helldefender.enjoylife.widget.recyclerview.RefreshHeaderLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/4/11.
 */

public class HomePageAdapter extends BaseAdapter<String> {

    private static final String TAG = HomePageAdapter.class.getName();

    public static final int TYPE_BANNER = 1;

    public static final int TYPE_FILTER = 2;

    public static final int TYPE_CONTENT = 4;

    private String headers[] = {"学校", "发布时间", "活动类型"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"重庆邮电大学", "重庆大学", "西南大学", "重庆交通大学"};
    private String sexs[] = {"发布时间", "活动热度"};
    private String constellations[] = {"不限","学术科技与创新创业类活动","文化艺术与身心发展类活动","社会实践与志愿服务活动","社团活动与技能培训类活动"
            ,"思想政治与道德素养类活动"};

    private int constellationPosition = 0;


    public HomePageAdapter(Context context, MultiViewType<String> multiViewType, List<String> data, RefreshHeaderLayout refreshHeaderLayout, FrameLayout loadMoreFrameLayout) {
        super(context, multiViewType, data, refreshHeaderLayout, loadMoreFrameLayout);
    }

    @Override
    public void onBind(BaseViewHolder holder, String s, int position, int viewType) {
        if (viewType == TYPE_BANNER) {
            bindBanner(holder);
        } else if (viewType == TYPE_FILTER) {
            bindFilterMenu(holder);
        } else {
            bindContent(holder);
        }
    }

    private void bindBanner(BaseViewHolder holder) {
        BannerView bannerView = holder.getView(R.id.carouselView);
        List<String> data = new ArrayList<String>();
        data.add("测试");
        data.add("测试");
        data.add("测试");
        data.add("测试");
        bannerView.setImageUrls(data, data);
        bannerView.initUI();
        bannerView.startPlay();
    }

    private void bindFilterMenu(BaseViewHolder baseViewHolder) {
        final FilterMenu mFilterMenu = baseViewHolder.getView(R.id.filter_homepage_menu);

        final ListView cityView = new ListView(context);
        cityAdapter = new GirdDropDownAdapter(context, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

//        //init age menu
//        final ListView ageView = new ListView(context);
//        ageView.setDividerHeight(0);
//        ageAdapter = new ListDropDownAdapter(context, Arrays.asList(ages));
//        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(context);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(context, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation
        final View constellationView = LayoutInflater.from(context).inflate(R.layout.custom_layout, null);
        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        constellationAdapter = new ConstellationAdapter(context, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilterMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
                mFilterMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(sexView);
        popupViews.add(constellationView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mFilterMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mFilterMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mFilterMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mFilterMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init context view
        TextView contentView = new TextView(context);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        //init dropdownview
        mFilterMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    private void bindContent(BaseViewHolder holder) {

    }
}
