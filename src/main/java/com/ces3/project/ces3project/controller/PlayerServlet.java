package com.ces3.project.ces3project.controller;

import com.ces3.project.ces3project.config.ServiceConfig;
import com.ces3.project.ces3project.dao.PlayerDAO;
import com.ces3.project.ces3project.dto.PaginationDTO;
import com.ces3.project.ces3project.model.Player;
import com.ces3.project.ces3project.service.PlayerService;
import com.ces3.project.ces3project.utils.UtilMethods;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Optional;

@WebServlet(name = "player-servlet", value = "/players")
public class PlayerServlet extends HttpServlet {

    private PlayerService playerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        playerService = ServiceConfig.getPlayerService();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();

        String action = req.getParameter("action");
        if ("transfer".equalsIgnoreCase(action)) {
            handlePlayerTransferRequest(req, resp);
            return;
        }

        if(req.getParameter("id") == null) {
            out.print(gson.toJson(playerService.getAllPlayers()));
        } else {
            Optional<Player> player = playerService.getPlayerById(Integer.valueOf(req.getParameter("id")));
            if (player.isPresent()) {
                out.print(gson.toJson(player.get()));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"message\": \"Player not found\"}");
            }
        }
        out.flush();
    }

    private void handlePlayerTransferRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            int reqPlayerId = Integer.parseInt(req.getParameter("playerId"));
            int reqTeamId = Integer.parseInt(req.getParameter("newTeam"));
            playerService.transferPlayerToTeam(reqPlayerId, reqTeamId);
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print("{\"message\": \"Player transferred successfully\"}");

        } catch(IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        JsonObject reqBody = UtilMethods.getParamsFromBody(req);
        PrintWriter out = resp.getWriter();

        try {
            playerService.createPlayer(new Player(
                    reqBody.get("id").getAsInt(),
                    reqBody.get("name").getAsString(),
                    reqBody.get("lastName").getAsString(),
                    new Date(reqBody.get("birthDate").getAsLong()),
                    reqBody.get("nationality").getAsString(),
                    reqBody.get("position").getAsString(),
                    reqBody.get("number").getAsInt(),
                    reqBody.get("teamId").getAsInt(),
                    reqBody.get("isActive").getAsBoolean()
            ));
            resp.setStatus(HttpServletResponse.SC_CREATED);
            out.print("{\"message\": \"Player created successfully\"}");
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            out.print("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\": \"Error creating player: " + e.getMessage() + "\"}");
        }
        out.flush();
    }
}
