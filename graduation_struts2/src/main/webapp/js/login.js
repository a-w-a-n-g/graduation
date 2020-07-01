function check(){
	var v1 = document.form.user.value;
	var v2 = document.form.psw.value;
	var v3 = document.form.code.value;
	
	if(v1==null||v1==""){
		document.getElementById("name").placeholder="用户名不能为空！";
		document.form.user.focus();
	}else if(v2==null||v2==""){
		document.getElementById("password").placeholder="密码不能为空！";
		document.form.psw.focus();
	}else if(v3==null||v3==""){
		document.getElementById("code").placeholder="请输入！";
		document.form.code.focus();
	}else{
		 window.form.submit();
	}
}

function enter() {
	if(event.keyCode==13) {
		check();
	}
}

function changeImg(){
    document.getElementById("codeImage").src="validateCode?timestamp="+new Date().getTime();
}