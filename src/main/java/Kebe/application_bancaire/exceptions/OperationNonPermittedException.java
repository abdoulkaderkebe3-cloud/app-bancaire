package Kebe.application_bancaire.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OperationNonPermittedException extends RuntimeException{

    private final String errorMessages;

    private final String operationId;

    private final String source;

    private final String dependency;
}
