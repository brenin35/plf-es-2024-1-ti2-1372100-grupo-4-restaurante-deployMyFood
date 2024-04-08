### 3.3.2 Processo 2 – Pedido pelo Cardápio

_O processo 2 permite que o cliente realize o pedido de itens pelo menu digital. O processo de pedido é simplificado, apresentando as informações e imagens dos pratos, podendo a critério do gerente também as avaliações do prato._

![Pedido pelo Cardápio](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1372100-grupo-4-restaurante/assets/63589918/3b47a07a-125f-4567-9f85-f650779ad820)




#### Detalhamento das atividades

**Atividade 1 - Escanear QR Code**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
|   QR              |      Imagem            |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Escanear QR | Atividade 2               |                   |

**Atividade 2 - Inserir Dados de Identificação do Cliente**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
|  Nome               |     Caixa de Texto             |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Finalizar Cadastro | Atividade 3               |                   |

**Atividade 3 - Escolher item no menu**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Itens  | Seleção múltipla  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Adicionar ao pedido | Atividade 3               |                   |
|  Finalizar pedido | Atividade 4               |                   |


**Atividade 4 - Receber pedido**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Pedidos | Tabela |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Processar pedido    |  Atividade 5           |                 |


**Atividade 5 - Preparar pedido para cliente**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Pedidos  | Tabela  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Pedido Feito    | Atividade 6              |                  |


**Atividade 6 - Receber pedido**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Avaliar Pedido    | Atividade 7 / Processo 3         |                  |



**Atividade 7 - Avaliar pedido**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Pedidos  | tabela  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Avaliar Pedido | Atividade 7  |  |
| Finalizar Avaliação     | Atividade 8 / Processo 4    |                   |



**Atividade 8 - Realizar pagamento**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Pedidos  | Tabela  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Finalizar Pagamento     | fim processo 2           |  default                 |
