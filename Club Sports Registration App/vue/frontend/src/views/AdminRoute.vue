<template>
  <div class="container">
      <h1 v-if="!isAdmin">The Route only allows Admins to go here</h1>

      <p v-if="isAdmin">Only David can see this.</p>
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: "admin-route",
  data() {
    return {
      user: {}
    }
  },
  computed: {
    isAdmin(vm) {
      return vm.user.rol === 'admin';
    }
  },
  created() {
    this.user = auth.getUser();
  }
}
</script>