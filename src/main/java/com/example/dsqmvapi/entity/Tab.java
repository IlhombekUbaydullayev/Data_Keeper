package com.example.dsqmvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tab extends SuperClass {
    private String tabName;
    @JsonIgnore
    @ManyToOne
    private SecondaryPage secondaryPage;

    @JsonIgnore
    @OneToMany(mappedBy = "tabs",cascade = CascadeType.ALL)
    private List<ThirdPage> tabs;

    @JsonIgnore
    @OneToMany(mappedBy = "tabs",cascade = CascadeType.ALL)
    private List<Request> requests;
    public Tab(String tabName) {
        this.tabName = tabName;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tabs",cascade = CascadeType.ALL)
    private List<ErrorMessage> errorMessages;
}
