<template>
  <div class="container">
    <h2>Restaurants</h2>
    <div class="restaurants">
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
        </div>
        <div class="footer">
          <span class="address">{{ restaurant.address }}</span>
          <span class="hours">{{ restaurant.hours }}</span>
          <span class="open?">{{
            openOrClosed(restaurant) ? "open" : "closed"
          }}</span>
          <span v-if="restaurant.takeout">takeout</span>
          <span v-if="restaurant.delivery">delivery</span>
          <span v-if="restaurant.phoneNumber">
            <button id="myButton" v-on:click="openPop(restaurant)">
              Call to Order
            </button>
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
  props: ["event"],
  created() {
    this.retrieveRestaurants();
  },
  data() {
    return {
      hours: null,
      isOpen: false,
      show: false,
      selected: 0,
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
    selectRestaurant(click) {
      if (this.event) {
        if (click.target.classList.includes("selected")) {
          click.target.classList.remove("selected");
          this.selected -= 1;
        } else {
          if (this.selected == 5) {
            alert("A max of five restaurants can be selected");
          } else {
            click.target.classList.add("selected");
            this.selected += 1;
          }
        }
      }
    },
    openPop(restaurant) {
      this.show = true;
      alert("Phone Number: " + restaurant.phoneNumber);
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
  background: blue;
}
.restaurant .header {
  display: flex;
  justify-content: space-between;
  text-align: center;
  text-decoration: underline;
  background: green;
}
.restaurant .header img {
  border-radius: 9999px;
  width: 300px;
  align-self: flex-start;

}
.restaurant .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 0.75rem;
  background: purple;
}
.hours {
  padding: 8px;
  border-radius: 20px;
  font-size: 0.7rem;
}
.selected {
  background-color: aqua;
}
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
.type {
  max-width: 50px;
  margin: 0 auto;
  text-align: left;
}
.restaurants{
  display: flex;
  gap:40px;
  width: 100%;
  flex-wrap: wrap;
}
.restaurants > * {
  flex-basis: 47%;
}

h3 {
  max-width: 200px;
}
</style>
