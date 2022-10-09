package com.sesxh.commoncore;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.sesxh.commoncore.log.Logger;
import com.sesxh.commoncore.log.XLogUtils;

import java.util.Locale;

public class SpeechUtils {
    private static TextToSpeech textToSpeech;
    /*
    * 语音初始化
    * */
    public static void initSpeech(final Context context){
        if (textToSpeech == null){
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = textToSpeech.setLanguage(Locale.CHINA);
                        if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                                && result != TextToSpeech.LANG_AVAILABLE){
                            Logger.e("SpeechUtils","TTS暂时不支持这种语音的朗读！");
                            close();
                        }
                    }
                }
            });
        }
    }
    /*
     * 语音调用
     * 修改不需要调用初始化可直接调用
     * */
    public static void toSpeech(final Context context, final String tts){
        if (textToSpeech == null){
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        int result = textToSpeech.setLanguage(Locale.CHINA);
                        if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                                && result != TextToSpeech.LANG_AVAILABLE){
                            XLogUtils.e("SpeechUtils","TTS暂时不支持这种语音的朗读！");
                            close();
                        }else {
                            textToSpeech.speak(tts, TextToSpeech.QUEUE_ADD, null);
                        }
                    }
                }
            });
        }else {
            textToSpeech.speak(tts, TextToSpeech.QUEUE_ADD, null);
        }
    }

    public static void stop(){
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    public static void close(){
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
    }
}
