## 5. Indicadores de desempenho

| **Indicador** | **Objetivos** | **Descrição** | **Fonte de dados** | **Fórmula de cálculo** |
| ---           | ---           | ---           | ---             | ---             |
| Percentual de Avaliações Positivas| Avaliar a satisfação do cliente                  | Percentual de avaliações com 4 ou 5 estrelas                 | Tabela Avaliacao        | (Número de avaliações com 4 ou 5 estrelas / Total de avaliações) * 100 |
| Taxa de Pedidos Completados      | Melhorar a eficiência do processo de pedidos     | Percentual de pedidos com status 'Pago'                     | Tabela Pedido           | (Número de pedidos pagos / Número total de pedidos) * 100  |
| Tempo Médio de Atendimento       | Reduzir o tempo de espera do cliente             | Média de tempo entre o pedido e a conclusão do pagamento     | Tabela Pedido           | SUM(Hora de pagamento - Hora do pedido) / Número total de pedidos |
