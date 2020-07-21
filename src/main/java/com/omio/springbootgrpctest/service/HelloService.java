package com.omio.springbootgrpctest.service;

import com.omio.springbootgrpctest.model.HelloRequest;
import com.omio.springbootgrpctest.model.HelloResponse;
import com.omio.springbootgrpctest.model.HelloServiceGrpc.HelloServiceImplBase;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class HelloService extends HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseStreamObserver) {
        System.out.println("Received request ===> "+request);

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
