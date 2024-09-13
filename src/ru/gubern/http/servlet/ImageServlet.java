package ru.gubern.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ru.gubern.http.service.ImageService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestURI = req.getRequestURI();
        var imagePath = requestURI.replace("/images", "");
        imageService.get(imagePath)
                .ifPresentOrElse(img ->{
                    resp.setContentType("application/octet-stream");
                    writeImage(img, resp);
                }, () -> resp.setStatus(404));
    }

    @SneakyThrows
    private void writeImage(InputStream img, HttpServletResponse resp) {
        try(img; var outputStream = resp.getOutputStream()){
            int currentByte;
            while((currentByte = img.read()) != -1){
                outputStream.write(currentByte);
            }
        }
    }
}
