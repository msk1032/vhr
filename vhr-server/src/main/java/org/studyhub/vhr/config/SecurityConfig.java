package org.studyhub.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.HrService;

import java.io.PrintWriter;

/**
 * @author haoren
 * @create 2023-02-22 19:05
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    HrService hrService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .successHandler((req, res, authentication)-> {
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = res.getWriter();
                    Hr hr = (Hr) authentication.getPrincipal();
                    hr.setPassword(null);
                    RespBean ok = RespBean.ok("login success", hr);
                    writer.write(new ObjectMapper().writeValueAsString(ok));
                    writer.flush();
                    writer.close();
                })
                .failureHandler((req, res, exception)-> {
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = res.getWriter();
                    RespBean error = RespBean.error(exception.getMessage());
                    writer.write(new ObjectMapper().writeValueAsString(error));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((req, res, authentication) -> {
                    res.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = res.getWriter();
                    RespBean ok = RespBean.ok("logout success");
                    writer.write(new ObjectMapper().writeValueAsString(ok));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
