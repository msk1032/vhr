package org.studyhub.vhr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.HrService;
import org.studyhub.vhr.utils.WriteHandler;

/**
 * @author haoren
 * @create 2023-02-22 19:05
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAccessDecisionManager customAccessDecisionManager;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;


    @Autowired
    HrService hrService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customAccessDecisionManager);
                        o.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((req, res, authentication) -> {

                    RespBean ok = RespBean.ok("logout success");
                    WriteHandler.write(ok,res);

                })
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, exception) -> {
                    RespBean ok = RespBean.error(exception.getMessage());
                    WriteHandler.write(ok,resp);

                });
        http.addFilterAt(usernameAndPasswordAuthenticationFilter(),UsernameAndPasswordAuthenticationFilter.class);
    }

    @Bean
    UsernameAndPasswordAuthenticationFilter usernameAndPasswordAuthenticationFilter() throws Exception {
        UsernameAndPasswordAuthenticationFilter filter = new UsernameAndPasswordAuthenticationFilter();
        filter.setUsernameParameter("username");
        filter.setPasswordParameter("password");
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/doLogin");
        filter.setAuthenticationSuccessHandler((req, res, authentication)-> {
        Hr hr = (Hr) authentication.getPrincipal();
        hr.setPassword(null);
        RespBean ok = RespBean.ok("login success", hr);
        WriteHandler.write(ok,res);
        });
        filter.setAuthenticationFailureHandler((req, res, exception)-> {
            RespBean error = RespBean.error("出错了");
            WriteHandler.write(error,res);
        });
        return filter;
    }
}
