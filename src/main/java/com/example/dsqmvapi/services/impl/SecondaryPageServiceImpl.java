package com.example.dsqmvapi.services.impl;

import com.example.dsqmvapi.entity.HomePage;
import com.example.dsqmvapi.entity.SecondaryPage;
import com.example.dsqmvapi.entity.Tab;
import com.example.dsqmvapi.repository.HomePageRepository;
import com.example.dsqmvapi.repository.SecRepository;
import com.example.dsqmvapi.repository.SecondaryPageRepository;
import com.example.dsqmvapi.repository.TabsRepository;
import com.example.dsqmvapi.services.SecondaryPageService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SecondaryPageServiceImpl implements SecondaryPageService {

    SecondaryPageRepository repository;
    HomePageRepository homePageRepository;
    TabsRepository tabsRepository;
    SecRepository secRepository;

    public SecondaryPageServiceImpl(
            SecondaryPageRepository repository, HomePageRepository homePageRepository,
            TabsRepository tabsRepository, SecRepository secRepository) {
        this.repository = repository;
        this.homePageRepository = homePageRepository;
        this.tabsRepository = tabsRepository;
        this.secRepository = secRepository;
    }

    @Override
    public String getAll(Long index, Model model) {
        HomePage homePage = homePageRepository.findById(index).orElseThrow();
        model.addAttribute("second", repository.findByHomePageIdOrderById(index));
        model.addAttribute("name",homePage.getName());
        model.addAttribute("sec", homePage.getId());
        model.addAttribute("secondaryPage", new SecondaryPage());
        return "secondary";
    }

    @Override
    public String create(SecondaryPage secondaryPage, Model model, Long sec) {
        secondaryPage.setName(secondaryPage.getName().replaceAll("'", "`"));
        secondaryPage.setRecipient(secondaryPage.getRecipient().replaceAll("'", "`"));
        secondaryPage.setInfoSender(secondaryPage.getInfoSender().replaceAll("'", "`"));
        HomePage homePage = homePageRepository.findById(sec).orElseThrow();
        secondaryPage.setHomePage(homePage);
        repository.save(secondaryPage);
        List<SecondaryPage> list = repository.findByHomePageIdOrderById(sec);
        model.addAttribute("name",homePage.getName());
        model.addAttribute("second", list);
        model.addAttribute("sec", sec);
        Tab def = tabsRepository.save(new Tab("page"));
        def.setSecondaryPage(secondaryPage);
        Tab save = tabsRepository.save(def);
        save.setDeleted(true);
        tabsRepository.save(save);
        System.out.println(sec);
        return "secondary";
    }

    @Override
    public String delete(Long index, Model model, Long index2) {
        repository.deleteById(index);
        List<SecondaryPage> list = repository.findByHomePageIdOrderById(index2);
        model.addAttribute("name",homePageRepository.findById(index2).orElseThrow().getName());
        model.addAttribute("secondaryPage", new SecondaryPage());
        model.addAttribute("second", list);
        model.addAttribute("sec", index2);
        System.out.println(index);
        return "secondary";
    }

    @Override
    public String update(SecondaryPage index2, Long sec, String second, Model model) {
        long l = Long.parseLong(second);
        index2.setName(index2.getName().replaceAll("'","`"));
        index2.setRecipient(index2.getRecipient().replaceAll("'","`"));
        index2.setInfoSender(index2.getInfoSender().replaceAll("'","`"));
        index2.setPeriodicity(index2.getPeriodicity().replaceAll("'","`"));
        index2.setMethod(index2.getMethod().replaceAll("'","`"));
        SecondaryPage secondaryPage = repository.findById(l).orElseThrow();
        HomePage homePage = homePageRepository.findById(sec).orElseThrow();
        secondaryPage.setName(index2.getName());
        secondaryPage.setInfoSender(index2.getInfoSender());
        secondaryPage.setMethod(index2.getMethod());
        secondaryPage.setPeriodicity(index2.getPeriodicity());
        secondaryPage.setRecipient(index2.getRecipient());
        repository.save(secondaryPage);
        model.addAttribute("second", repository.findByHomePageIdOrderById(sec));
        model.addAttribute("name",homePage.getName());
        model.addAttribute("sec", homePage.getId());
        model.addAttribute("secondaryPage", new SecondaryPage());
        return "secondary";
    }
}
