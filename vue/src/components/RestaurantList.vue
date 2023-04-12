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
                <span class="type">{{ restaurant.type}}</span>
                <span class="hours">{{ restaurant.hours}}</span>
            </div>

        </div>

    </div>
  </div>
</template>

<script>
import restaurantService from '../services/RestaurantService'

export default {
    name: 'restaurant-list',
    created() {
        this.retrieveRestaurants();
    },
    methods: {
        retrieveRestaurants() {
            restaurantService.getRestaurants().then(response => {
                this.$store.commit("SET_RESTAURANTS", response.data)
            })
        },
        filterRestaurants() {
          const locationFilter = this.$store.state.locationFilter;
          const typeFilter = this.$store.state.typeFilter;
          let restaurants = this.$store.state.restaurants;
          restaurants = restaurants.filter(restaurant => {
            if (locationFilter == '') {
              return true;
            } else if (restaurant.address.includes(locationFilter)) {
            return restaurant;
            }
          })
          restaurants = restaurants.filter(restaurant => {
            if (typeFilter == '') {
              return true;
            } else if (restaurant.type.includes(typeFilter)) {
              return restaurant;
            }
          })
          return restaurants;
        }
        // viewRestaurantDetails(restaurantID) {
        //     this.$router.push(`/restaurant/${restaurantID}`);
        // }
    }

}
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

</style>
