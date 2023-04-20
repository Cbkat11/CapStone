<template>
  <div class="createEvent">
    <!-- <header>
          <input type="textbox" id="eventName" placeholder="Name of Event: "/>
      </header> -->
    <body>
      <h1>Selected Restaurants</h1>
      <p
      class="selectedRestaurants"
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
        <label for="expireTime">Event Epiration Time:</label>
        <input type="time" id="expireTime" name="expireTime" placeholder="hh:mm" v-model="expireTime" required />
        <input v-if="!(isCreated)" type="submit" value="Create Event Link"/>
        <router-link v-if="isCreated" :to="`/event/${eventID}`" >{{ eventLink }}</router-link>
      </form>
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
      expireTime: '',
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
      this.newEvent.expDate = Date.parse(this.expireDate + 'T' + this.expireTime);
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
.createEvent {
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 20px;
  background-color: #f8f8f8;
  border-radius: 2%;
}

.createEvent h1 {
  font-size: 36px;
  margin-bottom: 20px;
  line-height: 1.2;
}

/* .selectedRestaurants {
  border: 1px solid #ccc;
  padding: 10px;
  margin: 10px;
  border-radius: 5px;
  width: 88%;
  text-align: left;
} */
.selectedRestaurants {
  background-color: #f9f9f9;
  border: 1px solid #ddd;
  border-radius: 5px;
  margin: 10px;
  padding: 20px;
  text-align: left;
  width: 83%;
}

.selectedRestaurants:before {
  content: "\2022";
  color: #4CAF50;
  display: inline-block;
  font-size: 20px;
  margin-right: 10px;
  vertical-align: middle;
}

.selectedRestaurants span {
  display: inline-block;
  font-size: 18px;
  vertical-align: middle;
}

.createEvent form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 71%;
  max-width: 500px;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 50px;
  margin-top: 20px;
}

.createEvent form label {
  font-size: 18px;
  margin-bottom: 10px;
  display: block;
  text-align: left;
}

.createEvent form input[type="textbox"],
.createEvent form input[type="date"],
.createEvent form input[type="time"] {
  font-size: 18px;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 100%;
  box-sizing: border-box;
}

.createEvent form input[type="submit"],
.createEvent form .router-link {
  font-size: 18px;
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  background-color: #4CAF50;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.createEvent form input[type="submit"]:hover,
.createEvent form .router-link:hover {
  background-color: #3e8e41;
}

.createEvent form input:required:invalid {
  border-color: red;
}
</style> 