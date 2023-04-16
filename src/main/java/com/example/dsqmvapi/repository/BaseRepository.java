package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.SuperClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends SuperClass> extends JpaRepository<T,Long>{}
