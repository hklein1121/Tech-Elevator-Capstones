<template>
  <div>
      <team-changes
        v-if="showRegistrationForm"
        v-on:showTeams="showTeams"
        :apiUrl="API_URL"
        :teamId="teamId"
        />
      <team-list 
        v-if="!showRegistrationForm"
        :apiUrl="API_URL"
        :teamId="teamId"
        v-on:createTeam="createTeam"
        v-on:editTeam="editTeam($event)"
        />

  </div>
</template>

<script>
import TeamChanges from '@/components/TeamChanges.vue'
import TeamList from '@/components/TeamList.vue'


export default {
    name: 'team',
    components: {
        TeamChanges,
        TeamList
    },
    data() {
        return {
            API_URL: "http://localhost:8080/AuthenticationApplication/api/clubsports/team",
            showRegistrationForm: false,
            teamId: 0
        };
    },
    methods: {
    createTeam() {
      this.showRegistrationForm = true;
    },
    showTeams(){
      this.showRegistrationForm = false;
      this.teamId = 0;
    },
    editTeam(id){
      this.teamId = id;
      this.showRegistrationForm = true;
    }
  }
};
</script>

<style>

</style>