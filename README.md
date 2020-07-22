# spring-boot-grpc-test

The project is created to verify basic grpc-spring boot integration.

`SpringBootGrpcTestApplication` is the grpc server app.

`GrpcClient` is the grpc Client app.

There are two protos defined.

`HelloService.proto`: To just test basic hello service

`CalculatorService.proto`: To check the basic calculation operation

# To Run Project
`mvn clean package`: This will create the stubs required for both server and client

Run `SpringBootGrpcTestApplication` as main program, which runs the grpc server

Run `GrpcClient` as main program, which prints the response consuming from the grpc server.

I see this one has active commits and updates. https://github.com/LogNet/grpc-spring-boot-starter/blob/master/ReleaseNotes.adoc
So used the `grpc-spring-boot-starter` dependency of the above project.