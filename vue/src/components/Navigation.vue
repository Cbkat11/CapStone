<template>
    <div class="navigation">
        <!-- <img src="./assets/Amurrica.jpg"> -->
        <h1>Restaurant Tinder</h1>
        <div>
            <input type="text" v-model="locationFilter">
            <input type="text" v-model="typeFilter">
        </div>
        <div v-if="!($store.state.token)">
            <router-link to="/login">Login</router-link>
        </div>
        <div v-else>
            <router-link to="/logout">Logout</router-link> <!-- Also need addEvent -->
        </div>
    </div>
</template>

<script>

export default {
  name: "navigation"
};