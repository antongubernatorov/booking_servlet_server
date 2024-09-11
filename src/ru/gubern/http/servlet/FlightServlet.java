package ru.gubern.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.gubern.http.service.FlightService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private static FlightService flightService = FlightService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("<h1>Список перелётов:</h1>");
            writer.write("<ul>");
            flightService.findAll().forEach(flight -> {
                writer.write("""
                        <li>
                            <a href="/tickets?flightId=%d">%s</a>
                        </li>
                        """.formatted(flight.getId(), flight.getDescription()));
            });
            writer.write("</ul>");
        }
        super.doGet(req, resp);
    }
}
