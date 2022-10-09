package com.sesxh.commoncore;

/**
 * @author LYH
 * @date 2021/6/22
 * @time 10:09
 * @desc
 **/
public class CommonUtils {

    /*
     * 判断是否为支付宝付款吗
     * */
    public static boolean isAliPayCode(String qrcode){
        if (qrcode.length()>=16){
            String head = qrcode.substring(0,2);
            try{
                int headint = Integer.parseInt(head);
                if (headint>=25){//支付宝付款码  25-30开头， 长度16-24位。
                    return true;
                }
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    /*
     * 判断是否为电子医保凭证
     * */
    public static boolean isECCode(String qrcode){
        if (qrcode.length()==28){
            return true;
        }
        return false;
    }

}
