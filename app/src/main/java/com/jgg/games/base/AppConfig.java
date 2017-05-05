package com.jgg.games.base;


import com.jgg.games.utils.LogUtils;

public class AppConfig {

	public static String NET_ADDRESS = "http://114.247.138.100/api/graphql/";// 正式地址
	public  static String TIEBA_URL = "http://coswx.99.com/api/webroot/discuz.php/";// 贴吧的接口
	public static String TIEBA_MD5KEY = "2Thf0noU%R&BdD!@aWbzEgqKCyF8OvZl"; // 签名
	public static String PRE_QINIU = "http://qiniu.51beautylife.com/"; // 七牛

	public static String WEIXIN_APP_ID = "wx325a3ed0c8977c46";
	public static String WEIXIN_APP_SECRET = "5dbedd4944adc17759e15c19c14b8255";

    public static String QQ_APP_ID = "1106123000";
	public static String QQ_APP_SECRET = "OxFzIx8HcnRlg8Co";

    public static String WEIBO_APP_ID = "2155703984";
    public static String WEIBO_APP_SECRET = "21295962aafde5371e42b08dec8af493";
    public static String WEIBO_APP_URL = "http://sns.whalecloud.com";


    public static void setDeBugModel(boolean debug) {
		LogUtils.init(debug,false,'e',"gameproject");
		if (debug) {
            NET_ADDRESS = "http://192.168.244.65:4000/api/graphql/";
			TIEBA_MD5KEY = "4ba41d4bdb797f807f8029cc38213f0a";
            TIEBA_URL = "http://coswx.99.com/api/webroot/discuz.php/";
		} else {
            NET_ADDRESS = "http://114.247.138.100/api/graphql/";
			TIEBA_MD5KEY = "2Thf0noU%R&BdD!@aWbzEgqKCyF8OvZl";
            TIEBA_URL = "http://114.247.138.100/discuz.php/";

		}
	}


}
