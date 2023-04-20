<template>
  <div class="createEvent">
    <navigation v-bind:event=true />
    <create-invitation />
  </div>
</template>

<script>

import Navigation from "../components/Navigation.vue"
import CreateInvitation from "../components/CreateInvitation.vue"

export default {
  name: "create",
  components: {
    CreateInvitation,
    Navigation
  }
};
</script>

<style scoped>
h1{
  text-decoration: underline;
  text-align: center;
}
</style>