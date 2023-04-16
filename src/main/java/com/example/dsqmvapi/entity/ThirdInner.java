package com.example.dsqmvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThirdInner extends SuperClass {
    private String name;
    private String mark;
    private String m;
    private String t;
    private String s;
    private String r;
    @ManyToOne
    private ThirdPage thirdPage;
}

