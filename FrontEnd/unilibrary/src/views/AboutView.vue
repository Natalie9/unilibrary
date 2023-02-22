<template>
  <main class="books">
    <h1 @click="getUsuarios" class="books_title">Usuários Disponíveis:</h1>
    <!-- Listar Usuarios  -->
    <ul v-for="usuario in usuarios" :key="usuario.id" class="books_list">
      <li>
        <h3>{{ usuario.nome }}</h3>
        <p>Email: {{ usuario.email }}</p>
        <p>Matrícula: {{ usuario.matricula }} - {{ usuario.status }}</p>
        <p>Telefone: {{ usuario.telefone }}</p>
      </li>
    </ul>

    <!-- Cadastrar Usuario -->
    <div class="books_create">
      <h2 class="books_create__title">Cadastrar novo usuario</h2>
      <form class="books_create__form" @submit.prevent="postNovoUsuario">
        <input v-model="novoUsuario.nome" placeholder="Nome" />
        <input v-model="novoUsuario.email" placeholder="Email" type="email" />
        <input v-model="novoUsuario.matricula" placeholder="Matricula" />
        <input v-model="novoUsuario.status" placeholder="Status" disabled />
        <input v-model="novoUsuario.telefone" placeholder="Telefone" />
        <button class="books_create_form-button" type="submit">Salvar</button>
      </form>
    </div>

    <!-- Cadastrar Usuario -->
    <!-- Realizar empréstimo -->
  </main>
</template>

<script>
export default {
  data() {
    return {
      usuarios: [],
      novoUsuario: {
        matricula: "",
        nome: "",
        email: "",
        telefone: "",
        status: "Ativo",
      },
    };
  },
  methods: {
    async getUsuarios() {
      fetch("http://localhost:8080/usuario", { method: "GET" }).then(
        async (response) => {
          this.usuarios = await response.json();
          console.log(this.usuarios);
        }
      );
    },
    postNovoUsuario() {
      const body = JSON.stringify(this.novoUsuario);
      console.log(body);
      fetch("http://localhost:8080/usuario", {
        method: "POST",
        body,
      }).then(async (response) => {
        console.log(response);
      });
    },
  },
  created() {
    this.getUsuarios();
  },
};
</script>

<style scoped>
.books {
  color: rgb(17, 59, 94);
}
.books_create {
  border: #efefef solid 2px;
  border-radius: 5px;
  padding: 24px;
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  width: 80vw;
  margin-top: 40px;
}

.books_create__title {
  margin-bottom: 24px;
}
.books_create__form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-gap: 10px;
  justify-content: space-evenly;
}
.books_create__form input {
  border: #efefef 1px solid;
  border-radius: 5px;
  padding: 8px;
}

.books_create__form button {
  background: rgb(47, 132, 201);
  border-radius: 5px;
  border: none;
  color: #efefef;
}
.books_create__form button:hover {
  background: rgb(28, 101, 161);
}
</style>
