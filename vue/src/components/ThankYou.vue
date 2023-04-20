<template>
  <div class='thanks'>
      <h1>Thank You For Voting!</h1>
      <h2>The results for {{currentEvent.eventName}} will be available on {{dateToDate()}}</h2>
  </div>
</template>

<script>
import eventService from "../services/EventService";

export default {
  name: "thankYou",
  data() {
    return {
      expireDate: '',
      currentEvent: {},
    };
  },
  created() {
    this.retrieveEvent();
  },
  methods: {
    retrieveEvent() {
      eventService.getEvent(this.$route.params.id).then((response) => {
        this.currentEvent = response.data;
      //   if(this.currentEvent.expDate < Date.now()) {
      // this.$router.push({ name: 'results', params: {id: this.$route.params.id }})
      //   }
      })
      .catch(response => {
          console.log(response)
        });
    //   if(this.currentEvent.expDate < Date.now()) {
    //   this.$router.push({ name: 'results', params: {id: 'this.$route.params.id' }})
    // }
    },
    
    dateToDate() {
      var date = new Date(this.currentEvent.expDate)
      date = date.toString();
      let strings = date.split('G')
      this.expireDate = strings[0];
      return this.expireDate;
    },
  }

}
</script>

<style>
.thanks {
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
}
</style>