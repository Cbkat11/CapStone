<template>
  <div class="navigation">
    <!-- <img src="./assets/Amurrica.jpg"> -->
    <h1>Restaucracy!</h1>
    <input
      v-if="!event"
      type="text"
      v-on:keyup="updateLocationFilter()"
      v-model="location"
      placeholder="City or Zip"
    />
    <label for="cuisine">Cuisine</label>
    <select v-on:keyup="updateTypeFilter()" name="cuisine" id="cuisine">
      <option value="pizza">Pizza</option>
      <option value="steakhouse">Steakhouse</option>
      <option value="diner">Diner</option>
      <option value="soup">Soup</option>
      <option value="italian">Italian</option>
      <option value="thai">Thai</option>
      <option value="jamaican">Jamaican</option>
      <option value="mexican">Mexican</option>
      <option value="bar">Bar</option>
      <option value="french">French</option>
      <option value="american">American</option>
      <option value="seafood">Seafood</option>
      <option value="barbeque">Barbeque</option>
      <option value="tapas">Tapas</option>
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

    <select
      v-if="!event"
      v-model="typeFilter"
      @change="updateTypeFilter($event)"
      name="type"
      id="type"
    >
      <option
        v-for="(type, i) in $store.state.types"
        v-bind:key="i"
        v-bind:value="type"
      >
        {{ type }}
      </option>
    </select>
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
      if (this.$store.state.selected < 2) {
        alert("Please Select 2-5 Restaurants For Ranking");
      } else {
        this.$router.push("/event");
      }
    },
  },
};
</script>

<style scoped>
.navigation {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
h1 {
  font-size: 4em;
  display: flex;
  flex-basis: 48%;
  justify-content: center;
  align-content: left;
  width: 20%;
  font-style: italic;
  color: white;
  text-shadow: 2px 2px 4px #0c0000;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  border-style: double;
  border-width: 5px;
  border-radius: 15px;
  padding: 0.2em;
}
#create-event {
}
</style>



