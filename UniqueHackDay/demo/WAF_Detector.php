<?php

function wafdetect($injurlx)
{
if($header = get_headers($injurlx,1))
{
if(preg_match("/200/",$header[0]))
{
}
elseif(preg_match("/300|301|302|400|401|403|406|500|501|502|503/",$header[0],$wafdetected))
{
if(preg_match("/300|301|302/",$wafdetected[0]))
{
echo "Redirection Mode Detected","<br \>";
}
if(preg_match("/404/",$wafdetected[0]))
{
echo "File does not exist","<br \>";
}
elseif(preg_match("/400|401|403|406/",$wafdetected[0]))
{
echo "WAF Detected","<br \>";
}
elseif(preg_match("/500/",$wafdetected[0]))
{
echo "WAF - Mod_Security Detected","<br \>";
}
elseif(preg_match("/501|502|503/",$wafdetected[0]))
{
echo "WAF Detected","<br \>";
}

}

}
else
{
echo "Get_Headers() is not supported","<br />";
}

}

?>