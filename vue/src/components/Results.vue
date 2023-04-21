<template>
  <div class="results">
    <h1>{{ currentEvent.eventName }}</h1>
    <div v-for="(restaurant, i) in eventRestaurants" v-bind:key="i">
      <h3>{{ restaurant.name }}</h3>
      <img :src="restaurant.imgUrl" class="avatar" />
      <span class="type">{{ restaurant.type }}</span>
      <span class="address">{{ restaurant.address }}</span>
      <span class="hours">{{ restaurant.hours }}</span>
      <span v-if="restaurant.takeout">takeout</span>
      <span v-if="restaurant.delivery">delivery</span>
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
.results {
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  max-width: 800px;
  margin: 0 auto;
}

.results h1 {
  font-size: 2.5rem;
  margin-bottom: 1rem;
}

.results h3 {
  font-size: 1.5rem;
  margin-top: 2rem;
}

.results .avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin: 0.5rem 0 1rem;
}

.results .type {
  display: block;
  margin-bottom: 0.5rem;
}

.results .address,
.results .hours {
  display: block;
  margin-bottom: 0.5rem;
  font-style: italic;
}

.results span {
  margin-right: 1rem;
  padding: 0.25rem 0.5rem;
  border-radius: 0.25rem;
  font-weight: bold;
  text-transform: uppercase;
}

.results span.takeout {
  background-color: #ffc107;
  color: #fff;
}

.results span.delivery {
  background-color: #17a2b8;
  color: #fff;
}

</style>
