package com.pacto_solucoes.recruitment.domain;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private VacancyStatus status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDate createAt;

    public Vacancy() {
    }

    public Vacancy(String title, String description, User user, LocalDate createAt) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.createAt = createAt;
    }

    public Vacancy(Long id, String title, String description, User user, LocalDate createAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VacancyStatus getStatus() {
        return status;
    }

    public void setStatus(VacancyStatus status) {
        this.status = status;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }
}
