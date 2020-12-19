package com.company.mvc4.api;

import com.company.mvc4.data.Car;
import com.company.mvc4.data.CarsRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ApiCarController {

    private CarsRepository repo;

    public ApiCarController() {
        repo = new CarsRepository();
    }

    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        return repo.getCars();
    }
}
