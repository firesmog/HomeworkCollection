package com.readboy.homeworkcollection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.readboy.homeworkcollection.R;
import com.readboy.homeworkcollection.bean.PicPathEvent;
import com.readboy.homeworkcollection.net.BaseRequest;
import com.readboy.homeworkcollection.net.RequestInterface;
import com.readboy.homeworkcollection.net.RequestResult;
import com.readboy.homeworkcollection.util.AnimatorUtil;
import com.readboy.homeworkcollection.util.BitmapUtil;
import com.readboy.homeworkcollection.util.log.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

        handleSSLHandshake();
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onGetMessage(PicPathEvent info) {
        if(null == ivPush){
            ivPush = (ImageView)findViewById(R.id.iv_pic_push);
            ivScan = (ImageView)findViewById(R.id.iv_scan_test);
        }
        LogUtils.d("getRootRecordLocation == " + info.getPath());
        Glide.with(PicOcrActivity.this).load(info.getPath()).into(ivPush);
        getImgResult(info.getPath());
        AnimatorUtil.startUpAndDownAnimator(ivScan);
    }


    void getImgResult(String filePath) {
        RequestInterface request = BaseRequest.getServer();

        LogUtils.d("getImgResult filePath = " + filePath);
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img1", file.getName(), requestBody);
        request.getImgResult(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RequestResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RequestResult requestResult) {
                        LogUtils.d("RequestResult == " + requestResult.toString());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("RequestResult error == " + e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AnimatorUtil.endUpAndDownAnimator();
        EventBus.getDefault().unregister(this);
    }

    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("TLS");
            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}