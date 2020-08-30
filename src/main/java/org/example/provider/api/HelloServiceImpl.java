package org.example.provider.api;

/**
 * 模拟一个对外服务
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello " + name + " 欢迎进行远程调用";
    }
}
