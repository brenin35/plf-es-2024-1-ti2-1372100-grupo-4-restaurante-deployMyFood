# Cardápio Digital


* Arthur George Souza Cardenas
* Breno de Oliveira Brandão
* Leonardo Vieira Machado
* Nicolas Almeida Prado da Silva
* Pedro Henrique de Vasconcellos Franco


---

Professores:

* Eveline Alonso Veloso
* Juliana Amaral Baroni de Carvalho

---

_Curso de Engenharia de Software_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade Católica de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---

Projeto de um cardápio digital para agilizar os passos dos pedidos em restaurantes da seleção do prato ate seu pagamento.

---

## 1. Introdução

Este projeto visa desenvolver uma alternativa para a população facilmente poder realizar todos os passos de um pedido em um restaurante, da seleção do prato, até sua avaliação.

### 1.1 Contextualização

O cardápio digital apresentou um aumento significativo de uso desde a pandemia segundo a reportagem [1.1] CNN Brasil,em que os cardápios em QR Code salvaram os restaurantes, como uma alternativa até então não muito explorada, grande  parte da população já apresenta preferência por eles por conta da praticidade e segurança que trazia naquela época.

### 1.2 Problema

Um dos principais problemas enfrentados por restaurantes em geral é a margem de erro inerente às atividades humanas. Erros na anotação de pedidos, demora no atendimento com o restaurante cheio e a complexidade no fechamento de contas, são fatores que contribuem para a insatisfação do cliente.. Esse desafio é muito comum no setor e reflete a necessidade de modernização dos processos. Foi possível observar os problemas citados no caso específico de um restaurante de uma pousada familiar, Villa Rica Pousada Boutique. A perspectiva de clientes que frequentam diversos restaurantes reforça essa realidade, indicando uma tendência generalizada no setor que demanda atenção e soluções inovadoras e mais eficientes.

### 1.3 Objetivo geral

O objetivo deste trabalho é desenvolver um sistema de cardápio digital automatizado que otimize o processo de anotação e fechamento de pedidos.

#### 1.3.1 Objetivos específicos

Realizar o cadastro de prato, pedido do cliente pelo cardápio de modo prático, avaliação de prato pelo cliente para recolher opiniões e realizar o pagamento

### 1.4 Justificativas

Através de depoimentos dos donos do restaurante da pousada Villa Rica e também após análise de diversas reclamações sobre atrasos de pedidos nos restaurantes no Reclame Aqui e concluímos que a existência de uma aplicação pode agilizar os processos deles na preparação de um pedido, evitando atrasos.

## 2. Participantes do processo

Gerentes de restaurante: Gerenciam a implementação e utilização do sistema, treinando a equipe para otimizar os processos de atendimento.
<br>
Clientes: Usuários finais do sistema, que se beneficiam de uma experiência de pedido mais eficiente e satisfatória.
<br>
Equipe do restaurante: Utiliza o sistema para receber pedidos de forma mais organizada e precisa, contribuindo para a eficiência na preparação dos pratos.

## 3. Modelagem do processo de negócio

### 3.1. Análise da situação atual

O processo de pedido em restaurantes ainda segue um modelo tradicional e manual. O cliente recebe o cardápio físico, faz sua escolha e chama o garçom para anotar o pedido em papel. O garçom então leva o pedido para a cozinha, onde é preparado e depois entregue ao cliente. Após o consumo, o cliente solicita a conta, que é novamente anotada pelo garçom e calculada.

Esse processo pode ser lento, especialmente em restaurantes lotados, e está sujeito a erros de anotação, pedidos incompletos ou atrasos na entrega. Além disso, exige a constante interação entre cliente e garçom, o que pode ser inconveniente em alguns casos.

### 3.2. Descrição geral da proposta

Com o intuito da diminuição de erros na produção, aumento da agilidade na casa, será disponibilizado um cardápio digital que permitirá o cliente fazer 100% do pedido e pagamento diretamente do celular!

Afirmado isto, os clientes que já conhecem a casa ou já sabem o que vão pedir, costumam sempre utilizar o cardápio digital, logo os garçons terão mais espaço para serem "vendedores" e atingir cada vez mais um público maior, mais satisfeito e bem atendido no restaurante. Contudo, se espera um aumento na satisfação dos clientes, devido a maior organização da casa e a possibilidade de receber mais clientes sem perder qualidade de venda, além de muito mais agilidade no processo de solicitar o prato ao processo do pagamento.

### 3.3. Modelagem dos processos

[PROCESSO 1 - Cadastro de Prato](processo-1-nome-do-processo.md "Detalhamento do Processo 1.")

[PROCESSO 2 - Pedido pelo Cardápio](processo-2-nome-do-processo.md "Detalhamento do Processo 2.")

[PROCESSO 3 - Avaliação de Pratos](processo-3-nome-do-processo.md "Detalhamento do Processo 3.")

[PROCESSO 4 - Pagamento](processo-4-nome-do-processo.md "Detalhamento do Processo 4.")

## 4. Projeto da solução

O documento a seguir apresenta o detalhamento do projeto da solução. São apresentadas duas seções que descrevem, respectivamente: modelo relacional e tecnologias.

[Projeto da solução](solution-design.md "Detalhamento do projeto da solução: modelo relacional e tecnologias.")


## 5. Indicadores de desempenho

O documento a seguir apresenta os indicadores de desempenho dos processos.

[Indicadores de desempenho dos processos](performance-indicators.md)


## 6. Interface do sistema

A sessão a seguir apresenta a descrição do produto de software desenvolvido. 

[Documentação da interface do sistema](interface.md)

## 7. Conclusão

A implementação do sistema de cardápio digital automatizado demonstrou ser uma solução eficaz para problemas comuns em restaurantes, como erros na anotação de pedidos e lentidão no atendimento. O novo sistema reduzirá significativamente esses problemas, aumentando a agilidade e a satisfação dos clientes. A funcionalidade de avaliação dos pratos fornecerá informações valiosas para melhorias contínuas.

Os indicadores de desempenho são um excelente meio para o gerente identificar melhorias no tempo de atendimento e na precisão dos pedidos. Em suma, o sistema cumpriu seus objetivos, mostrando-se uma solução moderna e eficiente para a gestão de pedidos em restaurantes.


# REFERÊNCIAS

**[1.1]** - WIENER-BRONNER, Danielle. Entenda por que os controversos cardápios em QR Code sobreviveram à pandemia. CNN Brasil, 20 ago. 2022. Disponível em: <https://www.cnnbrasil.com.br/economia/saiba-por-que-restaurantes-ainda-tem-cardapios-em-qr-code/>. Acesso em: 15 mar. 2024.

# APÊNDICES


_Atualizar os links e adicionar novos links para que a estrutura do código esteja corretamente documentada._


## Apêndice A - Código fonte

[Código do front-end](../src/myfood/front/MyFoodFront) -- repositório do código do front-end

[Código do back-end](../src/myfood/back_novo)  -- repositório do código do back-end


## Apêndice B - Apresentação final


[Slides da apresentação final](presentations/)


[Vídeo da apresentação final](video/)






