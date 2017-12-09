package com.helldefender.communityfairs.bindingadapter.command;


import java.util.concurrent.Callable;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Helldefender on 2017/11/30 for CommunityFairs.
 * Function:
 * Description:
 */

public class ReplyCommand<T> {
    private Action action; //Action0
    private Consumer<T> consumer; //Action1

    private Callable<Boolean> callable; //Fun0

    public ReplyCommand(Action action) {
        this.action = action;
    }

    public ReplyCommand(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public ReplyCommand(Action action, Callable<Boolean> callable) {
        this.action = action;
        this.callable = callable;
    }

    public ReplyCommand(Consumer<T> consumer, Callable<Boolean> callable) {
        this.consumer = consumer;
        this.callable = callable;
    }

    public void execute() {
        if (action != null && canExecute()) {
            try {
                action.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(T parameter) {
        if (consumer != null && canExecute()) {
            try {
                consumer.accept(parameter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean canExecute() {
        if (callable == null) {
            return true;
        }

        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
