package ru.gubern.http.service;

import ru.gubern.http.dao.FlightDao;
import ru.gubern.http.dto.FlightDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FlightService {

    private static final FlightService INSTANCE = new FlightService();
    private static final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }
    public List<FlightDto> findAll(){
        return flightDao.findAll().stream()
                .map(flight -> FlightDto.builder()
                        .id(flight.getId())
                        .description("""
                                    %s - %s - %s
                                """.formatted(flight.getDepartureAirportCode(),
                                flight.getArrivalAirportCode(),
                                flight.getStatus())).build()).collect(toList());
    }

    public static FlightService getInstance(){
        return INSTANCE;
    }
}
