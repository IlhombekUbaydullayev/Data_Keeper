package com.example.dsqmvapi.services.impl;

import com.example.dsqmvapi.entity.HomePage;
import com.example.dsqmvapi.entity.SecondaryPage;
import com.example.dsqmvapi.repository.HomePageRepository;
import com.example.dsqmvapi.repository.SecondaryPageRepository;
import com.example.dsqmvapi.services.HomePageService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {

    HomePageRepository repository;
    SecondaryPageRepository secondaryPageRepository;

    public HomePageServiceImpl(HomePageRepository repository,
                               SecondaryPageRepository secondaryPageRepository) {
        this.repository = repository;
        this.secondaryPageRepository = secondaryPageRepository;
    }

    @Override
    public String getAll(Model model) {
        List<HomePage> list = repository.findByOrderById();
        model.addAttribute("index", list);
        model.addAttribute("homePage", new HomePage());
        return "index";
    }

    @Override
    public String create(HomePage homePage, Model model) {
        homePage.setName(homePage.getName().replaceAll("'", "`"));
        repository.save(homePage);
        List<HomePage> list = repository.findByOrderById();
        model.addAttribute("index", list);
        return "index";
    }

    @Override
    public String delete(Long index, Model model) {
        repository.deleteById(index);
        List<HomePage> list = repository.findByOrderById();
        model.addAttribute("homePage", new HomePage());
        model.addAttribute("index", list);
        return "index";
    }

    @Override
    public String search(Model model, String keyword) {
        List<HomePage> homePages = repository.findByKeyword(keyword.toLowerCase());
        if (!homePages.isEmpty()) {
            model.addAttribute("homePage", new HomePage());
            model.addAttribute("index", homePages);
            System.out.println(homePages);
            return "index";
        }
        List<SecondaryPage> secondaryPages = secondaryPageRepository.findByKeyword(keyword.toLowerCase());
        if (!secondaryPages.isEmpty()) {
            model.addAttribute("secondaryPage", new SecondaryPage());
            model.addAttribute("name", "Asosiy");
            model.addAttribute("second", secondaryPages);
            return "secondary";
        }
        model.addAttribute("homePage", new HomePage());
        model.addAttribute("index", repository.findByOrderById());
        return "index";
    }

    @Override
    public String update(HomePage homePage, String index, Model model) {
        homePage.setName(homePage.getName().replaceAll("'","`"));
        System.out.println("id = " + index);
        long l = Long.parseLong(index);
        HomePage page = repository.findById(l).orElseThrow();
        page.setName(homePage.getName());
        repository.save(page);
        List<HomePage> list = repository.findByOrderById();
        model.addAttribute("homePage", new HomePage());
        model.addAttribute("index", list);
        return "index";
    }
}
