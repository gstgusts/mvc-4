package com.company.mvc4;

import com.company.mvc4.data.Car;
import com.company.mvc4.data.CarsRepository;
import com.company.mvc4.dto.CarSearchDto;
import com.company.mvc4.dto.CarUpdateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OwnerController {

    private CarsRepository repo;

    public OwnerController() {
        repo = new CarsRepository();
    }

    @GetMapping("/owners")
    public String owner(Model model) {

        var items = repo.getOwners();

        model.addAttribute("title", "Owners");
        model.addAttribute("owners", items);
        return "owners";
    }

//    @PostMapping("/owners")
//    public String searchOwners(@ModelAttribute("searchDto") OwnerSearchDto searchDto, Model model) {
//
//        var repo = new CarsRepository();
//        var items = repo.getOwners();
//
//        model.addAttribute("title", "Owners");
//        model.addAttribute("owners", items);
//
//        return "owners";
//    }

    @GetMapping("/cars")
    public String getCars(Model model) {

        var items = repo.getCars();

        model.addAttribute("title", "Cars");
        model.addAttribute("cars", items);

        return "cars";
    }

    @PostMapping("/cars")
    public String searchCars(@ModelAttribute("searchDto") CarSearchDto dto, Model model) {
        var items = repo.getCars(dto);

        model.addAttribute("title", "Cars");
        model.addAttribute("cars", items);

        return "cars";
    }

    @GetMapping("/cars/{id}")
    public String editCar(@PathVariable int id, Model model) {

        var car = repo.getCar(id);

        if(car == null) {
            car = new Car();
        }

        model.addAttribute("title", car != null ? car.getNumber() : "");
        model.addAttribute("car", car);
        model.addAttribute("id", id);
        model.addAttribute("owners", repo.getOwners());

        return "cars_edit";
    }

    // after changing data return to the list
    @PostMapping("/cars/{id}")
//    public String saveCar(@PathVariable int id, @ModelAttribute("updateDto") CarUpdateDto dto, Model model) {
    public ModelAndView saveCar(@PathVariable int id, @ModelAttribute("updateDto") CarUpdateDto dto, Model model) {

        var car = repo.getCar(id);

        if(car == null && id != 0) {
            throw new IllegalArgumentException("Item with such id not found");
        }

        if(id == 0) {
            car = new Car();
            car.setId(0);
        }

        // make field validation

        car.setNumber(dto.getNumber());
        car.setVinNumber(dto.getVinNumber());

        var owner = repo.getOwner(dto.getOwnerId());
        car.setOwner(owner);

        if(id != 0) {
            repo.save(car);
        } else {
            repo.addCar(car);
        }

//        model.addAttribute("title", car != null ? car.getNumber() : "");
//        model.addAttribute("car", car);
//        model.addAttribute("id", id);
//
//        return "cars_edit";

          return new ModelAndView("redirect:/cars");
    }
}
