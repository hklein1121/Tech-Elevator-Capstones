<template>
    <div>
        <div class="container">
            <h1>Users</h1>
        </div>


         <div class="container">
            <h3>Click on the User to Assign:</h3>
            <div v-for="user in users" :key="user.userId">

                <h2 ><router-link :to="{name:'assign-user', params:{userId:user.userId}}">{{user.username}}</router-link></h2>
            
                <!-- <div>
                    <a href="#" v-on:click="editTeam(parseInt(team.teamId))">
                        <router-link :to="{name:'team-edit', params:{teamId:team.teamId, leagueId:leagueId}}"><i class="button-edit">Edit</i></router-link>
                    </a>
                </div>

                <div>
                    <a href="#" v-on:click="deleteTeam(team.teamId)">
                       <router-link :to="{name:'team-list'}"><i class="button-delete">Delete</i></router-link>
                    </a>
                </div> -->
        
            </div>  
        </div>  
    </div>
</template>

<script>
export default {
    name: "UserList",
    props: {
        apiUrl: String
    },
    data() {
        return {
            users: []
        };
    },
    methods: {
        assignUser(teamId){
            this.$emit('assignUser', teamId)
        }
        // deleteTeam(teamId){
        //     fetch(`${this.apiUrl}/${teamId}`, {
        //         method: 'DELETE',
        //         headers: {
        //         "Content-Type": "application/json"
        //     }
        //     })
        //      .then( response => {
        //         if (response.ok) {
        //         const index = this.teams.map(team  => team.teamId).indexOf(teamId);
        //         this.teams.splice(index, 1);
        //         }
        //     })
        //     .catch( err => console.error(err) );
        // }

    },
    created(){
         fetch(this.apiUrl, {
             method: 'GET',
                headers: {
                    "Content-Type": "application/json"
                },
        })
        .then(response => {
            return response.json();
        })
        .then( data => {
            this.users = data;
        })
        .catch( err => console.error(err) );
    }

}
</script>

<style>

</style>