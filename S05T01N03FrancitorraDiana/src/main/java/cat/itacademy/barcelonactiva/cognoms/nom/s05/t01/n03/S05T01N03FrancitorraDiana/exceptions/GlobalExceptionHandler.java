package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03FrancitorraDiana.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(WebClientResponseException.NotFound.class)
    public ResponseEntity<ErrorMessage> handleClientErrorException(WebClientResponseException.NotFound ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WebClientResponseException.Conflict.class)
    public ResponseEntity<ErrorMessage> handleConflictException(WebClientResponseException.Conflict ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(WebClientResponseException.BadRequest ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientResponseException.InternalServerError.class)
    public ResponseEntity<ErrorMessage> handleServerErrorException(WebClientResponseException.InternalServerError ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                ex.getRawStatusCode(),
                new Date(),
                ex.getStatusText(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.valueOf(ex.getRawStatusCode()));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorMessage> handleRestClientException(RestClientException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Error de cliente: " + ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
