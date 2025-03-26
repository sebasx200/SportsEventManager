package com.ces3.project.ces3project.controller;

import com.ces3.project.ces3project.dao.PlayerDAO;
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

    private final PlayerService playerService;

    public PlayerServlet() {
        PlayerDAO playerDAO = new PlayerDAO();
        this.playerService =  new PlayerService(playerDAO);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        playerService.createPlayer(new Player(
                1,
                "Alberto",
                "Rodríguez",
                new Date(19951005),
                "Colombiano",
                "Mediocampista",
                10,
                1,
                true
        ));

        playerService.createPlayer(new Player(
                2,
                "Carlos",
                "González",
                new Date(19981215),
                "Mexicano",
                "Delantero",
                9,
                2,
                true
        ));

        playerService.createPlayer(new Player(
                3,
                "Juan",
                "Martínez",
                new Date(19920723),
                "Argentino",
                "Defensor",
                5,
                3,
                false
        ));
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
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
        super.doGet(req, resp);
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
