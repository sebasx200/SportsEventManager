package com.ces3.project.ces3project.controller;

import com.ces3.project.ces3project.dao.PlayerDAO;
import com.ces3.project.ces3project.dao.TeamDAO;
import com.ces3.project.ces3project.model.Team;
import com.ces3.project.ces3project.service.PlayerService;
import com.ces3.project.ces3project.service.TeamService;
import com.ces3.project.ces3project.utils.UtilMethods;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "team-servlet", value = "/teams")
public class TeamServlet extends HttpServlet {

    private final TeamService teamService;

    public TeamServlet() {
        TeamDAO teamDAO = new TeamDAO();
        this.teamService = new TeamService(teamDAO);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        teamService.createTeam(new Team(
                "Deportivo Tapitas",
                "Football",
                "Medellín",
                new Date(20060101),
                "logoD.png"
        ));
        teamService.createTeam(new Team(
                "Escombros FC",
                "Baloncesto",
                "La Pintada",
                new Date(20070413),
                "logoE.png"
        ));
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        if (req.getParameter("id") == null) {
            out.print(gson.toJson(teamService.getAllTeams()));
        } else {
            Optional<Team> team = teamService.getTeamById(Integer.valueOf(req.getParameter("id")));
            if (team.isPresent()) {
                out.print(gson.toJson(team.get()));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"message\": \"Team not found\"}");
            }
        }
        out.flush();
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JsonObject reqBody = UtilMethods.getParamsFromBody(req);
        PrintWriter out = resp.getWriter();
        try {
            teamService.createTeam(new Team(
                    reqBody.get("name").getAsString(),
                    reqBody.get("sport").getAsString(),
                    reqBody.get("city").getAsString(),
                    new Date(reqBody.get("fundationDate").getAsLong()),
                    reqBody.get("logo").getAsString()
            ));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print("{\"message\": \"Team created successfully\"}");
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Error creating team: " + e.getMessage() + "\"}");
        }
        out.flush();
    }
}
