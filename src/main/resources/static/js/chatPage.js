var webSocket;
var username = sessionStorage.getItem("username");
alert("我的，名字叫    "+username);
var userTo = document.getElementById('userTo').value;
if ("WebSocket" in window)
{
    webSocket = new WebSocket("ws://localhost:9292/ws/" + username);

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
        console.log("数据已接收:" +received_msg);
        var obj = JSON.parse(received_msg);
        console.log("可以解析成json:"+obj.messageType);
        //1代表上线 2代表下线 3代表在线名单 4代表普通消息
        if(obj.messageType==1){
            //把名称放入到selection当中供选择
            var onlineName = obj.username;
            var li = "<li class='setLi'>"+onlineName+"</li><hr>";
            $("#onLineUser").append(li);
            setMessageInnerHTML(onlineName+"上线了");
        }
        else if(obj.messageType==2){
            $("#onLineUser").empty();
            var onlineName = obj.onlineUsers;
            var offlineName = obj.username;
            var li = "<li class='setLi'>"+"--所有--"+"</li><hr>";
            for(var i=0;i<onlineName.length;i++){
                if(!(onlineName[i]==username)){
                    li+="<li class='setLi'>"+onlineName[i]+"</li><hr>"
                }
            }
            $("#onLineUser").append(li);

            setMessageInnerHTML(offlineName+"下线了");
        }
        else if(obj.messageType==3){
            var onlineName = obj.onlineUsers;
            var li = null;
            for(var i=0;i<onlineName.length;i++){
                if(!(onlineName[i]==username)){
                    li+="<li class='setLi'>"+onlineName[i]+"</li><hr>"
                }
            }
            $("#onLineUser").append(li);
            console.log("获取了在线的名单"+onlineName.toString());
        }
        else{
            //var divMsg = "<tr><td style='text-align:left'>"+obj.fromusername+"对"+obj.tousername+"说：<br>"+obj.textMessage+"</td></tr>"
            var divMsg = "<tr><td style='text-align:left'>"+obj.fromusername+"：<br>"+obj.textMessage+"</td></tr>"
            $("#message").append(divMsg);
            //setMessageInnerHTML();
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
    var userFrom = sessionStorage.getItem("username");
    var divMsg = "<tr><td style='text-align:right'>"+userFrom+":<br> "+$("#text").val()+"</td></tr>"
    $("#message").append(divMsg);
    var message = {
        "message":document.getElementById('text').value,
        "from":userFrom,
        "to":userTo
    };
    webSocket.send(JSON.stringify(message));
    $("#text").val("");
}
window.onload = function() {
    var userTo = document.getElementById('userTo').value;
    var li = "<li class='setLi'>"+userTo+"</li><hr>";
    $("#onLineUser").append(li);
}
