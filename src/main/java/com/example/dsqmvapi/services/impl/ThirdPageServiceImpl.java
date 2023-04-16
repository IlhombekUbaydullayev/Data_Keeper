package com.example.dsqmvapi.services.impl;

import com.example.dsqmvapi.entity.*;
import com.example.dsqmvapi.repository.*;
import com.example.dsqmvapi.services.ThirdPageService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ThirdPageServiceImpl implements ThirdPageService {
    SecRepository secRepository;
    SecondaryPageRepository secondaryPageRepository;
    SecInnerRepository secInnerRepository;
    TabsRepository tabsRepository;
    RequestRepository requestRepository;
    ErrorMessageRepository errorMessageRepository;
    ThirdPageRepository thirdPageRepository;
    public ThirdPageServiceImpl(SecRepository secRepository, SecondaryPageRepository secondaryPageRepository,
                                SecInnerRepository secInnerRepository, TabsRepository tabsRepository,
                                RequestRepository requestRepository, ErrorMessageRepository errorMessageRepository, ThirdPageRepository thirdPageRepository) {
        this.secRepository = secRepository;
        this.secondaryPageRepository = secondaryPageRepository;
        this.secInnerRepository = secInnerRepository;
        this.tabsRepository = tabsRepository;
        this.requestRepository = requestRepository;
        this.errorMessageRepository = errorMessageRepository;
        this.thirdPageRepository = thirdPageRepository;
    }

    @Override
    public String getAll(Long index3, String periodicity, Model model) {
        SecondaryPage secondaryPage = secondaryPageRepository.findById(index3).orElseThrow();
        Tab tab = tabsRepository.findBySecondaryPageIdAndDeletedTrue(index3);
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(index3, tab.getId()));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(index3, tab.getId()));
        model.addAttribute("third", secondaryPage.getId());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(index3, tab.getId()));
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(index3));
        model.addAttribute("tab", tabsRepository.findById(tab.getId()).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tab.getId()).orElseThrow().getTabName());
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("createTab", new Tab());
        model.addAttribute("tabId", tab.getId());
        System.out.println(periodicity);
        System.out.println(tab.getId());
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String create(ThirdPage second, Model model, Long third, Long tabId, String periodicity) {
        second.setName(second.getName().replaceAll("'","`"));
        second.setMark(second.getMark().replaceAll("'","`"));
        second.setM(second.getM().replaceAll("'","`"));
        second.setT(second.getT().replaceAll("'","`"));
        second.setS(second.getS().replaceAll("'","`"));
        second.setR(second.getR().replaceAll("'","`"));
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        second.setSecondaryPage(secondaryPage);
        second.setTabs(tabsRepository.findById(tabId).orElseThrow());
        secRepository.save(second);
        List<ThirdPage> all = secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId);
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("newRequest", new Request());
        model.addAttribute("thirdPage", all);
        model.addAttribute("tabId", tabId);
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("third", third);
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("createTab", new Tab());
        model.addAttribute("periodicity", periodicity);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String innerCreate(ThirdInner second, Model model, String sec, Long third, Long tabId, String periodicity) {
        second.setName(second.getName().replaceAll("'","`"));
        second.setMark(second.getMark().replaceAll("'","`"));
        second.setM(second.getM().replaceAll("'","`"));
        second.setT(second.getT().replaceAll("'","`"));
        second.setS(second.getS().replaceAll("'","`"));
        second.setR(second.getR().replaceAll("'","`"));
        ThirdInner save = secInnerRepository.save(second);
        long l = Long.parseLong(sec);
        ThirdPage byId = secRepository.findById(l).orElseThrow();
        save.setThirdPage(byId);
        secRepository.save(byId);
        secInnerRepository.save(second);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("newRequest", new Request());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("third", third);
        model.addAttribute("tabId", tabId);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("periodicity", periodicity);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String delete(Long index, Model model, Long index2, Long tabId, String periodicity) {
        secRepository.deleteById(index);
        List<ThirdPage> list = secRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(index2).orElseThrow();
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(index2));
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("thirdPage", list);
        model.addAttribute("third", index2);
        model.addAttribute("tabId", tabId);
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("createTab", new Tab());
        System.out.println(index);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String deleteInner(Long index, Model model, Long index2, Long tabId, String periodicity) {
        secInnerRepository.deleteById(index);
        List<ThirdPage> list = secRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(index2).orElseThrow();
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(index2));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("thirdPage", list);
        model.addAttribute("third", index2);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("tabId", tabId);
        model.addAttribute("periodicity", periodicity);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String createTab(String periodicity, Tab tab, Model model, Long third) {
        tab.setTabName(tab.getTabName().replaceAll("'","`"));
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        tab.setSecondaryPage(secondaryPage);
        Tab saved = tabsRepository.save(tab);
        List<ThirdPage> all = secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, saved.getId());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("tab", tabsRepository.findById(tab.getId()).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tab.getId()).orElseThrow().getTabName());
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, saved.getId()));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, saved.getId()));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("thirdPage", all);
        model.addAttribute("tabId", saved.getId());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("save", saved.getId());
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String getAllOnlayn(Long index3, String periodicity, Model model, Long save) {
        SecondaryPage secondaryPage = secondaryPageRepository.findById(index3).orElseThrow();
        model.addAttribute("activePage", "info");
        model.addAttribute("third", index3);
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(index3, save));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(index3, save));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(index3, save));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("tab", tabsRepository.findById(save).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(save).orElseThrow().getTabName());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(index3));
        model.addAttribute("createTab", new Tab());
        model.addAttribute("tabId", save);
        System.out.println(save);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String deleteTab(Long index3, String periodicity, Long tabId, Model model) {
        tabsRepository.deleteById(tabId);
        List<Tab> all = tabsRepository.findAll();
        SecondaryPage secondaryPage = secondaryPageRepository.findById(index3).orElseThrow();
        secRepository.deleteAllBySecondaryPage_IdAndTabsId(index3, tabId);
        requestRepository.deleteAllBySecondaryPage_IdAndTabsId(index3, tabId);
        errorMessageRepository.deleteAllBySecondaryPage_IdAndTabsId(index3, tabId);
        model.addAttribute("activePage", "info");
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("third", index3);
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(index3));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("tab", tabsRepository.findAll().get(all.size() - 1).isDeleted());
        model.addAttribute("tabsName", tabsRepository.findAll().get(all.size() - 1).getTabName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("createTab", new Tab());
        model.addAttribute("tabId", tabsRepository.findAll().get(all.size() - 1).getId());
        Tab tab = tabsRepository.findBySecondaryPageIdAndDeletedTrue(index3);
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(index3, tab.getId()));
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(index3,tab.getId()));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(index3,tab.getId()));
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String editTab(String periodicity, Tab tab, Model model, Long third, Long tabId) {
        tab.setTabName(tab.getTabName().replaceAll("'","`"));
        Tab tab1 = tabsRepository.findById(tabId).orElseThrow();
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        tab1.setTabName(tab.getTabName());
        tabsRepository.save(tab1);
        model.addAttribute("activePage", "info");
        model.addAttribute("tab", tabsRepository.findById(tab1.getId()).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tab1.getId()).orElseThrow().getTabName());
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("tabId", tabId);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String createRequest(Request request, Model model, Long third, Long tabId, String periodicity) {
        request.setName(request.getName().replaceAll("'","`"));
        request.setMark(request.getMark().replaceAll("'","`"));
        request.setM(request.getM().replaceAll("'","`"));
        request.setT(request.getT().replaceAll("'","`"));
        request.setS(request.getS().replaceAll("'","`"));
        request.setR(request.getR().replaceAll("'","`"));
        request.setSecondaryPage(secondaryPageRepository.findById(third).orElseThrow());
        request.setTabs(tabsRepository.findById(tabId).orElseThrow());
        requestRepository.save(request);
        List<ThirdPage> all = secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        model.addAttribute("thirdPage", all);
        model.addAttribute("tabId", tabId);
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("createTab", new Tab());
        model.addAttribute("periodicity", periodicity);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String deleteRequest(Long index, Long index2, String periodicity, Long tabId, Model model) {
        requestRepository.deleteById(index);
        List<ThirdPage> list = secRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(index2).orElseThrow();
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(index2));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("thirdPage", list);
        model.addAttribute("third", index2);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("tabId", tabId);
        model.addAttribute("periodicity", periodicity);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String createError(ErrorMessage errorMessage, Model model, Long third, Long tabId, String periodicity) {
        errorMessage.setName(errorMessage.getName().replaceAll("'","`"));
        errorMessage.setMark(errorMessage.getMark().replaceAll("'","`"));
        errorMessage.setM(errorMessage.getM().replaceAll("'","`"));
        errorMessage.setT(errorMessage.getT().replaceAll("'","`"));
        errorMessage.setS(errorMessage.getS().replaceAll("'","`"));
        errorMessage.setR(errorMessage.getR().replaceAll("'","`"));
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        errorMessage.setSecondaryPage(secondaryPage);
        errorMessage.setTabs(tabsRepository.findById(tabId).orElseThrow());
        errorMessageRepository.save(errorMessage);
        List<ThirdPage> all = secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId);
        model.addAttribute("thirdPage", all);
        model.addAttribute("tabId", tabId);
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("createTab", new Tab());
        model.addAttribute("periodicity", periodicity);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String deleteError(Long index, Long index2, String periodicity, Long tabId, Model model) {
        errorMessageRepository.deleteById(index);
        List<ThirdPage> list = secRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(index2).orElseThrow();
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(index2));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(index2, tabId));
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("thirdPage", list);
        model.addAttribute("third", index2);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("tabId", tabId);
        model.addAttribute("periodicity", periodicity);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }

        return "onlayn";
    }

    @Override
    public String updateReq(Request request, Model model, Long third, Long tabId, String periodicity, String id) {
        long l = Long.parseLong(id);
        request.setName(request.getName().replaceAll("'","`"));
        request.setMark(request.getMark().replaceAll("'","`"));
        request.setM(request.getM().replaceAll("'","`"));
        request.setT(request.getT().replaceAll("'","`"));
        request.setS(request.getS().replaceAll("'","`"));
        request.setR(request.getR().replaceAll("'","`"));
        Request request1 = requestRepository.findById(l).orElseThrow();
        request1.setName(request.getName());
        request1.setMark(request.getMark());
        request1.setM(request.getM());
        request1.setT(request.getT());
        request1.setS(request.getS());
        request1.setR(request.getR());
        requestRepository.save(request1);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        model.addAttribute("activePage", "info");
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("tabId", tabId);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }
        return "onlayn";
    }

    @Override
    public String update(ThirdPage second, Model model, Long third, Long tabId, String periodicity, String sec) {
        long l = Long.parseLong(sec);
        ThirdPage thirdPage = secRepository.findById(l).orElseThrow();
        thirdPage.setName(second.getName().replaceAll("'","`"));
        thirdPage.setMark(second.getMark().replaceAll("'","`"));
        thirdPage.setM(second.getM().replaceAll("'","`"));
        thirdPage.setT(second.getT().replaceAll("'","`"));
        thirdPage.setS(second.getS().replaceAll("'","`"));
        thirdPage.setR(second.getR().replaceAll("'","`"));
        secRepository.save(thirdPage);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        model.addAttribute("activePage", "info");
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("tabId", tabId);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }
        return "onlayn";
    }

    @Override
    public String updateInner(ThirdInner second, Model model, Long third, Long tabId, String periodicity, String sec) {
        long l = Long.parseLong(sec);
        ThirdInner thirdPage = secInnerRepository.findById(l).orElseThrow();
        thirdPage.setName(second.getName().replaceAll("'","`"));
        thirdPage.setMark(second.getMark().replaceAll("'","`"));
        thirdPage.setM(second.getM().replaceAll("'","`"));
        thirdPage.setT(second.getT().replaceAll("'","`"));
        thirdPage.setS(second.getS().replaceAll("'","`"));
        thirdPage.setR(second.getR().replaceAll("'","`"));
        secInnerRepository.save(thirdPage);

        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        model.addAttribute("activePage", "info");
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("thirdPage", secRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("tabId", tabId);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }
        return "onlayn";
    }

    @Override
    public String updateError(ErrorMessage second, Model model, Long third, Long tabId, String periodicity, String sec) {
        long l = Long.parseLong(sec);
        ErrorMessage thirdPage = errorMessageRepository.findById(l).orElseThrow();
        thirdPage.setName(second.getName().replaceAll("'","`"));
        thirdPage.setMark(second.getMark().replaceAll("'","`"));
        thirdPage.setM(second.getM().replaceAll("'","`"));
        thirdPage.setT(second.getT().replaceAll("'","`"));
        thirdPage.setS(second.getS().replaceAll("'","`"));
        thirdPage.setR(second.getR().replaceAll("'","`"));
        errorMessageRepository.save(thirdPage);
        SecondaryPage secondaryPage = secondaryPageRepository.findById(third).orElseThrow();
        model.addAttribute("activePage", "info");
        model.addAttribute("tab", tabsRepository.findById(tabId).orElseThrow().isDeleted());
        model.addAttribute("tabsName", tabsRepository.findById(tabId).orElseThrow().getTabName());
        model.addAttribute("thirdPage", secRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("requestPage", requestRepository.findBySecondaryPageIdAndTabsIdOrderById(third, tabId));
        model.addAttribute("errorsResp", errorMessageRepository.findAllBySecondaryPage_IdAndTabsIdOrderById(third, tabId));
        model.addAttribute("name", secondaryPage.getHomePage().getName());
        model.addAttribute("thirds", secondaryPage.getHomePage().getId());
        model.addAttribute("names", secondaryPage.getName());
        model.addAttribute("errors", new ErrorMessage());
        model.addAttribute("newRequest", new Request());
        model.addAttribute("tabs", tabsRepository.findBySecondaryPageIdOrderById(third));
        model.addAttribute("periodicity", periodicity);
        model.addAttribute("createTab", new Tab());
        model.addAttribute("newexample", new ThirdPage());
        model.addAttribute("newexamples", new ThirdInner());
        model.addAttribute("third", third);
        model.addAttribute("tabId", tabId);
        if (periodicity.equals("so`rov asosida")) {
            return "example";
        }
        return "onlayn";
    }

}

