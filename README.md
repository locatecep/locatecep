# LocateCEP: Localizador de UF/Cidade via Cep

O projeto consiste em um sistema para buscar informações de CEP a partir de um banco de dados.

## Uso:

Para utilizar o sistema, é necessário invocar o método console da classe ConsoleService, passando o número do CEP desejado como parâmetro. Isso retornará uma string formatada contendo as informações do CEP.

## Desenvolvimento do projeto:

Foi realizada uma análise detalhada dos requisitos do projeto, incluindo a capacidade de receber um CEP informado pelo usuário, verificar sua validade, acessar um arquivo local com a base de CEPs do Brasil e fornecer informações sobre a cidade e o estado associados ao CEP.

## Trechos de código relevantes:
`CepService`: Esta classe fornece métodos para buscar informações de CEP.


```java
public class CepService {

    public Cep buscar(String numeracao) {

        if (numeracao.length() > 8) {
            throw new NotFoundException("Cep inválido.");
        }

        CepRepository repository = new CepRepository();
        Cep cep = repository.buscarCep(numeracao);

        if (cep == null) {
            throw new NotFoundException("Não foram encontrados registros para o cep informado.");
        }

        return cep;

    }
}
```

`CepRepository`: Esta classe é responsável por acessar os dados de CEP no banco de dados.

```java
public class CepRepository {
	
	public Cep buscarCep(String numeracao) {
		
		Connection conn = Database.getConnection();
		
		try {
			String sql = "SELECT uf, cidade FROM faixas_cep WHERE ?::bigint BETWEEN CAST(cep_inicio AS bigint) AND CAST(cep_fim AS bigint) OFFSET 1LIMIT 1";
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, numeracao);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				Cep cep = new Cep();
				cep.setUf(rs.getString("uf"));
				cep.setCidade(rs.getString("cidade"));
				cep.setNumeracao(numeracao);
				return cep;
			}
			
		} catch (SQLException e) {
			throw new DatabaseConnectionError(e.getMessage());
		} finally {
			Database.closeConnection(conn);
		}
		return null;
	}

}
```


`ConsoleService`: Esta classe oferece métodos para interação com o console, incluindo a exibição das informações de CEP.

```java
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

```

## Instruções de instalação: 

Para utilizar o sistema, siga os passos abaixo

### Configuração do Banco de Dados:
Certifique-se de configurar corretamente o banco de dados com as tabelas necessárias. No caso deste projeto, é esperado que exista uma tabela chamada faixas_cep com as colunas `cep_inicio`, `cep_fim`, `uf` e `cidade`.

#### Dependências: Este projeto depende das seguinte biblioteca


Lombok: Uma biblioteca Java que automatiza a geração de código repetitivo, como getters e setters.

## Contribuição:
O projeto não aceita contribuições, pois é apenas um trabalho para aprendizado, e não algo que será lançado.

## Licença:
Este projeto não possui licenças.
