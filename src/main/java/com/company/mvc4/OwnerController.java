package com.company.mvc4;

import com.company.mvc4.data.CarsRepository;
import com.company.mvc4.dto.CarSearchDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        model.addAttribute("title", car != null ? car.getNumber() : "");
        model.addAttribute("car", car);
        model.addAttribute("id", id);

        return "cars_edit";
    }
}
