package com.readboy.homeworkcollection.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.readboy.homeworkcollection.R;
import com.readboy.homeworkcollection.bean.PicBean;
import com.readboy.homeworkcollection.bean.PicPathEvent;
import com.readboy.homeworkcollection.util.BitmapUtil;
import com.readboy.homeworkcollection.util.Constant;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btSure;
    private PicPathEvent picEvent = new PicPathEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        btSure = (Button)findViewById(R.id.bt_sure);
        recyclerView = (RecyclerView)findViewById(R.id.rv_show_pic);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        File sd = getExternalFilesDir("");
        List<PicBean> beanPath = new ArrayList<>();
        String mPath = sd.getPath() + "/MyDataPic";
        List<String> paths = BitmapUtil.getFilesAllName(mPath);
        for (String path : paths) {
            PicBean beanP = new PicBean();
            if(!TextUtils.isEmpty(path)){
                beanP.setPath(path);
                beanP.setChoose(false);
                beanPath.add(beanP);
            }
        }
        picEvent.setEventType(Constant.CHOSE_PIC_EVENT);
        picEvent.setPath(beanPath.get(0).getPath());
        recyclerView.setAdapter(new GridAdapter(beanPath));

        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(picEvent);
                startActivity(new Intent(PictureActivity.this,PicOcrActivity.class));
                finish();
            }
        });
    }



    class GridAdapter extends RecyclerView.Adapter<GridAdapter.Holder> {
        List<PicBean> beans = new ArrayList<>();

        public GridAdapter(List<PicBean> beans) {
            this.beans = beans;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(PictureActivity.this).inflate(R.layout.item_choose_pic, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Glide.with(PictureActivity.this).load(beans.get(position).getPath()).into(holder.ivPic);
            if(beans.get(position).isChoose()){
                holder.ivChoose.setVisibility(View.VISIBLE);
            }else {
                holder.ivChoose.setVisibility(View.GONE);
            }

            holder.ivPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (PicBean picBean : beans) {
                        picBean.setChoose(false);
                    }
                    beans.get(position).setChoose(true);
                    picEvent.setPath(beans.get(position).getPath());
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return beans.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            private ImageView ivPic;
            private ImageView ivChoose;
            public Holder(View itemView) {
                super(itemView);
                ivChoose = (ImageView) itemView.findViewById(R.id.iv_choose);
                ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            }
        }
    }
}