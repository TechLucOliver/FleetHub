package com.fleet_hub.fleethub.model;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.CategoriaVeiculo;
import com.fleet_hub.fleethub.enums.StatusVeiculo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 7, unique = true)
    private String placa;

    @Column(nullable = false, length = 15)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, length = 4)
    private Integer anoFabricacao;

    @Column(nullable = false, length = 15)
    private String renavam;

    @Column(nullable = false, length = 22)
    @Enumerated(EnumType.STRING)
    private CategoriaVeiculo categoria;

    @Column(nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private CategoriaCNH categoriaCNH;

    @Column(nullable = false, length = 10)
    private Long odometroKm;

    @Column(nullable = false, length = 13)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(nullable = false, name = "filial_id")
    private Filial filial;

    @PrePersist
    public void prePersist(){
        if (status == null){
            status = StatusVeiculo.DISPONIVEL;
        }
        if (dataCadastro == null){
            dataCadastro = LocalDateTime.now();
        }
    }
}
