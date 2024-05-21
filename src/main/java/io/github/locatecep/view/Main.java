package io.github.locatecep.view;

import io.github.locatecep.model.Cep;
import io.github.locatecep.repository.CepRepository;
import io.github.locatecep.repository.DatabaseCepRepository;
import io.github.locatecep.service.CepService;

public class Main {

    public static void main(String[] args) {
        CepView view = new CepView();
        String numeroCep = view.getCepInput();

        CepRepository repository = new DatabaseCepRepository();
        CepService cepService = new CepService(repository);

        try {
            Cep cep = cepService.buscar(numeroCep);
            view.displayCep(cep);
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }
}
