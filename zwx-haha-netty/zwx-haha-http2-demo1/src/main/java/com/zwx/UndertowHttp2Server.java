package com.zwx;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HeaderValues;

import static io.undertow.UndertowOptions.ENABLE_HTTP2;

/**
 * @author: haha
 * @date : 2020-11-15 23:34
 */
public class UndertowHttp2Server {
    public static void main(final String[] args) {
        Undertow server =
                Undertow.builder()
                        .addHttpListener(8080, "localhost")
                        .setServerOption(ENABLE_HTTP2, true)
                        .setHandler(
                                new HttpHandler() {
                                    @Override
                                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                                        System.out.println();
                                        System.out.println("RequestPath-> " + exchange.getRequestPath());
                                        System.out.println("RequestMethod-> " + exchange.getRequestMethod());
                                        HeaderMap requestHeaders = exchange.getRequestHeaders();
                                        for (HeaderValues requestHeader : requestHeaders) {
                                            System.out.println(requestHeader.toString());
                                        }
//                                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                                        exchange.getResponseSender().send("Hello World");
                                    }
                                })
                        .build();
        server.start();
    }
}
