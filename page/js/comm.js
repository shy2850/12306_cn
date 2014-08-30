(function($){
	//ie6，ie7升级提示
	if( $("html").hasClass("ie6") || $("html").hasClass("ie7") ){
		require(["iealert"],function(IEAlert){
			new IEAlert({
				ie6 : {
					allowContinue : true
				},
				ie7 : {
					allowContinue : true
				}
			});
		});
	};

	//回到顶部效果
	$('#_return_top').on('click',function(e){
		$('body').animate({
			scrollTop:0
		});
		e.preventDefault();
	});
	$(window).on('scroll',function(){
		if( $(this).scrollTop() > 10 ){
			$('#_return_top').show();
		}else{
			$('#_return_top').hide();
		}
	}).trigger('scroll');

	//日期控件
	require(['WdatePicker']);

	//登录弹出框
	require(['alerts','draggable'],function(){

		$('#btn-login').on('click',function(){
			jConfirm(
			'<ul class="form-dialog">'+
			'	<li>'+
			'		<label>用户名:</label>'+
			'		<input type="text" class="username" placeholder="请输入邮箱"/>'+
			'	</li>'+
			'	<li>'+
			'		<label>密码:</label>'+
			'		<input type="password" class="password" />'+
			'	</li>'+
			'</ul>',
			'用户登录',{
				okButton : "登录",
				cancelButton : "取消"
			},function(result){
				if( result ){
					var username = $('#popup_content .username').val(),
						password = $('#popup_content .password').val();
					$.ajax({
						url: "http://localhost:8080/12306_cn/userAction$login",
						data:{
							"user.email":username,
							"user.password":password
						},
						dataType:'jsonp',
						success:function(result){
							if( result.success ){
								$('.login-text').html('欢迎:'+result.user.name+' <a class="exit">退出</a>');
								window.loginUser = result.user;
								jTip('登陆成功!')
							}else{
								alert(result.error)
							}
						}
					});
					return false;
				}
					
			});
		});
		
		//退出登录事件
		$('.login-text').on('click','.exit',function(){
			jConfirm('确认退出?','用户退出',{
				okButton : "退出",
				cancelButton : "取消"
			},function(result){
				if(result){
					$.ajax({
						url: 'http://localhost:8080/12306_cn/userAction$logout',
						dataType:'jsonp',
						success:function(json){
							window.location.reload();
						}
					})
				}
			});
		});	
	});

	$.ajax({
		url:'http://localhost:8080/12306_cn/userAction$isLogin',
		dataType:'jsonp',
		success:function(result){
			if(result.user){
				window.loginUser = result.user;
				$('.login-text').html('欢迎:'+result.user.name+' <a class="exit">退出</a>');
			}else{
				if( window.needLogin){
					window.location.href = "kyfw.html"
				}
			}
		}
	});

	$('#my12306').on('click','a',function(e){
		if( !window.loginUser ){
			$('#btn-login').trigger('click');
			e.preventDefault();
		}
	});

})(jQuery);