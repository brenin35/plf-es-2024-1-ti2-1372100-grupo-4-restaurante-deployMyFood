### 3.3.2 Processo 2 – Pedido pelo Cardápio

_O processo 2 permite que o cliente realize o pedido de itens pelo menu digital. O processo de pedido é simplificado, apresentando as informações e imagens dos pratos, podendo a critério do gerente também as avaliações do prato._

![Pedido pelo Cardápio](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1372100-grupo-4-restaurante/assets/63589918/6c7ae776-8fc2-4e71-90de-5fe96a49efdc)






#### Detalhamento das atividades

**Atividade 1 - Escanear QR Code**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
|   QR              |      Imagem            |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Escanear QR | Realizar Cadastro             |                   |

**Atividade 2 - Realizar Cadastro**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
|  Nome               |     Caixa de Texto             |   not null             |                   |
|  Telefone               |     Caixa de Texto             |     not null           |                   |

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
| Nome pedido  |  Área de texto  |     Not null           |                   |
| Quantidade pedido  | Número  |     Número positivo           |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Processar pedido    |  Preparar pedido para cliente           |                 |


**Atividade 5 - Preparar pedido para cliente**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome pedido |  Área de texto  |     Not null           |                   |
| Quantidade pedido  | Número  |     Número positivo           |                   |                |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Pedido Feito    | Receber Pedido              |                  |


**Atividade 6 - Receber pedido**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
|  Avaliar Pedido    | Avaliar Pedido         |                  |

