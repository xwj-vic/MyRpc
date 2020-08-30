package org.example.tomcat;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.example.bean.Invocation;
import org.example.provider.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
//            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);class
            ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
           Invocation invocation = (Invocation)ois.readObject();
            String interfaceName = invocation.getInterfaceName();
            Class implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamType());
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

            System.out.println("执行结果:" + result);
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
