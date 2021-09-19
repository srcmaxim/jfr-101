package com.github.srcmaxim.jfr;

import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

import static com.github.srcmaxim.jfr.JfrEvent.NAME;

@Name(NAME)
@Label("JfrEvent")
public class JfrEvent extends Event {

    public static final String NAME = "jfr.app.JfrEvent";

    @Label("Message")
    String message;
    
}
