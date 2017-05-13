package com.helldefender.enjoylife.listener;

/**
 * Created by Helldefender on 2017/4/24.
 */

public interface RefreshStatusListener {
    void onStart(int headerHeight);

    void onMove(boolean finished, boolean automatic, int moved);

    void onRefresh();

    void onRelease();

    void onComplete();

    void onReset();
}
