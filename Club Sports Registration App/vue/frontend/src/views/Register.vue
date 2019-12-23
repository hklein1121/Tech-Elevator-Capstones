<template>
  <div>
    <form @submit.prevent="register">

        <div class="container">  
            <h1>Baller Account Registration</h1>
        </div>

        <div class="container" role="alert" v-if="registrationErrors">
        There were problems registering this user.
        </div>

        <div class="container">
            <label for="username">Enter a Baller Username:</label>
                <input
                type="text"
                class="form-input"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
                />
            <label for="email">Enter a Baller Email:</label>
                <input
                type="email"
                class="form-input"
                placeholder="Email"
                v-model="user.email"
                required
                />
            <label for="password">Enter a Baller Password:</label>
                <input
                type="password"
                class="form-input"
                placeholder="Password"
                v-model="user.password"
                required
                />
            <label for="password">Comfirm Your Baller Password:</label>
            <input
                type="password"
                class="form-input"
                placeholder="Confirm Password"
                v-model="user.confirmPassword"
                required
                />
        </div>

        <div class="container"> 
            <button type="submit">
            Create Account
            </button>
        </div>

        <div class="container"> 
            <router-link :to="{ name: 'login' }">
            Have an account?
            </router-link>
        </div>

    </form>
  </div>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        email: '',
        password: '',
        confirmPassword: ''
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(process.env.VUE_APP_REMOTE_API + "/user", {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/login', query: { registration: 'success' } });
          } else {
            this.registrationErrors = true;
          }
        })

        .then((err) => console.error(err));
    },
  },
};
</script>

<style>
</style>