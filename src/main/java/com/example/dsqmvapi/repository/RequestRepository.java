package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.Request;
import com.example.dsqmvapi.entity.ThirdPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends BaseRepository<Request> {
    List<Request> findBySecondaryPageIdAndTabsIdOrderById(Long secondaryPage_id, Long tabs_id);
    List<Request> deleteAllBySecondaryPage_IdAndTabsId(Long secondaryPage_id, Long tabs_id);
}
