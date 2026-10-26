package com.fleet_hub.fleethub.model;

import com.fleet_hub.fleethub.enums.StatusEntidade;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_departamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String centroCusto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private StatusEntidade status;

    @ManyToOne
    @JoinColumn(name = "filial_id", nullable = false)
    private Filial filial;

    @OneToMany(mappedBy = "departamento")
    private Set<Usuario> usuarios = new HashSet<>();

    @PrePersist
    public void prePersist(){
        if (status == null){
            status = StatusEntidade.ATIVO;
        }
    }
}
