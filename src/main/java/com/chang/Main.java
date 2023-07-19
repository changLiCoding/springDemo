package com.chang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Annotations that wrap annotations like: @ComponentScan, @EnableAutoConfiguration and @Configuration
@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setAge(request.age);
        customer.setEmail(request.email);
        customer.setName(request.name);
        customerRepository.save(customer);
    }

    ArrayList<String> followers = new ArrayList<String>(List.of(new String[]{"Kyle", "Kenney", "Timmy", "Eric"}));

    @GetMapping("/whatever")
    public GreetResponse welcome() {
        GreetResponse response = new GreetResponse("Welcome to Greeting",
                false, followers,
                List.of("Java", "Javascript", "Golong", "Python", "Ruby"),
                new Person("Chang", 30, 9999999.99)
                );
        return response;
    }

    record Person (String name, int age, double saving) {

    }
    record GreetResponse(String greeting,
                         boolean isRegistered,
                         ArrayList<String> followers,
                         List<String> favProgrammingLanguages,
                         Person person) {}
    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }
    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();

    }

}

