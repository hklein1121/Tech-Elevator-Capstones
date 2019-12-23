<template>
    <div>
        <div class="container">
            <h1>Teams</h1>
        </div>

        <div class="container">
            <a href="#" v-on:click="$emit('createTeam')">
                <router-link :to="{name:'team-new', params:{leagueId:leagueId}}"><i>Add Team</i></router-link> 
            </a>
        </div>

         <div class="container">
            <div v-for="team in teams" :key="team.teamId">

                <h2 ><router-link :to="{name:'availability-list', params:{teamId:team.teamId}}">{{team.name}}</router-link></h2>
            
                <div>
                    <a href="#" v-on:click="editTeam(parseInt(team.teamId))">
                        <router-link :to="{name:'team-edit', params:{teamId:team.teamId, leagueId:leagueId}}"><i class="button-edit">Edit</i></router-link>
                    </a>
                </div>

                <div>
                    <a href="#" v-on:click="deleteTeam(team.teamId)">
                       <router-link :to="{name:'team-list'}"><i class="button-delete">Delete</i></router-link>
                    </a>
                </div>
        
            </div>  
        </div>  
    </div>
</template>

<script>
export default {
    name: "TeamList",
    props: {
        apiUrl: String
    },
    data() {
        return {
            teams: [],
            league: ''
        };
    },
    methods: {
        editTeam(teamId){
            this.$emit('editTeam', teamId)
        },
        deleteTeam(teamId){
            fetch(`${this.apiUrl}/${teamId}`, {
                method: 'DELETE',
                headers: {
                "Content-Type": "application/json"
            }
            })
             .then( response => {
                if (response.ok) {
                const index = this.teams.map(team  => team.teamId).indexOf(teamId);
                this.teams.splice(index, 1);
                }
            })
            .catch( err => console.error(err) );
        }

    },
    created(){
        this.organizationId = this.$route.params.orgId;
        this.leagueId = this.$route.params.leagueId;
        this.teamId = this.$route.params.teamId;

         fetch(`${this.apiUrl}/${this.leagueId}`, {
             method: 'GET',
                headers: {
                    "Content-Type": "application/json"
                },
        })
        .then(response => {
            return response.json();
        })
        .then( data => {
            this.teams = data;
        })
        .catch( err => console.error(err) );
    }

}
</script>

<style>

</style>