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
          <button id="show-modal" @click="showModal = true">Contact</button>
          <modal v-if="showModal" @close="showModal = false">
            <h3>hello there</h3>
          </modal>
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
  components: {
    Modal,
  },
  created() {
    this.retrieveRestaurants();
  },
  data() {
    return {
      hours: null,
      isOpen: false,
      show: false,
      showModal: false,

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
    viewRestaurantDetails(restaurantID) {
      //     this.$router.push(`/restaurant/${restaurantID}`);
      return restaurantID;
    },
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
    checkSelected() {
      if (this.$store.state.selectedRestaurants != {}) {
        this.$store.state.selectedRestaurants.forEach((restaurant) => {
          let selected = document.getElementById(restaurant.id);
          selected.classList.add("selected");
          let checkBox = selected.childNodes[0].childNodes[0].childNodes[0];
          checkBox.checked = true;
          // let checkBox = selected.getElementById("addToCart");
          // checkBox.setAttribute("checked", 'checked');
        });
      }
    },
  },
};
</script>
<style scoped>
.overflow-hidden {
  overflow: hidden;
}
.modal {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    opacity: 0;
    visibility: hidden;
    transform: scale(1.1);
    transition: visibility 0s linear 0.25s, opacity 0.25s 0s, transform 0.25s;
}

.modal-content {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    padding: 1rem 1.5rem;
    width: 24rem;
    border-radius: 0.5rem;
}

.close-button {
    float: right;
    width: 1.5rem;
    line-height: 1.5rem;
    text-align: center;
    cursor: pointer;
    border-radius: 0.25rem;
    background-color: lightgray;
}

.close-button:hover {
    background-color: darkgray;
}

.show-modal {
    opacity: 1;
    visibility: visible;
    transform: scale(1.0);
    transition: visibility 0s linear 0s, opacity 0.25s 0s, transform 0.25s;
}
.restaurant {
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 5px solid black;
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
/* .popup {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  
}
.header.addToCart {
  display: inline-flexbox;
  align-content: flex-end;
  align-items: baseline;
}
.type {
  /*background: blue;
  display: flex;
  flex-basis: 100%;
*/
</style>