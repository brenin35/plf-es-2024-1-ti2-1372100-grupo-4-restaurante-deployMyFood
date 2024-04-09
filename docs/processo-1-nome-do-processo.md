### 3.3.1 Processo 1 – Cadastro de Prato

_O processo de cadastro de pratos permite que o gerente insira novos pratos no menu. O processo é simples e rápido, permitindo que o gerente atualize o menu com frequência e ofereça novos sabores aos seus clientes._

![Cadastro](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1372100-grupo-4-restaurante/assets/103607467/9febb266-19e0-429b-8fed-becded072430)


#### Detalhamento das atividades


**Inserir dados do prato**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome do Prato | Caixa de Texto  |                |                   |
| Descrição |	Área de Texto	|                |                   |
| Preço | Número  | Deve ser positivo |                |
| Categoria |	Seleção única	|  |           |
| Imagem |	Imagem	|  |           |


| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| Salvar | Registra no menu  | default |
| Cancelar | Retorna ao menu de gestão | cancel |


**Selecionar Prato (Modificar)**

**Campo** | **Tipo** | **Restrições** | **Valor default**
--- | --- | --- | ---
Selecionar Prato | Seleção única | Escolher de lista de pratos | 

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Continuar | Alterar dados do prato selecionado | default
Cancelar | Retorna ao menu de gestão | cancel


**Alterar dados do prato**

**Campo** | **Tipo** | **Restrições** | **Valor default**
--- | --- | --- | ---
Nome do Prato | Caixa de Texto | | Nome atual do prato
Descrição | Área de Texto | | Descrição atual do prato
Preço | Número | Deve ser positivo | Preço atual
Categoria | Seleção única | | Categoria atual
Imagem | Imagem | | Imagem atual

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Salvar Alterações | Registra no menu | default
Cancelar | Retorna ao menu de gestão | cancel


**Selecionar Prato (Avaliação)**

**Campo** | **Tipo** | **Restrições** | **Valor default**
--- | --- | --- | ---
Selecionar Prato | Seleção única | Escolher de lista de pratos | 

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Continuar | Visualizar avaliações do prato selecionado | default
Cancelar | Retorna ao menu de gestão | cancel


**Selecionar Avaliação**

**Campo** | **Tipo** | **Restrições** | **Valor default**
--- | --- | --- | ---
Selecionar Avaliação | Seleção única | Escolher de lista de avaliações do prato selecionado | 

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Continuar para Visibilidade | Alterar visibilidade da avaliação selecionada | 
Continuar para Exclusão | Confirmar exclusão da avaliação selecionada | 
Cancelar | Retorna ao menu de gestão | cancel


**Alterar visibilidade**

**Campo** | **Tipo** | **Restrições** | **Valor default**
--- | --- | --- | ---
Visibilidade | Seleção única | Opções "Público" ou "Privado" | Visibilidade atual da avaliação

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Salvar Visibilidade | Registra no menu | default
Cancelar | Retorna ao menu de gestão | cancel


**Confirmar Exclusão**

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Confirmar Exclusão | Concluir exclusão da avaliação | default
Cancelar | Retorna ao menu de gestão | cancel


**Selecionar Prato (Excluir)**

**Campo** | **Tipo** | **Restrições** | **Valor default**
--- | --- | --- | ---
Selecionar Prato | Seleção única | Escolher de lista de pratos | 

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Continuar | Confirmar exclusão do prato selecionado | default
Cancelar | Retorna ao menu de gestão | cancel


**Confirmar exclusão do prato**

**Comandos** | **Destino** | **Tipo**
--- | --- | ---
Confirmar Exclusão | Concluir exclusão | default
Cancelar | Retorna ao menu de gestão | cancel
