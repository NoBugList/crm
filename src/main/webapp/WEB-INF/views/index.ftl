<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="${ctx}/assets/css/icons.css" rel="stylesheet" />
        <link href="${ctx}/assets/css/bootstrap.css" rel="stylesheet" />
        <link href="${ctx}/assets/css/main.css" rel="stylesheet" />
    </head>
    <body class="login-page">
        <!-- Start #login -->
        <div id="login" class="animated bounceIn">
            <div class="login-wrapper">
                <ul id="myTab" class="nav nav-tabs nav-justified bn">
                    <li>
                        <a href="#log-in" data-toggle="tab">登录</a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content bn">
                    <div class="tab-pane fade active in" id="log-in">
                        <form class="form-horizontal mt10" id="login-form">
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="text" id="userName" class="form-control left-icon" placeholder="Your username ...">
                                    <i class="ec-user s16 left-input-icon"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="password" id="password" class="form-control left-icon" placeholder="Your password">
                                    <i class="ec-locked s16 left-input-icon"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-8">
                                    <!-- col-lg-12 start here -->
                                    <label class="checkbox">
                                        <input type="checkbox" name="remember" id="remember" value="option">记住密码 ?
                                    </label>
                                </div>
                                <!-- col-lg-12 end here -->
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-4">
                                    <!-- col-lg-12 start here -->
                                    <button class="btn btn-success pull-right" type="button" id="loginBtn">登录</button>
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- End #.login-wrapper -->
        </div>
    </body>
    <script src="${ctx}/highcharts4/jquery-1.8.3.min.js" ></script>
    <script src="${ctx}/js/jquery.cookie.js" ></script>
    <script>
    	$(document).ready(function(){
    		$("#loginBtn").click(function() {
    			var userName = $("#userName").val();
    			if (userName == null || userName.trim().length == 0) {
    				alert(请输入用户名);
    				return;
    			}
    			var password = $("#password").val();
    			if (password == null || password.trim().length == 0) {
    				alert(请输入密码);
    				return;
    			}
    			$.post("${ctx}/user/login", {userName:userName, userPwd:password}, function(resp) {
    			
    				if (resp.resultCode == 0) {
    					alert(resp.resultMessage);
    				} else {
    					// 登录成功
    					alert(resp.resultMessage);
    					$.cookie("userId", resp.result.userIdString);
    					$.cookie("userName", resp.result.userName);
    					$.cookie("realName", resp.result.realName);
    					window.location.href = "${ctx}/main"
    				}
    			});
    		});
    	})
    
    </script>
    
    
</html>