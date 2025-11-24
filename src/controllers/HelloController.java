package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import difexamples.ClientComponent;

@RestController
public class HelloController {

    private final ClientComponent clientComponent1;
    private final ClientComponent clientComponent2;

    public HelloController(ClientComponent clientComponent1,
                           ClientComponent clientComponent2) {
        this.clientComponent1 = clientComponent1;
        this.clientComponent2 = clientComponent2;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello from ClientComponent = " + clientComponent1;
    }

    @GetMapping("/test-clients")
    public String testClients() {
        return "clientComponent1 = " + clientComponent1 +
                "\nclientComponent2 = " + clientComponent2;
    }
}
