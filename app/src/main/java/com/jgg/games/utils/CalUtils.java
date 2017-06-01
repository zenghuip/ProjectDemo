package com.jgg.games.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalUtils {

    /**
     * 函数名称 : mul 功能描述 : 两数相乘 参数及返回值说明：
     *
     * @param v1
     * @param v2
     * @return 修改记录： 日期：2013-4-19 上午10:02:21 修改人： 描述 ：
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static long mul(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.multiply(b2).longValue();
    }

    public static int intMul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).intValue();
    }


    /**
     * 函数名称 : add 功能描述 : 两数相加 参数及返回值说明：
     *
     * @param v1
     * @param v2
     * @return 修改记录： 日期：2013-4-18 下午6:04:44 修改人： 描述 ：
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 函数名称 : sub 功能描述 : 两数相减 参数及返回值说明：
     *
     * @param v1
     * @param v2
     * @return 修改记录： 日期：2013-4-18 下午6:04:00 修改人： 描述 ：
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    public static long sub(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).longValue();
    }
    /**
     * 函数名称 : round 功能描述 : 四舍五入 参数及返回值说明：
     *
     * @param v
     * @param scale
     * @return 修改记录： 日期：2013-4-18 下午6:04:54 修改人： 描述 ：
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");// 保留小数点两位有效数字
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 函数名称 : div 功能描述 : 相除 参数及返回值说明：
     *
     * @param v1
     * @param scale
     * @return 修改记录： 日期：2013-4-18 下午6:04:54 修改人： 描述 ：
     */
    public static double div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String percent(String v1, String v2) {
        if (StringUtil.isEmpty(v2) || v2.equals("0")) {
            return "";
        }
        int result = intMul(div(v1, v2, 2), 100);
        return String.valueOf(result + "%");

    }

    public static double divs(double v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double divs(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static float div(int v1, int v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }


    /**
     * 函数名称 : formatDouble 功能描述 : double 保留小数点后2位 参数及返回值说明：
     *
     * @param d
     * @return 修改记录： 日期：2013-10-28 上午9:57:57 修改人： 描述 ：
     */
    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (!StringUtil.isEmpty(s)) {
            if (s.indexOf(".") > 0) {
                s = s.replaceAll("0+?$", "");//去掉多余的0
                s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
        }
        return s;
    }


    /**
     * int 转 16进制
     *
     * @param integer
     * @return
     */
    public static int encodeHex(int integer) {
        int result = 0;
        StringBuffer buf = new StringBuffer(2);
        if ((integer & 0xff) < 0x10) {
            buf.append("0");
        }
        buf.append(Long.toString(integer & 0xff, 16));
        return Integer.parseInt(buf.toString(), 16);
    }


    /**
     * 金钱加法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String add(String v1, String v2) {
        if (StringUtil.isEmpty(v1) && !StringUtil.isEmpty(v2)) {
            return v2;
        } else if (!StringUtil.isEmpty(v1) && StringUtil.isEmpty(v2)) {
            return v1;
        } else if (!StringUtil.isEmpty(v1) && !StringUtil.isEmpty(v2)) {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);

            return b1.add(b2).toString();
        } else {
            return "0";
        }

    }

    /**
     * 金钱乘法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String multiply(String v1, String v2) {
        if (StringUtil.isEmpty(v1)){
            v1="0";
        }
        if (StringUtil.isEmpty(v2)){
            v2="0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).toString();
    }

    public static int intMultiply(String v1, String v2) {
        if (StringUtil.isEmpty(v1)){
            v1="0";
        }
        if (StringUtil.isEmpty(v2)){
            v2="0";
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).intValue();
    }

    public static String multiply(String v1, String v2, int scale) {
        LogUtils.e("---------------:" + v1);
        LogUtils.e("---------------:" + v2);
        if (!StringUtil.isEmpty(v1) && !StringUtil.isEmpty(v2)) {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return b1.multiply(b2).setScale(scale).toString();
        } else {
            return "0";
        }
    }

    /**
     * 乘法
     *
     * @param v1    乘数
     * @param v2    被乘数
     * @param scale 小数点保留位数
     * @return
     */
    public static String multiplyWithScale(String v1, String v2, int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal result = b1.multiply(b2);
        result = result.setScale(scale);
        return result.toString();
    }

    /**
     * 金钱除法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String divide(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.divide(b2).toString();
    }

    /**
     * 如果除不断，产生无限循环小数，那么就会抛出异常，解决方法就是对小数点后的位数做限制
     *
     * @param v1
     * @param v2 小数模式 ，DOWN，表示直接不做四舍五入，参考{@link RoundingMode}
     * @return
     */
    public static String divideWithRoundingDown(String v1, String v2) {
        return divideWithRoundingMode(v1, v2, RoundingMode.DOWN);
    }

    /**
     * 选择小数部分处理方式
     */
    public static String divideWithRoundingMode(String v1, String v2,
                                                RoundingMode roundingMode) {
        return divideWithRoundingModeAndScale(v1, v2, RoundingMode.DOWN, 0);
    }

    /**
     * 选择小数点后部分处理方式，以及保留几位小数
     *
     * @param v1           除数
     * @param v2           被除数
     * @param roundingMode 小数处理模式
     * @param scale        保留几位
     * @return
     */
    public static String divideWithRoundingModeAndScale(String v1, String v2,
                                                        RoundingMode roundingMode, int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, roundingMode).toString();
    }

    /**
     * 金钱减法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String subtract(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.subtract(b2).toString();
    }


    public static String changeToString(int data){
        String result = "00";
        if (data<10){
            result = "0"+data;
        }else {
            result = String.valueOf(data);
        }
        return result;
    }

    public static String changeToString(String data){
        String result = "00";
        int res = 0;
        if (!StringUtil.isEmpty(data)){
            res = Integer.parseInt(data);
            if (res<10){
                result = "0"+res;
            }else {
                result = String.valueOf(res);
            }
        }

        return result;
    }

    // 计算百分比
    public static double getDivResult(int winTime,int total) {
        return CalUtils.divs(winTime,total,2);
    }

    public static String getRate(int winTime,int total) {
        if (total <= 0){
            return "0";
        }
        return subZeroAndDot(String.valueOf(mul(getDivResult(winTime,total), 100))) + "%";
    }

    // 计算百分比
    public static String getRate(double curData) {
        return subZeroAndDot(String.valueOf(mul(curData, 100))) + "%";
    }

    // 大于10000转为中文
    public static String digChangeStr(int num){
        String result = String.valueOf(num);
        if(num >= 10000 && num<1000000){
            double n = divs(num,10000,2);
            result = n +"万";
        }else if (num >= 1000000){
            int n = (int) divs(num,10000,2);
            result = n +"万";
        }
        return result;
    }
}
