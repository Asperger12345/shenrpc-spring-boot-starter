package com.shen.rpc.provider;

import com.shen.rpc.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Mediator {


    public static Map<String ,BeanMethod> map=new ConcurrentHashMap();

    private volatile static Mediator instance;

    private Mediator(){}

    public static Mediator getInstance(){
        if(instance==null){
            synchronized (Mediator.class){
                if(instance==null){
                    instance=new Mediator();
                }
            }
        }
        return instance;
    }

    public  Object process(Message message){
        String key=message.getClassName()+"."+message.getMethodName();
        BeanMethod beanMethod=map.get(key);
        if(beanMethod==null){
            return null;
        }
        Object bean=beanMethod.getBean();
        Method method=beanMethod.getMethod();
        try {
            return method.invoke(bean,message.getArgs());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
