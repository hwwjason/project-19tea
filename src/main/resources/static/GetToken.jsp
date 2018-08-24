<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="Example.Lib.*"  %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String QueryParam = "null";
if(request.getQueryString()!=null) 
{   
   QueryParam = request.getQueryString();           
} 

GetToken token = new GetToken();
String  code = token.GetAuthCode(QueryParam);                    
String pjson = token.DoGetToken(QueryParam);
  	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'GetToken.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>

     <table>  
          <tr>  		
            <td>  
               <H2>code:<%=code%></H2>
            </td>  
        </tr>  
         <tr>  		
            <td>  
               <H2><%=pjson%></H2>
            </td>  
        </tr>  
  
        </table>    
        
    
  </body>
</html>
