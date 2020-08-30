package org.example.consumer;

import org.example.provider.api.HelloService;
import org.example.proxy.ProxyFactory;

public class MyConsumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.hello("三胖");
        System.out.println("RPC调用结果：" + result);
    }
}
