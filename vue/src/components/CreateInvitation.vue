<template>
  <div class="createEvent">
    <!-- <header>
          <input type="textbox" id="eventName" placeholder="Name of Event: "/>
      </header> -->
    <body>
      <h1>Selected Restaurants</h1>
      <p
        v-for="(restaurant, i) in $store.state.selectedRestaurants"
        v-bind:key="i"
      >
        {{ i+1 }}: {{ restaurant.name }}
      </p>
      <form id="eventInfo" v-on:submit.prevent="submitForm">
        <label for="eventName">Name of Event:</label>
        <input type="textbox" id="eventName" v-model="newEvent.eventName" required />
        <label for="expireDate">Event Epiration Date:</label>
        <input type="date" id="expireDate" name="expireDate" placeholder="mm/dd/yy" v-model="expireDate" required />
        <!-- <label for="expireTime">Event Epiration Time:</label>
        <input type="time" id="expireTime" name="expireTime" placeholder="hh:mm" v-model="expireTime" required /> -->
        <input v-if="!(isCreated)" type="submit" value="Create Event Link"/>
        <router-link v-if="isCreated" :to="`/event/${eventID}`" >{{ eventLink }}</router-link>
      </form>
      <p>{{ newEvent }}</p>
    </body>
  </div>
</template>

<script>
import eventService from "../services/EventService"

export default {
  name: "create-invitation",
  data() {
    return{
      isCreated: false,
      eventID: 0,
      eventLink: '',
      expireDate: '',
      // expireTime: '',
      newEvent: {
        createDate: '',
        expDate: '',
        userId: '',
        eventName: ''
      },
      errorMsg: ""
    };
  },
  methods: {
    submitForm() {
      this.newEvent.createDate = Date.now();
      this.newEvent.expDate = Date.parse(this.expireDate);
      this.newEvent.userId = this.$store.state.user.id;
      let restaurantIDs = [];
      this.$store.state.selectedRestaurants.forEach(restaurant => {
        restaurantIDs.push(restaurant.id);
      })
      eventService.addEvent(this.newEvent)
      .then((response) => {
          this.eventID = response.data;
          this.eventLink = 'http://localhost:8080/event/' + this.eventID;
          this.isCreated = true;
          eventService.addEventRestaurants(this.eventID, restaurantIDs)
          // .then(response => {
          //   if (response.status == 201) {
              // this.isCreated = true;
          //   }
          // })
      })
    }
  }
};
</script>

<style>
</style>