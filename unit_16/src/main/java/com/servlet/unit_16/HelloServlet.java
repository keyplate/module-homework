package com.servlet.unit_16;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "")
public class HelloServlet extends HttpServlet {
    private ClientDB clientDB;

    public void init() throws ServletException {
        clientDB = new ClientDB();
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        clientDB.addClient(new Client(request.getRemoteAddr(), request.getHeader("User-Agent")), request.getRemoteAddr());
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        for (Client client : clientDB.getClientList().values()) {
            if(client.getIp().equals(request.getRemoteAddr())) {
                out.println( "<p><b>" + client + "</b></p>");
            }else{
                out.println("<p>" + client + "</p>");
            }
        }
    }

    public void destroy() {
    }
}