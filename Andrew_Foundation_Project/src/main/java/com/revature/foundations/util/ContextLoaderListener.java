package com.revature.foundations.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.daos.ReimbursementsDAO;
import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.services.ReimbursementsServices;
import com.revature.foundations.services.TokenService;
import com.revature.foundations.services.UsersService;
import com.revature.foundations.servlets.AuthServlet;
import com.revature.foundations.servlets.ReimburseServlet;
import com.revature.foundations.servlets.UsersServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing Foundations Project...");

        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);

        UserDAO userDAO = new UserDAO();
        UsersService userService = new UsersService(userDAO);
        UsersServlet userServlet = new UsersServlet(tokenService, userService, mapper);

        AuthServlet authServlet = new AuthServlet(tokenService, userService, mapper);

        ReimbursementsDAO ReimbursementDAO = new ReimbursementsDAO();
        ReimbursementsServices ReimbursementService = new ReimbursementsServices(ReimbursementDAO);
        ReimburseServlet reimbursementServlet = new ReimburseServlet(tokenService, ReimbursementService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("ReimburseServlet", reimbursementServlet).addMapping("/reimburse"))


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down Foundations Project...");
    }
}