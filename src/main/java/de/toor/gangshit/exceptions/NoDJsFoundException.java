package de.toor.gangshit.exceptions;

public class NoDJsFoundException extends Exception {

    public NoDJsFoundException() {
        super("Es konnten keine DJs gefunden werden");
    }
}
