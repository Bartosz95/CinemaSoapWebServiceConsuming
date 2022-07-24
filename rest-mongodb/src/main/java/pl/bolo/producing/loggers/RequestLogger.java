package pl.bolo.producing.loggers;

import pl.bolo.producing.api.Logger;

public class RequestLogger implements Logger {

    @Override
    public void write(String message) {
        System.out.println("Request : " + message);
    }
}
