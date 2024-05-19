import { ENDPOINT_URL } from "../../../../../../../../front/MyFoodFront/src/lib/constants";

export async function getProdutos() {
  const response = await fetch(`${ENDPOINT_URL}/produtos`);
  if (!response.ok) {
    throw new Error(`Erro ao buscar produtos: ${response.status}`);
  }
  return response.json();
}

export async function createProduto(produto: { nome: any; descricao: any; preco: any; imagem: any; visibilidadeAvaliacao: any; }) {
    const response = await fetch(`${ENDPOINT_URL}/produtos`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(produto),
    });
    if (!response.ok) {
      throw new Error("Failed to create produto");
    }
    return response.json();
  }

export async function updateProduto(id: number, updatedProduto: { nome: any; descricao: any; preco: any; imagem: any; visibilidadeAvaliacao: any; }) {
    const response = await fetch(`${ENDPOINT_URL}/produtos/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedProduto),
    });
    if (!response.ok) {
      throw new Error("Failed to update produto");
    }
    return response.json();
  }
  
  export async function deleteProduto(id: number) {
    const response = await fetch(`${ENDPOINT_URL}/produtos/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("Failed to delete produto");
    }
  }