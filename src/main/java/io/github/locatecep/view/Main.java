package io.github.locatecep.view;

import io.github.locatecep.model.Cep;
import io.github.locatecep.service.CepService;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java -jar locatecep.jar potes.Main <NUMERO DO CEP>");
            System.exit(1);
        }

        String numeroCep = args[0];

        try {
            CepService cepService = new CepService();
            Cep cep = cepService.buscar(numeroCep);

            if (cep != null) {
                System.out.println("CEP: " + cep.getNumeracao());
                System.out.println("UF: " + cep.getUf());
                System.out.println("Cidade: " + cep.getCidade());
            } else {
                System.out.println("Não foram encontrados registros para o CEP informado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar informações do CEP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}