package service;

import model.Cep;
import service.exception.DatabaseConnectionError;
import service.exception.NotFoundException;

public class ConsoleService {

	public static String console(String numeracao) {

		CepService service = new CepService();

		try {
			Cep cep = service.buscar(numeracao);
			return  "CEP: " + cep.getNumeracao() + "\nUF: " + cep.getUf() + "\nCidade: " + cep.getCidade();

		} catch (NotFoundException e) {
			throw new NotFoundException(e.getMessage());
		} catch (DatabaseConnectionError e) {
			throw new DatabaseConnectionError(e.getMessage());
		}
	}

}
