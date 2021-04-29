package com.readboy.homeworkcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.readboy.homeworkcollection.activity.PictureActivity;
import com.readboy.homeworkcollection.activity.StartQrCodeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btExit;
    private ImageView ivHead;
    private TextView tvUser;
    private ImageView ivCollect;
    private ImageView ivPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btExit = (Button)findViewById(R.id.bt_exit);
        btExit.setText(R.string.string_log);
        ivHead = (ImageView)findViewById(R.id.iv_head);
        ivPush = (ImageView)findViewById(R.id.iv_push_homework);
        ivCollect = (ImageView)findViewById(R.id.iv_collect_homework);
        tvUser = (TextView)findViewById(R.id.tv_user_name);
        tvUser.setOnClickListener(this);
        ivHead.setOnClickListener(this);
        btExit.setOnClickListener(this);
        ivCollect.setOnClickListener(this);
        ivPush.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_exit:
                startActivity(new Intent(MainActivity.this,StartQrCodeActivity.class));
                break;
            case R.id.iv_head:
            case R.id.tv_user_name:
            case R.id.iv_collect_homework:
                startActivity(new Intent(MainActivity.this, PictureActivity.class));
                break;
        }

    }
}