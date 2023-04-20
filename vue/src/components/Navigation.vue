<template>
    <div class="navigation">
        <!-- <img src="./assets/Amurrica.jpg"> -->
        <h1>Restaucracy!</h1>
            <input type="text" v-on="updateLocationFilter()" v-model="location" placeholder="City or Zip">
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
        <div v-if="!($store.state.token)">
            <router-link to="/login">Login</router-link>
        </div>
        <div v-else>
            <h3>Hi, {{$store.state.user.username}}!</h3>
            <router-link id="create-event" v-if="!(event)" to="/event">Create Event</router-link>
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
  props: ["event"],
  data() {
    return {
      location: "",
      type: "",
    };
  },
  methods: {
    updateLocationFilter() {
      this.$store.commit("UPDATE_LOCATION", this.location);
    },
    updateTypeFilter() {
      this.$store.commit("UPDATE_TYPE", this.type);
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
   color:  white;
  text-shadow: 2px 2px 4px #0c0000;
font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
    border-style: double;
    border-width: 5px;
    border-radius: 15px;
    padding: .2em;
}
#create-event {
    
}
</style>



