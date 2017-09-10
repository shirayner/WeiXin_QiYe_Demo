<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page language="java" import="com.ray.util.WeiXinUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>上传报销单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<style type="text/css">
html {
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%;
	-webkit-user-select: none;
	user-select: none;
}

body {
	line-height: 1.6;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	background-color: #f1f0f6;
}

* {
	margin: 0;
	padding: 0;
}

button {
	font-family: inherit;
	font-size: 100%;
	margin: 0;
	*font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

ul, ol {
	padding-left: 0;
	list-style-type: none;
}

a {
	text-decoration: none;
}

.label_box {
	background-color: #ffffff;
}

.label_item {
	padding-left: 15px;
}

.label_inner {
	padding-top: 10px;
	padding-bottom: 10px;
	min-height: 24px;
	position: relative;
}

.label_inner:before {
	content: " ";
	position: absolute;
	left: 0;
	top: 0;
	width: 200%;
	height: 1px;
	border-top: 1px solid #ededed;
	-webkit-transform-origin: 0 0;
	transform-origin: 0 0;
	-webkit-transform: scale(0.5);
	transform: scale(0.5);
	top: auto;
	bottom: -2px;
}

.lbox_close {
	position: relative;
}

.lbox_close:before {
	content: " ";
	position: absolute;
	left: 0;
	top: 0;
	width: 200%;
	height: 1px;
	border-top: 1px solid #ededed;
	-webkit-transform-origin: 0 0;
	transform-origin: 0 0;
	-webkit-transform: scale(0.5);
	transform: scale(0.5);
}

.lbox_close:after {
	content: " ";
	position: absolute;
	left: 0;
	top: 0;
	width: 200%;
	height: 1px;
	border-top: 1px solid #ededed;
	-webkit-transform-origin: 0 0;
	transform-origin: 0 0;
	-webkit-transform: scale(0.5);
	transform: scale(0.5);
	top: auto;
	bottom: -2px;
}

.lbox_close .label_item:last-child .label_inner:before {
	display: none;
}

.btn {
	display: block;
	margin-left: auto;
	margin-right: auto;
	padding-left: 14px;
	padding-right: 14px;
	font-size: 18px;
	text-align: center;
	text-decoration: none;
	overflow: visible;
	/*.btn_h(@btnHeight);*/
	height: 42px;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	color: #ffffff;
	line-height: 42px;
	-webkit-tap-highlight-color: rgba(255, 255, 255, 0);
}

.btn.btn_inline {
	display: inline-block;
}

.btn_primary {
	background-color: #437DBA;
}

.btn_primary:not (.btn_disabled ):visited {
	color: #ffffff;
}

.btn_primary:not (.btn_disabled ):active {
	color: rgba(255, 255, 255, 0.9);
	background-color: #3b78b9;
}

button.btn {
	width: 100%;
	border: 0;
	outline: 0;
	-webkit-appearance: none;
}

button.btn:focus {
	outline: 0;
}

.wxapi_container {
	font-size: 16px;
}

h1 {
	font-size: 14px;
	font-weight: 400;
	line-height: 2em;
	padding-left: 15px;
	color: #8d8c92;
}

.desc {
	font-size: 14px;
	font-weight: 400;
	line-height: 2em;
	color: #8d8c92;
}

.wxapi_index_item a {
	display: block;
	color: #3e3e3e;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.wxapi_form {
	background-color: #ffffff;
	padding: 0 15px;
	margin-top: 30px;
	padding-bottom: 15px;
}

h3 {
	padding-top: 16px;
	margin-top: 25px;
	font-size: 16px;
	font-weight: 400;
	color: #3e3e3e;
	position: relative;
}

h3:first-child {
	padding-top: 15px;
}

h3:before {
	content: " ";
	position: absolute;
	left: 0;
	top: 0;
	width: 200%;
	height: 1px;
	border-top: 1px solid #ededed;
	-webkit-transform-origin: 0 0;
	transform-origin: 0 0;
	-webkit-transform: scale(0.5);
	transform: scale(0.5);
}

.btn {
	margin-bottom: 15px;
}
</style>


</head>
<body>
	<%
		Map<String, Object> res = new HashMap<String, Object>();
		res = WeiXinUtil.getWxConfig(request);
		request.setAttribute("appId", res.get("appId"));
		request.setAttribute("timestamp", res.get("timestamp"));
		request.setAttribute("nonceStr", res.get("nonceStr"));
		request.setAttribute("signature", res.get("signature"));
	%>

<body>
	<div class="wxapi_container">

		<form action="" method="POST"></form>



		<div class="lbox_close wxapi_form">
			<h3 id="menu-basic">基础接口</h3>
			<span class="desc">判断当前客户端是否支持指定JS接口</span>
			<button class="btn btn_primary" id="checkJsApi">checkJsApi</button>

			<span class="desc">上传图片接口</span>
			<button class="btn btn_primary" id="uploadImage">uploadImage</button>
			<span class="desc">下载图片接口</span>
			<button class="btn btn_primary" id="downloadImage">downloadImage</button>


			<span class="desc">调起微信扫一扫接口</span>
			<button class="btn btn_primary" id="scanQRCode1">scanQRCode(直接返回结果)</button>

			<span class="desc">测试按钮</span>
			<button class="btn btn_primary" id="ceshi">ceshi</button>

		</div>
	</div>

	<script>
		/*
		 * 注意：
		 * 所有的JS接口只能在应用配置的安全域名下面使用。   
		 *
		 */
		wx.config({
			beta : true,
			debug : true,
			appId : '${appId}',
			timestamp : '${timestamp}',
			nonceStr : '${nonceStr }',
			signature : '${signature}',

			jsApiList : [ 'checkJsApi', 'chooseImage', 'previewImage',
					'uploadImage', 'downloadImage', 'scanQRCode', ]
		});

		//通过ready接口处理成功验证
		wx.ready(function() {
			// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			$("#ceshi").click(function() {
				alert("ceshi11111111");
			});

		});

		// 1 判断当前版本是否支持指定 JS 接口，支持批量判断
		$("#checkJsApi").click(function() {
			wx.checkJsApi({
				jsApiList : [ 'getNetworkType', 'previewImage' ],
				success : function(res) {
					alert(JSON.stringify(res));
				}
			});
		});

		//2.选择图片
		$('#uploadImage').on('click', function() {
			wx.chooseImage({
				success : function(res) {
					var localIds = res.localIds;
					syncUpload(localIds);
				}
			});
		});
		
		
		
		//3.上传图片
		var syncUpload = function(localIds) {
			var localId = localIds.pop();
			wx.uploadImage({
				localId : localId,
				isShowProgressTips : 1,
				success : function(res) {
					var serverId = res.serverId; // 返回图片的服务器端ID

					$.ajax({
								type : "POST",
								url : "http://5nffqn.natappfree.cc/WeiXin_QiYe_Demo/uploadExpenseAccaoutServlet",
								data : {
									serverId : res.serverId
								},
								dataType : "text",
								success : function(data) {
									alert(data);
								}
								
							});
					
					//其他对serverId做处理的代码
					if (localIds.length > 0) {
						syncUpload(localIds);
					}
				}
			});
		};

	

		//点击扫描按钮，扫描二维码并返回结果
		document.querySelector('#scanQRCode1').onclick = function() {
			wx
					.scanQRCode({
						desc : 'scanQRCode desc',
						needResult : 1,
						success : function(res) {
							//扫码后获取结果参数:htpp://xxx.com/c/?6123，截取到url中的防伪码后，赋值给Input
							var result = res.resultStr;
							alert(result);

							$
									.ajax({
										type : "POST",
										url : "http://tavvej.natappfree.cc/WeiXin_QiYe_Demo/qrservlet",
										data : {
											result : res.resultStr
										},
										dataType : "text",
										success : function(data) {
											alert(data);
										}

									});

						}
					});
		};
	</script>


</body>
</html>