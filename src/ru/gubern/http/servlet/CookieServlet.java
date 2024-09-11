package ru.gubern.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    private static final String UNIQUE_ID = "userId";
    public static AtomicInteger counter = new AtomicInteger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookies = req.getCookies();
        if (cookies == null || Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(UNIQUE_ID)).findFirst().isEmpty()){
            var cookie = new Cookie(UNIQUE_ID, "1");
            cookie.setPath("/cookies");
            resp.addCookie(cookie);
            counter.incrementAndGet();
        }
        resp.setContentType("text/html");
        try (var writer = resp.getWriter()) {
            writer.write(counter.get());
        }
    }
}
