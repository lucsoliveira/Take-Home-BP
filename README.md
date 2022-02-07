# Take Home BP
Access level control using Clojure.

## Sobre

Este repositório contém o projeto "takehome", elaborado em Clojure, cuja funcionalidade é servir de controle de acesso lógico para os diversos tipos de serviços fornecidos pela Brasil Paralelo.

## Instalação

É necessário ter instalado na máquina o [JDK](https://www.oracle.com/java/technologies/downloads/), o [Clojure](https://clojure.org/index), o [Leiningen](https://leiningen.org/) e, caso queira, o [IntelliJ IDEA by JetBrains](https://www.jetbrains.com/idea/).

Depois de instalados os softwares, efetue o clone deste repositório em sua máquina através do comando:

```sh
git clone https://github.com/lucsoliveira/Take-Home-BP
```

## Funcionamento

O projeto "takehome" é composto por dois códigos: 

- O "src/core.clj", onde há a função principal "can-access?", retornando "true" ou "false", a depender das regras de acesso;
- O "src/rules.clj", onde estão contidas as regras de acesso para cada tipo de usuário(patriota, premium e mecenas - chamado no código de "patron"); além disso, esse arquivo contém uma função que permite verificar se a assinatura do usuário ainda está ativa (user-still-registered?) e, também, uma função que verifica se o conteúdo de interessa foi lançado entre a data de inicio e fim da assinatura do comprador (released-between-begin-end-registration?).

Assim, o usuário do sistema tentará acessar determinado conteúdo e irá conseguir ou não, dependendo da lógica estabelicidade para controle de acesso de seu tipo de usuário.

## Executando

Há duas formas de executar o projeto. A primeira delas é utilizando o próprio terminal. Após efetuar o clone do repositório em sua máquina, navegue até a pasta "takehome" e execute o comando:

```sh
lein repl
```

Onde o terminal carregará o REPL.

Ou, como alternativa, pode-se abrir a pasta do projeto utilizando o IntelliJ IDEA e clicar com o direito do mouse no arquivo "project.clj" e, depois, clicar em "Run ' REPL for takehome' ".

Você pode fazer o buid completo do projeto ou efetuar o buid isoladamente, selecionando o arquivo e pressionando Alt + Shift + L (no Windows).

## Testes

O projeto contém três testes, um para cada usuário, sendo eles os arquivos: "patriota_test.clj", "premium_test.clj" e "patron_test.clj". Todos estão dentro da pasta "test/takehome".

Para executar os testes isoladamente, basta navegar até o diretório principal do projeto "takehome" e executar os comandos:

```sh
lein test :only takehome.patriota_test

lein test :only takehome.premium_test

lein test :only takehome.patron_test
```

Usando a última linha como exemplo, o retorno no terminal deverá ser algo como:

```sh
lein test takehome.patron_test

Ran 7 tests containing 20 assertions.
0 failures, 0 errors.
```

<!-- CONTACT -->
<a id="contact"></a>

## Contato

Lucas de Oliveira Pereira | [LinkedIn](https://www.linkedin.com/in/engenheiro-lucas-oliveira/) 

