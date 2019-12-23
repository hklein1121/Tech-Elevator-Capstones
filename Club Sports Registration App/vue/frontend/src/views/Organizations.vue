<template>
  <div>
    <organization-changes 
    v-if="showRegistrationForm"
    v-on:showOrganizations="showOrganizations"
    :apiUrl="API_URL"
    :organizationId="organizationId"
    />
    <organization-list 
    v-if="!showRegistrationForm"
    :apiUrl="API_URL"
    :organizationId="organizationId"
    v-on:createOrganization="createOrganization"
    v-on:editOrganization="editOrganization($event)"
    />
  </div>
</template>

<script>
import OrganizationChanges from '@/components/OrganizationChanges.vue'
import OrganizationList from '@/components/OrganizationList.vue'

export default {
  name: 'organization',
  components: {
    OrganizationChanges,
    OrganizationList
  },
  data() {
    return {
      API_URL: "http://localhost:8080/AuthenticationApplication/api/clubsports/organization",
      showRegistrationForm: false,
      organizationId: 0
    };
  },
  methods: {
    createOrganization() {
      this.showRegistrationForm = true;
    },
    showOrganizations(){
      this.showRegistrationForm = false;
      this.organizationId = 0;
    },
    editOrganization(id){
      this.organizationId = id;
      this.showRegistrationForm = true;
    }
  }
};
</script>

<style>

</style>