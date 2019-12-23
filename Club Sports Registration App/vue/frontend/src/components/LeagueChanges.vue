<template>
    <div>
        <div class="container">
            <h1>Register a League</h1>
        </div>

        <div class="container">    
            <a href="#" v-on:click="$emit('showLeagues')">
                <i>Show Leagues</i>
            </a>
        </div>  

        <div class="container">
            <span class="label">League Name:</span>
                <input
                    type="text"
                    id="name"
                    class="form-input"
                    placeholder="League Name"
                    v-model="league.name"
                    required
                />
                <input
                    type="hidden"
                    id="orgId"
                    :value="this.organizationId"
                    required
                />
        </div>
                    
        <div class="container">
            <router-link :to="{name:'league-list', params:{orgId:this.organizationId}}"><button :disabled="!isValidForm" v-on:click="saveLeague">Save League></button></router-link>
        </div>
    </div>    
</template>

<script>
export default {
    name: "LeagueChanges",
    props: {
        apiUrl: String,
        leagueId: Number,
    },
    data() {
        return {
            league:  {
                name: '',
                organizationId: '',
                adminId: '',
            }
        }
    },
    methods: {
         saveLeague() {
            this.league.organizationId = this.organizationId;
            this.leagueId === 0 ? this.createLeague() : this.editLeague();

            this.league.name = ''
            
         },
        createLeague() {
            fetch(this.apiUrl, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.league)
            })
            .then(response => {
                if(response.ok) {
                    this.$emit('showLeagues');
                }
            })
            .catch (err => console.error(err));
        },
        editLeague() {
            fetch(`${this.apiUrl}/${this.leagueId}`, {
                method: 'PUT',
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify(this.league)
            })
            .then( response => {
                if (response.ok ) {
                this.$emit('showLeagues');
                }
            })
            .catch( err => console.error(err) );

        }
    },
    computed: {
    isValidForm() {
      return this.league.name != '';
        }
    },
    created() {
        this.organizationId = this.$route.params.orgId;
        if(this.leagueId != 0 && this.leagueId != null){
            fetch(`${this.apiUrl}/${this.organizationId}/${this.leagueId}`)
            .then( response => {
                return response.json()
            })
            .then ( data => {
                this.league = data;
                console.log(this.league);
            })
        }
        
    }

}
</script>

<style>

</style>