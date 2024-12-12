package com.pacto_solucoes.recruitment.domain;

public enum ApplicationStatus {
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    UNDER_REVIEW("underReview");

    private final String status;

    ApplicationStatus(String status){
        this.status = status;
    }

    public String getRole(){
        return status;
    }
}
