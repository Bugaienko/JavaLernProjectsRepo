package ua.bugaienko.pizzaSiteApp.config.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.bugaienko.pizzaSiteApp.models.Person;
import ua.bugaienko.pizzaSiteApp.security.PersonDetailServiceApi;
import ua.bugaienko.pizzaSiteApp.security.PersonDetails;
import ua.bugaienko.pizzaSiteApp.services.UserDetailService;

import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Sergii Bugaienko
 */

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

//    @Autowired
//    private UserDetailService userDetailService;
    @Autowired
    private PersonDetailServiceApi personDetailServiceApi;

    private Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    public AuthTokenFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO filter
        String path = request.getServletPath();
        System.out.println("path " + path);
        if (path.startsWith("/api/")) {
            System.out.println("Запрос к api");
            try {
                System.out.println("filter work");
                String jwt = parseJwt(request);
                if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);

                    UserDetails userDetails = personDetailServiceApi.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            filterChain.doFilter(request, response);
            return;

        } else {
            System.out.println("Else");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                System.out.println("Dot 1");
                if (authentication != null) {
//                    PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//                    UserDetails userDetails = personDetailServiceApi.loadUserByUsername(personDetails.getUsername());
//                    System.out.println(userDetails);
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

//                PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//                System.out.println(personDetails);
                filterChain.doFilter(request, response);




//                PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//                UserDetails user = (UserDetails) personDetails.getPerson();
//                if (user != null) {
//                    System.out.println(user.getAuthorities());
//                }

            }

        }

    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }

}
