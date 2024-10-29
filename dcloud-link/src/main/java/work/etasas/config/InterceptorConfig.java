package work.etasas.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import work.etasas.interceptor.LoginInterceptor;

/**
 * @author sas
 * @create 2024-10-22-9:01
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/link/*/**", "/api/group/*/**", "/api/domain/*/**")
                .excludePathPatterns("");

    }
}
