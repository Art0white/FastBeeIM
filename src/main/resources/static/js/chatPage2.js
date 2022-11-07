function getQueryString(name) {
    const url_string = window.location.href; // window.location.href
    const url = new URL(url_string);
    return url.searchParams.get(name);
}

var webSocket;
var appid = 12;
var username = getQueryString("username");
alert("我的，名字叫    " + username);
var userTo = document.getElementById('userTo').value;
if ("WebSocket" in window)
{
    webSocket = new WebSocket("ws://localhost:9292/ws/" + appid + "/" + username);

    //连通之后的回调事件
    webSocket.onopen = function()
    {
        //webSocket.send( document.getElementById('username').value+"已经上线了");
        console.log("已经连通了websocket");
        setMessageInnerHTML("已经连通了websocket");
    };

    //接收后台服务端的消息
    webSocket.onmessage = function (evt)
    {
        var received_msg = evt.data;
        console.log("数据已接收:" + received_msg);
        var obj = JSON.parse(received_msg).message;
        console.log("可以解析成json:"+obj.messageType);
        /**
         * 1: 文本消息
         */
        if(obj.messageType==1){
            var divMsg = "<tr><td style='text-align:left'>"+obj.from+"：<br>"+obj.content+"</td></tr>"
            $("#message").append(divMsg);
        }
    };

    //连接关闭的回调事件
    webSocket.onclose = function()
    {
        console.log("连接已关闭...");
        setMessageInnerHTML("连接已经关闭....");
    };
}

else{
    // 浏览器不支持 WebSocket
    alert("您的浏览器不支持 WebSocket!");
}
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    document.getElementById('userInfo').innerHTML += innerHTML + '<br/>';
}

function closeWebSocket() {
    //直接关闭websocket的连接
    webSocket.close();
}

function send(){
    var userTo = document.getElementById('userTo').value;
    var userFrom = username
    var divMsg = "<tr><td style='text-align:right'>"+userFrom+":<br> "+$("#text").val()+"</td></tr>"
    $("#message").append(divMsg);
    var message = {
        "content":document.getElementById('text').value,
        "from":userFrom,
        "to":userTo,
        "messageType":1,
        "sendMessageType":1,
    };
    webSocket.send(JSON.stringify(message));
    $("#text").val("");
}
window.onload = function() {
    var userTo = document.getElementById('userTo').value;
    var li = "<li class='setLi'>"+userTo+"</li><hr>";
    $("#onLineUser").append(li);
}
