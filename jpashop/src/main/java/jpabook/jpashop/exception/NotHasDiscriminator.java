package jpabook.jpashop.exception;

public class NotHasDiscriminator extends RuntimeException{
    public NotHasDiscriminator() {
        super();
    }

    public NotHasDiscriminator(String message) {
        super(message);
    }

    public NotHasDiscriminator(String message, Throwable cause) {
        super(message, cause);
    }

    public NotHasDiscriminator(Throwable cause) {
        super(cause);
    }

    protected NotHasDiscriminator(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
