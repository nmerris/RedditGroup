package com.example.reddit;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .authorizeRequests()

                // anyone can see the default route, in real life a customer would just go straight to the purchase page
                // just keeping it all in one place for testing, and because this is a learning experience
                .antMatchers("/", "/index", "/allposts").permitAll()

                // customer can also see the purchaseproduct page route, and therefore also will be able to see the
                // receipt/confirmation page, which is just a diff view in same route
                .antMatchers("/newpost", "/userposts").access("hasRole('ROLE_USER')")

                .anyRequest().authenticated() // after authentication, all routes will be accessible

                // below: this just points to the page that will SHOW when user tries to access any route that requires auth
                // after successful login, they will automatically continue to whatever link they clicked to trigger the auth form
                .and().formLogin().loginPage("/login").permitAll()
                .and().httpBasic() // allows authentication in the URL itself
                // logout via httprequest, not very secure but ok for now
                // NOTE: unlike .antMatchers(antPatters...) above, .logoutSuccessUrl must be a ROUTE, in this case the default route works well
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().

                // customer can only purchase products and see their receipt
                        withUser("user").password("pass").roles("USER");


    }

}