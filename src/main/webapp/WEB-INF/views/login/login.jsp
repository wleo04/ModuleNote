<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<script src="./js/jquery-3.4.1.min.js"></script>
<body>
	<form id='loginForm'>
		<input type='text' name='userId'/>
		<input type='password' name='loginbtn'/>
		<input type='button' id='loginbtn' value='로그인'/>
	</form>
	
</body>
<script>
	$(document).ready(function(){
		$('#loginbtn').on('click',function(){
			var obj = $('#loginForm').serializeObject();
			console.log(JSON.stringify(obj));
			$.ajax({
					type:'POST',
					data:JSON.stringify(obj),
					contentType:'application/json',
					success:function(data){
						console.log(data);
					},
					url:'/loginTest'
				})
		})
		
	})
		
	$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};	
		
</script>
</html>