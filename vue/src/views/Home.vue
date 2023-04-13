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
<<<<<<< HEAD
 
=======
<style scoped>
h1{
  text-decoration: underline;
  text-align: center;
}
</style>
>>>>>>> 9d0704b7a4f7d12455030e0269433794c90e664b
