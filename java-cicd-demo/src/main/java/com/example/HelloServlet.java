package com.example;
import java.io.IOException;
import java.time.Instant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/")
public class HelloServlet extends HttpServlet {
 @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.setContentType("text/html;charset=UTF-8");
  response.getWriter().println("<!doctype html><html><head><title>Java CI/CD Demo</title></head><body>");
  response.getWriter().println("<h1>Hello from Java on Tomcat!</h1>");
  response.getWriter().println("<p>Deployed with GitHub, Jenkins, Maven, Docker, and an Azure VM.</p>");
  response.getWriter().println("<p>Build timestamp: " + Instant.now() + "</p></body></html>");
 }
}
