package com.example.carspace.controllers;

import com.example.carspace.authentication.User;
import com.example.carspace.models.Car;
import com.example.carspace.repositories.CarRepository;
import com.example.carspace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@Controller
public class CarsController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list-cars")
    public String listCars(Model model) {
        model.addAttribute("carList", carRepository.listAll());
        initUser(model);
        return "car-list";
    }

    @GetMapping("/createCar")
    public String getCreateCarView(Model model) {
        model.addAttribute("car", new Car());
        return "create-car";
    }

    @PostMapping("/createCar")
    public String createCarView(Model model, Car car) {
        initUser(model);

        carRepository.save(car);

        Collection<Car> cars = carRepository.listAll();
        model.addAttribute("carList", cars);

        return "car-list";
    }

    @GetMapping("/editCar")
    public String getEditCarView(Model model, long carId) {
        Car car = carRepository.findById(carId);
        model.addAttribute("carToEdit", car);
        initUser(model);
        return "edit-car";
    }

    @PostMapping("/editCar")
    public String editCar(Model model, Car car, long carId) {
        car.setId(carId);
        carRepository.save(car);
        initUser(model);
        Collection<Car> cars = carRepository.listAll();
        model.addAttribute("carList", cars);

        return "car-list";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(Model model, long carId) {
        carRepository.deleteById(carId);
        initUser(model);
        Collection<Car> cars = carRepository.listAll();
        model.addAttribute("carList", cars);

        return "car-list";
    }

    private void initUser(Model model) {
        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            String username = auth.getName();
            model.addAttribute("username", username);
        }
    }
}
