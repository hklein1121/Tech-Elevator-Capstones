<template>
  <div id="app">
    <div id="nav">
      <ul>
        <router-link to="/"><li class="navLinks">Home</li></router-link>
        <router-link :to="{name:'organization-list'}"><li class="navLinks">Organizations</li></router-link>
        <!-- <router-link to="/organization/list"><li class="navLinks">Join a League</li></router-link> -->
        <router-link :to="{ name: 'user-list' }"><li class="navLinks">Users</li></router-link>
        <router-link :to="{ name: 'admin' }"><li class="navLinks">Admin</li></router-link>
        <!-- <router-link :to="{ name: 'adminroute' }"><li class="navLinks">Admin Router</li></router-link> -->
        <router-link to="/login"><li class="navLinks">Login</li></router-link>
        <router-link to="/login"><a href="#" @click.prevent="logout"><li class="navLinks">Logout</li></a> </router-link>
      </ul>
    </div>
    <router-view/>
  </div>
</template>

<script>
import auth from './auth';

export default {
  name: 'app',
  methods: {
    logout() {
      auth.logout();
      this.$router.go('/');
    },
  },
};
</script>

<style>

@import url('https://fonts.googleapis.com/css?family=Permanent+Marker&display=swap');

/* ENTIRE PAGE */

* {
  font-family: 'Permanent Marker', cursive;
  letter-spacing: 5px;
  text-transform: uppercase;
}

body {
  background-image: url("https://www.logolynx.com/images/logolynx/cf/cfbd2a93a40edee75a651e60c237579e.jpeg");
  background-repeat: repeat;
  background-attachment: fixed;
  background-position: center;
  background-position-y: 0px;
  color: white;
}

h1 {
  text-align: center;
  font-size: 50px;
}

/* NAVIGATION BAR */

ul {
  list-style-type: none;
  margin: 0;
  margin-bottom: 50px;
  padding: 0;
  overflow: hidden;
  background-color: black;
  opacity: 0.8;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

li {
  float: left;
}

.navLinks {
  display: block;
  color: white;
  text-align: center;
  font-size: 25px;
  padding: 14px 16px;
  text-decoration: none;
}

li:hover {
  background-color: rgb(3, 102, 0);
}

/* DIV CONTAINERS */

.container {
  background-color: rgba(7, 61, 133, 0.685);
  padding: 10px;
}

/* FORMS */

label {
  font-size: 25px;
}

.form-input {
  text-align: center;
  padding: 10px;
  box-sizing: border-box;
  border-radius: 5px;
  width: 100%;
}

/* ALL LINKS */

div.container a {
  text-decoration: none;
  color: white;
}

/* BUTTONS */

input[type="submit"] {
    width: 150px;
  display: block;
  margin-bottom: 10px;
  background-color: yellow;
}

.button-edit {
  background-color: green;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 10px;
  border-radius: 28px;
}

.button-delete {
  background-color: red; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 10px;
  border-radius: 28px;
}

</style>