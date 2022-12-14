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
                            //????????????????????????
                            Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        } else {
                            //???????????????????????????
                            Toast.makeText(MainActivity.this, "????????????????????????", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        getDeviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer("");

                buffer.append("?????????root").append("\n");
                buffer.append("??????????????????").append(DeviceUtils.getSDKVersionName()).append("\n");
                buffer.append("??????????????????").append(DeviceUtils.getSDKVersionCode()).append("\n");
                buffer.append("AndroidID???").append(DeviceUtils.getAndroidID()).append("\n");
                buffer.append("MAC???").append(DeviceUtils.getMacAddress()).append("\n");
                buffer.append("???????????????").append(DeviceUtils.getManufacturer()).append("\n");
                buffer.append("???????????????").append(DeviceUtils.getModel()).append("\n");
                buffer.append("??????ABIs???").append(Arrays.toString(DeviceUtils.getABIs())).append("\n");
                buffer.append("????????????ID???").append(DeviceUtils.getUniqueDeviceId()).append("\n");
                buffer.append("??????SN???").append(DeviceUtils.getDeviceSN()).append("\n");

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
                    buffer.append("macdemo APP?????????").append("\n");
                }else {
                    buffer.append("macdemo APP?????????").append("\n");
                }
                buffer.append("App ?????????").append(AppUtils.getAppPackageName()).append("\n");
                buffer.append("App ?????????").append(AppUtils.getAppName()).append("\n");
                buffer.append("App ????????????").append(AppUtils.getAppVersionName()).append("\n");
                buffer.append("App ????????????").append(AppUtils.getAppVersionCode()).append("\n");
                buffer.append("App ?????????").append(Arrays.toString(AppUtils.getAppSignatures())).append("\n");
                buffer.append("App ?????????").append(AppUtils.getAppInfo().toString()).append("\n");

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sesxh/macdemo.apk";
                buffer.append("macdemo Apk ?????????").append(AppUtils.getApkInfo(path)).append("\n");

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer("");
                if (CleanUtils.cleanInternalCache()){
                    buffer.append("??????????????????").append("\n");
                }else {
                    buffer.append("??????????????????").append("\n");
                }
                if (CleanUtils.cleanInternalFiles()){
                    buffer.append("????????????????????????").append("\n");
                }else {
                    buffer.append("????????????????????????").append("\n");
                }
                if (CleanUtils.cleanInternalDbs()){
                    buffer.append("???????????????????????????").append("\n");
                }else {
                    buffer.append("???????????????????????????").append("\n");
                }
                if (CleanUtils.cleanInternalSp()){
                    buffer.append("??????SP????????????").append("\n");
                }else {
                    buffer.append("??????SP????????????").append("\n");
                }
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sesxh/config.txt.apk";
                if (CleanUtils.cleanCustomDir(path)){
                    buffer.append("??????config????????????").append("\n");
                }else {
                    buffer.append("??????config????????????").append("\n");
                }

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuffer buffer = new StringBuffer("");
                String str = "?????????????????????:sesxh2021";

                buffer.append(str).append("\n");
                buffer.append("MD5 ????????????").append(EncryptUtils.encryptMD5ToString(str)).append("\n");
                buffer.append("SHA1 ????????????").append(EncryptUtils.encryptSHA1ToString(str)).append("\n");
                buffer.append("SHA224 ????????????").append(EncryptUtils.encryptSHA224ToString(str)).append("\n");
                buffer.append("SHA256 ????????????").append(EncryptUtils.encryptSHA256ToString(str)).append("\n");

                String secretKey = "123456a89012sdd567fhh901h234";//DES???????????????128bits??????8bytes???
                String iv = "01234567";//8?????????
                String transformation;//????????????
                byte[] encrypt, decrypt;//??????????????????byte
                //RSA
                transformation = "RSA";
                KeyPair keyPair = EncryptUtils.generateRSAKeyPair(1024, transformation);
                byte[] publicKey = EncryptUtils.getPublicKeyByte(keyPair);
                byte[] privateKey = EncryptUtils.getPrivateKeyByte(keyPair);
                encrypt = EncryptUtils.encryptRSA(str.getBytes(), publicKey, 1024, transformation);
                buffer.append("RSA ????????????").append(ConvertUtils.bytes2String(encrypt)).append("\n");
                decrypt = EncryptUtils.decryptRSA(encrypt, privateKey, 1024, transformation);
                buffer.append("RSA ????????????").append(ConvertUtils.bytes2String(decrypt)).append("\n");
                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());

            }
        });

        encodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer("");
                String encode;
                String urlstr = "http://www.baudu.com?id=001&name=??????";
                buffer.append("URL ????????????").append(urlstr).append("\n");
                encode = EncodeUtils.urlEncode(urlstr);
                buffer.append("URL ????????????").append(encode).append("\n");
                buffer.append("URL ????????????").append(EncodeUtils.urlDecode(encode)).append("\n");
                String htmlstr = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?><xml><CardNo>30051</CardNo><Name>??????</Name><ReturnCode>0</ReturnCode><ReturnMsg>????????????</ReturnMsg></xml>";
                buffer.append("Html ????????????").append(htmlstr).append("\n");
                encode = EncodeUtils.htmlEncode(htmlstr);
                buffer.append("Html ????????????").append(encode).append("\n");
                buffer.append("Html ????????????").append(EncodeUtils.htmlDecode(encode)).append("\n");

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

        encodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer buffer = new StringBuffer("");
                String encode;
                String urlstr = "http://www.baudu.com?id=001&name=??????";
                buffer.append("URL ????????????").append(urlstr).append("\n");
                encode = EncodeUtils.urlEncode(urlstr);
                buffer.append("URL ????????????").append(encode).append("\n");
                buffer.append("URL ????????????").append(EncodeUtils.urlDecode(encode)).append("\n");
                String htmlstr = "<?xml version='1.0' encoding='UTF-8' standalone='no' ?><xml><CardNo>30051</CardNo><Name>??????</Name><ReturnCode>0</ReturnCode><ReturnMsg>????????????</ReturnMsg></xml>";
                buffer.append("Html ????????????").append(htmlstr).append("\n");
                encode = EncodeUtils.htmlEncode(htmlstr);
                buffer.append("Html ????????????").append(encode).append("\n");
                buffer.append("Html ????????????").append(EncodeUtils.htmlDecode(encode)).append("\n");

                Log.d("lfntest",buffer.toString());
                testshowTv.setText(buffer.toString());
            }
        });

    }


    private final int NETWORK_TEST = 1; //????????????
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
     * ??????????????????????????????
     */

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // ??????????????? http request.????????????????????????
            StringBuffer buffer = new StringBuffer("");
            if (NetworkUtils.isConnected()){
                buffer.append("????????????").append("\n");
            }else {
                buffer.append("???????????????").append("\n");
            }
            if (NetworkUtils.isAvailable()){
                buffer.append("????????????????????????").append("\n");
            }else {
                buffer.append("???????????????????????????").append("\n");
            }
            buffer.append("ping 172.29.197.1?????????").append("\n");
            if (NetworkUtils.isAvailableByDns("202.102.152.3")){
                buffer.append("DNS 202.102.152.3??????").append("\n");
            }else {
                buffer.append("DNS 202.102.152.3?????????").append("\n");
            }
            if (NetworkUtils.getMobileDataEnabled()){
                buffer.append("?????????????????????").append("\n");
            }else {
                buffer.append("??????????????????").append("\n");
            }
            if (NetworkUtils.isMobileData()){
                buffer.append("????????????????????????").append("\n");
            }else {
                buffer.append("????????????????????????").append("\n");
            }
            if (NetworkUtils.is4G()){
                buffer.append("?????????4G").append("\n");
            }
            if (NetworkUtils.getWifiEnabled()){
                buffer.append("WIFI?????????").append("\n");
            }else {
                buffer.append("WIFI?????????").append("\n");
            }
            if (NetworkUtils.isWifiConnected()){
                buffer.append("WIFI?????????").append("\n");
            }else {
                buffer.append("WIFI?????????").append("\n");
            }
            if (NetworkUtils.isWifiAvailable()){
                buffer.append("WIFI??????").append("\n");
            }else {
                buffer.append("WIFI?????????").append("\n");
            }
            buffer.append("??????????????????").append(NetworkUtils.getNetworkOperatorName()).append("\n");
            buffer.append("???????????????").append(NetworkUtils.getNetworkType()).append("\n");
            buffer.append("IP???").append(NetworkUtils.getIPAddress(true)).append("\n");
            buffer.append("??????IP???").append(NetworkUtils.getDomainAddress("www.baidu.com")).append("\n");

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