package com.omio.springbootgrpctest;

import com.omio.springbootgrpctest.model.CalculatorRequest;
import com.omio.springbootgrpctest.model.CalculatorResponse;
import com.omio.springbootgrpctest.model.CalculatorServiceGrpc;
import com.omio.springbootgrpctest.model.HelloRequest;
import com.omio.springbootgrpctest.model.HelloResponse;
import com.omio.springbootgrpctest.model.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 6666).usePlaintext().build();
        consumeHelloService(channel);
        consumeCalculatorService(channel);
        channel.shutdown();
    }

    private static void consumeHelloService(ManagedChannel channel) {
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        HelloRequest helloRequest = HelloRequest.newBuilder()
            .setFirstName("Prasad")
            .setLastName("Bandur")
            .build();
        HelloResponse helloResponse = stub.hello(helloRequest);
        log.info("helloResponse ===> {} ", helloResponse);
    }

    private static void consumeCalculatorService(ManagedChannel channel) {
        CalculatorServiceGrpc.CalculatorServiceBlockingStub calcStub = CalculatorServiceGrpc.newBlockingStub(channel);
        CalculatorRequest calculatorRequest = buildCalculatorRequest(1, 2, "+");
        CalculatorResponse calculatorResponse = calcStub.calculate(calculatorRequest);

        log.info("calculatorResponse ===> {} ", calculatorResponse);
    }

    private static CalculatorRequest buildCalculatorRequest(int number1, int number2, String operator) {
        return CalculatorRequest.newBuilder().setNumber1(number1).setNumber2(number2).setOperator(operator).build();
    }
}
