package live.livelab;

import live.livelab.configuration.AppSettingsFactory;
import live.livelab.configuration.IAppSettings;
import live.livelab.nginx.api.dependency.DependencyResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

@EnableSwagger2
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		start();
	}

	public static void start() {
		DependencyResolver.initializeResolver();
		AppSettingsFactory.initializeAppSettings(DependencyResolver.getInstance(IAppSettings.class));
		logger.info("Application Started");
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.directModelSubstitute(LocalDateTime.class, Long.class)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("live.livelab.nginx.api.controller.business"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Nginx RTMP REST API with Swagger")
				.description("Nginx RTMP REST API with Swagger")
				.license("Apache License Version 2.0")
				.version("2.0")
				.build();
	}
}
