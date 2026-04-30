package org.example.myservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/multiplication")
public class MultiplicationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String numStr = request.getParameter("number");
        out.println("<html><head><title>Таблиця множення</title></head><body>");
        if (numStr != null) {
            try {
                int number = Integer.parseInt(numStr);

                out.println("<h2>Таблиця множення для " + number + "</h2>");
                out.println("<table border='1'>");

                for (int i = 1; i <= 10; i++) {
                    out.println("<tr>");
                    out.println("<td>" + number + " × " + i + "</td>");
                    out.println("<td>=</td>");
                    out.println("<td>" + (number * i) + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");

            } catch (NumberFormatException e) {
                out.println("<p style='color:red;'>Помилка: введіть коректне число!</p>");
            }
        } else {
            out.println("<p>Число не введено</p>");
        }

        out.println("<br><a href='.'>Назад</a>");
        out.println("</body></html>");
    }
}
