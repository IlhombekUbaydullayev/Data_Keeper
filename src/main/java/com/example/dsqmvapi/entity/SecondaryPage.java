package com.example.dsqmvapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "secondary")
@AllArgsConstructor
@NoArgsConstructor
public class SecondaryPage extends SuperClass {
    private String name;
    private String infoSender;
    private String recipient;
    private String method;
    private String periodicity;
    @ManyToOne
    @JsonIgnore
    private HomePage homePage;

    @OneToMany(mappedBy = "secondaryPage",cascade = CascadeType.ALL)
    private List<ThirdPage> secondaryPages;

    @OneToMany(mappedBy = "secondaryPage",cascade = CascadeType.ALL)
    private List<Tab> tabs;

    @OneToMany(mappedBy = "secondaryPage",cascade = CascadeType.ALL)
    private List<Request> requests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfoSender() {
        return infoSender;
    }

    public void setInfoSender(String infoSender) {
        this.infoSender = infoSender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public List<ThirdPage> getSecondaryPages() {
        return secondaryPages;
    }

    public void setSecondaryPages(List<ThirdPage> secondaryPages) {
        this.secondaryPages = secondaryPages;
    }
}
