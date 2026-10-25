package Kebe.application_bancaire.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {


        @Getter
        private final Set<String> violations;

        @Setter
        private final String violationSource;


        public String getViolationSource() {
                return violationSource;
        }
}