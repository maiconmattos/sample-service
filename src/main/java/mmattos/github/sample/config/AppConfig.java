package mmattos.github.sample.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

  @Value("${metrics.common.tags}")
  private String[] metricsCommonTags;

  /**
   * allows swagger to send requests to API
   */
  @Bean
  @Profile("dev")
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods(PUT.name(), PATCH.name(), POST.name(), DELETE.name(), GET.name());
      }
    };
  }

  @Bean
  public MeterRegistryCustomizer meterRegistryCustomizer() {
    return registry -> registry.config().commonTags(metricsCommonTags);
  }
}
