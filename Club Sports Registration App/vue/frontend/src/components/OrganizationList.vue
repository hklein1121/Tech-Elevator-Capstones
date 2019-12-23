<template>
    <div>
        <div class="container">
            <h1>Organizations</h1>
        </div>

        <div class="container">
            <a href="#" v-on:click="$emit('createOrganization')">
                <router-link :to="{name:'organization-new'}"><i>Add Organization</i></router-link> 
            </a>
        </div>
    
        <div class="container">
            <div v-for="organization in organizations" :key="organization.organizationId">

                <h2><router-link :to="{name:'league-list', params:{orgId:organization.organizationId}}">{{organization.name}}</router-link></h2>

                <div>
                    <a href="#" v-on:click="editOrganization(parseInt(organization.organizationId))">
                       <router-link :to="{name:'organization-edit', params:{orgId:organization.organizationId}}"><i class="button-edit">Edit</i></router-link> 
                    </a>
                </div>

                <div>
                    <a href="#" v-on:click="deleteOrganization(organization.organizationId)">
                        <router-link :to="{name:'organization-list'}"><i class="button-delete">Delete</i></router-link>  
                    </a>
                </div>    

            </div>  
        </div>
    </div>
</template>

<script>
export default {
    name: "OrganizationList",
    props: {
        apiUrl: String
    },
    data() {
        return {
            organizations: []
        };
    },
    methods: {
        editOrganization(organizationId){
            this.$emit('editOrganization', organizationId)
        },
        deleteOrganization(organizationId){
            fetch(`${this.apiUrl}/${organizationId}`, {
                method: 'DELETE',
                headers: {
                "Content-Type": "application/json"
                }
            })
            .then( response => {
                if (response.ok) {
                const index = this.organizations.map(organization => organization.organizationId).indexOf(organizationId);
                this.organizations.splice(index, 1);
                }
            })
            .catch( err => console.error(err) );

        }
    
    },
    created() {
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
            this.organizations = data;
        })
        .catch( err => console.error(err) );
    }

}
</script>

<style>

</style>