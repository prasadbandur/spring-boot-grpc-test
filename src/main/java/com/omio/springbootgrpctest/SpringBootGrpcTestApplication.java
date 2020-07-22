package com.omio.springbootgrpctest;

import com.omio.springbootgrpctest.service.CalculatorService;
import com.omio.springbootgrpctest.service.HelloService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringBootGrpcTestApplication {
	private static final Logger logger = Logger.getLogger(SpringBootGrpcTestApplication.class.getName());

	@Autowired
	private HelloService helloService;

	@Autowired
	private CalculatorService calculatorService;

	private Server server;

	@EventListener(ApplicationStartedEvent.class)
	public void init() throws IOException, InterruptedException {
		SpringBootGrpcTestApplication grpcServer = new SpringBootGrpcTestApplication();
		grpcServer.start(helloService, calculatorService);
		grpcServer.blockUntilShutdown();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGrpcTestApplication.class, args);
	}

	private void start(HelloService helloService, CalculatorService calculatorService) throws IOException {
		int port = 6666;
		server = ServerBuilder.forPort(port)
			.addService(helloService)
			.addService(calculatorService)
			.build().start();
		logger.info("Server started, listening on " + port);
	}

	private void stop() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon threads.
	 */
	private void blockUntilShutdown() throws InterruptedException {
		if (server != null) {
			server.awaitTermination();
		}
	}
}
