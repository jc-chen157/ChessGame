import tornado.ioloop
import tornado.web

from tornado.options import define, options, parse_command_line


class IndexHandler(tornado.web.RequestHandler):

    @tornado.web.asynchronous
    def get(self):
        self.write("This is your response\n")
        self.finish()

class WebSocketHandler(tornado.web.WebSocketHandler):

    def __init__(self):
        self.ws_clients = []

    def open(self):
        if(self not in self.ws_clients)
        self.ws_clients.append(self)
        print("WebSocket opened")

    def on_message(self, message):
        for client in ws_clients:
            client.write_message(u"You said: " + message)

    def on_close(self):
        print("WebSocket closed")


app = tornado.web.Application([
    (r'/', IndexHandler),
])

if __name__ == '__main__':
    parse_command_line()
    app.listen(8886)
    tornado.ioloop.IOLoop.instance().start()
