# LocateCEP: Localizador de UF/Cidade via Cep

O LocateCEP é um componente criado para simplificar o processo de obtenção de informações sobre o estado brasileiro (
UF) e a cidade associados a um CEP específico. Ele é um sistema que utiliza um banco de dados para realizar consultas e
fornecer essas informações de forma rápida e eficiente.

O desenvolvimento do LocateCEP surgiu da necessidade de facilitar e agilizar a recuperação de informações geográficas
com base em um CEP. Muitas vezes, ao lidar com sistemas de logística, entrega de mercadorias ou simplesmente
preenchimento de formulários, é essencial ter acesso rápido e preciso às informações de UF e cidade associadas a um
determinado CEP. O LocateCEP visa resolver esse problema, fornecendo uma maneira fácil e confiável de obter esses
dados.

Sem um sistema dedicado como o LocateaCEP, obter informações de estado e cidade a partir de um CEP pode ser um processo
complexo e demorado, envolvendo consultas manuais em bancos de dados ou APIs externas. Em muitos cenários, especialmente
em sistemas em tempo real ou aplicativos que exigem respostas rápidas, é crucial ter um mecanismo que forneça essas
informações de forma rápida e eficiente.

## Uso:

Para utilizar o sistema, é necessário invocar o método console da classe ConsoleService, passando o número do CEP
desejado como parâmetro. Isso retornará uma string formatada contendo as informações do CEP.

### Exemplo: Aplicativo de Entregas

Exemplos mais práticos de como o sistema poderia ser utilizado.

1. - Um cliente insere seu CEP em um aplicativo de entrega, por exemplo, `cep: 12345-678`.

2. - O aplicativo valida o CEP fornecido.

3. - Se o CEP for válido, o sistema utiliza um serviço para buscar informações relacionadas ao CEP, como estado e cidade.

4. - Com base nessas informações, o aplicativo atribui a entrega a um centro de distribuição mais próximo.

5. - O pedido é então encaminhado para entrega, otimizando o processo e reduzindo tempos de espera e custos de envio.

Exemplo de Implementação:

```java
public class Main {
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);

      // Solicita ao usuário que informe o CEP
      System.out.println("Informe o CEP:");
      String cepDigit = scan.nextLine();

      // Verifica se o CEP fornecido possui o formato correto (8 dígitos)
      if (cepDigit.length() != 8) {
         System.out.println("CEP inválido");
         System.exit(1); // Encerra o programa com código de erro
      }

      try {
         // Instancia o serviço para buscar informações do CEP
         CepService cepService = new CepService();

         // Busca informações do CEP no serviço
         Cep cep = cepService.buscar(cepDigit);

         // Se informações do CEP forem encontradas, exibe-as
         if (cep != null) {
            System.out.println("CEP: " + cep.getNumeracao());
            System.out.println("UF: " + cep.getUf());
            System.out.println("Cidade: " + cep.getCidade());
         } else {
            // Se não forem encontradas informações, exibe mensagem adequada
            System.out.println("Não foram encontrados registros para o CEP informado.");
         }
      } catch (Exception e) {
         // Em caso de erro durante a busca do CEP, exibe mensagem de erro
         System.err.println("Erro ao buscar informações do CEP: " + e.getMessage());
         e.printStackTrace(); // Exibe detalhes do erro
      } finally {
         scan.close(); // Fecha o scanner para evitar vazamento de recursos
      }
   }
}
```

Saida:

````makfile
Informe o CEP:
CEP: 12345-678
UF: SP
Cidade: São Paulo
````

## Desenvolvimento do Projeto:

O LocateCEP foi desenvolvido após uma análise aprofundada dos requisitos do projeto. A identificação das principais
funcionalidades necessárias incluiu a capacidade de receber um CEP informada pelo usuário, verificar sua validade,
acessar um arquivo local com a base de CEPs do Brasil e fornecer dados sobre a cidade e o estado que estão relacionados
ao CEP.

### UML - Diagrama:

```mermaid
flowchart TB
   subgraph User["User"]
      direction LR
      idInrforma["Informa o Cep"]
      idVisualizar["Vizualizar UF/Cidade"]
      Usuario["Usuario"]
   end
   subgraph Banco["Banco"]
      direction LR
      idArmazenar["Armazena Informações Sobre os Ceps"]
      Banco_De_Dados["Banco_De_Dados"]
   end
   subgraph System["System"]
      direction RL
      idVerificar["Verficar Existencia Do Cep"]
      idConsultar["Consultar O UF/Cidade Do Cep no Arquivo"]
      Sistema_De_Geolocalizacao["Sistema_De_Geolocalizacao"]
   end
   subgraph subGraph3["Consultar CEP"]
      direction TB
      idStart(("Incio"))
      idStop(("Fim"))
      idRetornar["Retorna o UF/Cidade do CEP"]
      User
      Banco
      System
   end
   subgraph Sistema["Sistema"]
      direction TB
      subGraph3
   end
   idStart ==> idInrforma
   idVisualizar --> idStop
   Usuario --> idInrforma & idVisualizar
   Banco_De_Dados --> idArmazenar
   Sistema_De_Geolocalizacao --> idVerificar & idConsultar
   idInrforma ==> idVerificar
   idVerificar ==> idConsultar
   idConsultar ==> idRetornar
   idRetornar ==> idVisualizar
   idArmazenar <-- &lt; include &gt; --> idConsultar
style idInrforma fill:#2962FF,stroke:#FFFFFF,color:#FFFFFF
style idVisualizar stroke:#FFFFFF,fill:#2962FF
style idVerificar stroke:#FFFFFF,fill:#2962FF
style idConsultar stroke:#FFFFFF,fill:#2962FF
style Sistema_De_Geolocalizacao color:#FFFFFF,fill:#000000
style idStart color:#FFFFFF,fill:#00C853,stroke:#FFFFFF
style idStop fill:#D50000,stroke:#FFFFFF,color:#FFFFFF
style idRetornar stroke:#FFFFFF,fill:#2962FF
style User stroke:#FFFFFF
style Banco stroke:#FFFFFF
style System stroke:#FFFFFF
style Sistema fill:#000000
linkStyle 0 stroke:#2962FF,fill:none
linkStyle 7 stroke:#2962FF,fill:none
linkStyle 8 stroke:#2962FF,fill:none
linkStyle 9 stroke:#2962FF,fill:none
linkStyle 10 stroke:#2962FF,fill:none
```

### Escolhas de Design:

Durante o processo de desenvolvimento, foram realizadas diversas escolhas de design relevantes com o objetivo de
assegurar a eficiência, confiabilidade e facilidade de utilização do sistema. Algumas das opções apresentadas são:

**Utilização de um Banco de Dados Interno:** Escolhemos um banco de dados interno para armazenar as informações de CEP.
Isso foi escolhido para assegurar a agilidade e a confiabilidade das consultas, evitando a dependência de serviços
externos que poderiam ser lentos ou indisponíveis.

**Validação de CEP:** A validação de CEP foi implementada para garantir que somente CEPs válidos sejam aceitos pelo
sistema. Isso ajuda a prevenir erros de entrada e garante a precisão das informações fornecidas.

###Desafios Enfrentados:
Durante o desenvolvimento do LocateCEP, enfrentamos diversos desafios que demandaram soluções criativas e trabalho em
grupo. Alguns dos desafios são:

**Informações Corretas:** Incialmente o componente só retornava o uf (estado) ao consultar o banco de dados. Foi
necessário resolver esse problema, para que retornasse tanto o uf quanto a cidade.

**Otimização de Consultas:** Garantir que as consultas ao banco de dados fossem rápidas e eficientes foi outro desafio
importante. Isso exigiu a otimização das consultas e o uso de índices para melhorar o desempenho do sistema.

**Testes** Testes para assegurar a precisão e a confiabilidade do sistema foram um processo constante.

## Trechos de código relevantes:

`CepService`:  Esta classe fornece métodos para buscar informações de CEP. O método `buscar` recebe uma string
representando o número de um CEP como parâmetro e retorna um objeto `Cep` contendo as informações de UF e cidade
associadas a esse CEP.

```java
public class CepService {

    public Cep buscar(String numeracao) {

        if (numeracao.length() > 8) {
            throw new NotFoundException("Cep inválido.");
        }

        // Instancia um objeto CepRepository para acessar os dados do CEP no banco de dados
        CepRepository repository = new CepRepository();
        // Busca as informações do CEP no banco de dados
        Cep cep = repository.buscarCep(numeracao);

        // Se o CEP não foi encontrado no banco de dados, lança uma exceção
        if (cep == null) {
            throw new NotFoundException("Não foram encontrados registros para o cep informado.");
        }

        return cep;

    }
}
```

`CepRepository`: Esta classe é responsável por acessar os dados de CEP no banco de dados. O método `buscarCep` recebe
uma string representando o número de um CEP como parâmetro e retorna um objeto `Cep` contendo as informações de UF e
cidade associadas a esse CEP, consultando o banco de dados.

```java
public class CepRepository {

    public Cep buscarCep(String numeracao) {

        // Obtém uma conexão com o banco de dados
        Connection conn = Database.getConnection();

        try {
            // Prepara a query SQL para buscar o CEP no banco de dados
            String sql = "SELECT uf, cidade FROM faixas_cep WHERE ?::bigint BETWEEN CAST(cep_inicio AS bigint) AND CAST(cep_fim AS bigint) OFFSET 1LIMIT 1";

            // Cria um PreparedStatement com a query SQL
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, numeracao);

            // Executa a query SQL e obtém o resultado
            ResultSet rs = st.executeQuery();

            // Se houver um resultado, cria um objeto Cep e o retorna
            if (rs.next()) {
                Cep cep = new Cep();
                cep.setUf(rs.getString("uf"));
                cep.setCidade(rs.getString("cidade"));
                cep.setNumeracao(numeracao);
                return cep;
            }

        } catch (SQLException e) {
            // Em caso de erro de conexão ou query SQL, lança uma exceção
            throw new DatabaseConnectionError(e.getMessage());
        } finally {
            // Fecha a conexão com o banco de dados
            Database.closeConnection(conn);
        }
        // Se o CEP não foi encontrado, retorna null
        return null;
    }

}
```

## Instruções de Instalação:

Antes de iniciar a instalação do sistema, certifique-se de atender aos requisitos do sistema listados abaixo.

### Requisitos do Sistema:

- Sistema Operacional: Windows 10, MacOS, ou Linux;
- Java Development Kit (JDK) 8 ou superior;
- Banco de Dados PostgreSQL (ou outro sistema de gerenciamento de banco de dados compatível);
- Depêndencias descritas abixo.

### Guia Passo a Passo:

Siga os passos abaixo para configurar e executar o sistema:

1. **Instalar o JDK**: Se você ainda não tiver o JDK instalado, faça o download e instale-o a partir do site oficial da Oracle ou de uma fonte confiável.

2. **Instalar o Banco de Dados**: Se você ainda não tiver um sistema de gerenciamento de banco de dados instalado, instale o PostgreSQL a partir do site oficial ou usando um gerenciador de pacotes.

3. **Configurar o Banco de Dados**: Após a instalação do PostgreSQL, crie um banco de dados chamado `locate_cep` e execute o script SQL fornecido abaixo para criar a tabela `faixas_cep`.

    ```sql
    CREATE TABLE faixas_cep (
        cep_inicio VARCHAR(8) NOT NULL,
        cep_fim VARCHAR(8) NOT NULL,
        uf VARCHAR(2) NOT NULL,
        cidade VARCHAR(255) NOT NULL,
        PRIMARY KEY (cep_inicio)
    );
    ```

4. **Clonar o Repositório**: Clone o repositório do projeto para o seu ambiente de desenvolvimento local.

5. **Importar o Projeto**: Importe o projeto para a sua IDE preferida (como Eclipse, IntelliJ IDEA ou NetBeans).

6. **Adicionar Dependências**: Adicione as dependências do projeto ao seu gerenciador de dependências (Maven ou Gradle), incluindo o Lombok conforme instruído abaixo na seção de Dependências.

   #### Dependências:

   Este projeto depende das seguintes bibliotecas:

    - **Lombok**: Uma biblioteca Java que automatiza a geração de código repetitivo, como getters e setters.

      **Adiconar via Maven:**
      Para adicionar a dependência do Lombok ao projeto via Maven, adicione o seguinte trecho ao seu arquivo `pom.xml`:

        ```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
            <scope>provided</scope>
        </dependency>
        ```

      **Adiconar via Gradle:**
      Para adicionar a dependência do Lombok ao projeto via Gradle, adicione o seguinte trecho ao seu arquivo `build.gradle`:

        ```groovy
        dependencies {
            compileOnly 'org.projectlombok:lombok:1.18.20'
            annotationProcessor 'org.projectlombok:lombok:1.18.20'
        }
        ```

7. **Configurar o Arquivo de Conexão com o Banco de Dados**: Edite o arquivo de configuração do banco de dados (`application.properties` ou similar) para fornecer as informações de conexão corretas para o seu banco de dados local.

8. **Compilar o Projeto**: Compile o projeto para garantir que não haja erros de compilação.

9. **Executar o Projeto**: Execute o projeto a partir da sua IDE ou usando o comando apropriado do Maven ou Gradle.

10. **Testar o Sistema**: Após a execução bem-sucedida, teste o sistema para garantir que ele esteja funcionando conforme esperado.

Seguindo esses passos, você deverá ter o sistema LocateCEP configurado e em execução em seu ambiente local.

## Contribuição:

O projeto é destinado apenas para fins de aprendizado e não será aberto para contribuições. No entanto, agradecemos seu
feedback e perguntas! Se você tiver alguma dúvida, sugestão de melhoria ou quiser compartilhar sua experiência usando
este projeto em seus próprios projetos educacionais ou de aprendizado, não hesite em entrar em contato.

## Licença:

Este projeto é de domínio público, o que significa que você pode usar, modificar e distribuir o código como desejar, sem
restrições.
