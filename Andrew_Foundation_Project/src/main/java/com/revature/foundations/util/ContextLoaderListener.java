package com.revature.foundations.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.services.TokenService;
import com.revature.foundations.services.UsersService;
import com.revature.foundations.servlets.AuthServlet;
import com.revature.foundations.servlets.UsersServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("Initializing ERS web application...");

        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);

        UserDAO userDAO = new UserDAO();
        UsersService usersService = new UsersService(userDAO);
        UsersServlet usersServlet = new UsersServlet(tokenService, usersService, mapper);
        AuthServlet authServlet = new AuthServlet(tokenService, usersService, mapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("UsersServlet", usersServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Shutting down ERS web application...");
    }

}
