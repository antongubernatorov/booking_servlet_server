package ru.gubern.http.dao;

import ru.gubern.http.entity.Flight;
import ru.gubern.http.entity.FlightStatus;
import ru.gubern.http.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {
    private static final FlightDao INSTANCE = new FlightDao();
    public static final String FIND_ALL = """
           SELECT * FROM flight
            """;
    @Override
    public List<Flight> findAll() {
        try (var connection = ConnectionManager.get()) {
            var prepareStatement = connection.prepareStatement(FIND_ALL);
            var resultSet = prepareStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()){
                flights.add(buildFlight(resultSet));
            }
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    public static FlightDao getInstance(){
        return INSTANCE;
    }

    private FlightDao() {
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {
        return new Flight(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("flight_no", String.class),
                resultSet.getObject("departure_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("departure_airport_code", String.class),
                resultSet.getObject("arrival_date", Timestamp.class).toLocalDateTime(),
                resultSet.getObject("arrival_airport_code", String.class),
                resultSet.getObject("aircraft_id", Integer.class),
                FlightStatus.valueOf(resultSet.getObject("status", String.class))
        );
    }


}
