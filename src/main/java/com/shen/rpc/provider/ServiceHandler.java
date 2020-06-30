package com.shen.rpc.provider;


import com.shen.rpc.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceHandler implements Runnable {

    private Socket socket;

    public ServiceHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) objectInputStream.readObject();

            Mediator mediator=Mediator.getInstance();
            Object rs=mediator.process(message);

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rs);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
