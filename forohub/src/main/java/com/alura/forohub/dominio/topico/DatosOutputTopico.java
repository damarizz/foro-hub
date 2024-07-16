package com.alura.forohub.dominio.topico;

import java.time.LocalDateTime;

public record DatosOutputTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String autor,
        String curso
) {
}
