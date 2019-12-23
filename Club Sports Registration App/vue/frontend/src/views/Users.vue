<template>
    <div>
      
      <user-assignments
        
        v-on:showUsers="showUsers"
        :apiUrl="API_URL"
        :userId="userId"
        />

      <user-list 
        
        :apiUrl="API_URL"
        :userId="userId"
        v-on:assignUser="assignUser($event)"
        />    
    </div>
  
</template>

<script>
import UserList from '@/components/UserList.vue'
import UserAssignments from '@/components/UserAssignments.vue'

export default {
    name: 'user',
    components: {
        UserList,
        UserAssignments
    },
    data() {
        return {
            API_URL: "http://localhost:8080/AuthenticationApplication/api/clubsports/user",
            showRegistrationForm: false,
            userId: 0
        };
    },
    methods: {
    
    showUsers(){
      this.showRegistrationForm = false;
    },
    assignUser(id){
      this.userId = id;
      this.showRegistrationForm = true;
    }
  }

}
</script>

<style>

</style>