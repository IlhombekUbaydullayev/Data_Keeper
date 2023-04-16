package com.example.dsqmvapi.services;

import com.example.dsqmvapi.entity.SecondaryPage;
import org.springframework.ui.Model;

public interface SecondaryPageService {
    String getAll(Long index, Model model);

    String create(SecondaryPage secondaryPage, Model model, Long sec);

    String delete(Long index, Model model, Long index2);

    String update(SecondaryPage index2, Long sec, String second, Model model);
}
