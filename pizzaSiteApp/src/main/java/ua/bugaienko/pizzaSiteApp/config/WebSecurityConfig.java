package ua.bugaienko.pizzaSiteApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.bugaienko.pizzaSiteApp.config.jwt.AuthTokenFilter;
import ua.bugaienko.pizzaSiteApp.config.jwt.AuthEntryPointJwt;
import ua.bugaienko.pizzaSiteApp.security.PersonDetailServiceApi;
import ua.bugaienko.pizzaSiteApp.services.UserDetailService;

/**
 * @author Sergii Bugaienko
 */

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailService userDetailService;
    private final PersonDetailServiceApi personDetailServiceApi;
    private final AuthEntryPointJwt unauthorizedHandler;

    public WebSecurityConfig(PersonDetailServiceApi personDetailServiceApi, AuthEntryPointJwt unauthorizedHandler) {
        this.personDetailServiceApi = personDetailServiceApi;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }



    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(personDetailServiceApi).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()

                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/auth/registration", "/auth/login", "/error", "/", "/menu",
                        "/about", "/contact", "/cafe/pizza/*", "/pizza/addToFav/*", "/cafe", "/cafe/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/scss/**", "/favicon.ico").permitAll()

                .antMatchers("/pizza/checkPrice/*", "pizza/setPrice/*").hasAnyRole("ADMIN")
                .antMatchers("/user/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
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
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
//                .anyRequest().hasAnyRole("USER", "ADMIN")

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //		http
//		.authorizeRequests(authorizeRequests ->
//				authorizeRequests
//					.antMatchers("/board/*").hasAnyRole("MANAGER", "OPERATOR")
//					.antMatchers("/members/*").hasRole("MANAGER")
//					.antMatchers("/").permitAll())
//		.httpBasic().realmName("org team")
//		.and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }





}
