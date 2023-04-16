package com.example.dsqmvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request extends SuperClass {
    private String name;
    private String mark;
    private String m;
    private String t;
    private String s;
    private String r;

    @ManyToOne
    @JsonIgnore
    private Tab tabs;
    @ManyToOne
    @JsonIgnore
    private SecondaryPage secondaryPage;
}
