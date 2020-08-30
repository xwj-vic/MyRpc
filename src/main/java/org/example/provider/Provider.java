package org.example.provider;

import org.example.bean.URL;
import org.example.protocol.Protocol;
import org.example.provider.api.HelloService;
import org.example.provider.api.HelloServiceImpl;
import org.example.register.RemoteRegisterCenter;
import org.example.util.ProtocolFactory;

import java.net.MalformedURLException;


public class Provider {

    public static void main(String[] args) throws MalformedURLException {
//        注册服务，暴露接口
        URL url = new URL("localhost", 8888);
        RemoteRegisterCenter.regist(HelloService.class.getName(), url);

//        本地注册服务的实现类
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);


    }
}
