package com.readboy.homeworkcollection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.readboy.homeworkcollection.R;
import com.readboy.homeworkcollection.bean.PicPathEvent;
import com.readboy.homeworkcollection.util.AnimatorUtil;
import com.readboy.homeworkcollection.util.log.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PicOcrActivity extends AppCompatActivity {

    private ImageView ivPush;
    private ImageView ivScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_ocr);
        EventBus.getDefault().register(this);
        ivPush = (ImageView)findViewById(R.id.iv_pic_push);
        ivScan = (ImageView)findViewById(R.id.iv_scan_test);
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onGetMessage(PicPathEvent info) {
        if(null == ivPush){
            ivPush = (ImageView)findViewById(R.id.iv_pic_push);
            ivScan = (ImageView)findViewById(R.id.iv_scan_test);
        }
        LogUtils.d("getRootRecordLocation == " + info.getPath());
        Glide.with(PicOcrActivity.this).load(info.getPath()).into(ivPush);
        AnimatorUtil.startUpAndDownAnimator(ivScan);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AnimatorUtil.endUpAndDownAnimator();
        EventBus.getDefault().unregister(this);
    }
}