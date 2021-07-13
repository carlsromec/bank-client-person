package com.everis.bankclientepersonal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.everis.bankclientepersonal.handler.CustomerPersonHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {
	
	
	@Bean
	public RouterFunction<ServerResponse> routers(CustomerPersonHandler handler){
		return route(GET("/api/v2/customerperson"),handler::listCustomerPerson)
				.andRoute(GET("/api/v2/customerperson/{id}"), handler::listCustomerPersonId)
				.andRoute(POST("/api/v2/customerperson"), handler::addCustomerPerson)
				.andRoute(PUT("/api/v2/customerperson/{id}"), handler::editCustomerPerson)
				.andRoute(DELETE("/api/v2/customerperson/{id}"), handler::deleteCustomerPerson);
	}

}
