package com.fleet_hub.fleethub.config;

import com.fleet_hub.fleethub.enums.StatusEntidade;
import com.fleet_hub.fleethub.model.Filial;
import com.fleet_hub.fleethub.repository.FilialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class InicializadorDados {

    /*
    @Bean
    CommandLineRunner iniciarDB(FilialRepository filialRepository){
        return args -> {
            if (filialRepository.count() == 0){
                Filial filial = Filial.builder()
                        .sigla("")
                        .nomeFantasia("")
                        .cnpj("")
                        .sigla("")
                        .cidade("")
                        .uf("")
                        .status(StatusEntidade.ATIVO)
                        .build();
            }
        };
    }

     */
}
