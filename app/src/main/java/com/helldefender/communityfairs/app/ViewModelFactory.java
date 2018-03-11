package com.helldefender.communityfairs.app;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.helldefender.communityfairs.modules.community.detail.NewsDetailViewModel;
import com.helldefender.communityfairs.modules.community.detail.apply.ApplyViewModel;
import com.helldefender.communityfairs.modules.community.detail.comment.CommentViewModel;
import com.helldefender.communityfairs.modules.community.publish.PublishViewModel;
import com.helldefender.communityfairs.modules.main.MainViewModel;
import com.helldefender.communityfairs.modules.main.discovery.DiscoveryViewModel;
import com.helldefender.communityfairs.modules.main.discovery.organization.OrganizationTabViewModel;
import com.helldefender.communityfairs.modules.main.discovery.organization.OrganizationViewModel;
import com.helldefender.communityfairs.modules.main.homepage.HomePageViewModel;
import com.helldefender.communityfairs.modules.main.homepage.NewsItemViewModel;
import com.helldefender.communityfairs.modules.main.messsage.MessageViewModel;
import com.helldefender.communityfairs.modules.main.user.UserViewModel;
import com.helldefender.communityfairs.modules.user.login.LoginViewModel;
import com.helldefender.communityfairs.modules.user.register.RegisterViewModel;

/**
 * Created by Helldefender on 2017/12/9 for CommunityFairs.
 * Function:
 * Description:
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application application;

    private ViewModelFactory() {
        application = App.getInstance();
    }

    private static final class SingletonHolder {
        private static final ViewModelFactory instance = new ViewModelFactory();
    }

    public static ViewModelFactory getInstance() {
        return SingletonHolder.instance;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(application);
        } else if (modelClass.isAssignableFrom(HomePageViewModel.class)) {
            return (T) new HomePageViewModel(application);
        } else if (modelClass.isAssignableFrom(NewsItemViewModel.class)) {
            return (T) new NewsItemViewModel(application);
        } else if (modelClass.isAssignableFrom(DiscoveryViewModel.class)) {
            return (T) new DiscoveryViewModel(application);
        } else if (modelClass.isAssignableFrom(MessageViewModel.class)) {
            return (T) new MessageViewModel(application);
        } else if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(application);
        } else if (modelClass.isAssignableFrom(NewsDetailViewModel.class)) {
            return (T) new NewsDetailViewModel(application);
        } else if (modelClass.isAssignableFrom(OrganizationViewModel.class)) {
            return (T) new OrganizationViewModel(application);
        } else if (modelClass.isAssignableFrom(OrganizationTabViewModel.class)) {
            return (T) new OrganizationTabViewModel(application);
        } else if (modelClass.isAssignableFrom(CommentViewModel.class)) {
            return (T) new CommentViewModel(application);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(application);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(application);
        } else if (modelClass.isAssignableFrom(PublishViewModel.class)) {
            return (T) new PublishViewModel(application);
        } else if (modelClass.isAssignableFrom(ApplyViewModel.class)) {
            return (T) new ApplyViewModel(application);
        } else {
            throw new IllegalArgumentException("向你抛出了一个异常--->未知的ViewModel类型:" + modelClass);
        }
    }
}
