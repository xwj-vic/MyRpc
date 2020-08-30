package org.example.protocol;

import org.example.bean.Invocation;
import org.example.bean.URL;

public class MyHttpProtocal implements Protocol {

    @Override
    public void start(URL url) {

    }

    @Override
    public String send(URL url, Invocation invocation) {
        return null;
    }
}
