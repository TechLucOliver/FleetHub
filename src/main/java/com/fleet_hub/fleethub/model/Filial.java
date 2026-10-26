package com.fleet_hub.fleethub.model;

import com.fleet_hub.fleethub.enums.StatusEntidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_filial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String razaoSocial;

    @Column(nullable = false, length = 100)
    private String nomeFantasia;

    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;

    @Column(nullable = false, length = 8, unique = true)
    private String sigla;

    @Column(nullable = false, length = 30)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String uf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private StatusEntidade status;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "filial")
    private Set<Departamento> departamento = new HashSet<>();

    @OneToMany(mappedBy = "filial")
    private Set<Usuario> funcionarios = new HashSet<>();

    @OneToMany(mappedBy = "filial")
    private List<Veiculo> veiculos = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        if (dataCadastro == null){
            dataCadastro = LocalDateTime.now();
        }
        if (status == null){
            status = StatusEntidade.ATIVO;
        }
    }

}
