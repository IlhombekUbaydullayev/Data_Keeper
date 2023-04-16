package com.example.dsqmvapi.controllers;

import com.example.dsqmvapi.entity.HomePage;
import com.example.dsqmvapi.services.HomePageService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {
    HomePageService service;

    public HomePageController(HomePageService service) {
        this.service = service;
    }

    @GetMapping("/")
    String getAll(Model model) {
        return service.getAll(model);
    }

    @PostMapping("/home/create")
    String create(@ModelAttribute HomePage homePage, Model model) {
        System.out.println(homePage);
        return service.create(homePage, model);
    }

    @GetMapping("/home/create")
    String getHome(Model model) {
        return service.getAll(model);
    }

    @GetMapping("/home/delete")
    String delete(@RequestParam Long index, Model model) {
        System.out.println("id = " + index);
        return service.delete(index, model);
    }

    @GetMapping("/search")
    String search(Model model, @Param("keyword") String keyword) {
        return service.search(model, keyword);
    }

    @RequestMapping(value = "/home/update", method = {RequestMethod.POST, RequestMethod.GET})
    String update(@ModelAttribute HomePage homePage, @Param("third") String third, Model model) {
        return service.update(homePage, third, model);
    }
}
