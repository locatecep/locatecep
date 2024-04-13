package service;

import model.Cep;
import repository.CepRepository;
import service.exception.NotFoundException;

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
