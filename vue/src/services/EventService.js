import axios from 'axios';


export default {
  getEvent(eventID) {
    return axios.get(`/event/${eventID}`);
  },
  getRestaurantsByEvent(eventID) {
      return axios.get(`/restaurants/${eventID}`)
  },
  getEventsByUserID(userID) {
      return axios.get(`/${userID}/events`)
  },
  addEvent(event) {
      return axios.post('/event', event)
  },
  addEventRestaurants(eventID, restaurantIds) {
      return axios.post(`/restaurants/${eventID}`, restaurantIds)
  },
  updateRanking(eventID, restaurantID, rank) {
      return axios.put(`/rank/${eventID}/${restaurantID}/${rank}`)
  }
}
