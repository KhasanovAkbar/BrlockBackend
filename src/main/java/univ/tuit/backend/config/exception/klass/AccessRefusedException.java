package univ.tuit.backend.config.exception.klass;

public class AccessRefusedException extends RuntimeException {
    public AccessRefusedException(String message) {
        super(message);
    }
}
