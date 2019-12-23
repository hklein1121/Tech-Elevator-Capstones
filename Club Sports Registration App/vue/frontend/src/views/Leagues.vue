<template>
  <div>
    <league-changes 
    v-if="showRegistrationForm"
    v-on:showLeagues="showLeagues"
    :apiUrl="API_URL"
    :leagueId="leagueId"
    />
    <league-list
    v-if="!showRegistrationForm"
    :apiUrl="API_URL"
    :leagueId="leagueId"
    v-on:createLeague="createLeague"
    v-on:editLeague="editLeague($event)"
    />
  </div>
</template>

<script>
import LeagueChanges from '@/components/LeagueChanges.vue'
import LeagueList from '@/components/LeagueList.vue'

export default {
  name: 'league',
  components: {
    LeagueChanges,
    LeagueList
  },
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/clubsports/league",
      showRegistrationForm: false,
      leagueId: 0
    };
  },
  methods: {
    createLeague() {
      this.showRegistrationForm = true;
    },
    showLeagues(){
      this.showRegistrationForm = false;
      this.leagueId = 0;
    },
    editLeague(id){
      this.leagueId = id;
      this.showRegistrationForm = true;
    }
  }
};
</script>

<style>

</style>