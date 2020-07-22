package com.omio.springbootgrpctest.service;

import com.omio.springbootgrpctest.model.HelloRequest;
import com.omio.springbootgrpctest.model.HelloResponse;
import com.omio.springbootgrpctest.model.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloService extends HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseStreamObserver) {
        log.info("Received HelloService request ===> {} ", request);

        String greeting = new StringBuilder("Hello, ")
            .append(request.getFirstName())
            .append(" ")
            .append(request.getLastName())
            .toString();

        HelloResponse response = HelloResponse
            .newBuilder().setGreeting(greeting).build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}
