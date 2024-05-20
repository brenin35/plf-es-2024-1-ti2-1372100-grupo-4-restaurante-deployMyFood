import { writable } from 'svelte/store';

export type CartItem = {
    id: number;
    nome: string;
    quantidade: number;
    precoTotal: number;
  };

export const cart = writable<CartItem[]>([]);

