package org.example.loadBalance;

import org.example.bean.URL;

import java.util.List;
import java.util.Random;

/**
 * 做个最简单的负载均衡
 */
public class LoadBalance {
    public static URL random(List<URL> list) {
        Random random =new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }
}
