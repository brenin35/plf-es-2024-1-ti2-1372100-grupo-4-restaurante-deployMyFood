import { ENDPOINT_URL } from "$lib/constants";

let mesas: Mesa[] = [];

type Mesa = {
  id: number;
};

export async function getMesas() {
  const response = await fetch(`${ENDPOINT_URL}/mesas`);
  if (!response.ok) {
    throw new Error("Failed to fetch mesas");
  }
  return await response.json();
}

export async function criarMesa() {
  const response = await fetch(`${ENDPOINT_URL}/mesas`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({}),
  });

  if (!response.ok) {
    throw new Error("Falha ao criar mesa");
  }
  return await response.json();
}

export async function deleteMesa(id: number) {
  try {
    const response = await fetch(`${ENDPOINT_URL}/mesas/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error("Falha ao deletar mesa");
    }
    console.log(`Mesa ID ${id} foi deletada`);
    mesas = mesas.filter((mesa) => mesa.id !== id);
    window.location.reload();
  } catch (error) {
    console.error("Error:", error);
  }
}
