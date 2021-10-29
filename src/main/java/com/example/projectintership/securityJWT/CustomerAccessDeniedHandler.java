package com.example.projectintership.securityJWT;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomerAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException ade)throws IOException, ServletException{
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.getWriter().write("Access Denied!");
  }

}
