package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.ErrorMessage;

import java.util.List;

public interface ErrorMessageRepository extends BaseRepository<ErrorMessage> {
    List<ErrorMessage> findAllBySecondaryPage_IdAndTabsIdOrderById(Long secondaryPage_id, Long tabs_id);
    List<ErrorMessage> deleteAllBySecondaryPage_IdAndTabsId(Long secondaryPage_id, Long tabs_id);
}
