package com.pacto_solucoes.recruitment.DTOs;

import com.pacto_solucoes.recruitment.domain.UserRole;

public record RegisterDTO(String name, String login, String password, UserRole role) {
}
