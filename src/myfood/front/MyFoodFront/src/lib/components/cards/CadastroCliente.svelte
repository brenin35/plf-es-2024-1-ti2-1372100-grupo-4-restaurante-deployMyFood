<script lang="ts">
  import { Button } from "$lib/components/ui/button/index.js";
  import * as Card from "$lib/components/ui/card/index.js";
  import { Input } from "$lib/components/ui/input/index.js";
  import { Label } from "$lib/components/ui/label/index.js";
</script>

<Card.Root class="w-[350px]">
  <Card.Header>
    <Card.Title>Cadastro cliente</Card.Title>
    <Card.Description
      >Coloque seu nome e telefone para acessar o Cardapio!</Card.Description
    >
  </Card.Header>
  <Card.Content>
    <form>
      <div class="grid w-full items-center gap-4">
        <div class="flex flex-col space-y-1.5">
          <Label for="name">Nome</Label>
          <Input id="name" placeholder="Seu nome" />
        </div>
        <div class="flex flex-col space-y-1.5">
          <Label for="framework">Telefone</Label>
          <Input id="phone" placeholder="(31) 98765-4321" />
        </div>
      </div>
    </form>
  </Card.Content>
  <Card.Footer class="flex justify-end">
    <Button variant="buttonAdd">Cadastrar!</Button>
  </Card.Footer>
</Card.Root>
