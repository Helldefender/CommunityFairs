package com.helldefender.enjoylife.inject.component;

import android.content.Context;

import com.helldefender.enjoylife.data.impl.HttpServiceImpl;
import com.helldefender.enjoylife.data.local.DataBaseHelper;
import com.helldefender.enjoylife.data.local.PreferencesHelper;
import com.helldefender.enjoylife.inject.module.ApplicationModule;
import com.helldefender.enjoylife.inject.qualifier.ApplicationContext;
import com.helldefender.enjoylife.inject.scope.PerApp;

import dagger.Component;

/**
 * Created by Helldefender on 2017/4/7.
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext
    Context getApplicationContext();

    //scope注解是成对出现的
    // （Module类中的多个provide方法中是否可以使用多个Scope注解？）
    // （Component方法中是否可以使用不同的注解？比如：Component类的getObject方法中使用了PerApp，是否还可以使用Singleton）
    DataBaseHelper getDataBaseHelper();

    PreferencesHelper getPreferenceHelper();

    //HttpService
    //注意理解Scope注解的作用（类似于元注解，是用来定义注解的/更好地管理创建的类实例的生命周期），以及Singleton是Scope注解的默认实现
    HttpServiceImpl getHttpServiceImpl();



    //Component会帮助我们注入被Inject注解标注的依赖，并且可以注入多个
    //每次注入都是new了一个新的依赖


    //问题在于一部分依赖实例使用了PerApp,一部分使用了Singleton()


}
