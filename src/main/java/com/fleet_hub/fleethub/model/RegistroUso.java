package com.fleet_hub.fleethub.model;

import com.fleet_hub.fleethub.enums.NivelCombustivel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_registro_uso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroUso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private LocalDateTime dataHoraSaida;

    @Column(nullable = false, length = 5)
    private Long kmSaida;

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private NivelCombustivel combustivelSaida;

    @Column(length = 255)
    private String avariasSaida;

    @Column(length = 16)
    private LocalDateTime dataHoraDevolucao;

    @Column(nullable = false, length = 6)
    private Long kmDevolucao;

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private NivelCombustivel combustivelDevolucao;

    @Column(length = 255)
    private String avariasDevolucao;

    @PrePersist
    public void prePersist(){
        if (dataHoraSaida == null){
            dataHoraSaida = LocalDateTime.now();
        }
    }
}
