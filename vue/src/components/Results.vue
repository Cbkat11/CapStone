<template>
  <div class="results">
    <h1>{{ currentEvent.eventName }}</h1>
    <div v-for="(restaurant, i) in eventRestaurants" v-bind:key="i">
      <h3>{{ restaurant.name }}</h3>
      <img :src="restaurant.imgUrl" class="avatar" />
      <span class="type">{{ restaurant.type }}</span>
      <span class="address">{{ restaurant.address }}</span>
      <span class="hours">{{ restaurant.hours }}</span>
    </div>
  </div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "results",
  data() {
    return {
      currentEvent: {},
      eventRestaurants: [],
    };
  },
  created() {
    this.retrieveEvent();
    this.eventRestaurants = this.retrieveRestaurants();
  },
  methods: {
    retrieveEvent() {
      eventService
        .getEvent(this.$route.params.id)
        .then((response) => {
          this.currentEvent = response.data;
        })
        .catch((response) => {
          console.log(response);
        });
    },
    retrieveRestaurants() {
      eventService
        .getRestaurantsByEvent(this.$route.params.id)
        .then((response) => {
          this.eventRestaurants = response.data;
          this.eventRestaurants =this.eventRestaurants.sort((a, b) => {
            return a.totalRank - b.totalRank;
          })
        })
        .catch((response) => {
          console.log(response);
        });
    },
  },
};
</script>

<style>
</style>
