<template>
  <div class="navigation">
    <!-- <img src="./assets/Amurrica.jpg"> -->
    <h1>Restaucracy!</h1>
    <input v-if="!event"
      type="text"
      v-on:keyup="updateLocationFilter()"
      v-model="location"
      placeholder="City or Zip"
    />
    <!-- <input
      type="text"
      v-on:keyup="updateTypeFilter()"
      v-model="type"
      placeholder="Cuisine"
    /> -->
    <select v-if="!event" v-model='typeFilter' @change="updateTypeFilter($event)" name="type" id="type">
        <option v-for=" (type, i) in $store.state.types" 
        v-bind:key=i
        v-bind:value=type>{{ type }}</option>
    </select>

    <div v-if="!$store.state.token">
      <router-link to="/login">Login</router-link>
    </div>
    <div v-else>
      <h3>Hi, {{ $store.state.user.username }}!</h3>
      <button @click="createEvent()" v-if="!event">Create Event</button>
      <br v-if="!event" />
      <router-link v-if="event" to="/">Home</router-link>
      <br />
      <br v-if="event" />
      <router-link to="/logout">Logout</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "navigation",
  props: ["event"],
  data() {
    return {
      location: "",
      typeFilter: "",
    };
  },
  methods: {
    updateLocationFilter() {
      this.$store.commit("UPDATE_LOCATION", this.location);
    },
    updateTypeFilter() {
      this.$store.commit("UPDATE_TYPE", this.typeFilter);
    },
    createEvent() {
        if(this.$store.state.selected < 2) {
            alert("Please Select 2-5 Restaurants For Ranking")
        }else {
            this.$router.push('/event');
        }
    }
  },
};
</script>

<style scoped>
.navigation {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
h1 {
  display: flex;
  flex-basis: 48%;
  justify-content: flex-end;
  align-content: left;
  width: 20%;
  font-style: italic;
  color: white;
  text-shadow: 2px 2px 4px #0c0000;
}

/*input[type="text"]::placeholder {

    
} */
</style>



