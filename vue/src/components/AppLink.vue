<template>
<div>
  <a :href="to"><slot/></a>
  <router-link v-bind="$props"><slot/></router-link>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>