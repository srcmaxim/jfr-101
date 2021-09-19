package com.github.srcmaxim.jfr;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        var time = System.currentTimeMillis();
        var duration = TimeUnit.SECONDS.toMillis(10);
        while (System.currentTimeMillis() < time + duration) {
            var event = new JfrEvent();
            if (!event.isEnabled()) {
                return;
            }
            event.begin();
            event.message = "Hello World!!!";
            event.commit();
        }
    }

}
