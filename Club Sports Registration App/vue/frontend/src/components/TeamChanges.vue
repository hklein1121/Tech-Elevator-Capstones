<template>
    <div>
        <div class="container">
            <h1>Register a Team</h1>
        </div>

        <div class="container">    
            <a href="#" v-on:click="$emit('showTeams')">
                <i>Show Teams</i> 
            </a>
        </div>  

        <div class="container">
            <span class="label">Team Info:</span>
                <input
                    type="text"
                    class="form-input"
                    placeholder="Team Name"
                    v-model="team.name"
                    required
                />
                <input
                    type="text"
                    class="form-input"
                    placeholder="Contact Email"
                    v-model="team.email"
                    required
                />
                <input
                    type="text"
                    class="form-input"
                    placeholder="Primary Venue"
                    v-model="team.primaryVenue"
                    required
                />
                <input
                    type="text"
                    class="form-input"
                    placeholder="Secondary Venue"
                    v-model="team.secondaryVenue"
                    required
                />
                <input
                    type="hidden"
                    id="leagueId"
                    :value="this.leagueId"
                    required
                />

        </div>
                    
        <div class="container">
            <router-link :to="{name:'team-list', params:{leagueId:this.leagueId}}"><button :disabled="!isValidForm" v-on:click="saveTeam">Save Team></button></router-link>
        </div>
    </div>    
</template>

<script>
export default {
    name: "TeamChanges",
    props: {
        apiUrl: String,
        teamId: Number
    },
    data() {
        return {
            team:  {
                name: '',
                leagueId: 0
            }
        }
    },
    methods: {
         saveTeam() {
            this.team.leagueId = this.leagueId;
            this.teamId === 0 ? this.createTeam() : this.editTeam();
            this.team.name = '';
            
         },
        createTeam() {
            fetch(this.apiUrl, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.team)
            })
            .then(response => {
                if(response.ok) {
                    this.$emit('showTeams');
                }
            })
            .catch (err => console.error(err));
        },
        editTeam() {
            fetch(`${this.apiUrl}/${this.teamId}`, {
                method: 'PUT',
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify(this.team)
            })
            .then( response => {
                if (response.ok ) {
                this.$emit('showTeams');
                }
            })
            .catch( err => console.error(err) );

        }
    },
    computed: {
    isValidForm() {
      return this.team.name != '';
        }
    },
    created() {
        this.leagueId = this.$route.params.leagueId;
        if(this.teamId != 0 && this.teamId != null) {
        fetch(`${this.apiUrl}/${this.leagueId}/${this.teamId}`)
        .then( response => {
            return response.json()
        })
        .then ( data => {
            this.team = data;
            console.log(this.team);
            }) 
        }  
    }
}
</script>

<style>

</style>