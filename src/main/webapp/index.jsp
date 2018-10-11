<%@ page language="java"  contentType="text/html; ISO-8859-1" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.sckj.GJP.example.*"  %>
<%
    String path = request.getContextPath(); System.out.println("path"+path);
    String scheme = request.getScheme();    System.out.println("scheme"+scheme);
    String ServerName = request.getServerName();  System.out.println("ServerNames"+ServerName);
    String serverPort = request.getServerPort()+""; System.out.println("serverPort"+serverPort);
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; System.out.println("basePath"+basePath);

    Config con = new Config();
    String result=con.GetAuthCodeUrl(basePath + "GetToken.jsp?");                     //调用定义的方法
    String orderPath = basePath + "UploadesEshopSaleorders.jsp";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>


<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
<table>
    <tr>
        <td>
            获取授权
        </td>
        <td>
            <a href="<%=result%>">获取授权(<%=result%>)</a>
        </td>
    </tr>
    <tr>
        <td>
            <a href="<%=orderPath%>">上载订单</a>
        </td>
    </tr>

    <tr>
        <td>scheme</td>
        <td>
            <%=scheme%>
        </td>
    </tr>
    <tr>
        <td>ServerName</td>
        <td>
            <%=ServerName%>
        </td>
    </tr>
    <tr>
        <td>serverPort</td>
        <td>
            <%=serverPort%>
        </td>
    </tr>
    <tr>
        <td>path</td>
        <td>
            <img src="https://127.0.0.1/bak/image/showImg?imgFile=1.jpg">
        </td>
    </tr>


</table>
</body>
</html>
