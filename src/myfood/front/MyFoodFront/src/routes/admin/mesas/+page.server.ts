import type { PageServerLoad } from "./$types";
import { ENDPOINT_URL } from "$lib/constants";

export const load = (async () => {
  const response = await fetch(`${ENDPOINT_URL}/mesas`).then((res) =>
    res.json()
  );

  return { mesas: response };
}) satisfies PageServerLoad;
