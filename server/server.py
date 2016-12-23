import tornado.ioloop
import tornado.web
import tornado.websocket

from tornado.options import define, options, parse_command_line

ws_clients = []

class IndexHandler(tornado.web.RequestHandler):

    @tornado.web.asynchronous
    def get(self):
        self.write("This is your response\n")
        self.finish()

class WebSocketHandler(tornado.websocket.WebSocketHandler):

    def open(self):
        if self not in ws_clients:
            if len(ws_clients) >= 2:
                self.set_status(401)
                self.on_close()
            ws_clients.append(self)
            print("WebSocket opened")

    def on_message(self, message):
        for client in ws_clients:
            client.write_message(message )

    def on_close(self):
        ws_clients.remove(self)
        print("WebSocket closed")

app = tornado.web.Application(
handlers=[
    (r'/', IndexHandler),
    (r'/websocket/', WebSocketHandler),
    ]
)

if __name__ == '__main__':
    parse_command_line()
    app.listen(8886)
    tornado.ioloop.IOLoop.instance().start()
