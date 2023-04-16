package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.HomePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomePageRepository extends BaseRepository<HomePage> {
    @Query(value = "select * from home h where LOWER(h.name) like %:keyword%", nativeQuery = true)
    List<HomePage> findByKeyword(@Param("keyword") String keyword);
    List<HomePage> findByOrderById();
}
