package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.HomePage;
import com.example.dsqmvapi.entity.SecondaryPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecondaryPageRepository extends BaseRepository<SecondaryPage> {
    List<SecondaryPage> findByHomePageIdOrderById(Long homePage_id);
    @Query(value = "select * from secondary s where LOWER(s.name) like %:keyword%", nativeQuery = true)
    List<SecondaryPage>  findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from secondary s where LOWER(s.name) like %:keyword%", nativeQuery = true)
    SecondaryPage findByName(@Param("keyword") String keyword);
}
