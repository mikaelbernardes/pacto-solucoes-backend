package com.pacto_solucoes.recruitment.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "application")
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    private ApplicationStatus status;

    @Column(name = "application_at")
    private LocalDate applicationAt;

    public Application() {
    }

    public Application(LocalDate applicationAt, ApplicationStatus status, User user, Vacancy vacancy) {
        this.applicationAt = applicationAt;
        this.status = status;
        this.user = user;
        this.vacancy = vacancy;
    }

    public Application(Long id, Vacancy vacancy, User user, ApplicationStatus status, LocalDate applicationAt) {
        this.id = id;
        this.vacancy = vacancy;
        this.user = user;
        this.status = status;
        this.applicationAt = applicationAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getApplicationAt() {
        return applicationAt;
    }

    public void setApplicationAt(LocalDate applicationAt) {
        this.applicationAt = applicationAt;
    }
}
