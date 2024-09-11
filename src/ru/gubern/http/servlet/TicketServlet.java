package ru.gubern.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.gubern.http.service.TicketService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private static final TicketService ticketService = TicketService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flightId = req.getParameter("flightId");
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (flightId == null) {
            try (var writer = resp.getWriter()) {
                writer.write("<h1>Список всех билетов:</h1>");
                writer.write("<ul>");
                ticketService.getAll().forEach(flightDto ->
                        writer.write("""
                                <li> %d - %s </li>
                                """.formatted(flightDto.getId(), flightDto.getDescription())));
                writer.write("</ul>");
            }
        } else {
            Long id = Long.valueOf(flightId);
            try (var writer = resp.getWriter()) {
                writer.write("<h1>Список билетов на данный рейс:</h1>");
                writer.write("<ul>");
                ticketService.getAllById(id).forEach(flightDto -> writer.write("""
                        <li>
                            %d - %s
                        </li>
                        """.formatted(flightDto.getId(), flightDto.getDescription())));
                writer.write("</ul>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
