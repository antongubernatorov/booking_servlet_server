package ru.gubern.http.validatior;

import ru.gubern.http.dto.CreateUserDto;
import ru.gubern.http.entity.Gender;
import ru.gubern.http.service.UserService;
import ru.gubern.http.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserValidator(){}

    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Error.of("invalid.date", "Birthday is invalid"));
        }
        if (object.getGender() == null || Gender.valueOf(object.getGender()) == null){
            validationResult.add(Error.of("invalid.gender", "Gender is invalid"));
        }
        return validationResult;
    }
}
