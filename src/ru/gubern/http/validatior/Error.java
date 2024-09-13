package ru.gubern.http.validatior;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String code;
    String message;
}
