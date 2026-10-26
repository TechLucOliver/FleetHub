package com.fleet_hub.fleethub.service;

import com.fleet_hub.fleethub.dto.filialDTO.FilialRequestDTO;
import com.fleet_hub.fleethub.dto.filialDTO.FilialResponseDTO;
import com.fleet_hub.fleethub.model.Filial;
import com.fleet_hub.fleethub.repository.FilialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FilialService {

    private final FilialRepository filialRepository;

    @Transactional
    public FilialResponseDTO cadastrar(FilialRequestDTO requestDTO){
        boolean siglaJaExiste = filialRepository.existsBySiglaIgnoreCase(requestDTO.sigla());

        boolean cnpjJaExiste = filialRepository.existsByCnpjIgnoreCase(requestDTO.cnpj());

        if (siglaJaExiste){
            throw new RuntimeException("Ja existe uma filial com essa sigla.");
        }

        if (cnpjJaExiste){
            throw new RuntimeException("Ja existe uma filial com esse cnpj.");
        }

        Filial novaFilial = Filial.builder()
                .razaoSocial(requestDTO.razaoSocial())
                .nomeFantasia(requestDTO.nomeFantasia())
                .cnpj(requestDTO.cnpj())
                .sigla(requestDTO.sigla())
                .cidade(requestDTO.cidade())
                .uf(requestDTO.uf())
                .build();

        Filial filialCadastrada = filialRepository.save(novaFilial);
        return FilialResponseDTO.fromEntity(filialCadastrada);
    }

    @Transactional(readOnly = true)
    public List<FilialResponseDTO> listarTodos(){
        return filialRepository.findAll()
                .stream()
                .map(FilialResponseDTO::fromEntity)
                .toList();
    }


    @Transactional
    public FilialResponseDTO editar(Long id, FilialRequestDTO requestDTO){
        Filial filialBuscada = filialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial não encontrada com o id "+id));

        filialBuscada.setRazaoSocial(requestDTO.razaoSocial());
        filialBuscada.setNomeFantasia(requestDTO.nomeFantasia());
        filialBuscada.setCnpj(requestDTO.cnpj());
        filialBuscada.setSigla(requestDTO.sigla());
        filialBuscada.setCidade(requestDTO.cidade());
        filialBuscada.setUf(requestDTO.uf());

        Filial filialEditada = filialRepository.save(filialBuscada);
        return FilialResponseDTO.fromEntity(filialEditada);
    }

    /*
    @Transactional(readOnly = true)
    public FilialResponseDTO buscarPorId(Long id){

    }

    @Transactional(readOnly = true)
    public FilialResponseDTO listarPorStatus(StatusEntidade statusEntidade){

    }

    @Transactional
    public FilialResponseDTO inativar(Long id){

    }

    @Transactional
    public FilialResponseDTO reativar(Long id){

    }

     */

}
