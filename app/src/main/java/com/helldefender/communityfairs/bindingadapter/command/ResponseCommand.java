//package com.helldefender.communityfairs.bindingadapter.command;
//
//import java.util.function.Function;
//
///**
// * Created by Helldefender on 2017/12/3 for CommunityFairs.
// * Function:
// * Description:
// */
//
//public class ResponseCommand<T,R> {
//
//    private function<R> execute0;
//    private Function<T, R> execute1;
//
//    private Function<Boolean> canExecute0;
//
//    /**
//     * like {@link ReplyCommand},but ResponseCommand can return result when command has executed!
//     * @param execute function to execute when event occur.
//     */
//    public ResponseCommand(Func0<R> execute) {
//        this.execute0 = execute;
//    }
//
//
//    public ResponseCommand(Func1<T, R> execute) {
//        this.execute1 = execute;
//    }
//
//
//    public ResponseCommand(Func0<R> execute, Func0<Boolean> canExecute0) {
//        this.execute0 = execute;
//        this.canExecute0 = canExecute0;
//    }
//
//
//    public ResponseCommand(Func1<T, R> execute, Func0<Boolean> canExecute0) {
//        this.execute1 = execute;
//        this.canExecute0 = canExecute0;
//    }
//
//
//    public R execute() {
//        if (execute0 != null && canExecute0()) {
//            return execute0.call();
//        }
//        return null;
//    }
//
//    private boolean canExecute0() {
//        if (canExecute0 == null) {
//            return true;
//        }
//        return canExecute0.call();
//    }
//
//
//    public R execute(T parameter) {
//        if (execute1 != null && canExecute0()) {
//            return execute1.call(parameter);
//        }
//        return null;
//    }
//}
