import { ENDPOINT_URL } from "../../../../../../../../front/MyFoodFront/src/lib/constants";

export async function getClientes() {
  const response = await fetch(`${ENDPOINT_URL}/clientes`);
  if (!response.ok) {
    throw new Error("Failed to fetch clientes");
  }
  return response.json();
}

export async function createCliente(cliente: { nomeCliente: any; contatoCliente: any; mesaId: any; }) {
    const response = await fetch(`${ENDPOINT_URL}/clientes`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cliente),
    });
    if (!response.ok) {
      throw new Error("Failed to create cliente");
    }
    return response.json();
  }

export async function updateCliente(id: any, updatedCliente: any) {
    const response = await fetch(`${ENDPOINT_URL}/clientes/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedCliente),
    });
    if (!response.ok) {
      throw new Error("Failed to update cliente");
    }
    return response.json();
  }
  
  export async function deleteCliente(id: any) {
    const response = await fetch(`${ENDPOINT_URL}/clientes/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("Failed to delete cliente");
    }
  }