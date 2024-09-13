package ru.gubern.http.dao;

import lombok.SneakyThrows;
import ru.gubern.http.entity.Gender;
import ru.gubern.http.entity.Role;
import ru.gubern.http.entity.User;
import ru.gubern.http.util.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UserDao implements Dao<Integer, User> {
    private static final UserDao INSTANCE = new UserDao();
    private UserDao(){}
    public static UserDao getInstance(){
        return INSTANCE;
    }
    private static final String SAVE_SQL = """
            insert into users (name, birthday, email, password, role, gender) values (?,?,?,?,?,?,?)
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD = """
            SELECT * FROM users WHERE email = ? and password = ?
            """;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getBirthday());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPassword());
            preparedStatement.setObject(5, entity.getImage());
            preparedStatement.setObject(6, entity.getRole().name());
            preparedStatement.setObject(7, entity.getGender().name());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
        }
        return entity;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password){
        var connection = ConnectionManager.get();
        var preparedStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD);
        preparedStatement.setObject(1, email);
        preparedStatement.setObject(2, password);
        var resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()){
            user = buildEntity(resultSet);
        }

        return Optional.ofNullable(user);
    }

    private static User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .email(resultSet.getObject("email", String.class))
                .image(resultSet.getObject("image", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();
    }
}
