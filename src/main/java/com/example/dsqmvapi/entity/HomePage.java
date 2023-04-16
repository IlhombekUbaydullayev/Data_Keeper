package com.example.dsqmvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "home")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomePage extends SuperClass {
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "homePage",cascade = CascadeType.ALL)
    private List<SecondaryPage> secondaryPages;
}
