package greenting.client;

import com.proto.greeting.GreetingRequest;
import com.proto.greeting.GreetingResponse;
import com.proto.greeting.GreetingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
    public static void doGreet(String name, ManagedChannel channel) {
        System.out.println("calling greet from client");
        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingRequest request = GreetingRequest.newBuilder().setFirstName(name).build();

        GreetingResponse response = stub.greet(request);

        System.out.println("Response was "+response.getResult());
    }
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

        doGreet("Per", channel);

        doGreet("Jonas", channel);

        doGreet("Erik", channel);

        System.out.println("shutting down");
        channel.shutdown();
    }
}
