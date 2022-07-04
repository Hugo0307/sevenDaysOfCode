# #7daysOfCode

## First day - 27/06/2022

***Criei uma conta no IMDb para ter uma chave de acesso ao serviço, logo em seguida, no código Java usei o pacote java.net.http para criar um client através da classe HttpClient e executar uma requisição HTTP com o verbo GET (usando a classe HttpRequest) para a url do IMDb, obtendo a resposta através da classe HttpResponse.***

#

## Second day - 29/06/2022

***Para o desafio proposto para o segundo dia, implementei "limpeza" do json para deixá-lo conforme eu necessitava afim de obter as listas esperadas. Para isso, implementei com os métodos da classe String, a busca e remoção dos colchetes, depois removi as vírgulas e chaves atribuindo o resultado a um array de string para que cada filme com seus atributos estivesse em uma posição do arrya e assim pudesse manipulá-lo individualmente com o intuíto de buscar os valores dos atributos title, image, year e rating e adicioná-los a uma lista de cada atributo, concluíndo assim o desafio do segundo dia.***

#

## Third day - 01/07/2022

***Neste desafio do terceiro dia, criei um modelo de classe de domínio utilizando da Record Classes, que são um tipo especial de classes, incorporada ao Java 14, que nos permite simplificar em uma linha de código a criação de uma classe com seus atributos, métodos getters, equals e hashCode e toString. É, observe que não mencionei os setters, porque na classe Record, os atributos são final, o que significa que a classe Record se destina a servir como um simples "portador de dados", em outras palavras é imutável. Então optei por utilizar a classe Record, porque além de deixar o código mais elegante, diminuir código boilerplate, no final das contas só quero realizar a leitura dos dados da lista de filmes que criei para comportar os atributos do filme.***

#

## Fourth day

***Neste quarto dia do desafio, criei uma classe que gera o HTML com o do uso dos Text Blocks (Blocos de Texto) incorporado ao Java 15. Nele descrevi o corpo do HTML com as devidas informações que quis que fossem renderizadas no browser e no método de execução criei uma nova instância da classe PrintWriter passando como parâmetro o nome do arquivo .html que desejo onde seja gravado o que escrevi usando o text blocks, e ele gera automaticamente um arquivo com o nome e extensão que passei como parâmetro. Daí foi só abrir o arquivo gerado para visualizar no browser. O uso da escrita do HTML no Java foi intencional do desafio, mesmo não sendo uma boa prática, foi feito para trabalharmos um pouco mais de POO e a utilização de features incorporadas ao Java nas últimas versões.***