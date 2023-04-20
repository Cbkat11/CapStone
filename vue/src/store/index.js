import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    restaurants: [],
    types: [''],
    locationFilter: '',
    typeFilter: '',
    selectedRestaurants: [],
    selected: 0,
    userEvents: [],
    currentEvent: {},
    eventRestaurants: []
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_RESTAURANTS(state, data) {
      state.restaurants = data;
      state.restaurants.forEach(restaurant => {
        if (!(state.types.includes(restaurant.type))) {
          state.types.push(restaurant.type);
        }
      })

    },
    UPDATE_LOCATION(state, filter) {
      state.locationFilter = filter;
    },
    UPDATE_TYPE(state, filter) {
      state.typeFilter = ''
      state.typeFilter = filter;
    },
    ADD_SELECTED(state, selected) {
      state.selectedRestaurants.push(selected);
      state.selected += 1;
    },
    REMOVE_SELECTED(state, selected) {
      state.selectedRestaurants = state.selectedRestaurants.filter(rest => {
        if (selected == rest.id) {
          return false;
        }
        return true;
      })
      // let i = state.selectedRestaurants.indexOf(selected)
      // delete state.selectedRestaurants[i];
      state.selected -= 1;
    }
  },

})
