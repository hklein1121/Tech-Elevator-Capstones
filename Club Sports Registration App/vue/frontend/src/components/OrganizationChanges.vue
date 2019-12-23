<template>
    <div>
        <div class="container">
            <h1>Register an Organization</h1>
        </div>

        <div class="container">
            <a href="#" v-on:click="$emit('showOrganizations')">
                <i>Show Organizations</i>
            </a>
        </div> 

        <div class="container">
            <span>Organization Name:</span>
                <input
                    type="text"
                    id="name"
                    class="form-input"
                    placeholder="organization name"
                    v-model="organization.name"
                    required
                />
        </div>
        
        <div class="container">        
            <router-link :to="{name:'organization-list'}"><button :disabled="!isValidForm" v-on:click="saveOrganization">Save Organization></button></router-link>
        </div>
    </div>    
</template>

<script>
export default {
    name: "OrganizationChanges",
    props: {
        apiUrl: String,
        organizationId: Number
    },
    data() {
        return {
            organization: {
                name: ''
            }
        }
    },
    methods: {
         saveOrganization() {
            this.organizationId === 0 ? this.createOrganization() : this.editOrganization();
            this.organization.name = '';
         },
        createOrganization() {
            fetch(this.apiUrl, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.organization)
            })
            .then(response => {
                if(response.ok) {
                    this.$emit('showOrganizations');
                }
            })
            .catch (err => console.error(err));
        },
        editOrganization() {
            fetch(`${this.apiUrl}/${this.organizationId}`, {
                method: 'PUT',
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify(this.organization)
            })
            .then( response => {
                if (response.ok ) {
                this.$emit('showOrganizations');
                }
            })
            .catch( err => console.error(err) );

        }
    },
    computed: {
    isValidForm() {
      return this.organization.name != '';
        }
    },
    created() {
        if(this.organizationId === 0 || this.organizationId === null){
            fetch(`${this.apiUrl}`)
            .then( response => {
                return response.json()
            })
            .then ( data => {
                this.league = data;
                console.log(this.league);
            })
        } else {
            fetch(`${this.apiUrl}/${this.organizationId}`)
        .then( response => {
            return response.json()
        })
        .then ( data => {
            this.organization = data;
        })
        }
        
    }

}
</script>

<style>

</style>