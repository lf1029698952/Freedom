function getSpan(cobj){
	while(true)
	{
		if (cobj.nextSibling.nodeName != "SPAN")
			cobj = cobj.nextSibling;
		else
			return cobj.nextSibling;
	}
}

function check(obj, info, fun, click)
{
	var sp = getSpan(obj);
	
	obj.onfocus = function(){
		sp.innerHTML = info;
		sp.className = "stats2";
	}
	obj.onblur = function(){
		if (fun(this.value))
		{
			sp.innerHTML = "输入正确";
			sp.className = "stats4";
		}
		else
		{
			sp.innerHTML = info;
			sp.className = "stats3";
		}
	}
	
	if (click == "click")
	{
		obj.onblur();
	}
}

onload = formCheck
function formCheck(click, code){
	var stat = true;
	var password = document.getElementById("password");
	var repass = document.getElementById("repass");
	var realname = document.getElementById("realname");
	var tel = document.getElementById("tel");
	
	check(password, "密码长度为6~20位，不能为空格", function(val){
		if (val.match(/^\S+$/) && val.length >=6 && val.length <= 20)
		{
			return true;
		}
		else
		{
			stat = false;
			return false;
		}
	}, click)
	
	check(repass, "请确认密码，须与上面密码保持一致", function(val){
		if (val.match(/^\S+$/) && val.length >=6 && val.length <= 20 && val == password.value)
		{
			return true;
		}
		else
		{
			stat = false;
			return false;
		}
	}, click)
	
	check(realname, "姓名长度为1~20位，不能为空格", function(val){
		if (val.match(/^\S+$/) && val.length >=3 && val.length <= 20)
		{
			return true;
		}
		else
		{
			stat = false;
			return false;
		}
	}, click)
	
	check(tel, "联系电话为6~15位，必须为数字", function(val){
		if (val.match(/^[0-9]*$/) && val.length >= 6 && val.length <= 15)
		{
			return true;
		}
		else
		{
			stat = false;
			return false;
		}
	}, click)

	return stat;
}