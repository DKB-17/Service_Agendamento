package agendamento.servico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CadastroCliente(
        @NotBlank
        String nome,

        @NotBlank
        @Pattern(regexp= "\\(\\d{2}\\)\\d{5}-\\d{4}")
        String contato,

        @NotBlank
                @CPF
        String CPF,
        @NotBlank
                @Email
        String email,
        @NotBlank
                @Size(min = 12)
        String password
) {
}
