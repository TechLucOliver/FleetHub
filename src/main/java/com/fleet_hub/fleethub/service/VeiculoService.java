package com.fleet_hub.fleethub.service;

import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoRequestDTO;
import com.fleet_hub.fleethub.dto.veiculoDTO.VeiculoResponseDTO;
import com.fleet_hub.fleethub.enums.CategoriaVeiculo;
import com.fleet_hub.fleethub.enums.StatusVeiculo;
import com.fleet_hub.fleethub.model.Filial;
import com.fleet_hub.fleethub.model.Veiculo;
import com.fleet_hub.fleethub.repository.FilialRepository;
import com.fleet_hub.fleethub.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final FilialRepository filialRepository;

    @Transactional
    public VeiculoResponseDTO cadastrar(VeiculoRequestDTO veiculoRequestDTO) {
        if (veiculoRepository.existsByPlacaIgnoreCase(veiculoRequestDTO.placa())){
            throw new RuntimeException("Já existe um veículo cadastrado com este placa "+veiculoRequestDTO.placa());
        }

        Filial filialAssociada = filialRepository.findById(veiculoRequestDTO.filialId())
                .orElseThrow(() -> new RuntimeException("Nenhuma filial foi encontrada com o id "+veiculoRequestDTO.filialId()));

        Veiculo novoVeiculo = Veiculo.builder()
                .placa(veiculoRequestDTO.placa())
                .marca(veiculoRequestDTO.marca())
                .modelo(veiculoRequestDTO.modelo())
                .anoFabricacao(veiculoRequestDTO.anoFabricacao())
                .renavam(veiculoRequestDTO.renavam())
                .categoria(veiculoRequestDTO.categoriaVeiculo())
                .categoriaCNH(veiculoRequestDTO.categoriaCNH())
                .odometroKm(veiculoRequestDTO.odometroKm())
                .filial(filialAssociada)
                .build();

        Veiculo veiculoCadastrado = veiculoRepository.save(novoVeiculo);
        return VeiculoResponseDTO.fromEntity(veiculoCadastrado);
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDTO> listarTodos() {
        return veiculoRepository.findAll()
                .stream()
                .map(VeiculoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public VeiculoResponseDTO buscarPorId(Long id) {
        return VeiculoResponseDTO.fromEntity(veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado com o id "+id)));
    }

    @Transactional(readOnly = true)
    public VeiculoResponseDTO buscarPorPlaca(String placa) {
        return VeiculoResponseDTO.fromEntity(veiculoRepository.findByPlacaIgnoreCase(placa)
                .orElseThrow(() -> new RuntimeException("Nenhum veículo encontrado com a placa "+placa)));
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDTO> listarPorMarca(String marca) {
        return veiculoRepository.findByMarcaIgnoreCase(marca)
                .stream()
                .map(VeiculoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDTO> listarPorModelo(String modelo) {
        return veiculoRepository.findByModeloIgnoreCase(modelo)
                .stream()
                .map(VeiculoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDTO> listarPorAnoFabricacao(int anoFabricacao) {
        return veiculoRepository.findByAnoFabricacao(anoFabricacao)
                .stream()
                .map(VeiculoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDTO> listarPorCategoria(CategoriaVeiculo categoria) {
        return veiculoRepository.findByCategoria(categoria)
                .stream()
                .map(VeiculoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public List<VeiculoResponseDTO> listarPorStatus(StatusVeiculo status) {
        return veiculoRepository.findByStatus(status)
                .stream()
                .map(VeiculoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public VeiculoResponseDTO editar(Long id, VeiculoRequestDTO veiculoRequestDTO) {
        Veiculo veiculoBuscado = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado com o id "+id));

        if (!veiculoBuscado.getPlaca().equalsIgnoreCase(veiculoRequestDTO.placa()) &&
                veiculoRepository.existsByPlacaIgnoreCase(veiculoRequestDTO.placa())){
            throw new RuntimeException("Já existe um veiculo com a placa "+veiculoRequestDTO.placa());
        }

        Filial filialAssociada = filialRepository.findById(veiculoRequestDTO.filialId())
                .orElseThrow(() -> new RuntimeException("Não existe nenhuma filial com o id "+veiculoRequestDTO.filialId()));

        veiculoBuscado.setPlaca(veiculoRequestDTO.placa());
        veiculoBuscado.setMarca(veiculoRequestDTO.marca());
        veiculoBuscado.setModelo(veiculoRequestDTO.modelo());
        veiculoBuscado.setAnoFabricacao(veiculoRequestDTO.anoFabricacao());
        veiculoBuscado.setRenavam(veiculoRequestDTO.renavam());
        veiculoBuscado.setCategoria(veiculoRequestDTO.categoriaVeiculo());
        veiculoBuscado.setCategoriaCNH(veiculoRequestDTO.categoriaCNH());
        veiculoBuscado.setOdometroKm(veiculoRequestDTO.odometroKm());
        veiculoBuscado.setFilial(filialAssociada);

        Veiculo veiculoEditado = veiculoRepository.save(veiculoBuscado);
        return VeiculoResponseDTO.fromEntity(veiculoEditado);
    }

    @Transactional
    public VeiculoResponseDTO inativar(Long id) {
        Veiculo veiculoBuscado = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum veiculo encontrado com o id "+id));

        switch (veiculoBuscado.getStatus()) {
            case INATIVO:
                throw new RuntimeException("O veículo já está inativo");
            case EM_USO:
                throw new RuntimeException("O veículo está em uso e não pode ser inativado");
        }

        veiculoBuscado.setStatus(StatusVeiculo.INATIVO);

        Veiculo veiculoInativo = veiculoRepository.save(veiculoBuscado);
        return VeiculoResponseDTO.fromEntity(veiculoInativo);
    }
}
