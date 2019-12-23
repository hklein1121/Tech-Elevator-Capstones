<template>
    <div>
        <div class="container">
            <h1>Leagues</h1>
        </div>

        <div class="container">
            <a href="#" v-on:click="$emit('createLeague')">
                <router-link :to="{name:'league-new', params:{orgId:organizationId}}"><i>Add League</i></router-link> 
            </a>
        </div>

        <div class="container">
            <div v-for="league in leagues" :key="league.leagueId">

                <h2 ><router-link :to="{name:'team-list', params:{leagueId:league.leagueId}}">{{league.name}}</router-link></h2>
            
                <div>
                    <a href="#" v-on:click="editLeague(parseInt(league.leagueId))">
                        <router-link :to="{name:'league-edit', params:{leagueId:league.leagueId, orgId:organizationId}}"><i class="button-edit">Edit</i></router-link>
                    </a>
                </div>

                <div>
                    <a href="#" v-on:click="deleteLeague(league.leagueId)">
                       <router-link :to="{name:'league-list'}"><i class="button-delete">Delete</i></router-link>
                    </a>
                </div>
        
            </div>  
        </div>
    </div>
</template>

<script>
export default {
    name: "LeagueList",
    props: {
        apiUrl: String,
    },
    data() {
        return {
            leagues: [],
            organization: ''
        };
    },
    methods: {
        editLeague(leagueId){
            this.$emit('editLeague', leagueId)
        },
        deleteLeague(leagueId){
            fetch(`${this.apiUrl}/${leagueId}`, {
                method: 'DELETE',
                headers: {
                "Content-Type": "application/json"
            }
            })
             .then( response => {
                if (response.ok) {
                const index = this.leagues.map(league => league.leagueId).indexOf(leagueId);
                this.leagues.splice(index, 1);
                }
            })
            .catch( err => console.error(err) );
        }

    },
    created(){
        this.organizationId = this.$route.params.orgId;
        this.leagueId = this.$route.params.leagueId;

         fetch(`${this.apiUrl}/${this.organizationId}`, {
             method: 'GET',
                headers: {
                    "Content-Type": "application/json"
                },
        })
        .then(response => {
            return response.json();
        })
        .then( data => {
            this.leagues = data;
        })
        .catch( err => console.error(err) );
    }

}
</script>

<style>

</style>