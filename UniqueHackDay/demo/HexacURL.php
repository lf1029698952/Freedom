<?php
echo '<body background="1.png"></body>';

echo "<head>
<style type='text/css'>
body {color : white}
h2 {color : white}
</style>
</head>
";
	
echo 
'
<b>Web Scan</b>
<br />
<br />

<form action ="" method="post">
<b>Url</b> 
<br />
<input type = "text" name="url" size=127 />
<br />

<b>Whitespace</b>
<br />
<input type = "text" name="whitespace" size=40 />
<br />


<input type="submit" name="urlsubmit" />
<br />
</form>
';
//$url1="http://webscan.360.cn/index/checkwebsite?url=";
//$url=$url1.$url;

function curlurl($url)
{
$init = curl_init($url);
curl_setopt($init,CURLOPT_CONNECTTIMEOUT,40);
curl_setopt($init,CURLOPT_TIMEOUT,40);
curl_setopt($init,CURLOPT_RETURNTRANSFER,1);
curl_setopt($init,CURLOPT_MAXREDIRS,0);
curl_setopt($init,CURLOPT_FOLLOWLOCATION,false);
curl_setopt($init,CURLOPT_FRESH_CONNECT,True);
curl_setopt($init,CURLOPT_FORBID_REUSE,True);

if($exec = curl_exec($init))
{
echo "<b>/*! Content Output Start */</b>  ", "<br />", "<br />";

echo $exec , "<br />" , "<br />";

echo "<b>/*! Content Output End */</b>","<br />";

if($info = curl_getinfo($init))
{
if(preg_match("/200/",$info["http_code"]))
{
}
elseif(preg_match("/300|301|302|400|401|403|406|500|501|502|503/",$info["http_code"]))
{
if(preg_match("/300|301|302/",$info["http_code"]))
{
echo "Redirection Mode Detected","<br \>";
}
if(preg_match("/404/",$info["http_code"]))
{
echo "File does not exist","<br \>";
}
elseif(preg_match("/400|401|403|406/",$info["http_code"]))
{
echo "WAF Detected","<br \>";
}
elseif(preg_match("/500/",$info["http_code"]))
{
echo "WAF - Mod_Security Detected","<br \>";
}
elseif(preg_match("/501|502|503/",$info["http_code"]))
{
echo "WAF Detected","<br \>";
}

}

echo "<br />","<b>Server Information</b> : " , "<br />";

echo "<textarea rows=20 cols =105 />";

if($information = get_headers($url))
{
foreach($information as $header)
{
echo $header,"\n";
}
}

echo "Url : " , $info["url"] , "\n"; 
echo "Content_Type : ",$info["content_type"] , "\n";
echo "Http_Code : ",$info["http_code"] , "\n";
echo "Header_Size : ",$info["header_size"] , "\n"; 
echo "Request_Size : ",$info["request_size"] , "\n"; 
echo "Filetime : ",$info["filetime"] , "\n"; 
echo "Ssl_Verify_Result : ",$info["ssl_verify_result"] , "\n"; 
echo "Redirect_Count : ",$info["redirect_count"] , "\n"; 
echo "Total_Time : ",$info["total_time"] , "\n";
echo "Namelookup_Time : ",$info["namelookup_time"] , "\n";
echo "Connect_Time : ",$info["connect_time"] , "\n";
echo "Pretransfer_Time : ",$info["pretransfer_time"] , "\n";
echo "Size_upload : ",$info["size_upload"] , "\n";
echo "Size_Download : ",$info["size_download"] , "\n";
echo "Speed_Download : ",$info["speed_download"] , "\n";
echo "Speed_Upload : ",$info["speed_upload"] , "\n";
echo "Download_Content_Length : ",$info["download_content_length"] , "\n"; 
echo "Upload_Content_Length : ",$info["upload_content_length"] , "\n";
echo "Starttransfer_Time : ",$info["starttransfer_time"] , "\n";
echo "Redirect_Time : ",$info["redirect_time"] , "\n";

echo "</textarea>" , "<br />", "<br />";
}
else
{
if(get_headers($url))
{
$information = get_headers($url);
echo "<textarea rows=20 cols =105 />";

foreach($information as $header)
{
echo $header,"\n";
}
echo "</textarea>" , "<br />", "<br />";
}
else
{

}
}

}
if(curl_errno($init))
{
echo 'Curl error: ' . curl_error($init) . '<br />';
exit();
}

$close = curl_close($init);
}

if(isset($_POST['urlsubmit']))
{
$geturl = $_POST['url'];


if(isset($_POST['whitespace'])!=null)
{
$whitespace = $_POST['whitespace'];
}

$parseurl = str_replace(" ",$whitespace,$geturl); 
$url = $parseurl;


curlurl($url);
}



?>