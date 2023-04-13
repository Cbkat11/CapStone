 <template>
  <div class="home">
    <navigation/>
    
    <restaurant-list/>
  </div>
</template>

<script>

import Navigation from "../components/Navigation.vue"
import RestaurantList from "../components/RestaurantList.vue"

export default {
  name: "home",
  components: {
    Navigation,
    RestaurantList
  }
};

</script>
 