package com.omio.springbootgrpctest;

import com.omio.springbootgrpctest.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class SpringBootGrpcTestApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(8080).addService(new HelloServiceImpl()).build();

		server.start();
		server.awaitTermination();
		/*SpringApplication.run(SpringBootGrpcTestApplication.class, args);*/
	}

}
