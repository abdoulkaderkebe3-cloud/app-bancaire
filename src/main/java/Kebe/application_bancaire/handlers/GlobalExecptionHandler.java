package Kebe.application_bancaire.handlers;


import Kebe.application_bancaire.exceptions.ObjectValidationException;
import Kebe.application_bancaire.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception){

        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("object not valid exception has occurred")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();
         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                 .body(representation);

    }

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMessages())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException() {
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("le mail existe deja")
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }
}
