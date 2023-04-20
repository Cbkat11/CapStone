<template>
  <div class="container">
    <h2>Restaurants</h2>
    <div class="restaurants">
      <div
        class="restaurant"
        v-for="restaurant in filterRestaurants()"
        v-bind:key="restaurant.id"
        v-bind:id="restaurant.id"
      >
        <div class="header">
          <h3 class="name">{{ restaurant.name }}</h3>
           <span class="open?" v-if="openOrClosed(restaurant)">
            <img class="open-or-closed" src="https://www.svgrepo.com/show/179208/open-sign.svg" alt="Open" />
          </span>
          <span v-else>
            <img class="open-or-closed" src="https://cdn-icons-png.flaticon.com/512/174/174443.png" alt="Closed" />
          </span>
          
          <div class="addToCart">
          <input
            type="checkbox"
            id="addToCart" name="addToCart"
            v-if="$store.state.token"
            @click="selectRestaurant($event, restaurant)"
          />
          <label for="addToCart">Add to Event</label>
          </div>
          <span class="type">{{ restaurant.type }}</span>
          <span class="priceRange">{{ restaurant.priceRange }}</span>
          <img :src="restaurant.imgUrl" class="avatar" />
        </div>
        <div class="footer">
          <span class="address">{{ restaurant.address }}</span>
          <span class="hours">{{ restaurant.hours }}</span>
          <button>{{ restaurant.website }}</button>
          <span v-if="restaurant.takeout" title="Takeout">🥡</span>
          <span v-if="restaurant.delivery" title="Delivery">🚚</span>
          <span class="phoneNumber">{{ restaurant.phoneNumber }}</span>
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
    this.checkSelected();
  },
  mounted() {
    this.checkSelected();
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
        } else if (restaurant.address.toLowerCase().includes(locationFilter.toLowerCase())) {
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
    selectRestaurant(event, restaurant) {
      if (event.target.parentElement.parentElement.parentElement.classList.contains("selected")) {
        event.target.parentElement.parentElement.parentElement.classList.remove("selected");
        this.$store.commit("REMOVE_SELECTED", event.target.parentElement.parentElement.parentElement.id);
      } else {
        if (this.$store.state.selected == 5) {
          alert("A max of five restaurants can be selected");
          event.target.checked = false;
        } else {
          event.target.parentElement.parentElement.parentElement.classList.add("selected");
          this.$store.commit("ADD_SELECTED", restaurant);
        }
      }
    },

    openPop(restaurant) {
      this.show = true;
      alert("Phone Number: " + restaurant.phoneNumber);
    },
  checkSelected() {
      if(this.$store.state.selectedRestaurants != {}) {
        this.$store.state.selectedRestaurants.forEach(restaurant => {
          let selected = document.getElementById(restaurant.id);
          selected.classList.add('selected');
          let checkBox = selected.childNodes[0].childNodes[2].childNodes[0];
          checkBox.checked = true;
          // let checkBox = selected.getElementById("addToCart");
          // checkBox.setAttribute("checked", 'checked');
        });
      }

    }
    
    // closePop() {
    //   this.show = false;
    // },
  },

};
</script>

<style scoped>
.container {
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
.restaurant {
  background: linear-gradient(white, rgb(247, 28, 28));
  border-radius: 0.25rem;
  padding: 10px;
  border: 3px solid black;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
  
}
.restaurant .header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  
}

.menu-container img {
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
  display: grid;
  grid-template-columns: repeat(2, minmax(300px, 1fr));
  gap: 10px;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
.open-or-closed {
  width: 4em;
  height: auto;
  display: flex;
  justify-content: left;
  align-content: left;
  border-radius: 0;
}
h2 {
  font-style: italic;
  color: white;
  text-shadow: 2px 2px 4px #0c0000;
}
h3 {
  max-width: 200px;
}
.header .addToCart {
  display: flex;
  align-items: center;
}
.name {
  font-size: 2em;
  text-decoration-line: underline;
}

.menu-container {
  display: flex;
  justify-content: space-between;
  gap: 10px;
}
.priceRange {
  font-size: 1.2em;
  font-weight: bold;
}
#contact-header {
  color: rgb(245, 47, 47);
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  font-size: 333%;
  font-weight: 900;
  text-align: center;
  text-decoration: underline;
}

#contact-info {
  color: rgb(0, 0, 0);
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  font-size: 150%;
  font-weight: 100;
  text-align: center;
}

#close-popup {
  border-radius: 0.25rem;
  position: absolute;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
.popup {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
}

.restaurant.name {
  display: flex;
}

#menu {
  display: flex;
  flex-direction: column;
  flex: 1;

  justify-content: center;
  align-items: center;
  border: 2px solid rgb(247, 67, 67);
  /* background-color: #ccc; */
  height: 15rem;
  position: relative;
}
</style>
