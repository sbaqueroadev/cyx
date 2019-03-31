package co.com.sbaqueroadev.cyxtera.security;


import co.com.sbaqueroadev.cyxtera.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.cyxtera.model.implementation.Role;
import co.com.sbaqueroadev.cyxtera.services.ApplicationUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOG
            = Logger.getLogger(CustomAccessDeniedHandler.class.getName());

    @Autowired
    private ApplicationUserServiceImpl applicationUserServiceImpl;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOG.warning("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());
            ApplicationUser applicationUser = (ApplicationUser) applicationUserServiceImpl.findByUserName((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            if(applicationUser != null){
                if(applicationUser.getRole().getName().equals(Role.Roles.API.getValue())){
                    response.sendRedirect(request.getContextPath() + "/api/");
                    return;
                }
            }
            response.sendRedirect(request.getContextPath() + "/users/access");
            return;
        }
        response.sendRedirect(request.getContextPath() + "/users/access");
    }
}