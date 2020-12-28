package com.company.mvc4.api;

import com.company.mvc4.data.Car;
import com.company.mvc4.data.CarsRepository;
import com.company.mvc4.dto.CarSearchDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/cars", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Iterable<Car> searchCars(@RequestBody CarSearchDto searchDto) {
       return repo.getCars(searchDto);
    }
}
