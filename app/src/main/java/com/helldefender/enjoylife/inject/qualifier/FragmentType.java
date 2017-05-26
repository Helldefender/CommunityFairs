package com.helldefender.enjoylife.inject.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Helldefender on 2017/5/21.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentType {
    String value() default "BaseFragment";
}
