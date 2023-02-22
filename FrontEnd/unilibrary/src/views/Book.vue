<template>
  <main class="books">
    <h3>{{ livro.nome }}</h3>

    <small>ISBN: {{ livro.isbn }}</small>

    <p><b>Autor:</b> {{ livro.autor }}</p>

    <p>Disponíveis: {{ livro.qntd_Acervo + 1 }}</p>
    <select name="select" v-model="selectUser">
      <option v-for="usuario in usuarios" :key="usuario.id">
        {{ usuario.email }}
      </option>
    </select>
    <button class="reservar" @click="fazerReserva">Reservar</button>
  </main>
</template>
<script>
export default {
  data() {
    return {
      livro: {},
      usuarios: [],
      selectUser: {},
      teste: null,
    };
  },
  methods: {
    async getLivro() {
      const url = "http://localhost:8080/livro/" + this.$route.params.id;
      fetch(url, {
        method: "GET",
      }).then(async (response) => {
        console.log(response);
        this.livro = await response.json();
      });

      this.getUserList();
    },
    getUserList() {
      fetch("http://localhost:8080/usuario", { method: "GET" }).then(
        async (response) => {
          this.usuarios = await response.json();
          console.log(this.usuarios);
        }
      );
    },
    fazerReserva() {
      window.alert("Reserva feita com sucesso " + this.selectUser + "!!");
      this.$router.push("/");
    },
  },
  mounted() {
    this.getLivro();
  },
};
</script>
<style scoped>
.reservar {
  background: rgb(17, 59, 94);
  border: none;
  border-radius: 5px;
  color: #efefef;
  font-size: 18px;
  padding: 5px;
  cursor: pointer;
}
.reservar:hover {
  background: rgb(29, 104, 165);
}
a {
  text-decoration: none;
}
h2 {
  margin-bottom: 0;
}
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
