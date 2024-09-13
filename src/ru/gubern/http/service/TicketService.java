package ru.gubern.http.service;

import ru.gubern.http.dao.TicketDao;
import ru.gubern.http.dto.TicketDto;

import java.sql.SQLException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TicketService {

    private static final TicketService INSTANCE = new TicketService();
    private static final TicketDao ticketDao = TicketDao.getInstance();

    private TicketService(){}

    public static TicketService getInstance(){
        return INSTANCE;
    }

    public List<TicketDto>getAll(){
        return ticketDao.findAll().stream()
                .map(ticket -> TicketDto.builder()
                        .id(ticket.getId())
                        .description("""
                         %s - %s - %s
                         """.formatted(ticket.getPassenger_no(),
                        ticket.getPassenger_name(),
                        ticket.getSeat_no()
                        )).build()).collect(toList());
    }

    public List<TicketDto> getAllById(Long id) throws SQLException {
        return ticketDao.findAllByFlightId(id).stream()
                .map(ticket -> TicketDto.builder()
                        .id(ticket.getId())
                        .description("""
                         %s - %s - %s
                         """.formatted(ticket.getPassenger_no(),
                                ticket.getPassenger_name(),
                                ticket.getSeat_no()
                        )).build()).collect(toList());
    }
}
