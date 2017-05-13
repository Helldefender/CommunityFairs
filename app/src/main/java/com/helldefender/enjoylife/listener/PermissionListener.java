package com.helldefender.enjoylife.listener;

import java.util.List;

/**
 * Created by Helldefender on 2017/4/7.
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);
}
