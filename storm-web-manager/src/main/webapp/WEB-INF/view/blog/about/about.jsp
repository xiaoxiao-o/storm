<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String path = request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
	<title>后台@storm</title>
	<base href=" <%=path%>">
<jsp:include page="../../head.jsp"/>
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane" style="width: 90%;min-width: 360px;margin: 0 auto;">
			<input type="hidden" name="id" value="${about.id}">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>关于作者</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">作者</label>
				<div class="layui-input-block">
					<input type="text" name="pName" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.pName}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">签名</label>
				<div class="layui-input-block">
					<input type="text" name="pSign" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.pSign}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">头像</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传头像
					</button>
					<img src="${about.pHead}" width="38px" height="38px">
					<input type="hidden" name="pHead" value="${about.pHead}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">背景</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传背景
					</button>
					<img src="${about.pBack}" width="120px" height="38px">
					<input type="hidden" name="pBack" value="${about.pBack}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">地区</label>
				<div class="layui-input-inline">
					<select class="select" name="pProvince" url="dict/getAreaListByParent?id=0" child="pCity" childDataPath="dict/getAreaListByParent" selectValue="${about.pProvince}" promtion="请选择省">
						<option value="">请选择省</option>
					</select>
				</div>
				<div class="layui-input-inline">
					<select class="select" name="pCity" childDataPath="dict/GetAreaListByParent" selectValue="${about.pCity}" promtion="请选择市">
						<option value="">请选择市</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">微信交流</label>
				<div class="layui-input-block">
					<input type="text" name="pWechat" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.pWechat}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">QQ交流</label>
				<div class="layui-input-block">
					<input type="text" name="pQq" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.pQq}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">给我写信</label>
				<div class="layui-input-block">
					<input type="text" name="pEmail" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.pEmail}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新浪微博</label>
				<div class="layui-input-block">
					<input type="text" name="pWeibo" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.pWeibo}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">GitHub</label>
				<div class="layui-input-block">
					<input type="text" name="pGit" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.pGit}">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" class="layui-textarea" name="pDesc">${about.pDesc}</textarea>
				</div>
			</div>

			<fieldset class="layui-elem-field layui-field-title">
				<legend>关于网站</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-block">
					<input type="text" name="wName" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.wName}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">签名</label>
				<div class="layui-input-block">
					<input type="text" name="wSign" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.wSign}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">头像</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传头像
					</button>
					<img src="${about.wHead}" width="38px" height="38px">
					<input type="hidden" name="wHead" value="${about.wHead}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">背景</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传背景
					</button>
					<img src="${about.wBack}" width="120px" height="38px">
					<input type="hidden" name="wBack" value="${about.wBack}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">上线时间</label>
				<div class="layui-input-block">
					<input type="text" name="wOnTime" id="wOnTime" autocomplete="off" class="layui-input" value="<fmt:formatDate value="${about.wOnTime}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" class="layui-textarea" name="wDesc">${about.wDesc}</textarea>
				</div>
			</div>

			<fieldset class="layui-elem-field layui-field-title">
				<legend>友情链接</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-block">
					<input type="text" name="lName" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.lName}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">签名</label>
				<div class="layui-input-block">
					<input type="text" name="lSign" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.lSign}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">头像</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传头像
					</button>
					<img src="${about.lHead}" width="38px" height="38px">
					<input type="hidden" name="lHead" value="${about.lHead}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">背景</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传封面
					</button>
					<img src="${about.lBack}" width="120px" height="38px">
					<input type="hidden" name="lBack" value="${about.lBack}">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">友链列表
					<i class="layui-icon layui-icon-add-circle" style="color:#1E9FFF;cursor: pointer" title="新增" onclick="lbodyTrAdd()"></i>
				</label>
				<div class="layui-input-block">
					<table class="layui-table" style="margin-top: 0;" id="lbody">
						<thead>
						<tr>
							<th>站点名称</th>
							<th>站点链接</th>
							<th>站点LOGO</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${lBody}" var="lb">
								<tr>
									<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value="${lb.name}"></td>
									<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value="${lb.link}"></td>
									<td>
										<div>
											<button type="button" class="layui-btn layui-btn-normal layui-btn-sm" upload-btn style="height: 26px;line-height: 26px;">
												<i class="layui-icon"></i>上传
											</button>
											<img src="${lb.logo}" width="26px" height="26px">
											<input type="hidden" name="" value="${lb.logo}">
										</div>
									</td>
									<td>
										<i class="layui-icon layui-icon-delete" style="color:red;cursor: pointer" title="删除" onclick="lbodyTrRemove(this)"></i>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<fieldset class="layui-elem-field layui-field-title">
				<legend>小额赞赏</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">名称</label>
				<div class="layui-input-block">
					<input type="text" name="mName" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.mName}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">签名</label>
				<div class="layui-input-block">
					<input type="text" name="mSign" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.mSign}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">头像</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传头像
					</button>
					<img src="${about.mHead}" width="38px" height="38px">
					<input type="hidden" name="mHead" value="${about.mHead}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">背景</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传背景
					</button>
					<img src="${about.mBack}" width="120px" height="38px">
					<input type="hidden" name="mBack" value="${about.mBack}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">微信收钱</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传二维码
					</button>
					<img src="${about.mWechatCode}" width="38px" height="38px">
					<input type="hidden" name="mWechatCode" value="${about.mWechatCode}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">支付宝收钱</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" upload-btn >
						<i class="layui-icon"></i>上传二维码
					</button>
					<img src="${about.mAlibabaCode}" width="38px" height="38px">
					<input type="hidden" name="mAlibabaCode" value="${about.mAlibabaCode}">
				</div>
			</div>

			<div class="layui-form-item right">
				<button class="layui-btn" lay-submit lay-filter="form">更新站点信息</button>
			</div>
		</form>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
    layui.config({
        base: 'static/plugin/layui-v2.4.5/layui/lay/modules/'
    }).extend({
        cascadeSelect: 'kz.cascadeSelect',
    }).use(['table','form','cascadeSelect','upload','laydate'], function() {
		var table = layui.table;
		var form = layui.form;
		var cascadeSelect = layui.cascadeSelect;
        var upload = layui.upload;
        var laydate = layui.laydate;

        laydate.render({
            elem: '#wOnTime'
        });


		cascadeSelect.init('pProvince', false);//地区下拉联动

		//配置上传
        var uploadInst = upload.render({
            elem: '[upload-btn]',//jquery选择器
            url: 'file/upload/about',
            done: function(res){
                if(res.code == 0){
                    this.item.parent("div").find("img").attr("src",res.file_path);
                    this.item.parent("div").find("input[type='hidden']").val(res.file_path);
                    return layer.msg('上传成功');
                }else{
                    this.error();
                    return layer.msg('上传失败');
                }
            },
            error:function(){
                return layer.msg('上传失败');
            }
        });

        //监听提交
        form.on('submit(form)', function(data) {
            var lbody = [];
            $("#lbody").find("tbody").children("tr").each(function(){
                var ll = {
                    name : $(this).children("td").eq(0).children("input").val(),
					link : $(this).children("td").eq(1).children("input").val(),
					logo : $(this).children("td").eq(2).find("input[type='hidden']").val()
				}
                lbody.push(ll);
            });
            data.field.lbody = lbody;

            //存name,考录后决定存value
            //data.field.pProvince = $("[name='pProvince']").find("option:selected").text().trim();
            //data.field.pCity = $("[name='pCity']").find("option:selected").text().trim();

            $.ajax({
                url:'com.whyxs.controller.blog/about/save',
                method:'POST',
                data:{"param":JSON.stringify(data.field)},
                dataType:'json',
                success:function(data){
                    if(data.code==200){
                        layer.alert("更新站点信息成功",{shade: 0,icon:1},function(index){
                            window.location.reload();
                        });
                    }else{
                        layer.alert("更新站点信息失败",{icon:2});
                    }
                }
            });
            return false;
        });
	});

    function lbodyTrAdd(){
        $("#lbody").find("tbody").append(
			['<tr>'
			,'<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value=""></td>'
			,'<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value=""></td>'
			,'<td><div><button type="button" class="layui-btn layui-btn-normal layui-btn-sm" upload-btn style="height: 26px;line-height: 26px;"> <i class="layui-icon"></i>上传 </button> <img src="" width="26px" height="26px"> <input type="hidden" name="" value=""> </div> </td>'
			,'<td> <i class="layui-icon layui-icon-delete" style="color:red;cursor: pointer" title="删除" onclick="lbodyTrRemove(this)"></i> </td>'
			,'</tr>'].join(' ')
		);
	}

    function lbodyTrRemove(t){
        $(t).parents("tr").remove();
	}

</script>
</html>