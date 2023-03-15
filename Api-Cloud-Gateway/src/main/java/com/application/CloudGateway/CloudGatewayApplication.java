package com.application.CloudGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	@Bean
	KeyResolver userKeySolver() {
		return exchange -> Mono.just("userKey");
	}

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(
				id -> new Resilience4JConfigBuilder(id).circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()

				).build());
	}

	/**
	 * Un API gateway è un componente architetturale che funge da punto di ingresso
	 * principale per un'architettura basata su microservizi. L'API gateway gestisce
	 * tutte le richieste di ingresso dai clienti e le reindirizza ai servizi
	 * appropriati all'interno del sistema.
	 * 
	 * L'API gateway funge da interfaccia tra il cliente e i microservizi
	 * sottostanti. Agisce come un proxy inverso, nascondendo la complessità della
	 * struttura dei microservizi dal cliente. Ciò significa che i clienti possono
	 * interagire con l'API gateway senza dover preoccuparsi della posizione o della
	 * struttura dei servizi sottostanti.
	 * 
	 * L'API gateway offre anche funzionalità come l'autenticazione,
	 * l'autorizzazione, il controllo degli accessi, la gestione del traffico, il
	 * bilanciamento del carico e la gestione degli errori. Ciò semplifica la
	 * gestione dei microservizi sottostanti e garantisce la scalabilità,
	 * l'affidabilità e la sicurezza dell'intero sistema.
	 */
}
