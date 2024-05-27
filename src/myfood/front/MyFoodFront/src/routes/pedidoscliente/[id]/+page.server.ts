import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async ({ params }) => {
  const id = params.id;

  const response = await fetch(`${ENDPOINT_URL}/clientes/${id}`).then((res) =>
    res.json()
  );

  const responsePedidos = await fetch(`${ENDPOINT_URL}/pedidos`).then((res) =>
    res.json()
  );

  return { clienteId: response, pedidos: responsePedidos };
}) satisfies PageServerLoad;
