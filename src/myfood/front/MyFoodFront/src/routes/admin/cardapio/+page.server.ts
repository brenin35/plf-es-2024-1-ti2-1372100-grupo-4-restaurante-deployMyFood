import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async () => {
  const response = await fetch(`${ENDPOINT_URL}/produtos`).then((res) =>
    res.json()
  );

  const responseAvaliacao = await fetch(`${ENDPOINT_URL}/avaliacao`).then((res) =>
    res.json()
  );
  
  return { produtos: response, responseAvaliacao };
}) satisfies PageServerLoad;
