package com.sckj.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class HttpUtils {
    public static String getRequestUrl2(HttpServletRequest request ){
        String url = "https://" + request.getServerName() //服务器地址
                + ":"
                + request.getServerPort()           //端口号
                + request.getContextPath()      //项目名称
                + request.getServletPath()      //请求页面或其他地址
                + "?" + (request.getQueryString()); //参数
        return url;
    }

    public static String getServerUrl(){
        try{
            InetAddress address = InetAddress.getLocalHost();//获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
            String hostAddress = address.getHostAddress();//192.168.0.121
            return hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
