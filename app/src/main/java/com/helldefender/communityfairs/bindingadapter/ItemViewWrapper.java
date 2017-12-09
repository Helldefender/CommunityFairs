package com.helldefender.communityfairs.bindingadapter;

/**
 * Created by Helldefender on 2017/12/5 for CommunityFairs.
 * Function:
 * Description:
 */

public class ItemViewWrapper<T> {
    private ItemView itemView;
    private MultiViewType<T> multiViewType;

    public ItemViewWrapper(ItemView itemView) {
        this.itemView = itemView;
        //this.multiViewType
        //this.itemViewType = BaseItemViewType.DEFAULT();
        //this.itemViewType = ItemViewType.DEFAULT;
    }

    public ItemViewWrapper(MultiViewType<T> multiViewType) {
        this.itemView = new ItemView();
        this.multiViewType = multiViewType;
    }

    public static <T> ItemViewWrapper<T> of(ItemView itemView) {
        return new ItemViewWrapper<>(itemView);
    }

    public static <T> ItemViewWrapper<T> of(MultiViewType<T> multiViewType) {
        return new ItemViewWrapper<T>(multiViewType);
    }

    public int itemViewType(int position) {
        return multiViewType.getItemViewType(position);
    }

    public int getLayoutRes(int viewType) {
        return multiViewType.getLayoutRes(viewType);
    }

    public void getLayoutRes(int position, T viewModel) {
        multiViewType.getLayoutRes(itemView, position, viewModel);
    }

    public int bindingVariable() {
        return this.itemView.getBindingVariable();
    }

    public int layoutRes() {
        return itemView.getLayoutRes();
    }

//    public static class BaseItemViewType<T> implements ItemViewType<T> {
//
//        private static ItemViewType itemViewType = new BaseItemViewType();
//
//        public static <T> ItemViewType<T> DEFAULT() {
//            return itemViewType;
//        }
//    }
}
