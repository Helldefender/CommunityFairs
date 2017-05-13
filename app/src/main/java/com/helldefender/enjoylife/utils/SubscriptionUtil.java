package com.helldefender.enjoylife.utils;

import rx.Subscription;

/**
 * Created by Helldefender on 2017/5/2.
 */

public class SubscriptionUtil {
    public static void cancelSubscription(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
