import { API_URL, ENDPOINT_URL, SIZE } from "../../../../../../../../front/MyFoodFront/src/lib/constants";

export async function getMesas() {
  const response = await fetch(`${ENDPOINT_URL}/mesas`);
  if (!response.ok) {
    throw new Error("Failed to fetch mesas");
  }
  return response.json();
}

export async function createMesa(mesa: any) {
  const response = await fetch(`${ENDPOINT_URL}/mesas`, {
    method: "POST",
    mode: 'no-cors',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(mesa),
  });
  if (!response.ok) {
    throw new Error("Failed to create mesa");
  }
  const createdMesa = await response.json();

  // Generate QR code for the created mesa
  createdMesa.qrCode = `${API_URL}/mesas/${createdMesa.id}${SIZE}`;

  return createdMesa;
}

export async function updateMesa(id: any, updatedMesa: any) {
  const response = await fetch(`${ENDPOINT_URL}/mesas/${id}`, {
    method: "PUT",
    mode: 'no-cors',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(updatedMesa),
  });
  if (!response.ok) {
    throw new Error("Failed to update mesa");
  }
  return response.json();
}

export async function deleteMesa(id: any) {
  const response = await fetch(`${ENDPOINT_URL}/mesas/${id}`, {
    method: "DELETE",
  });
  if (!response.ok) {
    throw new Error("Failed to delete mesa");
  }
}