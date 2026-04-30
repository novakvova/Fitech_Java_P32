package org.example.myservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "martinServlet", value = "/martin-servlet")
public class MartinServlet extends HttpServlet {
    //як конструктор
    public void init() {}

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException  {
        response.setContentType("text/html");
        response.getWriter()
                .println("Any fool can write code that a computer " +
                        "can understand. Good programmers write code " +
                        "that humans can\n" +
                        "understand");
    }

    public void destroy() {}

}
