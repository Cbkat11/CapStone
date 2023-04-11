import axios from 'axios';


export default {
  getRestaurants() {
    alert("got to getRestaurants")
    return axios.get('/restaurants');
  }
}
