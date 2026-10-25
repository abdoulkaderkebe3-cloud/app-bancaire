package Kebe.application_bancaire.handlers;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionRepresentation {

    public String errorMessage;

    public String errorSource;

    public Set<String> validationErrors;

}
