/**   
*    
* @版权所有: 网龙天晴程序部应用软件开发组
* @作  者  : 陈希
* @创建时间: 2014-4-16 下午2:22:37 
* @文件描述:  
* @version  
*/
package com.jgg.games.model.entity;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * 服务器返回状态
 * <br>Created 2014-5-4 下午2:16:03
 * @version
 * @see 	 
 */
public class ServerStatus implements Serializable{
	private int status;
	private int code;

	private String msg=null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



	public ArrayList getList() {

		return new ArrayList();
	}
	public ArrayList getList(Class t) {

		return new ArrayList();
	}

	public boolean isSuccess(){
		return "0".equals(code);
	}
}
