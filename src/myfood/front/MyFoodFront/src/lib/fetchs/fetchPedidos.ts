import { ENDPOINT_URL } from "$lib/constants";
import { get } from "svelte/store";
import { pedidoStore } from "../stores/pedidoStore";

export async function postPedidos(clienteId: number) {
  try {
    const clienteResponse = await fetch(
      `${ENDPOINT_URL}/clientes/${clienteId}`
    );
    if (!clienteResponse.ok) {
      throw new Error(
        `Failed to fetch cliente details! Status: ${clienteResponse.status}`
      );
    }
    const clienteData = await clienteResponse.json();
    const mesaId = clienteData.mesaId;

    const ItemsPedido = get(pedidoStore);
    const pedido = {
      cliente: clienteId,
      mesa: mesaId,
      statusPreparo: true,
      statusPagamento: false,
      precoTotalPedido: 0,
      itensPedido: ItemsPedido,
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
