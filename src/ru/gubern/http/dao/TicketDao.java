package ru.gubern.http.dao;

import ru.gubern.http.entity.Ticket;
import ru.gubern.http.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

    private static final TicketDao INSTANCE = new TicketDao();

    private TicketDao(){
    }

    private static final String FIND_ALL = """
            SELECT * FROM ticket
            """;
    private static final String FIND_ALL_BY_ID = """
            SELECT * FROM ticket
            where flight_id = ?
            """;

    @Override
    public List<Ticket> findAll() {
        var connection = ConnectionManager.get();
        try {
            var preparedStatement = connection.prepareStatement(FIND_ALL);
            var resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Ticket buildTicket(ResultSet resultSet) throws SQLException {
        return new Ticket(
                resultSet.getObject("id", Long.class),
                resultSet.getObject("passenger_no", String.class),
                resultSet.getObject("passenger_name", String.class),
                resultSet.getObject("flight_id", Long.class),
                resultSet.getObject("seat_no", String.class),
                resultSet.getObject("cost", BigDecimal.class));
    }

    public static TicketDao getInstance(){
        return INSTANCE;
    }

    public List<Ticket> findAllByFlightId(Long id) throws SQLException {
        try (var connection = ConnectionManager.get()) {
            var preparedStatement = connection.prepareStatement(FIND_ALL_BY_ID);
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while(resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }
            return tickets;
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Ticket entity) {

    }

    @Override
    public Ticket save(Ticket entity) {
        return null;
    }
}
