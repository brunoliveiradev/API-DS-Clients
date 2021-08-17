package dev.brunoliveira.dsclients.dto;

import dev.brunoliveira.dsclients.model.Client;

import java.io.Serializable;
import java.time.Instant;

public class ClientDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Integer children;
    private Instant birthDate;

    public ClientDto() {
    }

    public ClientDto(Long id, String name, String cpf, Double income, Integer children, Instant birthDate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.children = children;
        this.birthDate = birthDate;
    }

    public ClientDto(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.children = entity.getChildren();
        this.birthDate = entity.getBirthDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }
}
