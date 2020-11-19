package com.thrift.demo.server;

import com.thrift.demo.HelloWorldService;
import com.thrift.demo.impl.HelloWorldImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-11-15 17:19
 */
public class HelloServer {
    public static final int SERVER_PORT = 9090;

    public void startServer() {
        try {
            System.out.println("server start ....");
            TProcessor tprocessor = new HelloWorldService.Processor(new HelloWorldImpl());
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (Exception e) {
            System.out.println("Server happened error!!!");
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("thrift server executing...");
        HelloServer server = new HelloServer();
        server.startServer();
    }
}
