package com.omio.springbootgrpctest.service;

import com.omio.springbootgrpctest.model.CalculatorResponse;
import com.omio.springbootgrpctest.model.CalculatorServiceGrpc.CalculatorServiceImplBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CalculatorService extends CalculatorServiceImplBase {

    @Override
    public void calculate(com.omio.springbootgrpctest.model.CalculatorRequest request,
                          io.grpc.stub.StreamObserver<com.omio.springbootgrpctest.model.CalculatorResponse> responseObserver) {
        log.info("Received CalculatorService request ===> {} ", request);
        long result = 0;
        switch(request.getOperator()) {
            case "+":
                result = request.getNumber1()+request.getNumber2();
                break;
            case "-":
                result = request.getNumber1() - request.getNumber2();
                break;
            case "*":
                result = request.getNumber1() * request.getNumber2();
                break;
            case "/":
                result = request.getNumber1() / request.getNumber2();
                break;
            default:
                throw new IllegalArgumentException("Illegal operator input. Cannot calculate for operator "+request.getOperator());
        }
        responseObserver.onNext(CalculatorResponse.newBuilder().setResult(result).build());
        responseObserver.onCompleted();
    }

}
