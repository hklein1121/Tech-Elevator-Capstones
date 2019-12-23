<template>
  <div>
    <div class="container" v-if="!isAdmin">
      <h1 >Only Admins Can view this page</h1>
      <img src="https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2018/11/VideoAutoPlay-796x418.jpg"/>
    </div>
      <AdminOnly v-if="isAdmin"/>
  </div>  
</template>

<script>
import auth from '../auth';
import AdminOnly from '@/components/AdminOnly.vue'

export default {
  name: 'admin',
  components: {
    AdminOnly
  },
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
<style scoped>
  img {
    display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
  }
</style>