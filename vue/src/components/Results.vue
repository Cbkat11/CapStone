<template>
  <div class="results">
      
  </div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "results",
  data() {
    return {
      currentEvent: {},
      restaurants: [],
    };
  },
  methods: {
    retrieveEvent() {
      this.currentEvent = eventService.getEvent(this.$route.params.id);
    },
    eventRestaurants() {
      this.restaurants = eventService.getRestaurantsByEvent(
        this.$route.params.id
      );
    },
  },
};
</script>

<style>
</style>