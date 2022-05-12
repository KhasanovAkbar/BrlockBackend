package univ.tuit.backend.config.exception.klass;

import lombok.Getter;

@Getter
public class InvalidParameterException extends RuntimeException {


    public InvalidParameterException(String message) {
        super(message);
    }
}
