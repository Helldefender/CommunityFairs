package com.helldefender.enjoylife.modules.register;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.helldefender.enjoylife.R;
import com.helldefender.enjoylife.model.Province;
import com.helldefender.enjoylife.ui.activity.MainActivity;
import com.helldefender.enjoylife.ui.activity.base.BaseActivity;
import com.helldefender.enjoylife.utils.JsonDataUtil;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Helldefender on 2017/6/8.
 */

public class EditInfoActivity extends BaseActivity {

    @BindView(R.id.tv_edit_info_title)
    TextView mTitleTv;

    @BindView(R.id.tv_edit_info_save)
    TextView mSaveTv;

    @BindView(R.id.et_edit_info_name)
    EditText mNameEdit;

    @BindView(R.id.tv_edit_info_sex)
    TextView mSexTv;

    @BindView(R.id.ll_edit_info_sex)
    LinearLayout mSexLayout;

    @BindView(R.id.tv_edit_info_identity)
    TextView mIdentityTv;

    @BindView(R.id.ll_edit_info_identity)
    LinearLayout mIndentityLayout;

    @BindView(R.id.et_edit_info_lab)
    EditText mLabEt;

    @BindView(R.id.tv_edit_info_area)
    TextView mAreaTv;

    @BindView(R.id.ll_edit_info_area)
    LinearLayout mAreaLayout;

    @BindView(R.id.et_edit_info_detail)
    EditText mDetailEt;

    @BindView(R.id.ll_edit_info_pi_info)
    LinearLayout piInfoLayout;

    public static final String USER_NAME = "userName";

    private boolean isPI = false;


    private ArrayList<String> mSexOptionsList = new ArrayList<>();

    private ArrayList<String> mIdentityOptionsList = new ArrayList<>();

    private ArrayList<Province> mProvinceOptionsList = new ArrayList<>();

    private ArrayList<ArrayList<String>> mCityOptionsList = new ArrayList<>();

    private ArrayList<ArrayList<ArrayList<String>>> mAreaOptionsList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_edit_info;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.ll_edit_info_sex:
                OptionsPickerView sexOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String textSelect = mSexOptionsList.get(options1);
                        mSexTv.setText(textSelect);
                    }
                })
                        .setTitleText("选择性别")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                sexOptions.setPicker(mSexOptionsList);
                sexOptions.show();
                break;
            case R.id.ll_edit_info_identity:

                OptionsPickerView identityOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String textSelect = mIdentityOptionsList.get(options1);
                        mIdentityTv.setText(textSelect);
                        if (textSelect.equals("PI")) {
                            piInfoLayout.setVisibility(View.VISIBLE);
                            isPI = true;
                        }
                    }
                })
                        .setTitleText("选择身份")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                identityOptions.setPicker(mIdentityOptionsList);
                identityOptions.show();
                break;
            case R.id.ll_edit_info_area:
                OptionsPickerView areaOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        String textSelect = mProvinceOptionsList.get(options1).getPickerViewText()
                                + mCityOptionsList.get(options1).get(option2)
                                + mAreaOptionsList.get(options1).get(option2).get(options3);
                        mAreaTv.setText(textSelect);
                    }
                })
                        .setTitleText("选择区域")
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.parseColor("#ECECEC"))//设置分割线的颜色
                        .setBgColor(Color.parseColor("#FFFFFF"))
                        .setTitleBgColor(Color.parseColor("#F5F6F7"))
                        .setTitleColor(Color.parseColor("#666666"))
                        .setCancelColor(Color.parseColor("#999999"))
                        .setSubmitColor(Color.parseColor("#999999"))
                        .setTextColorCenter(Color.parseColor("#999999"))
                        .build();
                areaOptions.setPicker(mProvinceOptionsList, mCityOptionsList, mAreaOptionsList);
                areaOptions.show();
                break;
            case R.id.tv_edit_info_save:
                if (isPI) {
                    if (mNameEdit.getText().length() > 0 && mSexTv.getText().length() > 0 && mIdentityTv.getText().length() > 0 && mLabEt.getText().length() > 0 && mAreaTv.getText().length() > 0 && mDetailEt.getText().length() > 0) {
                        startActivity(MainActivity.class);
                        finish();
                    } else {
                        showToast("请完善全部信息");
                    }
                } else {
                    if (mNameEdit.getText().length() > 0 && mSexTv.getText().length() > 0 && mIdentityTv.getText().length() > 0) {
                        startActivity(MainActivity.class);
                        finish();
                    } else {
                        showToast("请完善全部信息");
                    }
                }
                break;
        }
    }

    @Override
    protected void setWidgetListener() {
        mSexLayout.setOnClickListener(this);
        mIndentityLayout.setOnClickListener(this);
        mAreaLayout.setOnClickListener(this);
        mSaveTv.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


        mSexOptionsList.add("男");
        mSexOptionsList.add("女");

        mIdentityOptionsList.add("PI");
        mIdentityOptionsList.add("管理员");
        mIdentityOptionsList.add("普通用户");

        initJsonDate();
    }

    private void initJsonDate() {
        String JsonData = JsonDataUtil.getJson(this, "province.json");

        ArrayList<Province> province = parseData(JsonData);

        mProvinceOptionsList = province;

        for (int i = 0; i < province.size(); i++) {
            ArrayList<String> CityList = new ArrayList<>();
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();

            for (int c = 0; c < province.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = province.get(i).getCityList().get(c).getName();
                CityList.add(CityName);

                ArrayList<String> City_AreaList = new ArrayList<>();

                if (province.get(i).getCityList().get(c).getArea() == null
                        || province.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < province.get(i).getCityList().get(c).getArea().size(); d++) {
                        String AreaName = province.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);
                    }
                }
                Province_AreaList.add(City_AreaList);
            }

            mCityOptionsList.add(CityList);

            mAreaOptionsList.add(Province_AreaList);
        }
    }

    public ArrayList<Province> parseData(String result) {
        ArrayList<Province> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                Province entity = gson.fromJson(data.optJSONObject(i).toString(), Province.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
