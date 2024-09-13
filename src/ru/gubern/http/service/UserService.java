package ru.gubern.http.service;

import lombok.SneakyThrows;
import ru.gubern.http.dao.UserDao;
import ru.gubern.http.dto.CreateUserDto;
import ru.gubern.http.dto.UserDto;
import ru.gubern.http.entity.User;
import ru.gubern.http.exception.ValidationException;
import ru.gubern.http.mapper.CreateUserMapper;
import ru.gubern.http.mapper.UserMapper;
import ru.gubern.http.validatior.CreateUserValidator;
import ru.gubern.http.validatior.ValidationResult;

import java.util.Optional;

public class UserService {
    private static final String IMAGE_FOLDER = "users/";
    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private UserService(){}
    public static  UserService getInstance(){
        return INSTANCE;
    }

    @SneakyThrows
    public Integer create(CreateUserDto userDto){
        //validation
        var validationResult = createUserValidator.isValid(userDto);
        if (validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);
        return userEntity.getId();
    }

    public Optional<UserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }
}
