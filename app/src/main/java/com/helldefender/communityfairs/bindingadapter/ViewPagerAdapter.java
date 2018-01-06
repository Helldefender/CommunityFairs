package com.helldefender.communityfairs.bindingadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.helldefender.communityfairs.BR;
import com.helldefender.communityfairs.R;
import com.helldefender.communityfairs.model.entity.Image;
import com.helldefender.communityfairs.modules.main.homepage.BannerItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helldefender on 2017/12/10 for CommunityFairs.
 * Function:
 * Description:
 */

public class ViewPagerAdapter extends PagerAdapter {

    private LayoutInflater mInflater;

    private List<BannerItemViewModel> mImages;

    private boolean mNotify;

    public ViewPagerAdapter() {
        mImages = new ArrayList<>();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mImages.size() == 0) {
            return null;
        }

        position %= mImages.size();

        if (position < 0) {
            position = mImages.size() + position; //用户左滑，position出现负值，对负值进行处理，使其落在正确的区间内
        }

        Image image = mImages.get(position).getImage();

        if (mInflater == null) {
            mInflater = LayoutInflater.from(container.getContext());
        }

        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, R.layout.view_banner_item, container, false);
        binding.setVariable(BR.viewModel, mImages.get(position));
        binding.executePendingBindings();


//            ViewParent viewParent = imageView.getParent();  //如果view在之前已经添加到了一个父组件，则必须先remove,封否则会抛出异常
//
//            if (viewParent != null) {
//                ViewGroup viewGroup = (ViewGroup) viewParent;
//                viewGroup.removeView(imageView);
//            }

        binding.getRoot().setTag(image);
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        //return Integer.MAX_VALUE;
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void notifyDataSetChanged() {
        mNotify = true;
        super.notifyDataSetChanged();
        mNotify = false;
    }

    @Override
    public int getItemPosition(Object object) {
        if (mNotify) {
            Image tag = (Image) ((View) object).getTag();

            for (int i = 0; i < mImages.size(); i++) {
                Image image = mImages.get(i).getImage();

                if (TextUtils.equals(image.title, tag.title) && TextUtils.equals(image.imageUrl, tag.imageUrl)) {
                    return super.getItemPosition(object);
                }
            }
            return PagerAdapter.POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    public void setList(List<BannerItemViewModel> images) {
        mImages.clear();
        mImages.addAll(images);
        notifyDataSetChanged();
    }
}
