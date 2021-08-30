package de.toor.gangshit.exceptions;

public class TooManyDJsException extends Exception {

    public TooManyDJsException() {
        super("Es gibt bereits zu viele DJs");
    }

}
