<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="js/webuploader/webuploader.css">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/webuploader/webuploader.min.js"></script>
    <script src="./jquery-2.2.0.js" type="text/javascript"></script>

    <script type="text/javascript">
        //图片上传demo
        $(function() {
            var $ = jQuery, $list = $('#fileList'),
                // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,
                // 缩略图大小
                thumbnailWidth = 100 * ratio, thumbnailHeight = 100 * ratio,
                // 初始化Web Uploader
                uploader = WebUploader.create({
                    // 自动上传。
                    auto : true,
                    // swf文件路径
                    swf : 'js/webuploader/Uploader.swf',
                    // 文件接收服务端。
                    server : 'WebUpLoaderPicture',
                    // 选择文件的按钮。可选。
                    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                    pick : '#filePicker',
                    // 只允许选择文件，可选。
                    accept : {
                        title : 'Images',
                        extensions : 'gif,jpg,jpeg,bmp,png',
                        mimeTypes : 'image/*'
                    },
                    fileNumLimit : 5, //限制上传个数
                    fileSingleSizeLimit : 2048000
                    //限制单个上传图片的大小
                });

            // 当有文件添加进来的时候
            uploader
                .on(
                    'fileQueued',
                    function(file) {
                        var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
                            + '<img>'
                            + '<div class="info">'
                            + file.name + '</div>'+ '</div>'),
                            $img = $li.find('img');
                        $list.append($li);

                        // 创建缩略图
                        uploader.makeThumb(file, function(error, src) {
                            if (error) {
                                $img.replaceWith('<span>不能预览</span>');
                                return;
                            }

                            $img.attr('src', src);
                        }, thumbnailWidth, thumbnailHeight);
                    });

            // 文件上传过程中创建进度条实时显示。
            uploader.on('uploadProgress', function(file, percentage) {
                var $li = $('#' + file.id), $percent = $li.find('.progress span');

                // 避免重复创建
                if (!$percent.length) {
                    $percent = $('<p class="progress"><span></span></p>').appendTo(
                        $li).find('span');
                }

                $percent.css('width', percentage * 100 + '%');
            });

            // 文件上传成功，给item添加成功class, 用样式标记上传成功。
            uploader.on('uploadSuccess', function(file) {
                $('#' + file.id).addClass('upload-state-done');
            });

            // 文件上传失败，现实上传出错。
            uploader.on('uploadError', function(file) {
                var $li = $('#' + file.id), $error = $li.find('div.error');

                // 避免重复创建
                if (!$error.length) {
                    $error = $('<div class="error"></div>').appendTo($li);
                }

                $error.text('上传失败');
            });


            // 完成上传完了，成功或者失败，先删除进度条。
            uploader.on('uploadComplete', function(file) {
                $('#' + file.id).find('.progress').remove();
            });
        });

    </script>
</head>
<body>
<!--dom结构部分-->
<div id="uploader-demo" class="wu-example">
    <!--用来存放item-->
    <div id="fileList" class="uploader-list"></div>
    <div id="filePicker">选择图片</div>
</div>
<!--    <div id="uploader" class="wu-example"> -->
<!--用来存放文件信息-->
<!--  <div id="thelist" class="uploader-list"></div>
 <div class="btns">
     <div id="picker">选择文件</div>
     <button id="ctlBtn" class="btn btn-default">开始上传</button>
 </div></div>     -->

</body>
</html>