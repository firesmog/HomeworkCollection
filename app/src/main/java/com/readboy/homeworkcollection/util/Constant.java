package com.readboy.homeworkcollection.util;

/**
 * 常量
 */
public interface Constant {
    // request参数
    int REQ_QR_CODE = 11002; // // 打开扫描界面请求码
    int REQ_PERM_CAMERA = 11003; // 打开摄像头
    int REQ_PERM_EXTERNAL_STORAGE = 11004; // 读写文件
    int CHOSE_PIC_EVENT = 1; // 读写文件

    String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";
}
