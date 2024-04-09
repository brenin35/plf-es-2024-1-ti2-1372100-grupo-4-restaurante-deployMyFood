### 3.3.4 Processo 4 – PAGAMENTO

_O processo do pagamento permite o pagamento dos pedidos diretamente no sistema, sem a necessidade de um funcionario, onde o cliente consegue selecionar o metodo que deseja e inserir as informações diretamente pelo celular_

![diagram_processo_4_TI](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1372100-grupo-4-restaurante/assets/129969591/dfbdccf7-1baf-4617-8c31-3fbba319ef19)


#### Detalhamento das atividades

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
