package ru.gubern.http.validatior;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
