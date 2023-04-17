<template>
    <div class="navigation">
        <!-- <img src="./assets/Amurrica.jpg"> -->
        <h1>Restaurant Tinder</h1>
            <input type="text" v-on:keyup="updateLocationFilter()" v-model="location" placeholder="City or Zip">
            <input type="text" v-on:keyup="updateTypeFilter()" v-model="type" placeholder="Cuisine">
            
        <div v-if="!($store.state.token)">
            <router-link to="/login">Login</router-link>
        </div>
        <div v-else>
            <h3>Hi, {{$store.state.user.username}}!</h3>
            <router-link v-if="!(event)" to="/event">Create Event</router-link>
            <br v-if="!(event)"/>
            <router-link v-if="(event)" to="/">Back to Home</router-link>
            <br/>
            <br  v-if="(event)"/>
            <router-link to="/logout">Logout</router-link>
        </div>
    </div>
</template>

<script>

export default {
  name: "navigation",
  props: ['event'],
  data() {
      return {
          location: '',
          type: ''
      }
  },
  methods: {
      updateLocationFilter() {
          this.$store.commit("UPDATE_LOCATION", this.location);
      },
      updateTypeFilter() {
          this.$store.commit("UPDATE_TYPE", this.type);
      }
  }
};
</script>

<style scoped>
.navigation {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}
h1 {
   
    flex-basis: 40%;
    justify-content: center;
    align-content: center;

}
 


input[type="text"]::placeholder {
    border: 1px solid red;
}

</style>



