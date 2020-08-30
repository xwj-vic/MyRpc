package org.example.proxy;

import org.example.bean.Invocation;
import org.example.bean.URL;
import org.example.loadBalance.LoadBalance;
import org.example.protocol.Protocol;
import org.example.register.RemoteRegisterCenter;
import org.example.util.ProtocolFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 代理层
 * 使用jdk的动态代理
 * 服务消费者调用服务提供者的接口，是通过代理层转发的
 * @param <T>
 */
public class ProxyFactory<T> {
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args, method.getParameterTypes());
                List<URL> urlList = RemoteRegisterCenter.get(interfaceClass.getName()); //从注册中心获取服务接口数据

                Protocol protocol = ProtocolFactory.getProtocol(); //初始化通信协议
                URL url = LoadBalance.random(urlList);
                String result = protocol.send(url, invocation); //转发到协议层
                return result;
            }
        });
    }
}
