package com.deepak.tools.test.servlets.spec_3_1;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AtomicInteger reqNum = new AtomicInteger();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("Req param : " + request.getParameter("logoff"));
            if ("true".equalsIgnoreCase(request.getParameter("logoff"))) {
                request.getSession().invalidate();
            } else {
                request.getSession();//dummy to create a session once
            }
            response.getWriter().write("helloooo servlet: " + reqNum.incrementAndGet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

