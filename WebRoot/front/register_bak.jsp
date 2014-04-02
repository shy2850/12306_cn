<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>用户注册</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" href="<%=basePath%>css/jqueryui/jquery-ui.css">
	<link rel="stylesheet" href="<%=basePath%>css/form-style.min.css">
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/require.js"></script>
	<script src="<%=basePath%>js/require-config.js"></script>
	<style>
		#register-main{
			width: 600px;
			margin: 0 auto;
		}
		#register-main h2{
			text-align: center;
		}
		#register-main ul{
			padding: 0;
		}
		#register-main li{
			list-style: none;
			padding: 0;
			padding-left: 170px;
			height: 40px;
			line-height: 40px;
			position:relative;
		}
		#register-main li>label{
			float: left;
			display: block;
			width: 170px;
			height: 40px;
			margin-left: -170px;
			text-align: right;
		}
		#register-main input[type=text],
		#register-main input[type=password],
		#register-main input[type=date],
		#register-main input[type=email]
		{
			height: 26px;
			line-height: 26px;
			padding: 0 4px;
		}
		.tip-error{
			color: red;
		}
		.tip-default{
			color: #3f3;
			font-weight: bold;
		}
	</style>
  </head>
  	
  <body>
    <div id="register-main">
		<h2>用户注册 <span class="tip-error">${error}</span></h2>
	    <form action="userAction$register" id="register-form" method="post">
	    	<ul>
	    		<li>
	    			<label>用户名:</label>
	    			<span>
	    				<input type="text" name="user.email" id="user_email" placeholder="输入您的邮箱地址">
	    			</span>
	    		</li>
	    		<li>
	    			<label>密码:</label>
	    			<span>
	    				<input type="password" name="user.password" id="user_password">
	    			</span>
	    		</li>
	    		<li>
	    			<label>重复密码:</label>
	    			<span>
	    				<input type="password" name="password" id="password">
	    			</span>
	    		</li>
	    		<li>
	    			<label>真实姓名:</label>
	    			<span>
	    				<input type="text" name="user.name" id="user_name">
	    			</span>
	    		</li>
	    		<li>
	    			<label>身份证:</label>
	    			<span>
	    				<input type="text" name="user.id_card" id="user_id_card">
	    			</span>
	    			<span>
	    				<a href="javascript:;" class="fm-button" id="upload-card">上传身份证照片</a>
	    				<em></em>
	    			</span>
	    		</li>
	    		<li>
	    			<label>性别:</label>
	    			<span>
	    				<input type="radio" name="user.sex" id="user_sex1" value="1"><label for="user_sex1"> 男 </label>
	    				<input type="radio" name="user.sex" id="user_sex0" value="0"><label for="user_sex0"> 女 </label>
	    			</span>
	    		</li>
	    		<li>
	    			<label>籍贯:</label>
	    			<span>
	    				<input type="text" name="user.from" id="user_from" value="">
	    			</span>
	    		</li>
	    		<li>
	    			<label>出生日期:</label>
	    			<span>
	    				<input type="text" name="user.birth" id="user_borth">
	    			</span>
	    		</li>
	    		<li>
	    			<input type="submit" value="注册">
	    			<input type="reset" value="重置">
	    		</li>
	    	</ul>
	    </form>	
    </div>

<script>
(function($){
	require(['iealert'],function(IEAlert){
		new IEAlert();
	});

	require(['frameUpload'],function(FrameUpload){
		var item = $("#upload-card"), show = item.next();

		var frameUpload = new FrameUpload({
			el: item[0],
			//src: "json/iframe-upload.html",
			action: "uploadAction$do",
			onchange: function(){
				show.html( this.value.match(/[^\\\/]+$/) );
				item.html("上传中...");
				this.submit();
			},
			afterUpload: function(data){
				if(data.status){
					alert( '上传成功' );
					item.html("上传身份证照片");
				}
				show.html( "" );
			},
			ready: function(){
				this.setAttribute("multiple","multiple");
				this.setAttribute("accept","image/*")
			}
		});
	});

	require(['selectors','area'],function(){

		//var ChinaAreaMap = window.ChinaAreaMap;
		delete window.ChinaAreaMap;	//清除 ChinaAreaMap 后 idCard-valid将使用籍贯的非严格验证, 防止部分身份证查询不到数据

		$("#user_from").selectors({
			o:window.Area["亚洲_1"]["中国_156"]
		});
		$("#user_borth").selectors({
			beginDate : new Date("1900/1/1")
		});

	});

	require(['formValid','idCard-valid','area'],function(){

		$.form.settings = {

            /**需要提示的标签后面追加提示标签*/
            initTip: function(input, defaultTip) {
                input.after( $("<span class='tip tip-default'></span>") );
            },

            /**默认的出错提示方案*/
            validTip: function(input, errorInfo, defaultTip) {
                if (errorInfo) {
                    input.next()
                        .removeClass('tip-default')
                        .addClass("tip-error")
                        .text(errorInfo);
                } else {
                    input.next()
                        .removeClass('tip-error')
                        .addClass("tip-default")
                        .text(defaultTip || " √ ");
                }
            }

        };

		$.form.render({
			"#user_email":{
				type:"email",
				errorTip : "输入的邮箱地址有误"
			},
			"#user_password":{
				minlen: 6,
				maxlen:20,
				lenTip: "密码长度为6-20位"
			},
			"#password":{
				validFun:function(v){
					return {
						errorInfo: v === $("#user_password").val() ? "" : "重复密码不一致"
					};
				}
			},
			"#user_name":{
				type:"chinese",
				minlen:2,
				maxlen:20,
				errorTip: "需要2-20位的中文字符"
			},
			"#user_id_card":{
				type:"IdCard",
				success:function(input,validReturn){
					$( '#user_sex0,#user_sex1' ).filter('[value="' + validReturn.sex + '"]').attr({checked:true}).change();
					$( '#user_from' ).selectValue( input.val().substring(0,6) ).change();
					$( '#user_borth' ).selectValue( validReturn.birth.year+"-"+validReturn.birth.month+"-"+validReturn.birth.day  ).change();
				}
			}

		},{
			required: true,
			option:"blur",
			requiredTip: "需要填写哦"
		});

	});


})(jQuery);

</script>
  </body>
</html>
