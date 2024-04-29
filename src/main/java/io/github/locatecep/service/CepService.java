package io.github.locatecep.service;

import io.github.locatecep.model.Cep;
import io.github.locatecep.repository.CepRepository;
import io.github.locatecep.service.exception.NotFoundException;

public class CepService {

    public Cep buscar(String numeracao) {

        if(numeracao.length() > 8) {
            throw new NotFoundException("Cep inválido.");
        }

        CepRepository repository = new CepRepository();
        Cep cep = repository.buscarCep(numeracao);

        if(cep == null) {
            throw new NotFoundException("Não foram encontrados registros para o cep informado.");
        }

        return cep;

    }

}
