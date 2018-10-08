package com.sckj.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


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

    /**
     * 获取本机所有IP
     */
    public static String getEn0() {
        String en0 = "";
        Enumeration netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                if(!"en0".equals(ni.getName()) && !"Ethernet0".equals(ni.getName()) ){
                    continue;
                }
                Enumeration nii = ni.getInetAddresses();
                while (nii.hasMoreElements()) {
                    ip = (InetAddress) nii.nextElement();
                    if (ip.getHostAddress().indexOf(":") == -1) {
                        en0 = ip.getHostAddress();
                        break;
                    }
                }
                break;
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return en0;
    }
}
