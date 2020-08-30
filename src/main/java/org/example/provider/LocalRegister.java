package org.example.provider;

import java.util.HashMap;
import java.util.Map;

public interface LocalRegister {

    static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
