package com.example.dsqmvapi.controllers;

import com.example.dsqmvapi.entity.SecondaryPage;
import com.example.dsqmvapi.repository.SecRepository;
import com.example.dsqmvapi.services.SecondaryPageService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecondaryPageController {

    SecRepository secRepository;

    SecondaryPageService service;

    public SecondaryPageController(SecondaryPageService service, SecRepository secRepository) {
        this.service = service;
        this.secRepository = secRepository;
    }

    @GetMapping("/secondary/getAll")
    String getAll(@RequestParam Long index, Model model) {
        return service.getAll(index, model);
    }

    @RequestMapping(value = "/secondary/create", method = {RequestMethod.POST, RequestMethod.GET})
    String create(@ModelAttribute SecondaryPage secondaryPage, Model model, @Param("sec") Long sec) {
        System.out.println(sec);
        return service.create(secondaryPage, model, sec);
    }

    @GetMapping("/secondary/delete")
    String delete(@RequestParam Long index, @RequestParam Long index2, Model model) {
        return service.delete(index, model, index2);
    }

    @RequestMapping(value = "/secondary/update", method = {RequestMethod.POST, RequestMethod.GET})
    String update(@ModelAttribute SecondaryPage secondaryPage, Model model, @Param("sec") Long sec, @Param("second") String second) {
        return service.update(secondaryPage, sec, second, model);
    }
}
