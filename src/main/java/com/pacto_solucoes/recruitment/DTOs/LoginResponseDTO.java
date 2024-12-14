package com.pacto_solucoes.recruitment.DTOs;

import com.pacto_solucoes.recruitment.domain.UserRole;

public record LoginResponseDTO(String token, Long id, UserRole role) {
}
