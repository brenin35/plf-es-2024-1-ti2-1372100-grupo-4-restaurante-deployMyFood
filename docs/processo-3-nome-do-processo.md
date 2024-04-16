### 3.3.3 Processo 3 – AVALIAÇÃO DE PRATOS

_O processo de avaliação permite que o usuario avalie separadamente cada prato, selecionando uma nota geral e podendo escrever uma breve descrição sobre o prato_

![diagram_processo_3_TI](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1372100-grupo-4-restaurante/assets/129969591/fb947ad5-a487-46dd-8434-0c2030c4fbac)

#### Detalhamento das atividades


**Atividade 1 - Selecionar prato que gostaria de avaliar**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Escolher prato   | Seleção múltipla  |Escolher prato cadastrado e que foi pedido|                   |


| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| Selecionar Prato | Avaliar prato  |                     |



**Atividade 2 - Avaliar prato**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Avaliação de pratos | Seleção múltipla  |De 1 a 5 |                   |
|Comentar avaliação|Caixa de texto|Maximo de 200 caracteres|                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Avaliar Prato        | fim processo de avaliação             | default |
|                      |                                |                   |
