import { ENDPOINT_URL } from "$lib/constants";

export async function postPedidos() {
  try {
    const pedido = {
      cliente: {
        id: 1,
      },
      mesa: {
        id: 1,
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

    return data.id;
  } catch (error) {
    console.error("Error:", error);
    throw error;
  }
}
