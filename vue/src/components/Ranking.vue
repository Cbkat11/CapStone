<template>
  <div class="ranking">
    <p>This event expires on {{ dateToDate() }}</p>
    <h1>{{ currentEvent.eventName }}</h1>
    <p>
      Please rank each restaurant from 1 to {{ eventRestaurants.length }}, with 1
      being your favorite
    </p>
    <div v-for="(restaurant, i) in eventRestaurants" v-bind:key="i">
      <h3>{{ restaurant.name }}</h3>
      <img :src="restaurant.imgUrl" class="avatar" />
      <span class="type">{{ restaurant.type }}</span>
      <span class="address">{{ restaurant.address }}</span>
      <span class="hours">{{ restaurant.hours }}</span>
      <!-- <span v-if="restaurant.takeout">takeout</span>
      <span v-if="restaurant.delivery">delivery</span> -->
      <form>
        <select v-model="restaurant.rank" name="rank" id="rank">
          <option value="1">1</option>
          <option value="2">2</option>
          <option v-if="eventRestaurants.length > 2" value="3">3</option>
          <option v-if="eventRestaurants.length > 3" value="4">4</option>
          <option v-if="eventRestaurants.length > 4" value="5">5</option>
        </select>
        <!-- <button class="btn btn-submit" v-on:click.prevent="saveRanks">
          Save Ranks
        </button> -->
      </form>
    </div>
    <button class="btn btn-submit" v-on:click.prevent="saveRanks">
      Save Ranks
    </button>
    <div>{{ eventRestaurants }}</div>
  </div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "ranking",
  data() {
    return {
      expireDate: '',
      currentEvent: {},
      eventRestaurants: [],
      rank: []
    };
  },
  created() {
    this.retrieveEvent();
    this.eventRestaurants = this.retrieveRestaurants();
  },
  methods: {
    retrieveEvent() {
      eventService.getEvent(this.$route.params.id).then((response) => {
        this.currentEvent = response.data;
      })
      .catch(response => {
          console.log(response)
        });
      if(this.currentEvent.expDate < Date.now()) {
      this.$router.push({ name: 'results', params: {id: 'this.$route.params.id' }})
    }
    },
    retrieveRestaurants() {
      eventService
        .getRestaurantsByEvent(this.$route.params.id)
        .then((response) => {
          this.eventRestaurants = response.data;
          return this.eventRestaurants;
        })
        .catch(response => {
          console.log(response)
        });
    },
    dateToDate() {
      var date = new Date(this.currentEvent.expDate)
      date = date.toString();
      let strings = date.split('G')
      return strings[0];
    },
    saveRanks() {
      this.eventRestaurants.forEach((restaurant) => {
        eventService.updateRanking(
          this.currentEvent.eventID,
          restaurant.id,
          Number.parseInt(restaurant.rank)
        );
      });
    },
  },
};
</script>

<style>
img {
  border-radius: 9999px;
  width: 70px;
  align-self: flex-start;
}
span {
  padding: 5px;
}
</style>
