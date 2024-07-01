package net.javagudies.todo.config;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableMethodSecurity
@Configuration
@AllArgsConstructor
public class SpringSecurity {

private UserDetailsService userDetailsService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
         @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
             httpSecurity.csrf((c)->c.disable()).
                     authorizeHttpRequests((author)->{
                         author.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
                         author.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
                         author.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
                         author.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
                         author.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USERS");

                         author.requestMatchers(HttpMethod.GET,"/api/**").permitAll();

                         author.anyRequest().authenticated();
                     }).httpBasic(Customizer.withDefaults());
            return httpSecurity.build();
        }

//        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//        }
        @Bean
     public UserDetailsService userDetailsService(){
            UserDetails aditya= User.builder().username("Aditya").
                    password(passwordEncoder().encode("aditya")).roles("USER" ).build();

            UserDetails admin=User.builder().username("Admin").
                    password(passwordEncoder().encode("admin")).roles("ADMIN").build();

            return new InMemoryUserDetailsManager(aditya,admin);
        }
}
