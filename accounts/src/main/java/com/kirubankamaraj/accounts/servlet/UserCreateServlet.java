package com.kirubankamaraj.accounts.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirubankamaraj.accounts.model.User;
import com.kirubankamaraj.sbm.ShardByException;
import com.kirubankamaraj.sbm.ShardByMappingRegistry;
import com.kirubankamaraj.accounts.UserException;
import com.kirubankamaraj.accounts.UserResourceAPI;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author kiruban-11285
 * @date 06/07/25
 */


@WebServlet(
        name = "UserCreate",
        urlPatterns = {"/users"}
)
public class UserCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        Long oldUserID = null;

        try {

            Long userID = UserResourceAPI.getNewUserID();

            oldUserID = ShardByMappingRegistry.setCurrentUserID(userID);

            String inputJson = req.getParameter("JSONString");
            ObjectMapper objectMapper = new ObjectMapper();

            User user = objectMapper.readValue(inputJson, User.class);

            user = UserResourceAPI.createUser(user);

            String jsonString = objectMapper.writeValueAsString(user);

            resp.setStatus(HttpServletResponse.SC_CREATED);

            resp.getWriter().print(jsonString);

        } catch (ShardByException | IOException | UserException e) {

            throw new RuntimeException(e);
        }
        finally {
            ShardByMappingRegistry.setCurrentUserID(oldUserID);
        }
    }
}
