package ru.gubern.http.dto;

import lombok.Builder;
import lombok.Value;
import ru.gubern.http.entity.Gender;
import ru.gubern.http.entity.Role;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    String email;
    String image;
    Role role;
    Gender gender;
    LocalDate birthday;
}
