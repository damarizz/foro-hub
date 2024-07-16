package com.alura.forohub.dominio.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosUpdateTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotBlank
        String autor,

        @NotBlank
        String curso
) {
}
