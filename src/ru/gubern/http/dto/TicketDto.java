package ru.gubern.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TicketDto {
    Long id;
    String description;
}
