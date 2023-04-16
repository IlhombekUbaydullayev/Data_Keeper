package com.example.dsqmvapi.services;

import com.example.dsqmvapi.entity.*;
import org.springframework.ui.Model;

public interface ThirdPageService {
    String getAll(Long index3, String periodicity, Model model);

    String create(ThirdPage thirdPage, Model model, Long third, Long tabId, String periodicity);

    String innerCreate(ThirdInner second, Model model, String sec, Long third, Long tabId, String periodicity);

    String delete(Long index, Model model, Long index2, Long tabId, String periodicity);

    String deleteInner(Long index, Model model, Long index2, Long tabId, String periodicity);

    String createTab(String periodicity, Tab tab, Model model, Long third);

    String getAllOnlayn(Long index3, String periodicity, Model model, Long save);

    String deleteTab(Long index3, String periodicity, Long tabId, Model model);

    String editTab(String periodicity, Tab tab, Model model, Long third, Long tabId);

    String createRequest(Request request, Model model, Long third, Long tabId, String periodicity);

    String deleteRequest(Long index, Long index2, String periodicity, Long tabId, Model model);

    String createError(ErrorMessage errorMessage, Model model, Long third, Long tabId, String periodicity);

    String deleteError(Long index, Long index2, String periodicity, Long tabId, Model model);

    String updateReq(Request request, Model model, Long third, Long tabId, String periodicity, String id);

    String update(ThirdPage second, Model model, Long third, Long tabId, String periodicity, String sec);

    String updateInner(ThirdInner second, Model model, Long third, Long tabId, String periodicity, String sec);

    String updateError(ErrorMessage second, Model model, Long third, Long tabId, String periodicity, String sec);
}
