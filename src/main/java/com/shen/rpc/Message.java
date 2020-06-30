package com.shen.rpc;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Message implements Serializable {
    private String className;
    private String methodName;
    private Object[] args;
    private Class[] types;
}
