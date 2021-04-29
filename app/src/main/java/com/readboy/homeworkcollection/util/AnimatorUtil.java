package com.readboy.homeworkcollection.util;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class AnimatorUtil {
    private static ObjectAnimator animator;

    public static void startUpAndDownAnimator(View view){
        animator = ObjectAnimator.ofFloat(view, "translationY", 0.0f , -800f, 900f , 0f);
        animator.setDuration(2000);//动画时间
        animator.setInterpolator(new LinearInterpolator());//实现反复移动的效果
        animator.setRepeatCount(-1);//设置动画重复次数
        animator.start();//启动动
    }

    public static void endUpAndDownAnimator(){
        if(null == animator || !animator.isRunning()){
            return;
        }
        animator.end();
    }
}
