package com.jgg.games.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
	private StringUtil() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}


	/**
	 * 判断字符串是否为null或全为空格
	 *
	 * @param s 待校验字符串
	 * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
	 */
	public static boolean isSpace(String s) {
		return (s == null || s.trim().length() == 0);
	}

	/**
	 * 判断两字符串是否相等
	 *
	 * @param a 待校验字符串a
	 * @param b 待校验字符串b
	 * @return {@code true}: 相等<br>{@code false}: 不相等
	 */
	public static boolean equals(CharSequence a, CharSequence b) {
		if (a == b) return true;
		int length;
		if (a != null && b != null && (length = a.length()) == b.length()) {
			if (a instanceof String && b instanceof String) {
				return a.equals(b);
			} else {
				for (int i = 0; i < length; i++) {
					if (a.charAt(i) != b.charAt(i)) return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断两字符串忽略大小写是否相等
	 *
	 * @param a 待校验字符串a
	 * @param b 待校验字符串b
	 * @return {@code true}: 相等<br>{@code false}: 不相等
	 */
	public static boolean equalsIgnoreCase(String a, String b) {
		return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
	}

	/**
	 * null转为长度为0的字符串
	 *
	 * @param s 待转字符串
	 * @return s为null转为长度为0字符串，否则不改变
	 */
	public static String null2Length0(String s) {
		return s == null ? "" : s;
	}

	/**
	 * 返回字符串长度
	 *
	 * @param s 字符串
	 * @return null返回0，其他返回自身长度
	 */
	public static int length(CharSequence s) {
		return s == null ? 0 : s.length();
	}

	/**
	 * 首字母大写
	 *
	 * @param s 待转字符串
	 * @return 首字母大写字符串
	 */
	public static String upperFirstLetter(String s) {
		if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
		return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
	}

	/**
	 * 首字母小写
	 *
	 * @param s 待转字符串
	 * @return 首字母小写字符串
	 */
	public static String lowerFirstLetter(String s) {
		if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
		return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
	}

	/**
	 * 反转字符串
	 *
	 * @param s 待反转字符串
	 * @return 反转字符串
	 */
	public static String reverse(String s) {
		int len = length(s);
		if (len <= 1) return s;
		int mid = len >> 1;
		char[] chars = s.toCharArray();
		char c;
		for (int i = 0; i < mid; ++i) {
			c = chars[i];
			chars[i] = chars[len - i - 1];
			chars[len - i - 1] = c;
		}
		return new String(chars);
	}

	/**
	 * 转化为半角字符
	 *
	 * @param s 待转字符串
	 * @return 半角字符串
	 */
	public static String toDBC(String s) {
		if (isEmpty(s)) return s;
		char[] chars = s.toCharArray();
		for (int i = 0, len = chars.length; i < len; i++) {
			if (chars[i] == 12288) {
				chars[i] = ' ';
			} else if (65281 <= chars[i] && chars[i] <= 65374) {
				chars[i] = (char) (chars[i] - 65248);
			} else {
				chars[i] = chars[i];
			}
		}
		return new String(chars);
	}

	/**
	 * 转化为全角字符
	 *
	 * @param s 待转字符串
	 * @return 全角字符串
	 */
	public static String toSBC(String s) {
		if (isEmpty(s)) return s;
		char[] chars = s.toCharArray();
		for (int i = 0, len = chars.length; i < len; i++) {
			if (chars[i] == ' ') {
				chars[i] = (char) 12288;
			} else if (33 <= chars[i] && chars[i] <= 126) {
				chars[i] = (char) (chars[i] + 65248);
			} else {
				chars[i] = chars[i];
			}
		}
		return new String(chars);
	}

	/**
	 * 字符串不为空
	 * @param srcStr
	 * @return
	 */
	public static final boolean isEmptyOrNull(String srcStr)
	{
		if ((srcStr == null) || (srcStr.trim().length() <= 0))
		{
			return true;
		}
		return "null".equals(srcStr);
	}

	public static final boolean isEmptyNotNull(String s)
	{
		return s == null || s.length() == 0 ||s.equals(" ");

	}

	/**
	 * 字符串不为空也不为0
	 * @param srcStr
	 * @return
	 */
	public static final boolean isEmptyOrZero(String srcStr)
	{
		return (isEmptyOrNull(srcStr)) || ("0".equals(srcStr));
	}
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0 || s.equals("null")||s.equals(" ");
	}
	/**
	 * 正则式匹配
	 * @param srcStr
	 * @param pattern
	 * @return
	 */
	public static final boolean isPattern(String srcStr, String pattern)
	{
		//生成Pattern对象并且编译一个简单的正则表达式pattern
		Pattern p = Pattern.compile(pattern);
		//用Pattern类的matcher()方法生成一个Matcher对象
		Matcher m = p.matcher(srcStr);
		//使用find()方法查找第一个匹配的对象
		return m.find();
	}

	/**
	 * 格式化输出
	 * @param num 要转换的数
	 * @param pattern 转换格式 eg："0.00"(两位小数) 
	 * @return
	 */
	public static final String fomatInteger(int num, String pattern)
	{
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(num);
	}

	/**
	 * 将对象装成字符串
	 * @param o
	 * @return
	 */
	public static final String valueOf(Object o)
	{
		return o == null ? "" : o.toString();
	}

	/**
	 * 转成gbk格式
	 * @param szSrcData 被复制字符
	 * @param nOffset 起始位置
	 * @param nLen 长度
	 * @return
	 */
	public static String encodeWithGBK(byte[] szSrcData, int nOffset, int nLen)
	{
		try
		{
			byte[] szTemp = new byte[nLen];
			System.arraycopy(szSrcData, nOffset, szTemp, 0, nLen);
			return new String(szTemp, "GBK");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 截取len-1 长度的字符串 
	 * @param srcStr
	 * @return
	 */
	public static String GetSubString(String srcStr)
	{
		int index = srcStr.indexOf(0);
		if (index != -1)
		{
			return srcStr.substring(0, index);
		}
		return srcStr;
	}

	/**
	 * 截取指定位置的字符串和其他字符串结合
	 * @param srcStr 
	 * @param nLen 位置
	 * @param formatStr 
	 * @return
	 */
	public static String getSubstrByLen(String srcStr, int nLen, String formatStr)
	{
		if (srcStr.length() > nLen)
		{
			return srcStr.substring(0, nLen) + formatStr;
		}
		return srcStr;
	}
	/**
	 * 截取指定开始和结束位置的字符串
	 * @param srcStr
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String getSubstrByBeginEnd(String srcStr, int begin, int end)
	{
		if (srcStr.length() > end && begin < end)
		{
			return srcStr.substring(begin , end);
		}
		return srcStr;
	}

	/**
	 * 匹配国内电话号码
	 * <br>Created 2014-8-25 下午5:01:28
	 *
	 * @param str 源字符串
	 * @return 是国内电话号码返回true，否则返回false
	 * @author huangyx
	 */
	public static boolean isTelephone(String str) {
		if ("".equals(str)) {
			return true;
		}
		Pattern pattern = Pattern.compile("^1(3|5|7|8|4)\\d{9}");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
