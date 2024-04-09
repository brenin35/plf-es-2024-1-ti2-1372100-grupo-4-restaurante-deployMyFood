### 3.3.4 Processo 4 – PAGAMENTO

_O processo do pagamento permite o pagamento dos pedidos diretamente no sistema, sem a necessidade de um funcionario, onde o cliente consegue selecionar o metodo que deseja e inserir as informações diretamente pelo celular_

![diagram_processo_4_TI](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1372100-grupo-4-restaurante/assets/129969591/4c9772a8-5c82-443f-a586-e82a05c83ef6)


#### Detalhamento das atividades

_Descreva aqui cada uma das propriedades das atividades do processo 4. 
Devem estar relacionadas com o modelo de processo apresentado anteriormente._

_Os tipos de dados a serem utilizados são:_

_* **Área de texto** - campo texto de múltiplas linhas_

_* **Caixa de texto** - campo texto de uma linha_

_* **Número** - campo numérico_

_* **Data** - campo do tipo data (dd-mm-aaaa)_

_* **Hora** - campo do tipo hora (hh:mm:ss)_

_* **Data e Hora** - campo do tipo data e hora (dd-mm-aaaa, hh:mm:ss)_

_* **Imagem** - campo contendo uma imagem_

_* **Seleção única** - campo com várias opções de valores que são mutuamente exclusivas (tradicional radio button ou combobox)_

_* **Seleção múltipla** - campo com várias opções que podem ser selecionadas mutuamente (tradicional checkbox ou listbox)_

_* **Arquivo** - campo de upload de documento_

_* **Link** - campo que armazena uma URL_

_* **Tabela** - campo formado por uma matriz de valores_

**Atividade 1 - Selecionar método de pagamento**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Método de pagamento | Seleção única  |                |                   |


| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| Pagamento via pix | Realizar pagamento/Fim do processo 4  |  |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| Pagamento via cartão | Atividade 2  |  |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| Pagamento com dinheiro | Entregar dinheiro/Fim do processo 4  |  |


**Atividade 2 - Pagamento com cartão**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Numero cartão | Número  | Minimo e máximo 16 digitos               |                   |

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome no cartão | Caixa de texto  |               |                   |

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Data de validade | Data  | Caixa de texto               | Maior que data atual                  |

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Código de segurança | Número  |  Minimo e máximo 3 digitos             |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Realizar pagamento | Fim do processo 4  |  |

**Atividade 3 - Registrar pagamento no sistema**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Valor pago | Número  |                |                   |

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Valor troco | Número  |                |                   |

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Mesa | Seleção única  |                |                   |


| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| Registrar pagamento em dinheiro | Fim do processo 4  | default |
