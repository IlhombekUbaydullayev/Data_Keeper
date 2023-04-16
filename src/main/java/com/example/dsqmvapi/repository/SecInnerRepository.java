package com.example.dsqmvapi.repository;

import com.example.dsqmvapi.entity.ThirdInner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecInnerRepository extends BaseRepository<ThirdInner> {

}
