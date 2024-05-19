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
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(mesa),
  });
  if (!response.ok) {
    throw new Error("Failed to create mesa");
  }
  const createdMesa = await response.json();

  createdMesa.qrCode = `${API_URL}/mesas/${createdMesa.id}${SIZE}`;

  const updateResponse = await fetch(`${ENDPOINT_URL}/mesas/${createdMesa.id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(createdMesa),
  });

  if (!updateResponse.ok) {
    throw new Error("Failed to update mesa with QR code");
  }

  const updatedMesa = await updateResponse.json();

  return updatedMesa;
}

export async function deleteMesa(id: any) {
  const response = await fetch(`${ENDPOINT_URL}/mesas/${id}`, {
    method: "DELETE",
  });
  if (!response.ok) {
    throw new Error("Failed to delete mesa");
  }
}