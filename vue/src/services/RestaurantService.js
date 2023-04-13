import axios from 'axios';


export default {
  getRestaurants() {
    return axios.get('/restaurants');
  }
}
