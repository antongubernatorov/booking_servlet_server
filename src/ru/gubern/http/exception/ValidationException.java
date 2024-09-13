package ru.gubern.http.exception;

import lombok.Getter;
import ru.gubern.http.validatior.Error;

import java.util.List;

public class ValidationException extends RuntimeException{

    @Getter
    private final List<Error> errors;


    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
