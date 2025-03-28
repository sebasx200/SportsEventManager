package com.ces3.project.ces3project.controller;

import com.ces3.project.ces3project.config.ServiceConfig;
import com.ces3.project.ces3project.model.Event;
import com.ces3.project.ces3project.model.EventStatus;
import com.ces3.project.ces3project.service.EventService;
import com.ces3.project.ces3project.utils.UtilMethods;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet(name = "event-servlet", value = "/events")
public class EventServlet extends HttpServlet {

    private EventService eventService;

    @Override
    public void init() throws ServletException {
        eventService = ServiceConfig.getEventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        try{
            if (req.getParameter("id") == null) {
                out.println(gson.toJson(eventService.getAllEvents()));
            } else {
                Optional<Event> event = eventService.getEventById(Integer.valueOf(req.getParameter("id")));
                if (event.isPresent()) {
                    out.println(gson.toJson(event));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"message\": \"Event not found\"}");
                }
            }
        } catch (NoSuchElementException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"message\": \"There are no events in the database\"}");
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Invalid id entered\"}");
        }
        out.flush();
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JsonObject reqBody = UtilMethods.getParamsFromBody(req);
        PrintWriter out = resp.getWriter();
        try{

            JsonArray teamsIdArray = reqBody.get("teamsId").getAsJsonArray();
            ArrayList<Integer> teamsId = new ArrayList<>();
            for (JsonElement element : teamsIdArray) {
                teamsId.add(element.getAsInt());
            }
            eventService.createEvent(new Event(
                    reqBody.get("name").getAsString(),
                    new Date(reqBody.get("date").getAsLong()),
                    reqBody.get("place").getAsString(),
                    reqBody.get("sport").getAsString(),
                    teamsId,
                    reqBody.get("capacity").getAsInt(),
                    reqBody.get("soldTickets").getAsInt(),
                    EventStatus.valueOf(reqBody.get("status").getAsString().toUpperCase())
            ));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print("{\"message\": \"Event created successfully\"}");
        } catch (IllegalArgumentException e){
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }
}
