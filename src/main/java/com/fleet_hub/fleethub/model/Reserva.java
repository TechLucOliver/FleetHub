package com.fleet_hub.fleethub.model;

import com.fleet_hub.fleethub.enums.StatusReserva;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime inicioPrevisto;

    @Column(nullable = false)
    private LocalDateTime fimPrevisto;

    @Column(nullable = false, length = 50)
    private String destino;

    @Column(nullable = false, length = 255)
    private String justificativa;

    @Column(nullable = false, length = 12)
    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    @Column(nullable = false, length = 255)
    private String motivoDecisao;

    @Column(nullable = false)
    private LocalDateTime dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "avaliador_id")
    private Usuario avaliador;

    @ManyToOne
    @JoinColumn(name = "registro_id")
    private RegistroUso registro;
}
