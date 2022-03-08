package com.revature.foundations.servlets;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.dtos.requests.*;
import com.revature.foundations.dtos.responses.AppUserResponse;
import com.revature.foundations.dtos.responses.Principal;
import com.revature.foundations.dtos.responses.ReimbursementResponse;
import com.revature.foundations.dtos.responses.ResourceCreationResponse;
import com.revature.foundations.models.ERSUser;
import com.revature.foundations.models.ERSReimbursements;
import com.revature.foundations.models.ERSReimbursementsStatuses;
import com.revature.foundations.services.ReimbursementsServices;
import com.revature.foundations.services.TokenService;
import com.revature.foundations.services.UsersService;
import com.revature.foundations.util.exceptions.AuthenticationException;
import com.revature.foundations.util.exceptions.InvalidRequestException;
import com.revature.foundations.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReimburseServlet extends HttpServlet{

    private final TokenService tokenService;
    private final UsersService userService;
    private final ObjectMapper mapper;

    public ReimburseServlet(TokenService tokenService, UsersService userService, ObjectMapper mapper) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


        // get users (all, by id, by w/e)
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setStatus(401);
            return;
        }

        Principal requester = (Principal) session.getAttribute("authUser");

        if (!requester.getRoleId().equals("FManager")) {
            resp.setStatus(403); // FORBIDDEN
        }

        List<ReimbursementResponse> reimbursements = ReimbursementsServices.getAllReimbursements();
        String payload = mapper.writeValueAsString(reimbursements);
        resp.setContentType("application/json");
        resp.getWriter().write(payload);

    }

    // registration endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter respWriter = resp.getWriter();

        try {

            NewUserRequest newUserRequest = mapper.readValue(req.getInputStream(), NewUserRequest.class);
            ERSUser newUser = userService.register(newUserRequest);
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(newUser.getUserId()));
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }

    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter respWriter = resp.getWriter();

        try{
            Principal ifAdmin = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            //check if time expired on token = null
            //System.out.println(ifAdmin);
            if(!(ifAdmin.getRoleId().equals("Admin"))){
                throw new InvalidRequestException("Not an Admin!");
            }
            UpdateUserRequest updateUser = mapper.readValue(req.getInputStream(), UpdateUserRequest.class);
            ERSUser updatedUser = userService.updatedUser(updateUser);

            resp.setStatus(201); // Succesful
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(updatedUser.getUserId()));
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
            resp.setStatus(400); // BAD REQUEST
            e.printStackTrace();
        } catch (ResourceConflictException e) {
            e.printStackTrace();
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }

    }

}
