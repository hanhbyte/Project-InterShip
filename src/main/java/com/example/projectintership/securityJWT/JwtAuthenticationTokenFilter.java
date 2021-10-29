package com.example.projectintership.securityJWT;

import com.example.projectintership.model.user.AppUser;
import com.example.projectintership.service.app.AppUserService;
import com.example.projectintership.service.jwt.JwtService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

  private final static String TOKEN_HEADER = "authorization";
  @Autowired
  private JwtService jwtService;
  @Autowired
  private AppUserService userService;

  @Autowired
  @Override
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }

  @Override
  public void doFilter(ServletRequest request,
      ServletResponse response,
      FilterChain chain) throws IOException,
      ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String authToken = httpServletRequest.getHeader(TOKEN_HEADER);
    if (jwtService.validateTokenLogin(authToken)) {
      String username = jwtService.getUsernameFromToken(authToken);
      AppUser user = userService.loadUserByUsername(username);
      if (user != null) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialNonExpired = true;
        boolean accountNonLocked = true;
        UserDetails userDetails = new User(username,
            user.getPassword(),
            enabled,
            accountNonExpired,
            credentialNonExpired,
            accountNonLocked,
            user.getAuthorities());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities());
        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    chain.doFilter(request, response);
  }
}
