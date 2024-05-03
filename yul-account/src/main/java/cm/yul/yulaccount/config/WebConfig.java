package cm.yul.yulaccount.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    public void addCorsMapping(CorsRegistry registry)
    {
        registry.addMapping("/**")
        .allowedMethods("GET","POST","PUT","DELETE")
        .allowedOrigins("")
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(3600);
    }

}
