package io.maksym.employeemanager.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

//Use @Configuration instead extends WebSecurityConfigurerAdapter (from Spring Security 5)
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthProvider userAuthProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, "/login","/employees/","/employees/login", "/employees/register").permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
//        return http
//                .authorizeHttpRequests(authorizeHttp -> {
//                            authorizeHttp.requestMatchers("/").permitAll();
//                            authorizeHttp.requestMatchers("/employees");
//                            authorizeHttp.requestMatchers("/employees/");
//                            authorizeHttp.anyRequest().authenticated();
//                }
//                )
//                .formLogin(l -> l.defaultSuccessUrl("/employees/login"))
//                .logout(l -> l.logoutSuccessUrl("/"))
//                .addFilterBefore(new JwtAuthFilter(userAuthProvider), AuthenticationFilter.class)
//        .build();

    }
}
