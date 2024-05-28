import type { ItemPedido } from "$lib/types";
import { writable, derived } from "svelte/store";

function createPedidoStore() {
  const baseStore = writable<ItemPedido[]>([]);
  const { subscribe, set, update } = baseStore;

  const precoTotalPedido = derived(baseStore, $baseStore =>
    $baseStore.reduce((total, item) => total + item.precoTotal, 0)
  );

  return {
    subscribe,
    set,
    update,
    precoTotalPedido,
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
