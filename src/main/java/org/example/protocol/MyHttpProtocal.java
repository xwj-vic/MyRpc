package org.example.protocol;

import org.example.bean.Invocation;
import org.example.bean.URL;
import org.example.tomcat.HttpClient;
import org.example.tomcat.HttpServer;

public class MyHttpProtocal implements Protocol {

    /**
     * 开启服务，用的tomcat
     * @param url
     */
    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostName(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostName(), url.getPort(), invocation);
    }
}
