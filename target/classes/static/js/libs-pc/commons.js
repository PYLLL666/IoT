var clientUrl = "http://localhost:8080/PalmCampusClient";
var imageUrl = "http://localhost:6688";

//页面过滤
$.post(clientUrl+"/user/pageFilter.action?token="+localStorage.getItem("token"),function (result) {
	if(location.href.indexOf("LoginPage.html") > -1 ){
		return;
	}
	if(result == null){
		location.href = "LoginPage.html";
	}
	if(result.code == "-101" || result.code == null){
		location.href = "LoginPage.html";
	}
});	

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

//按钮跳转注册表单
function reg() {
	var value = document.getElementById("profile").className;
	if (value.indexOf("active") >= 0) {
		$("#profile").removeClass("active");
		$("#home").addClass("active");
		$("#login").removeClass("active");
		$("#register").addClass("active");
	}
}
//取得验证码方法 
function getPhoneCode() {
	var user_phone = $('.reg_phone').val();
	console.log(user_phone);
	if ($('.reg-phone').hasClass("is-empty") || $('.reg-phone').hasClass("has-error")) {
		$(".reg-tip").html("&nbsp&nbsp&nbsp手机号码格式不正确或未输入！请您重新输入");
	} else {
		$.post(clientUrl + "/user/checkPhoneCode.action", {
			"user_phone": user_phone
		}, function(result) {
			$(".reg-tip").html(result.msg);
			if (result.code > 0) {
				var count = 60;
				var countDown = setInterval(function() {
					if (count === 0) {
						$('.feachBtn').text('重新发送').removeAttr('disabled');
						$(".feachBtn").css("pointer-events", "auto");
						clearInterval(countDown);
					} else {
						$('.feachBtn').attr('disabled', true);
						$(".feachBtn").css("pointer-events", "none");
						$('.feachBtn').text('重新获取' + count);
					}
					count--;
				}, 1000);
			} else {
				$(".reg-tip").html(result.msg);
			}
		});
	}
}
//注册
function regGo() {
	user_name = $(".reg_name").val();
	console.log(user_name)
	user_phone = $(".reg_phone").val();
	user_pwd = $(".reg_pwd").val();
	user_birth = "01/08/1999";
	user_sex = $(".reg_sex option:selected").val();
	var yzm = $(".yzcode").val();
	$.post(clientUrl + "/user/addUser.action", {
		"user_name": user_name,
		"user_phone": user_phone,
		"user_pwd": user_pwd,
		"user_birth": user_birth,
		"user_sex": user_sex,
		"code": yzm
	}, function(result) {
		if (result.code > 0) {
			console.log(result.msg);
			location.href = "LoginPage.html";
		} else if (result.code == 0) {
			console.log(result.msg);
		} else {
			console.log(result.msg);
		}
	});
}

//登录
function logIn(){
	user_phone = $("#log_phone").val();
	console.log(user_phone);
	user_pwd = $("#log_pwd").val();
	$.post(clientUrl+"/user/login.action",{"user_phone":user_phone,"user_pwd":user_pwd},function(result){
					if(result.code > 0){
						localStorage.setItem("token",result.msg);
						localStorage.setItem("user_id",result.data);
						location.href = "FleaFairPage.html";
					}else{
						layer.msg(result.msg);
					}
				})
}
//设置拦截   没有token就跳到登录界面
/* 	if(localStorage.getItem("token")==null){
		layer.msg("未登录");
		window.setTimeout("window.location='LoginPage.html",1000);
	} */
	
//设置退出登录
function logOut(){
	$.post(clientUrl+"/user/logOut.action?token="+localStorage.getItem("token"),{"user_id":user_id},function(result){
					layer.msg(result.msg);
					localStorage.setItem("token",null);
					window.setTimeout("window.location='LoginPage.html'",1000);
				})
	
}
/* $("#logOut").click(function(){
			layer.msg("正在退出，请等待");
			localStorage.setItem("token",null);
			window.setTimeout("window.location='LoginPage.html",1000);
		}) */
		
/* 在使用layui的分页插件时，必须得将下面这三行先导进来，并且将插件写到这三行里面 */
 layui.use(['laypage', 'layer', 'upload', 'layedit'], function() {
	var laypage = layui.laypage,
		layer = layui.layer,
		upload = layui.upload;
	var layedit = layui.layedit;
	//自定义首页、尾页、上一页、下一页文本
	/*laypage.render({
		elem: 'demo1-1',
		count: 100,
		first: '首页',
		last: '尾页',
		prev: '上一页',
		next: '下一页'
	}); */

	//贴吧页面的分页插件
	/* $.post(clientUrl + "/findAllPost.action", function(result) {
		laypage.render({
		elem: 'demo1-1',
		count: result.data.total,
		first: '首页',
		last: '尾页',
		prev: '上一页',
		next: '下一页'
		});
	}); */

	/* 相册页面的分页插件
	 $.post(clientUrl + "/findAllPhoto.action",function(result) {
		laypage.render({
		elem: 'demo1-2',
		count: result.data.total,
		first: '首页',
		last: '尾页',
		prev: '上一页',
		next: '下一页'
		});
	}); */

	/* 视频页面的分页插件 
	$.post(clientUrl + "/findAllVideo.action", function(result) {
		laypage.render({
		elem: 'demo1-3',
		count: result.data.total,
		first: '首页',
		last: '尾页',
		prev: '上一页',
		next: '下一页'
		});
	});*/

	/* layui上传图片插件 */
	var uploadInst = upload.render({
		elem: '#test1' //绑定元素
			,
		accept: 'image' //普通文件
			,
		url: clientUrl+'/goods/uploadImage.action?token='+localStorage.getItem("token") //上传接口
			,
		size: 3062 //最大允许上传的文件大小
			,
		done: function(res) {
			//上传完毕回调
			$("#zhutu").attr("src",imageUrl+res.msg)
			zhutuUrl = res.msg;
		},
		error: function() {
			//请求异常回调
		}
	});
	
	/* layui修改头像 */
	upload.render({
		elem: '#touxiang' //绑定元素
			,
		accept: 'image' //普通文件
			,
		url: clientUrl+"/goods/uploadImage.action?token="+localStorage.getItem("token") //上传接口
			,
		size: 3062 //最大允许上传的文件大小
			,
		done: function(res) {
			//上传完毕回调
			if(res.msg=="上传失败"){
				layer.msg("上传失败")
			}else{
				$("#touxiangzhutu").attr("src",imageUrl+res.msg)
				zhutuUrl = res.msg;
				$.post(clientUrl+"/user/updatePhoto.action?token="+localStorage.getItem("token"),{'user_id':user_id,'user_photo':zhutuUrl},function (result) {
							layer.msg(result.msg);
						});	
			}
		},
		error: function() {
			//请求异常回调
		}
	});
	//创建富文本编辑器
	/* layedit.set({
		//暴露layupload参数设置接口 --详细查看layupload参数说明
		uploadImage: {
			url: '/uploadImage.action',
			accept: 'image',
			acceptMime: 'image/*',
			exts: 'jpg|png|gif|bmp|jpeg',
			size: '1024' //大小为1M
		}
		//开发者模式 --默认为false
		,
		devmode: true
			//插入代码设置
			,
		codeConfig: {
			hide: true, //是否显示编码语言选择框
			default: 'java' //hide为true时的默认语言格式
		},
		height: '90%'
	});
	var index = layedit.build('content'); *///建立编辑器
});
/* 发表评论事件 还需要获取当前用户的id */
function oct() {
	var text = $(".form").val()
	console.log(text);
}


/* 发表评论里面的取消，点击取消后，文本框的内容删除 */
function cancel(){
	$(".formcel").val('');
}

/* 删除帖子，先获取帖子的id */
/* $.post(clientUrl + "/user/editPost.action",function cancel(){
	$(".formcel").val('');
}); */
/* 删除帖子，先获取帖子的id */
/* function del(obj){
	var id = obj.id
	console.log(id);
} */

/* $.post(clientUrl + "/user/deletePost.action",function del(obj){
	var editid=obj.id
	console.log(editid);
}); */

/* 编辑贴子 获取帖子的id */
/* function edit(obj){
	var editid=obj.id
	console.log(editid);
} */
/* 编辑贴子 获取帖子的id */
/* $.post(clientUrl + "/user/editPost.action",function edit(obj){
	var editid=obj.id
	console.log(editid);
}); */

/* 点赞加一
var num1 = document.getElementById('num1');//不是数值类型，是字符串类型
var flag1 = 0;
function dianzan(){
	console.log("触发点击事件");
  if (flag1 == 0) {
    num.innerHTML++;
	console.log(num.innerHTML++);
  }
  if (flag1 == 1) {
    num1.innerHTML--;
	console.log(num1.innerHTML--);
  }
  if (flag1 == 2) {
    flag1 = 0;
  }
  flag1++;
}
 */

