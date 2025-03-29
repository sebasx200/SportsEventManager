package com.ces3.project.ces3project.controller;

import com.ces3.project.ces3project.config.ServiceConfig;
import com.ces3.project.ces3project.service.StatisticsService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

@WebServlet(name = "statistics-servlet", value = "/statistics")
public class StatisticsServlet extends HttpServlet {
    private StatisticsService statisticsService;

    @Override
    public void init() throws ServletException {
        statisticsService = ServiceConfig.getStatisticsService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        try{
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(statisticsService.getStatisticsDTO()));
        } catch (NoSuchElementException e){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"message\": \"There are no events in the database\"}");
        }
    }
}
