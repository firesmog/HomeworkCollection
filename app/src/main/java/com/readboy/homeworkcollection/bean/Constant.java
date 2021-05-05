package com.readboy.homeworkcollection.bean;

/**
 * 常量
 */
public interface Constant {
    // request参数
    int REQ_QR_CODE = 11002; // // 打开扫描界面请求码
    int REQ_PERM_CAMERA = 11003; // 打开摄像头
    int REQ_PERM_EXTERNAL_STORAGE = 11004; // 读写文件
    int CHOSE_PIC_EVENT = 1; // 读写文件
    //String DOMAIN = "http://172.16.1.40/";
    //String DOMAIN = "http://192.168.88.215/";
    String DOMAIN = "https://api.ebag-test.readboy.com/exam-omr/";
    String DOMAIN_EBAG = "https://api.ebag-test.readboy.com/login/";
    String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";
}
