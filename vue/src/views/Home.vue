<template>
  <div class="home">
    <navigation/>
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>

import Navigation from "../components/Navigation.vue"

export default {
  name: "home",
  components: {
    Navigation
  }
};
</script>
