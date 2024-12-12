package com.pacto_solucoes.recruitment.DTOs;

import com.pacto_solucoes.recruitment.domain.ApplicationStatus;
import com.pacto_solucoes.recruitment.domain.User;
import com.pacto_solucoes.recruitment.domain.Vacancy;

public record ApplyVacancyDTO(Vacancy vacancy, User user, ApplicationStatus status) {
}
