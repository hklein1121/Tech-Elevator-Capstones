<template>
    <div>
        <availability-changes
        v-if="showRegistrationForm"
        v-on:showAvailabilities="showAvailabilities"
        :apiUrl="API_URL"
        :availabilityId="availabilityId"
        />
        <availability-list 
        v-if="!showRegistrationForm"
        :apiUrl="API_URL"
        :availabilityId="availabilityId"
        v-on:createAvailability="createAvailability"
        v-on:editAvailability="editAvailability($event)"
        />
    </div>
</template>

<script>
import AvailabilityList from '@/components/AvailabilityList.vue'
import AvailabilityChanges from '@/components/AvailabilityChanges.vue'

export default {
    name: 'availability',
    components: {
        AvailabilityList,
        AvailabilityChanges
    },
    data() {
        return {
            API_URL: "http://localhost:8080/AuthenticationApplication/api/clubsports/availability",
            showRegistrationForm: false,
            availabilityId:0
        };
    },
    methods: {
    createAvailability() {
      this.showRegistrationForm = true;
    },
    showAvailabilities(){
      this.showRegistrationForm = false;
      this.availabilityId = 0;
    },
    editAvailability(id){
      this.availabilityId = id;
      this.showRegistrationForm = true;
    }
  }

};
</script>

<style>

</style>