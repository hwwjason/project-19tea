<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/15 0015
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>19tea</title>
    <script>"jquery-2.2.0.js"</script>
    <script src="./jquery-2.2.0.js" type="text/javascript"></script>

</head>
<script type="text/javascript">
    function selectUser() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("test").innerHTML = xmlhttp.responseText;
            }
        }
        xmlhttp.open("POST", "user/showUserById", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("id=1");
    };
    function selectUser2() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("test").innerHTML = xmlhttp.responseText;
            }
        }
        xmlhttp.open("POST", "user/showUserById2", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("id=1");
    };

    $.ajax({
        url : 'http://localhost:8080/user/showUserById',
        type : "POST",
        data:{id:1},
        success : function(r){
        }
    });

</script>
<body>
<p id="test">Hello World!</p>
<button type="button" onclick="selectUser()">onclick test</button>
<button type="button" onclick="selectUser2()">onclick test</button>

</body>
</html>