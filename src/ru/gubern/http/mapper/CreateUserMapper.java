package ru.gubern.http.mapper;

import ru.gubern.http.dto.CreateUserDto;
import ru.gubern.http.entity.Gender;
import ru.gubern.http.entity.Role;
import ru.gubern.http.entity.User;
import ru.gubern.http.util.LocalDateFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final String IMAGE_FOLDER = "users/";
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private CreateUserMapper(){}
    public static CreateUserMapper getInstance(){
        return INSTANCE;
    }
    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .build();
    }
}
