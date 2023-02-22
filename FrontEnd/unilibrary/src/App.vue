<script setup>
import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <div>
    <header>
      <div>
        <nav>
          <h1>
            <RouterLink to="/">Livros</RouterLink>
          </h1>
          <h1>
            <RouterLink to="/about">Usuários</RouterLink>
          </h1>
        </nav>
      </div>
    </header>

    <RouterView />
  </div>
</template>

<style scoped>
header {
  background: rgb(23, 68, 68);
  color: aliceblue;
}
a {
  font-style: none;
  color: aliceblue;
  text-decoration: none;
}
nav {
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
}
h1 :hover {
  /* color: rgb(178, 184, 190); */
  background: rgb(14, 41, 41);
}
</style>
