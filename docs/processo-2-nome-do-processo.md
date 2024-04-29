### 3.3.2 Processo 2 – Pedido pelo Cardápio

_O processo 2 permite que o cliente realize o pedido de itens pelo menu digital. O processo de pedido é simplificado, apresentando as informações e imagens dos pratos, podendo a critério do gerente também as avaliações do prato._

![Pedido pelo Cardápio](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1372100-grupo-4-restaurante/assets/63589918/a94dd0a9-20e6-4b92-87a6-a749e8a3696c)






#### Detalhamento das atividades

**Atividade 1 - Escanear QR Code**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
|   QR              |      Imagem            |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Escanear QR | Inserir Dados de Identificação do Cliente             |                   |

**Atividade 2 - Inserir Dados de Identificação do Cliente**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
|  Nome               |     Caixa de Texto             |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Finalizar Cadastro | Escolher item no menu            |                   |

**Atividade 3 - Escolher item no menu**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Itens  | Seleção múltipla  |    Escolher de lista de pratos            |       |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Adicionar ao pedido | Escolher item no menu              |                   |
|  Finalizar pedido | Receber pedido             |                   |


**Atividade 4 - Receber pedido**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Pedidos  |  Área de texto  |     Not null           |                   |
| Pedidos  | Número  |     Número positivo           |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Processar pedido    |  Preparar pedido para cliente           |                 |


**Atividade 5 - Preparar pedido para cliente**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Pedidos  |  Área de texto  |     Not null           |                   |
| Pedidos  | Número  |     Número positivo           |                   |                |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Pedido Feito    | Receber Pedido              |                  |


**Atividade 6 - Receber pedido**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Avaliar Pedido    | Avaliar Pedido         |                  |

