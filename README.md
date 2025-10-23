# CRMfy - Backend

<br />

<div align="center">
   <img width="512" height="512" src="https://ik.imagekit.io/codequeens/crmfy_logo.png?updatedAt=1761154152762" title="source: imgur.com" /> 
</div>


<br /><br />

## 1. Descrição

A CRMfy nasceu para conectar empresas e pessoas de forma inteligente.
Com tecnologia, dados e IA, transformamos cada interação em uma oportunidade de crescimento.
Mais que um CRM, somos o elo entre o humano e o digital — o futuro dos relacionamentos.

Entre os principais recursos que um CRM oferece, destacam-se:

- Usuário: representa vendedores e gestores que utilizam o sistema.
- Cliente: centraliza dados de pessoas e empresas.
- Oportunidade: registra negociações em andamento, com valor, prazo e status.
- Arquitetura pensada para simplicidade e eficiência.

------

## 2. Sobre esta API

A API do CRMfy foi desenvolvida em Java, utilizando o framework Spring, e segue os princípios da Arquitetura MVC e REST. Ela disponibiliza endpoints para o gerenciamento dos recursos Cliente, Oportunidades e Usuário, com testes de CRUD (Create, Read, Update e Delete) realizados por meio do Insomnia.

### 2.1. Principais Funcionalidades

1 - Organizar informações de clientes e negociações.
2 - Gerenciar oportunidades no funil de vendas, com acompanhamento dos status das propostas.
3 - Potencializar o atendimento e o fechamento de negócios.

------

## 3. Diagrama de Classes

O Diagrama de Classes é um modelo visual usado na programação orientada a objetos para representar a estrutura de um sistema. Ele exibe classes, atributos, métodos e os relacionamentos entre elas, como associações, heranças e dependências.

Esse diagrama ajuda a planejar e entender a arquitetura do sistema, mostrando como as entidades interagem e se conectam. É amplamente utilizado nas fases de design e documentação de projetos.

```mermaid
classDiagram
    direction LR

 class TbUsuarios {
        +BIGINT id
        +VARCHAR(100) cargo
        +VARCHAR(1000) foto
        +VARCHAR(100) nome
        +VARCHAR(100) senha
        +VARCHAR(100) usuario
    }
    class TbOportunidades {
        +BIGINT id
        +VARCHAR(255) descricao
        +VARCHAR(255) status
        +DECIMAL(38,2) valor
        +BIGINT cliente_id
        +BIGINT usuario_id
    }
    class TbClientes {
        +BIGINT id
        +VARCHAR(255) email
        +VARCHAR(100) nome
        +VARCHAR(255) origem
        +VARCHAR(255) telefone
    }
    TbUsuarios "1" --> "0.." TbOportunidades : possui
    TbClientes "1" --> "0.." TbOportunidades : possui

```

------

## 4. Diagrama Entidade-Relacionamento (DER)

O DER (Diagrama Entidade-Relacionamento) do projeto CRM representa de forma visual como os dados estão organizados no banco de dados relacional e como as entidades se relacionam entre si.

<div align="center">
    <img src="https://ik.imagekit.io/codequeens/der1.png?updatedAt=1761239541402" title="source: imgur.com" />
</div>

------

## 5. Tecnologias utilizadas

| Item                          | Descrição       |
| ----------------------------- | ----------------|
| **Servidor**                  | Apache Tomcat   |
| **Linguagem de programação**  | Java            |
| **Framework**                 | SpringBoot      |
| **ORM**                       | JPA + Hibernate |
| **Banco de dados Relacional** | MySQL           |

------

## 6. Requisitos
 
<br />
 
Para executar os códigos localmente, você precisará:
 
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Banco de dados [MySQL](https://dev.mysql.com/downloads/)
- [STS](https://spring.io/tools)
- [Insomnia](https://insomnia.rest/download) 
 
<br />
 
## 7. Como Executar o projeto no STS
 
<br />
 
### 7.1. Importando o Projeto
 
1. Clone o repositório do Projeto [CRM](https://github.com/CodeQueensjava83/crm_backend.git) dentro da pasta do *Workspace* do STS
 
```bash
git clone https://github.com/CodeQueensjava83/crm_backend.git
```
 
2. **Abra o STS** e selecione a pasta do *Workspace* onde você clonou o repositório do projeto
3. No menu superior do STS, clique na opção: **File 🡲 Import...**
4. Na janela **Import**, selecione a opção: **General 🡲 Existing Projects into Workspace** e clique no botão **Next**
5. Na janela **Import Projects**, no item **Select root directory**, clique no botão **Browse...** e selecione a pasta do Workspace onde você clonou o repositório do projeto
6. O STS reconhecerá o projeto automaticamente
7. Marque o Projeto Loja Games no item **Projects** e clique no botão **Finish** para concluir a importação
 
<br />
 
### 7.2. Executando o projeto
 
1. Na Guia **Boot Dashboard**, localize o  **Projeto  crm_backend**
2. Selecione o **Projeto CRM_Backend**
3. Clique no botão **Start or Restart** <img src="https://i.imgur.com/wdoZqWP.png" title="source: imgur.com" width="4%"/> para iniciar a aplicação
4. Caso seja perguntado se você deseja autorizar o acesso ao projeto via rede, clique no botão **Permitir Acesso**
5. Acompanhe a inicialização do projeto no console do STS
6. Verifique se o banco de dados `db_crm` foi criado corretamente e se as tabelas foram geradas automaticamente.
7. Utilize o [Insomnia](https://insomnia.rest/) para testar os endpoints.
 
<br />
 
> [!TIP]
>
> Ao acessar a URL `http://localhost:8080` em seu navegador, a interface do Swagger será carregada automaticamente, permitindo a visualização e a interação com os endpoints da API, bem como a consulta dos modelos de dados utilizados.
 
<br />

## 8. Contribuição
 
<br />
 
Este repositório é parte de um projeto educacional, mas contribuições são sempre bem-vindas! Caso tenha sugestões, correções ou melhorias, fique à vontade para:
 
- Criar uma **issue**
- Enviar um **pull request**
- Compartilhar com colegas que estejam aprendendo Java!
 
<br />
 
##  9. Contato
 
<br />
 
Desenvolvido por [**CodeQueens: Carina, Luana, Maria, Milena, Myriam, Rafaela**](https://github.com/CodeQueensjava83/crm_backend.git)
Para dúvidas, sugestões ou colaborações, entre em contato via GitHub ou abra uma issue!bra uma issue!