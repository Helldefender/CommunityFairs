package com.helldefender.enjoylife.config;

/**
 * Created by ：AceMurder
 * Created on ：2017/5/5
 * Created for : U-Lab.
 * Enjoy it !!!
 */

//后台接口地址常量
public interface Contract {
    String SERVER_URL = "http://119.29.223.130:8080";

    String SEND_MESSAGE = "/ulab_user/users/message";

    String SEND_EMAIL = "/ulab_user/users/email";

    String REGISTER = "/ulab_user/users";

    String GET_ITEMS = "/ulab_user/users/items";

    String GET_USER_INFO = "/ulab_user/users/info";

    String GET_ORDERS = "/ulab_user/users/orders";

    String LOGIN = "/ulab_user/users/session";

    String RESET_PASSWORD = "/ulab_user/users/password";

    String TODAY_WORK = "/ulab_user/users/todayWork";

    String LAB = "ulab_lab/labs";

    String LAB_USER = "/ulab_lab/lab/users";

    String LAB_ITEMS = "/ulab_lab/lab/items";

    String LAB_FRIEND = "/ulab_lab/lab/friendlyLabs";

    String ITEM_PURCHASES = "/ulab_item/item/application";

    String ITEM = "/ulab_item/item";

    String ITEM_BORROW = "/ulab_item/item/request";

    String USER_PROJECT = "/ulab_user/users/project";

    String USER_EXPERIMENT = "/ulab_user/users/experiment";

}
