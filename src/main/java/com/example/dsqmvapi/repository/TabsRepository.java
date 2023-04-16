package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.Tab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TabsRepository extends BaseRepository<Tab> {
    List<Tab> findBySecondaryPageIdOrderById(Long secondaryPage_id);
    Tab findBySecondaryPageIdAndDeletedTrue(Long secondaryPage_id);
}
