package com.example.dsqmvapi.services;

import com.example.dsqmvapi.entity.HomePage;
import org.springframework.ui.Model;

public interface HomePageService {
    String getAll(Model model);
    String create(HomePage homePage, Model model);
    String delete(Long index, Model model);
    String search(Model model, String keyword);
    String update(HomePage homePage, String index, Model model);
}
