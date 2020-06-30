package com.shen.rpc.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

public class InitialMediator implements BeanPostProcessor {


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean.getClass().isAnnotationPresent(Service.class)){
            Method[] methods=bean.getClass().getDeclaredMethods();
            for(Method method:methods){
                String key=bean.getClass().getInterfaces()[0].getName()+"."+method.getName();
                BeanMethod beanMethod=new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.map.put(key,beanMethod);
            }
        }
        return bean;
    }
}
