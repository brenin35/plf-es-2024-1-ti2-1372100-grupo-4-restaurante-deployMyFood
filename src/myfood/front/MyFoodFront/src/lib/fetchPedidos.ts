import { ENDPOINT_URL } from "$lib/constants";

export async function postPedidos(clienteId) {
  try {
    const clienteResponse = await fetch(`${ENDPOINT_URL}/clientes/${clienteId}`);
    if (!clienteResponse.ok) {
      throw new Error(`Failed to fetch cliente details! Status: ${clienteResponse.status}`);
    }
    const clienteData = await clienteResponse.json();
    const mesaId = clienteData.mesaId;

    const pedido = {
      cliente: {
        id: clienteId,
      },
      mesa: {
        id: mesaId,
      },
      statusPreparo: true,
      statusPagamento: false,
      precoTotalPedido: 0,
      itensPedido: [],
    };

    const response = await fetch(`${ENDPOINT_URL}/pedidos`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(pedido),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.json();
    console.log("Response from server:", data);

    return data;
  } catch (error) {
    console.error("Error:", error);
    throw error;
  }
}
