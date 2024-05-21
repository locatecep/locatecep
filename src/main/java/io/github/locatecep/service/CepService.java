package io.github.locatecep.service;

import io.github.locatecep.model.Cep;
import io.github.locatecep.repository.CepRepository;
import io.github.locatecep.service.exception.CepServiceException;
import io.github.locatecep.service.exception.NotFoundException;

public class CepService {

    private CepRepository cepRepository;

    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public Cep buscar(String numeracao) {
        if (numeracao.length() > 8) {
            throw new CepServiceException("CEP inválido.");
        }

        Cep cep = cepRepository.buscarCep(numeracao);

        if (cep == null) {
            throw new NotFoundException("Não foram encontrados registros para o CEP informado.");
        }

        return cep;
    }
}
