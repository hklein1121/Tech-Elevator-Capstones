<template>
  <div class="container">
    <h1 v-if="!isAdmin">Welcome User!</h1>
    <h1 v-if="isAdmin">Welcome Admin!</h1>

    <div v-if="isAdmin">
      <img src="https://www.livechatinc.com/wp-content/uploads/2017/01/leadership-how-to-be-a-good-boss.jpg"/>
    </div>

    <div v-if="!isAdmin">
      <img src="http://www.mtv.com.au/sites/default/files/styles/image-w-900-scale/public/mtv_au/galleries/large/2018/04/12/tommyspace.jpg?itok=kvCutZZt"/>
    </div>
  
  </div>
</template>

<script>
import auth from '../auth';

export default {
  name: 'home',
  data() {
    return {
      user: {},
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