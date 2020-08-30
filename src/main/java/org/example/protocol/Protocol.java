package org.example.protocol;

import org.example.bean.Invocation;
import org.example.bean.URL;

public interface Protocol {
    void start(URL url);

    String send(URL url, Invocation invocation);
}
