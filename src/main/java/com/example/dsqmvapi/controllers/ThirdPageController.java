package com.example.dsqmvapi.controllers;

import com.example.dsqmvapi.entity.*;
import com.example.dsqmvapi.services.ThirdPageService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThirdPageController {

    ThirdPageService service;

    public ThirdPageController(ThirdPageService service) {
        this.service = service;
    }

    @GetMapping("/example")
    String getAll(@ModelAttribute Tab tab, @RequestParam Long index3,
                  @RequestParam String periodicity, Model model) {
        model.addAttribute("activePage", "info");
        return service.getAll(index3, periodicity, model);
    }

    @RequestMapping(value = "/new", method = {RequestMethod.POST, RequestMethod.GET})
    String create(@ModelAttribute ThirdPage second, Model model, @Param("third") Long third,
                  @Param("tabId") Long tabId, @Param("periodicity") String periodicity) {
        System.out.println("Tabs : " + tabId);
        return service.create(second, model, third, tabId, periodicity);
    }

    @RequestMapping(value = "/inner/create", method = {RequestMethod.POST, RequestMethod.GET})
    String innerCreate(@ModelAttribute ThirdInner second, Model model, @Param("sec") String sec,
                       @Param("third") Long third, @Param("tabId") Long tabId, @Param("periodicity") String periodicity) {
        return service.innerCreate(second, model, sec, third, tabId, periodicity);
    }

    @GetMapping("/third/delete")
    String delete(@RequestParam Long index, @RequestParam Long index2, @RequestParam Long tabId,
                  @RequestParam String periodicity, Model model) {
        return service.delete(index, model, index2, tabId, periodicity);
    }

    @GetMapping("/inner/delete")
    String deleteInner(@RequestParam Long index, @RequestParam Long index2, Model model,
                       @RequestParam Long tabId, @RequestParam String periodicity) {
        return service.deleteInner(index, model, index2, tabId, periodicity);
    }

    @GetMapping("/tabsOthers")
    String getAllOnlayn(@RequestParam Long index3, @RequestParam String periodicity, Model model, @RequestParam Long save) {
        return service.getAllOnlayn(index3, periodicity, model, save);
    }

    @RequestMapping(value = "/create/tab", method = {RequestMethod.POST, RequestMethod.GET})
    String createTab(@RequestParam String periodicity, @ModelAttribute Tab tab, Model model, @Param("third") Long third) {
        return service.createTab(periodicity, tab, model, third);
    }

    @RequestMapping(value = "/edit/tab", method = {RequestMethod.POST, RequestMethod.GET})
    String editTab(@RequestParam String periodicity, @RequestParam Long tabId, @ModelAttribute Tab tab, Model model, @Param("third") Long third) {
        return service.editTab(periodicity, tab, model, third, tabId);
    }

    @GetMapping("/deleteTab")
    String deleteTab(@RequestParam Long index3, @RequestParam String periodicity, @RequestParam Long tabId, Model model) {
        System.out.println(tabId);
        return service.deleteTab(index3, periodicity, tabId, model);
    }

    @RequestMapping(value = "/new/request", method = {RequestMethod.POST, RequestMethod.GET})
    String createRequest(@ModelAttribute Request request, Model model, @Param("third") Long third,
                         @Param("tabId") Long tabId, @Param("periodicity") String periodicity) {
        System.out.println("Tabs : " + tabId);
        return service.createRequest(request, model, third, tabId, periodicity);
    }

    @GetMapping("/request/delete")
    String deleteRequest(@RequestParam Long index, @RequestParam Long index2, Model model,
                         @RequestParam Long tabId, @RequestParam String periodicity) {
        System.out.println(tabId);
        return service.deleteRequest(index, index2, periodicity, tabId, model);
    }

    @RequestMapping(value = "/new/error", method = {RequestMethod.POST, RequestMethod.GET})
    String createRequest(@ModelAttribute ErrorMessage errorMessage, Model model, @Param("third") Long third,
                         @Param("tabId") Long tabId, @Param("periodicity") String periodicity) {
        System.out.println("Tabs : " + tabId);
        return service.createError(errorMessage, model, third, tabId, periodicity);
    }

    @GetMapping("/error/delete")
    String deleteError(@RequestParam Long index, @RequestParam Long index2, Model model,
                       @RequestParam Long tabId, @RequestParam String periodicity) {
        System.out.println(tabId);
        return service.deleteError(index, index2, periodicity, tabId, model);
    }

    @RequestMapping(value = "/third/update", method = {RequestMethod.POST, RequestMethod.GET})
    String updateReq(@ModelAttribute Request request, Model model, @Param("third") Long third,
                         @Param("tabId") Long tabId, @Param("periodicity") String periodicity, @Param("id") String id) {
        System.out.println("Tabs : " + tabId);
        return service.updateReq(request, model, third, tabId, periodicity, id);
    }

    @RequestMapping(value = "/row/update", method = {RequestMethod.POST, RequestMethod.GET})
    String updateRow(@ModelAttribute ThirdPage second, Model model, @Param("third") Long third,
                  @Param("tabId") Long tabId, @Param("periodicity") String periodicity, @Param("sec") String sec) {
        System.out.println("Tabs : " + tabId);
        return service.update(second, model, third, tabId, periodicity,sec);
    }

    @RequestMapping(value = "/inner/update", method = {RequestMethod.POST, RequestMethod.GET})
    String updateInner(@ModelAttribute ThirdInner second, Model model, @Param("third") Long third,
                     @Param("tabId") Long tabId, @Param("periodicity") String periodicity, @Param("sec") String sec) {
        System.out.println("Tabs : " + tabId);
        return service.updateInner(second, model, third, tabId, periodicity,sec);
    }

    @RequestMapping(value = "/update/error", method = {RequestMethod.POST, RequestMethod.GET})
    String updateError(@ModelAttribute ErrorMessage second, Model model, @Param("third") Long third,
                       @Param("tabId") Long tabId, @Param("periodicity") String periodicity, @Param("sec") String sec) {
        System.out.println("Tabs : " + tabId);
        return service.updateError(second, model, third, tabId, periodicity,sec);
    }

}
