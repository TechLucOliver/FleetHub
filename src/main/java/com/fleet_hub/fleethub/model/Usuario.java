package com.fleet_hub.fleethub.model;

import com.fleet_hub.fleethub.enums.CategoriaCNH;
import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.enums.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Email
    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String senhaHash;

    @Column(nullable = false, length = 13)
    @Enumerated(EnumType.STRING)
    private TipoUsuario role;

    @Column(nullable = false, length = 15)
    private String numeroCNH;

    @Column(nullable = false, length = 1)
    private CategoriaCNH categoriaCNH;

    @Column(nullable = false)
    private LocalDate validadeCNH;

    @Column(nullable = false, length = 7)
    @Enumerated(EnumType.STRING)
    private StatusEntidade status;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(nullable = false, name = "departamento_id")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(nullable = false, name = "filial_id")
    private Filial filial;

    @PrePersist
    public void prePersist(){
        if (status == null){
            status = StatusEntidade.ATIVO;
        }
        if (dataCadastro == null){
            dataCadastro = LocalDateTime.now();
        }
    }
}
