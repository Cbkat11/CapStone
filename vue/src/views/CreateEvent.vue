<template>
  <div class="createEvent">
    <navigation v-bind:event="true" />
    <h2>Please Select up to Five Establishments for Ranking</h2>
    <restaurant-list v-bind:event="true" />
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
</style>