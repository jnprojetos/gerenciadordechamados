package br.com.gestordechamados.exceptions;

public class ObjectBadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ObjectBadRequestException(String message) {
        super(message);
    }

    public ObjectBadRequestException(Throwable cause) {
        super(cause);
    }
}
