package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.ThirdPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecRepository extends BaseRepository<ThirdPage> {
    List<ThirdPage> findAllBySecondaryPage_IdAndTabsIdOrderById(Long secondaryPage_id, Long tabs_id);
    List<ThirdPage> findBySecondaryPageIdAndTabsIdOrderById(Long secondaryPage_id, Long tabs_id);
    List<ThirdPage> deleteAllBySecondaryPage_IdAndTabsId(Long secondaryPage_id, Long tabs_id);
}
