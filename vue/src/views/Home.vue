<template>
  <div class="home">
    <navigation/>
    <h1>Home</h1>
    <restaurant-list/>
  </div>
</template>

<script>

import Navigation from "../components/Navigation.vue"
import RestaurantList from "../components/RestaurantList.vue"

export default {
  name: "home",
  components: {
    Navigation,
    RestaurantList
  }
};
</script>
<style scoped>
h1{
  text-decoration: underline;
  text-align: center;
}
body{
  background: black;
}


</style>