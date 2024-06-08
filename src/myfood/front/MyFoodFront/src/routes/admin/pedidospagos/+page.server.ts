import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async ({ params }) => {
  const response = await fetch(`${ENDPOINT_URL}/pedidos`).then((res) => res.json());

  const filteredPedidos = response.filter((pedido: { statusPagamento: boolean }) => pedido.statusPagamento === true);

  console.log(filteredPedidos);
  
  return { pedidos: filteredPedidos };
}) satisfies PageServerLoad;
