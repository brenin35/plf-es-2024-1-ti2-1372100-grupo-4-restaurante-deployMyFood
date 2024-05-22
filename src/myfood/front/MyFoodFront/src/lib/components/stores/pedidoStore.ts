import type { ItemPedido } from "$lib/types";
import { writable, get } from "svelte/store";

function createPedidoStore() {
  const baseStore = writable<ItemPedido[]>([]);
  const { subscribe, set, update } = baseStore;

  return {
    subscribe,
    set,
    update,
    addItem: (item: ItemPedido) => {
      update((itensPedido) => {
        itensPedido.push(item);
        return itensPedido;
      });
    },
    removeItem: (item: ItemPedido) => {
      update((itensPedido) => {
        return itensPedido.filter((value) => value.id !== item.id);
      });
    },
  };
}

export const pedidoStore = createPedidoStore();
