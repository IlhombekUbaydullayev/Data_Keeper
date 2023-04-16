package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.ThirdPage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThirdPageRepository extends BaseRepository<ThirdPage> {
    List<ThirdPage> findAllBySecondaryPage_IdAndDeletedFalse(Long id);
}
