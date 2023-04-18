<template>
  <div class="ranking">
    <h1>{{ currentEvent.name }}</h1>
    <p>
      Please rank each restaurant from 1 to {{ restaurants.length }}, with 1
      being your favorite
    </p>
    <form>
      <div v-for="(restaurant, i) in restaurants" v-bind:key="i">
        <h3>{{ restuarant.name }}</h3>
        <select v-model="restaurant.rank" name="rank" id="rank">
          <option value="1">1</option>
          <option value="2">2</option>
          <option v-if="restaurants.length > 2" value="3">3</option>
          <option v-if="restaurants.length > 3" value="4">4</option>
          <option v-if="restaurants.length > 4" value="5">5</option>
        </select>
      </div>
      <button class="btn btn-submit" v-on:click.prevent="saveRanks">
        Save Ranks
      </button>
    </form>
  </div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: 'ranking',
  data() {
    return {
      currentEvent: {},
      restaurants: [],
    };
  },
  beforeCreate() {
    this.retrieveEvent();
    this.eventRestaurants();
  },
  methods: {
    retrieveEvent() {
      eventService.getEvent(this.$route.params.id).then((response) => {
        this.currentEvent = response.data;
      });
    },
    eventRestaurants() {
      eventService
        .getRestaurantsByEvent(this.$route.params.id)
        .then((response) => {
          this.restaurants = response.data;
        });
    },
    saveRanks() {
      this.restaurants.forEach(restaurant => {
        eventService.updateRanking(this.currentEvent.id, restaurant.id, restaurant.rank)
      })
    },
  },
};
</script>

<style>
</style>