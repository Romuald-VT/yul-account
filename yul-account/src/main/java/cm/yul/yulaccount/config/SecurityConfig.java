package cm.yul.yulaccount.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception
    {
       return  httpSecurity.csrf(AbstractHttpConfigurer::disable)
       .authorizeHttpRequests(authorize -> authorize
                                           .requestMatchers(HttpMethod.POST,"/add").permitAll()
                                           .requestMatchers(HttpMethod.POST,"/activate").permitAll()
                                           .anyRequest().authenticated())
       .build();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
