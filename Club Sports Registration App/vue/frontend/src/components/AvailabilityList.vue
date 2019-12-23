<template>
  <div>
    <div class="container">
        <h1>Open Availability</h1>
    </div>
    <div class="container">
        <a href="#" v-on:click="$emit('createAvailability')">
            <router-link :to="{name:'availability-new', params:{teamId:teamId}}"><i>Add New Availability</i></router-link> 
        </a>
    </div>

      <div class="container">
        <div v-for="availability in availabilities" :key="availability.availabilityId">

          <h3 v-if="availability.home && availability.away">Can Host or Travel:</h3>
          <h3 v-if="availability.home && !availability.away">Can Host:</h3>
          <h3 v-if="availability.away && !availability.home">Can Travel:</h3>
          <h3>{{availability.date}}</h3>
          <h4>From: {{availability.earliestStart}} To: {{availability.latestStart}} </h4>
    
          <div>
              <a href="#" v-on:click="editAvailability(parseInt(availability.availabilityId))">
                  <router-link :to="{name:'availability-edit', params:{availabilityId:availability.availabilityId, teamId:teamId}}"><i class="button-edit">Edit</i></router-link>
              </a>
          </div>

          <div>
              <a href="#" v-on:click="deleteAvailability(availability.availabilityId)">
                  <router-link :to="{name:'availability-list'}"><i class="button-delete">Delete</i></router-link>
              </a>
          </div>
        </div>  
      </div>
    
  </div>
  
</template>

<script>
export default {
    name: "AvailabilityList",
    props: {
      apiUrl: String
    },
data() {
  return {
    availabilities: [],
    team: ''
  };
},
methods: {
  editAvailability(availabilityId){
    this.$emit('editAvailability', availabilityId)
  },
  deleteAvailability(availabilityId){
            fetch(`${this.apiUrl}/${availabilityId}`, {
                method: 'DELETE',
                headers: {
                "Content-Type": "application/json"
            }
            })
             .then( response => {
                if (response.ok) {
                const index = this.availabilities.map(availability  => availability.availabilityId).indexOf(availabilityId);
                this.availabilities.splice(index, 1);
                }
            })
            .catch( err => console.error(err) );
        }

},
created() {

      this.teamId = this.$route.params.teamId;
      this.availabilityId = this.$route.params.availabilityId;
          fetch(`${this.apiUrl}/${this.teamId}`, {
            method: 'GET',
                headers: {
                    "Content-Type": "application/json"
                },
          })
          .then(response => {
              return response.json();
          })
          .then( data => {
              this.availabilities = data;
          })
          .catch( err => console.error(err) );
        }
}
</script>

<style>

</style>