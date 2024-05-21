import { writable } from "svelte/store";
import { type Prato } from "$lib/fetchProdutos";

export type ItemPedido = {
  id: number;
  produtos: Prato;
  quantidade: number;
  itemPreco: number;
};

function persist(key: string, value: any) {
  if (typeof window !== "undefined") {
    const storedValue = window.localStorage.getItem(key);
    if (storedValue) {
      value = JSON.parse(storedValue);
    }

    const store = writable(value);

    store.subscribe(($value) => {
      window.localStorage.setItem(key, JSON.stringify($value));
    });

    return store;
  } else {
    return writable(value);
  }
}
export const cart = persist('cart', [] as ItemPedido[]);
export const session = persist("session", { clienteId: null, mesaId: null });
