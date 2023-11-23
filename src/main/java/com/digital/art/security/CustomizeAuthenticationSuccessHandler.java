package com.digital.art.security;

import com.digital.art.dto.MediUserDTO;
import com.digital.art.model.User;
import com.digital.art.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);

        boolean admin = false;

        logger.info("AT onAuthenticationSuccess(...) function!");
        MediUserDTO authUser = (MediUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) userService.findById(authUser.getUserId());

        //**********Session Creation ********
        HttpSession session = request.getSession();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());


        //***********************************

        String page="home";

        if(admin){
            response.sendRedirect(page);
        }else{
            response.sendRedirect(page);
        }

    }

}
