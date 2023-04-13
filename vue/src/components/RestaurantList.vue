<template>
  <div class="container">
    <h2>Restaurants</h2>
    <div class="restaurants">
        <div class="restaurant" 
            v-for="restaurant in filterRestaurants()"
            v-bind:key="restaurant.id"
            v-on:click="viewRestaurantDetails(restaurant.id)">
            <div class="header">
                <h3>{{ restaurant.name }}</h3>
                <img :src="restaurant.imgUrl" class="avatar" />
            </div>
          <div class="footer">
            <span class="address">{{ restaurant.address }}</span>
            <span class="type">{{ restaurant.type }}</span>
            <span class="hours">{{
              openOrClosed(restaurant) ? "open" : "closed"
            }}</span>
            <span v-if="restaurant.takeout">takeout</span>
            <span v-if="restaurant.delivery">delivery</span>
            <span><button @click="TogglePopup('buttonTrigger')">☎ Contact</button></span>
            <div v-if="restaurant.popupTrigger" :TogglePopup="() => restaurant.popupTrigger = true">
              <div class="popup">
                <!-- img not working -->
               <!-- <img :src="restaurant.imgUrl" style="position: absolute; top: 10px; left: 10px;"/> -->
                <h2 id="contact-header"> Contact {{ restaurant.name }}</h2> <br>
              <p id="contact-info">
                {{ restaurant.phoneNumber }} <br>
                <a href="restaurant.website"> {{ restaurant.website }}</a> <br>
                <a href="restaurant.reviews"> {{ restaurant.reviews }} </a> <br>
                {{ restaurant.address }}
              </p>
              <button id="close-popup" @click="() => restaurant.popupTrigger = false">Close</button>
              </div>
            </div> <!-- this -->
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import restaurantService from "../services/RestaurantService";
import Popup from './Popup.vue'

export default {
  name: "restaurant-list",
  props: ['event'],
  created() {
    this.retrieveRestaurants();
  },
  data() {
    return {
      hours: null,
      isOpen: false,
      show: false,
      selected: 0
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
    returnName(restaurant) {
      return restaurant.name;
    }
  },
  setup () {
    const popupTriggers = ref({
      buttonTrigger: false
    });

    const TogglePopup = (trigger) => {
      popupTriggers.value[trigger] = !popupTriggers.value[trigger]
    }

    return {
      Popup,
      popupTriggers,
      TogglePopup
    }
  },
    selectRestaurant(click) {
      if(this.event) {
        if(click.target.classList.includes("selected")) {
          click.target.classList.remove("selected");
          this.selected -= 1;
        } else {
          if(this.selected == 5) {
            alert("A max of five restaurants can be selected")
          }else {
            click.target.classList.add("selected");
            this.selected += 1;
          }
        }
      }
    },

};
</script>

<style scoped>
.restaurant {
  background: #fff;
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
}
.selected {
  background-color: aqua;
}
.popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 70%;
  height: 40%;
  background-color: rgb(255, 251, 252);
  border: 3px solid rgb(48, 48, 48);
  padding: 10px;
  border-radius: 2%;
}

#contact-header {
color: rgb(233, 0, 0);
font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
font-size: 333%;
font-weight: 900;
text-align: center;
text-decoration: underline;
}

#contact-info {
color:rgb(0, 0, 0);
font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
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
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
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
</style>
