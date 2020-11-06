package com.omio.springbootgrpctest;

import com.goeuro.search2.pi.proto.PiboxGetOfferRequest;
import com.goeuro.search2.pi.proto.PiboxGetOfferResponse;
import com.goeuro.search2.pi.proto.PiboxOfferServiceGrpc;
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
            .forAddress("10.132.0.78", 8710).usePlaintext().build();
        consumeGetOfferService(channel);
        //consumeHelloService(channel);
        //consumeCalculatorService(channel);
        channel.shutdown();
    }

    private static void consumeGetOfferService(ManagedChannel channel) {
        PiboxOfferServiceGrpc.PiboxOfferServiceBlockingStub piboxOfferServiceBlockingStub = PiboxOfferServiceGrpc.newBlockingStub(channel);

        PiboxGetOfferRequest piboxGetOfferRequest = PiboxGetOfferRequest.newBuilder().setOfferStoreId("a22a7776-6d19-4b0b-b860-2b5c1418dd00::c97fed37-2722-4e1f-8395-bb42aa55e6ff").build();

        PiboxGetOfferResponse offer = piboxOfferServiceBlockingStub.getOffer(piboxGetOfferRequest);

        System.out.println("offer ====> "+offer);
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
