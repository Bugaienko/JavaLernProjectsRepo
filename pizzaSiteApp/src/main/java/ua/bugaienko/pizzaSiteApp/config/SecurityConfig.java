package ua.bugaienko.pizzaSiteApp.config;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ua.bugaienko.pizzaSiteApp.security.JwtCsrfFilter;
import ua.bugaienko.pizzaSiteApp.security.JwtTokenRepository;

import ua.bugaienko.pizzaSiteApp.services.PersonService;


/**
 * @author Sergii Bugaienko
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
//@OpenAPIDefinition(info = @Info(title = "Pizza site api", version = "1.0", description = "Pizzas @ Cafes Details"))
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String SCHEME_NAME = "basicAuth";
    private static final String SCHEME = "basic";


    private final PersonService personService;
    private  final JwtTokenRepository jwtTokenRepository;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver resolver;



    @Autowired
    public SecurityConfig(PersonService personService, JwtTokenRepository jwtTokenRepository, HandlerExceptionResolver resolver) {
        this.personService = personService;
        this.jwtTokenRepository = jwtTokenRepository;
        this.resolver = resolver;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/registration", "/auth/login", "/error", "/", "/menu",
                        "/about", "/contact", "/cafe/pizza/*", "/pizza/addToFav/*", "/cafe", "/cafe/**", "/swagger-ui/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/scss/**", "/favicon.ico").permitAll()
                .antMatchers("/pizza/checkPrice/*", "pizza/setPrice/*").hasAnyRole("ADMIN")
                .antMatchers("/user/*").hasAnyRole("USER", "ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .addFilterAt(new JwtCsrfFilter(jwtTokenRepository, resolver), CsrfFilter.class)
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/login")
                .authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint((request, response, e) -> resolver.resolveException(request, response, null, e));


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        AAAauth.userDetailsService(personDetailService).passwordEncoder(getPasswordEncoder()); //OLD worked
        auth.userDetailsService(personService).passwordEncoder(getPasswordEncoder());
    }

//    @Bean
//    public OpenAPI customOpenAPI(){
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes(SCHEME_NAME, createSecurityScheme())
//                ).addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));
//    }
//
//    private SecurityScheme createSecurityScheme() {
//        return new SecurityScheme()
//                .name(SCHEME_NAME)
//                .type(SecurityScheme.Type.HTTP)
//                .scheme(SCHEME);
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
