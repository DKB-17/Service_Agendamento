package agendamento.servico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotBlank @Email
        String email,
        @NotBlank
        String senha
) {
}
