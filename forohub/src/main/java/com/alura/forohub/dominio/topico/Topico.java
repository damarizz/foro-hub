package com.alura.forohub.dominio.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;

    @Column(name = "fecha")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    public Topico(DatosCreateTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();
        this.status = Status.SIN_RESPUESTA;
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarTopico(DatosUpdateTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }

        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }

        if (datos.autor() != null) {
            this.autor = datos.autor();
        }

        if (datos.curso() != null) {
            this.curso = datos.curso();
        }
    }
}
