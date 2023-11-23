package com.digital.art.security;

import com.digital.art.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**",
                        "/assets/**",
                        "/images/**",
                        "/js/**",
                        "/cssLoging/**","/signUp/**",
                        "/assetsLoging/**",
                        "/addItem/**",
                        "/fontsLoging.icomoon/**",
                        "/allowed-url-pattern/**",
                        "/vendors/**",
                        "/css/**",
                        "/main/**"
                ).permitAll()
                .anyRequest().hasAnyRole("Admin")
                .and()
                .formLogin().loginPage("/login").successHandler(customizeAuthenticationSuccessHandler).permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDeniedPage")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

}
