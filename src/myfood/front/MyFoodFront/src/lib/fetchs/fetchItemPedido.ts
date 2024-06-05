import { ENDPOINT_URL } from "$lib/constants";

export async function createItemPedido(pedidoId: number, itemPedido: any) {
  const response = await fetch(`${ENDPOINT_URL}/itempedidos/${pedidoId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(itemPedido),
  });

  if (!response.ok) {
    throw new Error("Failed to create itemPedido");
  }

  return await response.json();
}

export async function updateItemPedido(id: number, itemPedido: any) {
  const response = await fetch(`${ENDPOINT_URL}/itempedidos/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(itemPedido),
  });

  if (!response.ok) {
    throw new Error("Failed to update itemPedido");
  }

  return await response.json();
}

export async function deleteItemPedido(id: number) {
  const response = await fetch(`${ENDPOINT_URL}/itempedidos/${id}`, {
    method: "DELETE",
  });

  if (!response.ok) {
    throw new Error("Failed to delete itemPedido");
  }
}
