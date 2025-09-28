package com.kirubankamaraj.accounts.servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirubankamaraj.sbm.ShardByException;
import com.kirubankamaraj.sbm.ShardByMappingRegistry;
import com.kirubankamaraj.accounts.UserResourceAPI;
import com.kirubankamaraj.accounts.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kiruban-11285
 * @date 05/07/25
 */

@WebServlet(
        name = "ProfileView",
        urlPatterns = {"/profiles/(\\d+)"}
)
public class UserViewServlet extends HttpServlet {

    private static String URI = "/profiles/(\\d+)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        Long userID = Long.valueOf(req.getRequestURI());

        Long oldUserID = null;

        try {

            oldUserID = ShardByMappingRegistry.setCurrentUserID(userID);

            User user = UserResourceAPI.getCurrentUserData();

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(user);

            resp.setStatus(HttpServletResponse.SC_OK);

            resp.getWriter().print(jsonString);

        } catch (ShardByException | IOException e) {

            throw new RuntimeException(e);
        }
        finally {
            ShardByMappingRegistry.setCurrentUserID(oldUserID);
        }
    }
}
