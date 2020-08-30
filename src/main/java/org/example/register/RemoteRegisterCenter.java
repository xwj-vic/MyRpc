package org.example.register;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用map模拟实现一个服务注册中心，并把节点持久化到本地
 * 额外：dubbo 用的是zookeeper
 */
public class RemoteRegisterCenter {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();


    /**
     * 服务注册
     * @param interfaceName
     * @param url
     */
    public static void regist(String interfaceName, URL url) {
        List<URL> list = REGISTER.get(interfaceName);
        if (list == null)
            list = new ArrayList<>();
        list.add(url);
        REGISTER.put(interfaceName, list);
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();

        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }

    /**
     * 保存到本地
     */
    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从本地读取
     * @return
     */
    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
