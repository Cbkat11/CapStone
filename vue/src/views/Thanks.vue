<template>
<div>
    <navigation v-bind:event=true />
    <thankYou />

</div>
</template>

<script>
import Navigation from "../components/Navigation.vue"
import ThankYou from "../components/ThankYou.vue"

export default {
    name: 'thank',
    components: {
        Navigation,
        ThankYou
    }

}
</script>

<style>

</style>