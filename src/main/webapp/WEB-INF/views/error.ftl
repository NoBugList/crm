<html>
	<head>
		<title>错误页面</title>
	</head>
	<body>
		<script>
			window.onload = function() {
				alert("${resultInfo.resultMessage}");
				setTimeout(function(){
					window.parent.location.href = "${ctx}/index"; // 父窗体进行跳转
				}, 1000);
			}
		</script>
	</body>
</html>