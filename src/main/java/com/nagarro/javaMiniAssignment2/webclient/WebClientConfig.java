package com.nagarro.javaMiniAssignment2.webclient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.nagarro.javaMiniAssignment2.constants.UrlConstant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient api1WebClient() {
		int connectionTimeout = 2000;
		int readTimeout = 2000;
		int writeTimeout = 2000;

		HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
				.responseTimeout(Duration.ofMillis(connectionTimeout))
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

		return WebClient.builder().baseUrl(UrlConstant.GET_RANDOM_USERS)
				.clientConnector(new ReactorClientHttpConnector(httpClient)).build();
	}

	@Bean
	public WebClient.Builder api2WebClient() {
		int connectionTimeout = 1000;
		int readTimeout = 1000;
		int writeTimeout = 1000;

		HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
				.responseTimeout(Duration.ofMillis(connectionTimeout))
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
	}

	@Bean
	public WebClient.Builder api3WebClient() {
		int connectionTimeout = 1000;
		int readTimeout = 1000;
		int writeTimeout = 1000;

		HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
				.responseTimeout(Duration.ofMillis(connectionTimeout))
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
	}

}
