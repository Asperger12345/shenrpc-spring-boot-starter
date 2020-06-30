package com.shen.rpc.consumer;

import com.shen.rpc.Message;
import org.springframework.beans.factory.annotation.Value;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcHandler implements InvocationHandler {

    @Value("${provider.host}")
    private String host;
    @Value("${provider.port}")
    private int port;



    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket(host, port);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Message message = Message.builder()
                .className(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .args(args)
                .types(method.getParameterTypes()).build();
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return objectInputStream.readObject();

    }
}
