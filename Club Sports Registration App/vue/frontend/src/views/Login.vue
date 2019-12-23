<template>
  <div>
    <form @submit.prevent="login">

      <div class="container">
      <h1> Please Sign In Baller! </h1>
      </div>
      
      <div class="container" role="alert" v-if="invalidCredentials">
        Invalid baller username and baller password!
      </div>

      <div class="container" role="alert" v-if="this.$route.query.registration">
        Thank you for registering baller, now please sign in baller.
      </div>
      
      <div class="container">
        <label for="username">Baller Username</label>
          <input
            type="text"
            id="username"
            class="form-input"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
      </div>  

      <div class="container">
        <label for="password">Baller Password</label>
          <input
            type="password"
            id="password"
            class="form-input"
            placeholder="Password"
            v-model="user.password"
            required
          />
      </div>

      <div class="container">
        <button type="submit">Sign in</button>
      </div>

      <div class="container">
        <router-link :to="{ name: 'register' }">Need a baller account?</router-link>
      </div>

    </form>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(process.env.VUE_APP_REMOTE_API + "/login", {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
            this.$router.push('/');
          }
        })
        .catch((err) => console.error(err));
    },
  },
};
</script>

<style>

</style>