window.onload = function(){
	waterFallInit({
		parent:"main",
		pin:"pin"
	})
}
	var style = 4;
	window.onclick = function(){
		style++;
		if (style > 4)
			style = 1;
	}
	
function waterFallInit(json){
	var parent = json.parent;
	var pin = json.pin;
	waterFall(parent, pin);
	var data = [{"src":"1.jpg"}, {"src":"2.jpg"}, {"src":"3.jpg"}, {"src":"4.jpg"}, {"src":"5.jpg"}, {"src":"6.jpg"}, {"src":"7.jpg"},{"src":"8.jpg"},
	            {"src":"9.jpg"}, {"src":"10.jpg"}, {"src":"11.jpg"}, {"src":"12.jpg"}, {"src":"13.jpg"}, {"src":"14.jpg"}, {"src":"15.jpg"}, {"src":"16.jpg"}, ];
	
	window.onscroll = function(){
		if (checkScrollSite())
		{
			/*$.ajax({
				type:"GET",
				url:"request.php",
				data:"",
				dataType:"html",
				success:function(data){
					alert(data);
				}
			})*/
			
			var oParent = document.getElementById(parent);
			for ( var i in data) {
				var oPin = document.createElement("div");
				oPin.className = "pin";
				oParent.appendChild(oPin);
				var oBox = document.createElement("div");
				oBox.className = "box";
				oPin.appendChild(oBox);
				var oImg = document.createElement("img");
				loadImg("images/" + data[i].src, callBack, oImg);
				oImg.src = "images/" + data[i].src;
				oBox.appendChild(oImg);
			}
			
			waterFall(parent, pin);  //调用此函数，使新增的数据按照瀑布流布局。
		}
	}
}
	
	//瀑布流布局函数
	function waterFall(parent, pin){
		var oParent = document.getElementById(parent);
		var aPin = getClassObj(oParent, pin);  //获取图片对象数组
		var iPinW = aPin[0].offsetWidth;
		var num = Math.floor(document.documentElement.clientWidth/iPinW);
		oParent.style.cssText = "width:"+num*iPinW+"px; margin:0 auto;";

		var compareArr = [];
		for ( var i = 0; i < aPin.length; i++) {
			if (i<num)
			{
				compareArr[i] = aPin[i].offsetHeight;
			}
			else
			{
				var minH = Math.min.apply(null, compareArr);
				var minKey = getMinKey(compareArr, minH);  //对每个新加的图片对象设置位置
				compareArr[minKey] += aPin[i].offsetHeight;
				setMoveStyle(aPin[i], minH, aPin[minKey].offsetLeft, i, style);
			}
		}
	}
	
	var startNum = 0;
	//设置运动风格
	function setMoveStyle(obj, top, left, index, style){
		if (index <= startNum)
		{
			return;
		}
		var documentW = document.documentElement.clientWidth;
		obj.style.position = "absolute";
		switch (style) {
		case 1:
			obj.style.top = getTotalH()+"px";
			obj.style.left = Math.floor((documentW-obj.offsetWidth)/2)+"px";
			$(obj).stop().animate({
				top:top,
				left:left
			}, 1000)
			break;
		case 2:
			obj.style.top = top+"px";
			obj.style.left = left+"px";
			obj.style.opacity = 0;
			obj.style.filter = "alpha(opacity=0)";
			$(obj).stop().animate({
				opacity:1
			}, 1000)
			break;
		case 3:
			obj.style.top = getTotalH()+"px";
			if (index % 2)
			{
				obj.style.left = -obj.offsetWidth+"px";
			}
			else
			{
				obj.style.left = documentW+"px";
			}
			$(obj).stop().animate({
				top:top,
				left:left
			}, 1000)
			break;
		case 4:
			obj.style.top = getTotalH()+"px";
			obj.style.left = left+"px";
			$(obj).stop().animate({
				top:top,
				left:left
			}, 1000)
			break;
		}
		
		startNum = index;
	}
	
	function callBack(w, h, imgObj)
	{
		imgObj.style.width = 205+"px";
		var scale = w/205;
		imgObj.style.height = Math.floor(h/scale)+"px";
	}
	
	//解决因网络延迟而导致图片无法正常显示问题，提前设置好图片宽高
	function loadImg(url, fn, imgObj)
	{
		var img = new Image();
		img.src = url;
		if (img.complete)
		{
			fn(img.width, img.height, imgObj);
		}
		else
		{
			img.onload = function()
			{
				fn(img.width, img.height, imgObj);
			}
		}
	}
	
	//获取总高度
	function getTotalH()
	{
		var totalH = document.documentElement.scrollHeight || document.body.scrollHeight;
		
		return totalH;
	}
	
	//检验滚动条的位置，在下半部分时，才开始请求数据
	function checkScrollSite()
	{
		var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
		var documentH = document.documentElement.clientHeight;
		
		if (getLastPinH() < scrollTop+documentH)
			return true;
		else
			return false;
	}
	
	function getLastPinH()
	{
		var oParent = document.getElementById("main");
		var aPin = getClassObj(oParent, "pin");
		var lastPinH = aPin[aPin.length-1].offsetTop + Math.floor(aPin[aPin.length-1].offsetHeight/2);
		return lastPinH;
	}
	
	//获取数组中最小值的索引值
	function getMinKey(arr, minH)
	{
		for ( var key in arr) {
			if (arr[key] == minH)
				return key;
		}
	}
	
	//获取类名为className的对象
	function getClassObj(parent, className){
		var obj = parent.getElementsByTagName("*");
		var result = [];
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].className == className)
			{
				result.push(obj[i]);
			}
		}
		
		return result;
	}