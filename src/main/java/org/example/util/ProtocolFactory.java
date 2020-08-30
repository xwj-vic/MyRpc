package org.example.util;

import org.apache.commons.lang3.StringUtils;
import org.example.protocol.MyHttpProtocal;
import org.example.protocol.Protocol;

/**
 * 通过工厂来创建一个Protocol，方便以后拓展
 * 尽管现在只有一个http
 * 先按这样结构写着
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {
        String name = System.getProperty("protocolName");
        if (StringUtils.isEmpty(name))
            name = "http";
        switch (name) {
            case "http": return new MyHttpProtocal();
            default:break;
        }
        return new MyHttpProtocal();
    }
}
