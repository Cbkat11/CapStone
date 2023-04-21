<template>
  <div class="ranking">

    <p>This event expires on {{ dateToDate() }} at {{ expireTime }}</p>
    <h1>{{ currentEvent.eventName }}</h1>
    <h1>
      Please rank each restaurant from 1 to {{ eventRestaurants.length }}, with 1
      being your favorite
    </h1>
    <div class="restaurant" v-for="(restaurant, i) in eventRestaurants" v-bind:key="i">
      <h3>{{ restaurant.name }}</h3>
      <img :src="restaurant.imgUrl" class="avatar" />
      <span class="type">Type: {{ restaurant.type }} </span>
      <span class="address">Address: {{ restaurant.address }} </span>
      <span class="hours">Hours: {{ restaurant.hours }} </span>
      <span v-if="restaurant.takeout">takeout</span>
      <span v-if="restaurant.delivery">delivery</span>
      <form>
        <select @change='filterRank()' v-model="restaurant.rank" name="rank" id="rank">
          <option v-for="rank in filterRank()" 
        v-bind:key=rank
        v-bind:value=rank>{{ rank }}</option>
        </select>
        <!-- <button class="btn btn-submit" v-on:click.prevent="saveRanks">
          Save Ranks
        </button> -->
      </form>
    </div><br />
    <button class="btn btn-submit" v-on:click.prevent="saveRanks">
      Save Ranks
    </button>
  </div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "ranking",
  data() {
    return {
      expireTime: '',
      currentEvent: {},
      eventRestaurants: [],
      ranks: []
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
        if(this.currentEvent.expDate < Date.now()) {
      this.$router.push({ name: 'results', params: {id: this.$route.params.id }})
        }
      })
      .catch(response => {
          console.log(response)
        });
    //   if(this.currentEvent.expDate < Date.now()) {
    //   this.$router.push({ name: 'results', params: {id: 'this.$route.params.id' }})
    // }
    },
    retrieveRestaurants() {
      eventService
        .getRestaurantsByEvent(this.$route.params.id)
        .then((response) => {
          this.eventRestaurants = response.data;
          for(let i = 0; i < this.eventRestaurants.length; i++) {
            this.ranks.push(i+1);
          }
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
      date = strings[0];
      // let index = date.indexOf(':');
      // date = date.split(index - 2)
      // this.expireTime = date.substring(index -2);
      return date
    },
    saveRanks() {
      this.eventRestaurants.forEach((restaurant) => {
        eventService.updateRanking(
          this.currentEvent.eventID,
          restaurant.id,
          Number.parseInt(restaurant.rank)
        );
      });
      this.$router.push(`/thanks/${this.$route.params.id}`)
    },
    filterRank() {
      let holder = [];
      holder = this.ranks.filter((rank) => {
        this.eventRestaurants.forEach(element => {
          if (element.rank == rank) {
            return false;
          }
        })
        return true;
      })
      return holder
    }
  },
};
</script>


<style>
  body {
    font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
    font-size: 16px;
    background-color: #f5f5f5;
  }
  
  .ranking {
    max-width: 800px;
    margin: 0 auto;
    text-align: center;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  }
  
  h1 {
    font-size: 24px;
    margin-bottom: 20px;
  }
  
  h3 {
    font-size: 20px;
    margin-bottom: 10px;
    text-decoration-line: underline;
  }
  
  img.avatar {
    border-radius: 50%;
    width: 100px;
    height: 100px;
    object-fit: cover;
    margin-bottom: 10px;
  }
  
  form {
    margin-top: 10px;
  }
  
  select {
    padding: 5px;
    font-size: 16px;
    border-radius: 5px;
    border: 1px solid #ccc;
    margin-right: 10px;
  }
  
  .btn-submit {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .btn-submit:hover {
    background-color: #0062cc;
  }
</style>

