package com.sesxh.commondemo;

import static com.sesxh.commoncore.PermissionUtils.request;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.sesxh.commoncore.AppUtils;
import com.sesxh.commoncore.CleanUtils;
import com.sesxh.commoncore.ConvertUtils;
import com.sesxh.commoncore.DeviceUtils;
import com.sesxh.commoncore.EncodeUtils;
import com.sesxh.commoncore.EncryptUtils;
import com.sesxh.commoncore.NetworkUtils;
import java.io.File;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private Button permissionBtn;
    private Button getDeviceBtn;
    private Button getNetworkBtn;
    private Button appBtn;
    private Button cleanBtn;
    private Button encryptBtn;
    private Button encodeBtn;

    private TextView testshowTv;

    private static String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();

        onClick();

    }


    private void onClick(){

        permissionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //表示用户同意权限
                            Toast.makeText(MainActivity.this, "用户同意使用权限", Toast.LENGTH_SHORT).show();
                        } else {
                            //表示用户不同意权限
                            Toast.makeText(MainActivity.this, "用户拒绝使用权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        getDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer("");

                buffer.append("设备未root").append("\n");
                buffer.append("系统版本号：").append(DeviceUtils.getSDKVersionName()).append("\n");
                buffer.append("系统版本码：").append(DeviceUtils.getSDKVersionCode()).append("\n");
                buffer.append("AndroidID：").append(DeviceUtils.getAndroidID()).append("\n");
                buffer.append("MAC：").append(DeviceUtils.getMacAddress()).append("\n");
                buffer.append("设备厂商：").append(DeviceUtils.getManufacturer()).append("\n");
                buffer.append("设备型号：").append(DeviceUtils.getModel()).append("\n");
                buffer.append("设备ABIs：").append(Arrays.toString(DeviceUtils.getABIs())).append("\n");
                buffer.append("唯一设备ID：").append(DeviceUtils.getUniqueDeviceId()).append("\n");
                buffer.append("设备SN：").append(DeviceUtils.getDeviceSN()).append("\n");

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

        getNetworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(networkTask).start();
            }
        });

        appBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer("");

                if (AppUtils.isAppInstalled("com.sesxh.macdemo")){
                    buffer.append("macdemo APP已安装").append("\n");
                }else {
                    buffer.append("macdemo APP未安装").append("\n");
                }
                buffer.append("App 包名：").append(AppUtils.getAppPackageName()).append("\n");
                buffer.append("App 名称：").append(AppUtils.getAppName()).append("\n");
                buffer.append("App 版本号：").append(AppUtils.getAppVersionName()).append("\n");
                buffer.append("App 版本码：").append(AppUtils.getAppVersionCode()).append("\n");
                buffer.append("App 签名：").append(Arrays.toString(AppUtils.getAppSignatures())).append("\n");
                buffer.append("App 信息：").append(AppUtils.getAppInfo().toString()).append("\n");

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sesxh/macdemo.apk";
                buffer.append("macdemo Apk 信息：").append(AppUtils.getApkInfo(path)).append("\n");

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer("");
                if (CleanUtils.cleanInternalCache()){
                    buffer.append("清除缓存成功").append("\n");
                }else {
                    buffer.append("清除缓存失败").append("\n");
                }
                if (CleanUtils.cleanInternalFiles()){
                    buffer.append("清除文件缓存成功").append("\n");
                }else {
                    buffer.append("清除文件缓存失败").append("\n");
                }
                if (CleanUtils.cleanInternalDbs()){
                    buffer.append("清除数据库信息成功").append("\n");
                }else {
                    buffer.append("清除数据库信息失败").append("\n");
                }
                if (CleanUtils.cleanInternalSp()){
                    buffer.append("清除SP信息成功").append("\n");
                }else {
                    buffer.append("清除SP信息失败").append("\n");
                }
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sesxh/config.txt.apk";
                if (CleanUtils.cleanCustomDir(path)){
                    buffer.append("清除config文件成功").append("\n");
                }else {
                    buffer.append("清除config文件失败").append("\n");
                }

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuffer buffer = new StringBuffer("");
                String str = "这是待加密数据:sesxh2021";

                buffer.append(str).append("\n");
                buffer.append("MD5 加密后：").append(EncryptUtils.encryptMD5ToString(str)).append("\n");
                buffer.append("SHA1 加密后：").append(EncryptUtils.encryptSHA1ToString(str)).append("\n");
                buffer.append("SHA224 加密后：").append(EncryptUtils.encryptSHA224ToString(str)).append("\n");
                buffer.append("SHA256 加密后：").append(EncryptUtils.encryptSHA256ToString(str)).append("\n");

                String secretKey = "123456a89012sdd567fhh901h234";//DES固定格式为128bits，即8bytes。
                String iv = "01234567";//8位向量
                String transformation;//加密规则
                byte[] encrypt, decrypt;//加解密得数据byte
                //RSA
                transformation = "RSA";
                KeyPair keyPair = EncryptUtils.generateRSAKeyPair(1024, transformation);
                byte[] publicKey = EncryptUtils.getPublicKeyByte(keyPair);
                byte[] privateKey = EncryptUtils.getPrivateKeyByte(keyPair);
                encrypt = EncryptUtils.encryptRSA(str.getBytes(), publicKey, 1024, transformation);
                buffer.append("RSA 加密后：").append(ConvertUtils.bytes2String(encrypt)).append("\n");
                decrypt = EncryptUtils.decryptRSA(encrypt, privateKey, 1024, transformation);
                buffer.append("RSA 解密后：").append(ConvertUtils.bytes2String(decrypt)).append("\n");
                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());

            }
        });

        encodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer("");
                String encode;
                String urlstr = "http://www.baudu.com?id=001&name=张三";
                buffer.append("URL 源数据：").append(urlstr).append("\n");
                encode = EncodeUtils.urlEncode(urlstr);
                buffer.append("URL 编码后：").append(encode).append("\n");
                buffer.append("URL 解码后：").append(EncodeUtils.urlDecode(encode)).append("\n");
                String htmlstr = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?><xml><CardNo>30051</CardNo><Name>张三</Name><ReturnCode>0</ReturnCode><ReturnMsg>调用成功</ReturnMsg></xml>";
                buffer.append("Html 源数据：").append(htmlstr).append("\n");
                encode = EncodeUtils.htmlEncode(htmlstr);
                buffer.append("Html 编码后：").append(encode).append("\n");
                buffer.append("Html 解码后：").append(EncodeUtils.htmlDecode(encode)).append("\n");

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

        encodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer("");
                String encode;
                String urlstr = "http://www.baudu.com?id=001&name=张三";
                buffer.append("URL 源数据：").append(urlstr).append("\n");
                encode = EncodeUtils.urlEncode(urlstr);
                buffer.append("URL 编码后：").append(encode).append("\n");
                buffer.append("URL 解码后：").append(EncodeUtils.urlDecode(encode)).append("\n");
                String htmlstr = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?><xml><CardNo>30051</CardNo><Name>张三</Name><ReturnCode>0</ReturnCode><ReturnMsg>调用成功</ReturnMsg></xml>";
                buffer.append("Html 源数据：").append(htmlstr).append("\n");
                encode = EncodeUtils.htmlEncode(htmlstr);
                buffer.append("Html 编码后：").append(encode).append("\n");
                buffer.append("Html 解码后：").append(EncodeUtils.htmlDecode(encode)).append("\n");

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

    }


    private final int NETWORK_TEST = 1; //网络请求
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NETWORK_TEST:
                    Bundle data = msg.getData();
                    String val = data.getString("value");
                    testshowTv.setText(val);
                    break;
            }
        }
    };

    /**
     * 网络操作相关的子线程
     */

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
            StringBuffer buffer = new StringBuffer("");
            if (NetworkUtils.isConnected()){
                buffer.append("设备联网").append("\n");
            }else {
                buffer.append("设备未联网").append("\n");
            }
            if (NetworkUtils.isAvailable()){
                buffer.append("设备公共网络可用").append("\n");
            }else {
                buffer.append("设备公共网络不可用").append("\n");
            }
            buffer.append("ping 172.29.197.1不可用").append("\n");
            if (NetworkUtils.isAvailableByDns("202.102.152.3")){
                buffer.append("DNS 202.102.152.3可用").append("\n");
            }else {
                buffer.append("DNS 202.102.152.3不可用").append("\n");
            }
            if (NetworkUtils.getMobileDataEnabled()){
                buffer.append("移动数据未打开").append("\n");
            }else {
                buffer.append("移动数据打开").append("\n");
            }
            if (NetworkUtils.isMobileData()){
                buffer.append("正在使用移动数据").append("\n");
            }else {
                buffer.append("未再使用移动数据").append("\n");
            }
            if (NetworkUtils.is4G()){
                buffer.append("当前为4G").append("\n");
            }
            if (NetworkUtils.getWifiEnabled()){
                buffer.append("WIFI已开启").append("\n");
            }else {
                buffer.append("WIFI未开启").append("\n");
            }
            if (NetworkUtils.isWifiConnected()){
                buffer.append("WIFI已连接").append("\n");
            }else {
                buffer.append("WIFI未连接").append("\n");
            }
            if (NetworkUtils.isWifiAvailable()){
                buffer.append("WIFI可用").append("\n");
            }else {
                buffer.append("WIFI不可用").append("\n");
            }
            buffer.append("网络运营商：").append(NetworkUtils.getNetworkOperatorName()).append("\n");
            buffer.append("网络类型：").append(NetworkUtils.getNetworkType()).append("\n");
            buffer.append("IP：").append(NetworkUtils.getIPAddress(true)).append("\n");
            buffer.append("百度IP：").append(NetworkUtils.getDomainAddress("www.baidu.com")).append("\n");

            Log.d("lfntest",buffer.toString());

            Message msg = new Message();
            msg.what = NETWORK_TEST;
            Bundle data = new Bundle();
            data.putString("value", buffer.toString());
            msg.setData(data);
            mHandler.sendMessage(msg);
        }
    };


    private void findViewById(){
        permissionBtn = (Button) findViewById(R.id.permission_btn);
        getDeviceBtn = (Button) findViewById(R.id.getDevice_btn);
        getNetworkBtn = (Button) findViewById(R.id.getNetwork_btn);
        appBtn = (Button) findViewById(R.id.app_btn);
        cleanBtn = (Button) findViewById(R.id.clean_btn);
        encryptBtn = (Button) findViewById(R.id.encrypt_btn);
        encodeBtn = (Button) findViewById(R.id.encode_btn);
        testshowTv = (TextView) findViewById(R.id.testshow_tv);

    }

}