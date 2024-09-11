package ru.gubern.http.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Ticket {
    private Long id;
    private String passenger_no;
    private String passenger_name;
    private Long flight_id;
    private String seat_no;
    private Integer cost;

    public Ticket(Long id, String passenger_no, String passenger_name, Long flight_id, String seat_no, Integer cost) {
        this.id = id;
        this.passenger_no = passenger_no;
        this.passenger_name = passenger_name;
        this.flight_id = flight_id;
        this.seat_no = seat_no;
        this.cost = cost;
    }
}
