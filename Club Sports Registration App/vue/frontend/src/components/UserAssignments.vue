<template>
    <div>
        
        <div class="container">    
            <a href="#" v-on:click="$emit('showUsers')">
                <i>Back to Users</i> 
            </a>
        </div>  
        <div class="container">
            <h2>User's Teams:</h2>
            <div v-for="team in userTeams" :key="team.teamId">
                <h3>{{team.name}}</h3>
            </div>
        </div>            

        <div class="container">
            <h2>Choose a Team for User:</h2>
            <select>
                <option v-for="team in teams" :key="team.teamId">{{team.name}}</option>
            </select>
                
            <router-link :to="{name:'assign-user', params:{userId:this.userId}}"><button v-on:click="saveUser" class="button-edit">Assign</button></router-link>
            

        </div>
    </div>    
</template>

<script>
export default {
    name: "UserAssignments",
    props: {
        apiUrl: String,
        userId: Number
    },
    data() {
        return {
            userTeams: [],
            teams: []
        }
    },
    methods: {
         saveUser() {
            
            this.assignUser();
            
         },
        assignUser() {
            fetch(`${this.apiUrl}/assign`, {
                method: 'POST',
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify(this.user)
            })
            .then( response => {
                if (response.ok ) {
                this.$emit('showUsers');
                }
            })
            .catch( err => console.error(err) );

        }
    },
    created() {
        this.userId = this.$route.params.userId;
        // if(this.userId != 0 && this.userId != null) {
        // fetch(`${this.apiUrl}/${userId}`)
        // .then( response => {
        //     return response.json()
        // })
        // .then ( data => {
        //     this.userTeams = data;
        //     }) 
        // }
        fetch(`${this.apiUrl}/assign`)
        .then( response => {
            return response.json()
        })
        .then ( data => {
            this.teams = data;
            })   
    }    
}
</script>

<style>

</style>