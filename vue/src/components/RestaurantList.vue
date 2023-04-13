<template>
  <div class="container">
    <h2>Restaurants</h2>
    <div class="restaurants">
<<<<<<< HEAD
        <div class="restaurant" 
        
            v-for="restaurant in $store.state.restaurants"
            v-bind:key="restaurant.id"
            v-on:click="viewRestaurantDetails(restaurant.id)">
            <div class="header">
              
                <h3>{{ restaurant.name }}</h3>
                <img :src="restaurant.imgUrl" class="avatar" />
            </div>
            <div class="footer">
                <span class="address">{{ restaurant.address }}</span>
                <span class="type">{{ restaurant.type}}</span>
                <span class="hours">{{ restaurant.hours}}</span>
            </div>

=======
      <div
        class="restaurant"
        v-for="restaurant in filterRestaurants()"
        v-bind:key="restaurant.id"
        v-on:click="viewRestaurantDetails(restaurant.id)"
      >
        <div class="header">
          <h3>{{ restaurant.name }}</h3>
          <span class="type">{{ restaurant.type }}</span>
          <img :src="restaurant.imgUrl" class="avatar" />
>>>>>>> 9d0704b7a4f7d12455030e0269433794c90e664b
        </div>
        <div class="footer">
          <span class="address">{{ restaurant.address }}</span>
          <span class="hours">{{ restaurant.hours }}</span>
          <span class="open?">{{
            openOrClosed(restaurant) ? "open" : "closed"
          }}</span>
          <span v-if="restaurant.takeout">takeout</span>
          <span v-if="restaurant.delivery">delivery</span>
          <span v-if="(restaurant.phoneNumber)">
            <button id="myButton" v-on:click="openPop(restaurant)">Call to Order</button>
            <!-- <div id="myPopup" class="popup" v-if="show">
              <div class="popup-content">
                <h1>GeekforGeeks !</h1>
                <p>This is a popup box!</p>
                <button id="closePopup" :click="closePop">Close</button>
              </div> -->
            <!-- </div> -->
            </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import restaurantService from "../services/RestaurantService";

export default {
  name: "restaurant-list",
  created() {
    this.retrieveRestaurants();
  },
  data() {
    return {
      hours: null,
      isOpen: false,
      show: false,
    };
  },
  methods: {
    retrieveRestaurants() {
      restaurantService.getRestaurants().then((response) => {
        this.$store.commit("SET_RESTAURANTS", response.data);
      });
    },
    filterRestaurants() {
      const locationFilter = this.$store.state.locationFilter;
      const typeFilter = this.$store.state.typeFilter;
      let restaurants = this.$store.state.restaurants;
      restaurants = restaurants.filter((restaurant) => {
        if (locationFilter == "") {
          return true;
        } else if (restaurant.address.includes(locationFilter)) {
          return restaurant;
        }
      });
      restaurants = restaurants.filter((restaurant) => {
        if (typeFilter == "") {
          return true;
        } else if (restaurant.type.includes(typeFilter)) {
          return restaurant;
        }
      });
      return restaurants;
    },
    // viewRestaurantDetails(restaurantID) {
    //     this.$router.push(`/restaurant/${restaurantID}`);
    // }
    openOrClosed(restaurant) {
      const now = new Date();
      const currentHours = now.getHours();
      const currentMinutes = now.getMinutes();
      const currentTime = `${currentHours}:${currentMinutes}`;

      const [startTime, endTime] = restaurant.hours.split("-").map((time) => {
        const [hours, minutes] = time.split(":");
        const [timeOfDay] = minutes.slice(-2);
        const hour = parseInt(hours) % 12;
        const minute = minutes.slice(0, 2).padStart(2, "0"); // minutes always two digits
        const hour24 = timeOfDay === "P" ? hour + 12 : hour;

        return `${hour24}:${minute}`;
      });

      this.isOpen = currentTime >= startTime && currentTime <= endTime;
      return this.isOpen;
    },
    openPop(restaurant) {
      this.show = true;
      alert("Phone Number: " + restaurant.phoneNumber)
    },
    // closePop() {
    //   this.show = false;
    // },
  },
};
</script>

<style scoped>
.restaurant {
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}
.restaurant .header {
  display: flex;
  justify-content: space-between;
}
.restaurant .header img {
  border-radius: 9999px;
  width: 32px;
  align-self: flex-start;
}
.restaurant .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 0.75rem;
}
.hours {
  padding: 8px;
  border-radius: 20px;
  font-size: 0.7rem;
  background: rgb(2,0,36);
  background: linear-gradient(90deg, rgba(2,0,36,1) 0%, rgba(43,121,9,0.6755077030812324) 8%, rgba(0,212,255,1) 100%);
}
h2{
text-decoration: underline;
text-align: center;
}
<<<<<<< HEAD
=======
/* .popup {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.4);
  display: none;
}
.popup-content {
  background-color: white;
  margin: 10% auto;
  padding: 20px;
  border: 1px solid #888888;
  width: 30%;
  font-weight: bolder;
}
.popup-content button {
  display: block;
  margin: 0 auto;
}
.show {
  display: block;
} */
>>>>>>> 9d0704b7a4f7d12455030e0269433794c90e664b
</style>
