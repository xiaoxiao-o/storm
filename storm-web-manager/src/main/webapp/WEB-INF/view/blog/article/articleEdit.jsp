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
<base href="<%=basePath%>">
<jsp:include page="../../head.jsp"/>
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="id" id="id" value="${article.id}">
			<div class="layui-form-item">
				<label class="layui-form-label">标题</label>
				<div class="layui-input-block">
					<input type="text" name="title" lay-verify="title" value="${article.title}"
						   autocomplete="off" placeholder="请输入标题" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">分类</label>
				<div class="layui-input-block">
					<select name="subject" lay-verify="subject">
						<option value="">请选择分类</option>
						<c:forEach var="subject" items="${subjects }">
							<option value="${subject.id }" <c:if test="${article.subject==subject.id }">selected</c:if>>${subject.subjectName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">标签</label>
				<div class="layui-input-block">
					<select name="tag" lay-verify="tag" multiple lay-search>
						<option value="">请选择标签</option>
						<c:forEach var="tag" items="${tags }">
							<option value="${tag.id }"
								<c:forEach var="aTag" items="${aTags}">
									<c:if test="${tag.id==aTag.id }">selected</c:if>
								</c:forEach>
							>${tag.tagName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">正文</label>
				<div class="layui-input-block">
					<textarea id="content" name="content" placeholder="请输入内容" autofocus  class="layui-textarea" lay-verify="content">${article.content}</textarea>
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
							<input type="hidden" name="cover" id="cover" value="${article.cover}" lay-verify="cover">
							<img class="layui-upload-img" id="cover-img" width="92px" height="92px" src="${article.cover}">
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
								<thead><tr><th>文件名</th><th>状态</th><th>操作</th></tr></thead>
								<tbody id="enclosureList">
									<c:forEach var="enclosure" items="${enclosures}">
										<tr id="upload-ex" fileName="${enclosure.fileName}" filePath="${filePath}">
											<td>${enclosure.fileName}</td>
											<td><span style="color: #5FB878;">上传成功</span></td>
											<td>
												<a class="layui-btn layui-btn-xs layui-btn-danger" onclick="$(this).parents('tr').remove()">删除</a>
											</td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block right">
					<c:if test="${article.status==0}">
						<button class="layui-btn" lay-submit lay-filter="form" value="0">保存为草稿</button>
					</c:if>
					<button class="layui-btn" lay-submit lay-filter="form" value="1">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
    layui.config({
        base: 'static/plugin/layui-v2.4.5/layui/lay/modules/'
    }).extend({
        kzLayedit: 'kz.layedit',
		kzForm: 'kz.form'
    }).use(['kzLayedit','kzForm','upload'], function(){
        var $=layui.jquery;
        var form = layui.kzForm;
        var upload = layui.upload;

        var editor = new Simditor({
            textarea: $('#content'),
			placeholder:'',
            toolbar: ['title','bold','italic','underline','strikethrough','fontScale','color',
                'ol','ul','blockquote','code' ,'table','link','image','hr','indent','outdent','alignment'
    		],
            upload : {
                url : 'file/upload/articleContent', //文件上传的接口地址,返回参数{success:true,msg:'',file_path:''}
                fileKey:'file', //服务器端获取文件数据的参数名
                leaveConfirm: '正在上传文件..',
                connectionCount: 3
            },
            imageButton: ['upload','external'],
            pasteImage: true
        });


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
                if(res.code == 0){
                    $("#cover").val(res.file_path);//把结果写入对应表单
                    return layer.msg('上传成功');
                }else{
                    this.error();
                    return layer.msg('上传失败');
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
                if(res.code == 0){ //上传成功
                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tr.attr({"fileName":res.file_name,"filePath":res.file_path});
                    tds.eq(1).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(2).find('.demo-delete').removeClass('layui-hide'); //清空操作
                    layer.msg('上传成功');
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }else{
                    layer.msg('上传失败');
                    this.error(index, upload);
                }
            },
            error: function(index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(1).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(2).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });

        //监听提交
        form.on('submit(form)', function(data) {
            //附件列表
            var enclosure = new Array();
			demoListView.find("tr").each(function(){
                enclosure.push({"fileName":$(this).attr("fileName"),"filePath":$(this).attr("filePath")});
			});
            data.field.enclosure = enclosure;
            //富文本正文
            data.field.content = editor.getValue();
            //文章状态
            data.field.status = data.elem.value;

            $.ajax({
                url:'blog/article/save',
                data:{"param":JSON.stringify(data.field)},
                dataType:'json',
                success:function(data){
                    if(data.code==200){
                        layer.msg("修改文章成功",{shade: 0,icon:1},function(index){
                            parent.layer.closeAll();	//关闭所有
                        });
                    }else{
                        layer.msg("修改文章失败",{icon:2});
                    }
                }
            });
            return false;
        });

        //表单校验
        form.verify({
            title: function(value, item){
                if(value == ''){
                    return '标题不能为空';
                }
            },
            summary: function(value, item){
                if(value == ''){
                    return '摘要不能为空';
                }
            },
            subject: function(value, item){
                if(value == ''){
                    return '请勾选分类';
                }
            },
            tag: function(value, item){
                if(value == null || value.length==0){
                    return '请勾选标签';
                }
            },
            content: function(value, item){
                if(value == ''){
                    return '正文不能为空';
                }
            },
            cover: function(value, item){
                if(value == ''){
                    return '封面未能正确设置';
                }
            }
        });

    });
</script>
</html>