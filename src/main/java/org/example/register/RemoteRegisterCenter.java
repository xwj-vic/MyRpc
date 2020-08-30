package org.example.register;

import org.example.bean.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用map模拟实现一个服务注册中心，并持久化到本地
 * 额外：dubbo 用的是zookeeper
 */
public class RemoteRegisterCenter {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    private static final String FILE_PATH = "/Volumes/项目/MyRpc/temp.txt";


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
            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
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
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
