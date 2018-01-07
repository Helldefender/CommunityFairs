package com.helldefender.communityfairs.bindingadapter;

/**
 * Created by Helldefender on 2017/12/4 for CommunityFairs.
 * Function:
 * Description:
 */

public class ItemView {

    private int bindingVariable;

    private int layoutRes;

    public ItemView() {

    }

    public static ItemView of(int bindingVariable, int layoutRes) {
        return new ItemView().setBindingVariable(bindingVariable).setLayoutRes(layoutRes);
    }

    public ItemView set(int bindingVariable, int layoutRes) {
        this.bindingVariable = bindingVariable;
        this.layoutRes = layoutRes;
        return this;
    }

    public ItemView setBindingVariable(int bindingVariable) {
        this.bindingVariable = bindingVariable;
        return this;
    }

    public ItemView setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
        return this;
    }

    public int getBindingVariable() {
        return bindingVariable;
    }

    public int getLayoutRes() {
        return layoutRes;
    }
}
