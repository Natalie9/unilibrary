<template>
  <main class="books">
    <h1 class="books_title">Biblioteca</h1>
    <!-- Listar Livros   -->
    <ul v-for="livro in livros" :key="livro.id" class="books_list">
      <li>
        <RouterLink :to="`/book/${livro.id}`" class="books_book">
          <h3>{{ livro.nome }}</h3>
        </RouterLink>
        <small>ISBN: {{ livro.isbn }}</small>

        <p><b>Autor:</b> {{ livro.autor }}</p>

        <p>Disponíveis: {{ livro.qntd_Acervo + 1 }}</p>
      </li>
    </ul>

    <!-- Cadastrar Livro -->
    <div class="books_create">
      <h2 class="books_create__title">Cadastrar novo livro</h2>
      <form class="books_create__form">
        <input v-model="novoLivro.nome" placeholder="Nome" />
        <input v-model="novoLivro.isbn" placeholder="ISBN" />
        <input v-model="novoLivro.autor" placeholder="Autor" />
        <input v-model="novoLivro.ano_publicacao" placeholder="Ano" />
        <input v-model="novoLivro.edicao" placeholder="Edição" />
        <input v-model="novoLivro.genreo" placeholder="Gênero" />
        <input
          v-model="novoLivro.qntd_Acervo"
          placeholder="Quantidade no acerto"
          type="number"
        />
        <button
          type="submit"
          @click="postNovoLivro"
          class="books_create_form-button"
        >
          Salvar
        </button>
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
      livros: [],
      novoLivro: {
        nome: "",
        isbn: "",
        autor: "",
        ano_publicacao: "",
        edicao: "",
        genero: "",
        qntd_Acervo: 0,
      },
    };
  },
  methods: {
    async getLivros() {
      fetch("http://localhost:8080/livro", { method: "GET" }).then(
        async (response) => {
          this.livros = await response.json();
          console.log(this.livros);
        }
      );
    },
    postNovoLivro() {
      const body = JSON.stringify({
        nome: "O Alquimista",
        isbn: "1234567890144",
        autor: "Jorge Angel Livraga",
        ano_publicacao: "1989",
        edicao: "2",
        genero: "filosofia",
        qntd_acervo: 3,
      });
      console.log(body);
      fetch("http://localhost:8080/livro", {
        method: "POST",
        body,
      }).then(async (response) => {
        console.log(response);
      });
    },
  },
  mounted() {
    this.getLivros();
  },
};
</script>
<style scoped>
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
