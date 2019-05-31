<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href=" <%=basePath%>">
<link rel="stylesheet" href="static/plugin/layui-v2.4.5/layui/css/layui.css" />
<link rel="stylesheet" href="static/css/mainPage.css" />
<link rel="stylesheet" href="static/css/layui-select-m.css" />
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label">标题</label>
				<div class="layui-input-block">
					<input type="text" name="blogName" lay-verify="blogName"
						   autocomplete="off" placeholder="请输入标题" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">摘要</label>
				<div class="layui-input-block">
					<input type="text" name="summary" lay-verify="summary"
						   placeholder="请输入摘要" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">分类</label>
				<div class="layui-input-block">
					<select name="subject" lay-filter="subject">
						<option value="">请选择分类</option>
						<c:forEach var="subject" items="${subjects }">
							<option value="${subject.id }">${subject.subjectName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">标签</label>
				<div class="layui-input-block">
					<select name="tag" lay-verify="tag" multiple lay-search>
						<option value="">请选择标签</option>
						<c:forEach var="tag" items="${ }">
							<option value="${tag.id }">${tag.tagName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">文章正文</label>
				<div class="layui-input-block">
					<textarea id="content" name="content" placeholder="请输入内容" class="layui-textarea" lay-verify="content"></textarea>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">封面</label>
				<div class="layui-textarea">
					<div class="layui-upload">
						<button type="button" class="layui-btn layui-btn-normal" id="cover-btn" style="float: left;margin-right: 20px;">
							<i class="layui-icon"></i>上传封面
						</button>
						<div class="layui-upload-list">
							<img class="layui-upload-img" id="cover-img" width="92px" height="92px">
							<p id="cover-text" style="padding: 10px 0 0 132px;"></p>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">附件</label>
				<div class="layui-textarea">
					<div class="layui-upload">
						<button type="button" class="layui-btn layui-btn-normal" id="enclosure-btn">
							<i class="layui-icon"></i>上传附件
						</button>
						<div class="layui-upload-list">
							<table class="layui-table">
								<thead>
								<tr>
									<th>文件名</th>
									<th>大小</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody id="enclosureList"></tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block right">
					<button class="layui-btn" lay-submit="" lay-filter="form">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form>
	</div>
</body>
<script src="static/plugin/jquery-2.1.4.min.js" charset="utf-8"></script>
<script src="static/plugin/layui-v2.4.5/layui/layui.js" charset="utf-8"></script>
<script>
    layui.config({
        base: 'static/plugin/layui-v2.4.5/layui/lay/modules/'
    }).extend({
        kzLayedit: 'kz.layedit',
		kzForm: 'kz.form'
    }).use(['kzLayedit','kzForm'], function(){
        var $=layui.jquery;
        var layedit = layui.kzLayedit;
        var form = layui.kzForm;

        layedit.set({
            tool: [
                'strong', 'italic', 'underline', 'del',
                '|','fontFomatt','fontfamily','fontSize','colorpicker','fontBackColor',
                '|','left', 'center', 'right', '|', 'link','code','image_alt',
                '|','fullScreen','preview'
            ],
            uploadImage: {
                url: 'img/upload/articleContent', //接口url
                type: 'post'
            }
        });
        var ieditor = layedit.build('content');

        //封面示例
        var uploadInst = upload.render({
            elem: '#cover-btn',
            url: 'file/upload/articleCover',
            before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#cover-img').attr('src', result); //图片链接（base64）
                });
            },
            done: function(res){
                if(res.code == 200){
                    return layer.msg('上传成功',{icon:1});
                }else{
                    this.error();
                    return layer.msg('上传失败',{icon:2});
                }
            },
            error:function(){
                //演示失败状态，并实现重传
                var demoText = $('#cover-text');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });


        //多文件列表示例
        var demoListView = $('#enclosureList');
        var uploadListIns = upload.render({
            elem: '#enclosure-btn',
            url: 'file/upload/articleEnclosure',
            accept: 'file',
            multiple: true,
            auto: true,
            choose: function(obj){
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    if(demoListView.find('tr#upload-'+ index).length > 0){
                        return;	//若该文件之前已经被预读，则无须再次预读
                    }
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                        ,'<td></td>'
                        ,'<td>'
                        ,'<a class="layui-btn layui-btn-xs demo-reload layui-hide">重传</a>'
                        ,'<a class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</a>'
                        ,'</td>'
                        ,'</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            },
            done: function(res, index, upload){
                if(res.code == 200){ //上传成功
                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).find('.demo-delete').removeClass('layui-hide'); //清空操作
                    layer.msg('上传成功',{icon:1});
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }else{
                    layer.msg('上传失败',{icon:2});
                    this.error(index, upload);
                }
            },
            error: function(index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });

    });
</script>
</html>