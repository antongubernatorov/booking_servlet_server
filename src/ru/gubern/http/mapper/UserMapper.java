package ru.gubern.http.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.gubern.http.dto.UserDto;
import ru.gubern.http.entity.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .image(object.getImage())
                .birthday(object.getBirthday())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }

    public static UserMapper getInstance(){
        return INSTANCE;
    }
}
