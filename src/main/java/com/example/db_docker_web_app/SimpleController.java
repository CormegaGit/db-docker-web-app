package com.example.db_docker_web_app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SimpleController {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(SimpleController.class.getName());

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) throws IOException {
        model.addAttribute("appName", appName);
        // Read data from JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> people = Arrays.asList(objectMapper.readValue(new File("springboot_java_demo_app.json"), Person[].class));
        // Add people to the model
        model.addAttribute("people", people);
        return "home";
    }

    @GetMapping("/add")
    public String addPersonForm(Model model) {
        return "addPerson";
    }

    @PostMapping("/add")
    public String addPerson(@RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("country") String country) throws IOException {
        // Add the new person to the JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> people = new ArrayList<>(Arrays.asList(objectMapper.readValue(new File("springboot_java_demo_app.json"), Person[].class))); // Create a modifiable list
        people.add(new Person(firstName, lastName, country));
        try (FileWriter writer = new FileWriter("springboot_java_demo_app.json")) {
            objectMapper.writeValue(writer, people);
        }
        log.info("{\"Demo App\": \"First Name: " + firstName + ", Last Name: " + lastName +", Country: " + country + "\"}");
        return "redirect:/";
    }
}
