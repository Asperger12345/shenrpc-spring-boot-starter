package com.shen.rpc.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {

    private final ExecutorService executorService= Executors.newCachedThreadPool();

    @Value("${provider.port}")
    private int port;


    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(port);
            while(true){
                Socket socket=serverSocket.accept();
                executorService.execute(new ServiceHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
