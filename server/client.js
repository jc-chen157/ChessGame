var ws = new WebSocket("ws://localhost:8886/websocket/");
ws.addEventListener('message',function(msg){
  console.log(JSON.parse(msg.data));
});
