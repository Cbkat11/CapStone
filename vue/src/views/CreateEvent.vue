<template>
  <div class="createEvent">
    <create-invitation/>
  </div>
</template>

<script>


import CreateInvitation from "../components/CreateInvitation.vue"

export default {
  name: "create",
  components: {
    CreateInvitation
  }
};
</script>

<style scoped>
h1{
  text-decoration: underline;
  text-align: center;
}
</style>