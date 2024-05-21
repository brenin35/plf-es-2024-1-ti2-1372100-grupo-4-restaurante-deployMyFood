import { ENDPOINT_URL } from "$lib/constants";

export async function getProdutos() {
    const response = await fetch(`${ENDPOINT_URL}/produtos`);
    if (!response.ok) {
      throw new Error("Falha produtos");
    }
    return await response.json();
  }