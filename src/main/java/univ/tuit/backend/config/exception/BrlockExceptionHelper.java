package univ.tuit.backend.config.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import univ.tuit.backend.config.exception.klass.*;
import univ.tuit.backend.helper.APIResponse;
import univ.tuit.backend.helper.FailureMessage;
import univ.tuit.backend.helper.ResponseBuilder;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BrlockExceptionHelper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<APIResponse> handleExceptions(Exception exception) {
        FailureMessage failureMessage = new FailureMessage();

        failureMessage.setExceptionMessage(UnexpectedException.class.toString());
        failureMessage.setExceptionMessage(exception.getMessage());
        return ResponseBuilder.buildOK(null, failureMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessRefusedException.class)
    protected ResponseEntity<APIResponse> handleAccessRefusedException(AccessRefusedException e) {
        FailureMessage failureMessage = new FailureMessage();
        failureMessage.setExceptionName(AccessRefusedException.class.toString());
        failureMessage.setExceptionMessage(e.getMessage());
        return ResponseBuilder.buildOK(null, failureMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    protected ResponseEntity<APIResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        FailureMessage failureMessage = new FailureMessage();

        failureMessage.setExceptionName(AlreadyExistsException.class.toString());
        failureMessage.setExceptionMessage(e.getMessage());

        return ResponseBuilder.buildOK(null, failureMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<APIResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        FailureMessage failureMessage = new FailureMessage();

        failureMessage.setExceptionName(DataIntegrityViolationException.class.toString());
        failureMessage.setExceptionMessage(e.getMessage());

        return ResponseBuilder.buildOK(null, failureMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<APIResponse> handleInvalidParameterException(InvalidParameterException e) {
        FailureMessage failureMessage = new FailureMessage();

        failureMessage.setExceptionMessage(InvalidParameterException.class.toString());
        failureMessage.setExceptionMessage(e.getMessage());

        return ResponseBuilder.buildOK(null, failureMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<APIResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        FailureMessage failureMessage = new FailureMessage();

        failureMessage.setExceptionMessage(InvalidParameterException.class.toString());
        failureMessage.setExceptionMessage(e.getMessage());

        return ResponseBuilder.buildOK(null, failureMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnexpectedException.class)
    protected ResponseEntity<APIResponse> handleUnexpectedException(UnexpectedException e) {
        FailureMessage failureMessage = new FailureMessage();

        failureMessage.setExceptionMessage(UnexpectedException.class.toString());
        failureMessage.setExceptionMessage(e.getMessage());

        return ResponseBuilder.buildOK(null, failureMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
