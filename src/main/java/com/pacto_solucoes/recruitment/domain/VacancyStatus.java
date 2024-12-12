package com.pacto_solucoes.recruitment.domain;

public enum VacancyStatus {
    OPEN("open"),
    CLOSED("closed");

    private final String status;

    VacancyStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
