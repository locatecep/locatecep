
package io.github.locatecep.repository;

import io.github.locatecep.model.Cep;

public interface CepRepository {
    Cep buscarCep(String numeracao);
}
