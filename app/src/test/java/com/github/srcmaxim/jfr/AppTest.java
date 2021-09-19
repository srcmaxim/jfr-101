package com.github.srcmaxim.jfr;

import org.junit.jupiter.api.Test;
import org.moditect.jfrunit.EnableEvent;
import org.moditect.jfrunit.JfrEventTest;
import org.moditect.jfrunit.JfrEvents;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.moditect.jfrunit.ExpectedEvent.event;
import static org.moditect.jfrunit.JfrEventsAssert.assertThat;

@JfrEventTest
public class AppTest {

    public JfrEvents jfrEvents = new JfrEvents();

    @Test
    @EnableEvent("jfr.app.JfrEvent")
    @EnableEvent("jdk.GarbageCollection")
    @EnableEvent("jdk.ThreadSleep")
    public void hasJfrEvent() throws Exception {
        var app = new App();
        app.main(new String[0]);
        System.gc();
        TimeUnit.SECONDS.sleep(1);

        jfrEvents.awaitEvents();

        assertThat(jfrEvents).contains(event(JfrEvent.NAME)
                .with("message", "Hello World!!!"));
        assertThat(jfrEvents).contains(event("jdk.GarbageCollection"));
        assertThat(jfrEvents).contains(event("jdk.ThreadSleep")
                .with("time", Duration.ofSeconds(1)));
    }

}
