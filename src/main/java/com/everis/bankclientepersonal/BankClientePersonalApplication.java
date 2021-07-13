package com.everis.bankclientepersonal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.everis.bankclientepersonal.model.document.CategoryDoc;
import com.everis.bankclientepersonal.model.document.CustomerPersonDoc;
import com.everis.bankclientepersonal.model.service.CustomerPersonService;

import reactor.core.publisher.Flux;

@EnableEurekaClient
@SpringBootApplication
public class BankClientePersonalApplication implements CommandLineRunner {
	
	@Autowired
	private CustomerPersonService customerPersonService;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(BankClientePersonalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankClientePersonalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("CustomerPersonDoc").subscribe();
		mongoTemplate.dropCollection("CategoryDoc").subscribe();
		
		CategoryDoc VIP = new CategoryDoc("VIP");
		CategoryDoc normal = new CategoryDoc("normal");
		
		Flux.just(VIP,normal)
		.flatMap(customerPersonService::saveCategory)
		.doOnNext(t ->{
			log.info("CategoryDoc created: " + t.getDescription() + ", Id: " + t.getId());
		}).thenMany(
				Flux.just(new CustomerPersonDoc("45302424","carlos","manuel","lima 333","999999999",VIP),
							new CustomerPersonDoc("20302424","juan","ramos","arequipa 333","999999999",normal),
							new CustomerPersonDoc("30302424","luisa","camargo","cusco 333","999666333",VIP)
						
						)
				.flatMap(customerPerson ->{
					return customerPersonService.save(customerPerson);
				})
			)
		.subscribe(customerPerson -> log.info("insert" + customerPerson.getId()));
		
	}

}
