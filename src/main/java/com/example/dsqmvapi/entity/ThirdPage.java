package com.example.dsqmvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sec")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPage extends SuperClass {
    private String name;
    private String mark;
    private String m;
    private String t;
    private String s;
    private String r;
    @OneToMany(mappedBy = "thirdPage",cascade = CascadeType.ALL)
    @OrderBy("id")
    @JsonIgnore
    private List<ThirdInner> secInners;

    @ManyToOne
    @JsonIgnore
    private Tab tabs;
    @ManyToOne
    @JsonIgnore
    private SecondaryPage secondaryPage;
}

