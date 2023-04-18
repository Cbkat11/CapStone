<template>
  <div class="container">
    <h2>Restaurants:</h2>
    <div class="restaurants">
      <div
        class="restaurant"
        v-for="restaurant in filterRestaurants()"
        v-bind:key="restaurant.id"
      >
        <div class="header">
          <div class="addToCart">
            <input
              type="checkbox"
              id="addToCart"
              name="addToCart"
              v-if="$store.state.token"
              @click="selectRestaurant($event, restaurant.id)"
            />
            <div class="cart">
              <label for="addToCart">Add to Cart</label>
            </div>
          </div>
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
  // props: ["event"],
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
    selectRestaurant(event, restaurantID) {
      if (this.$store.state.selectedRestaurants.includes(restaurantID)) {
        event.target.parentElement.parentElement.parentElement.classList.remove(
          "selected"
        );
        this.selected -= 1;
        this.$store.commit("REMOVE_SELECTED", restaurantID);
      } else {
        if (this.selected == 5) {
          alert("A max of five restaurants can be selected");
        } else {
          event.target.parentElement.parentElement.parentElement.classList.add(
            "selected"
          );
          this.selected += 1;
          this.$store.commit("ADD_SELECTED", restaurantID);
        }
      }
      alert(this.$store.state.selectedRestaurants);
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
  border: 5px solid black;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
  
}
.restaurant .header {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  text-align: center;
  align-items: baseline;
  /*text-decoration: underline;*/
  column-gap: 75px;
  row-gap: 50px;
  align-content: space-around;
  font-family: monospace;
    font-size: 1.15rem;
    color: black;


  
}
.restaurant .header img {
  border-radius: 9999px;
  width: 300px;
  align-self: flex-start;
  height: 300px;
  border: 2px solid black;
  filter: drop-shadow(0 0 2.75rem crimson);
}
.restaurant .footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 20px 0 10px 0;
  font-size: 1.15rem;
    font-family: monospace;
      font-style: italic;


 

}
.hours {
  padding: 8px;
  border-radius: 20px;
  font-size: 1.15rem;
    font-family: monospace;
      font-style: italic;



}
.selected {
  background-color: aqua;
}
.type {
  max-width: auto;
  margin: 20;
  text-align: left;
  align-content: space-around;
}
.restaurants {
  display: flex;
  gap: 40px;
  width: 100%;
  flex-wrap: wrap;
  
}
.restaurants > * {
  flex-basis: 47%;
  background: linear-gradient(white, red);
}
h2 {
   font-style: italic;
     color:  white;
  text-shadow: 2px 2px 4px #0c0000;
  
}
h3 {
  max-width: 200px;
  
  /*  background: blueviolet; */
}
.addToCart {
  display: inline-flexbox;
  align-content: flex-end;
  align-items: baseline;
  ackground:yellow; 
  display: flex;
  flex-basis: 100%;
  justify-content: flex-end;
  align-content: left;
  width: 100%;
  
}
.header.addToCart {
  display: inline-flexbox;
  align-content: flex-end;
  align-items: baseline;
}
.type {
  /*background: blue;*/
  display: flex;
  flex-basis: 100%;
}
.restaurant.name {
}
</style>