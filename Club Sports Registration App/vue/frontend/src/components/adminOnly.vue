<template>
    <div>
        <div class="container">
            <h1>This is where the boss stuff goes</h1>
            <img src="https://www.lifeandstylemag.com/wp-content/uploads/2015/08/the-office-quotes-2.jpg?fit=800%2C447"/>
        </div>

    </div>
</template>

<script>
import auth from '../auth';

export default {
    name: 'AdminOnly',
        props: {
        apiUrl: String,
        userId: Number
    },
    data() {
        return {
            success: false,
            user: {
                userId: ''
            }
        }
    },
    methods: {
        editUser() {
            fetch(`${this.apiUrl}/${this.userId}`, {
                method: 'PUT',
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
        fetch(process.env.VUE_APP_REMOTE_API, {
            headers: {
                'Authorization': 'Bearer ' + auth.getToken()
            },
        })
        .then(response => {
            if (!response.ok) {
                console.error(response);
                this.$router.push({ name: 'unauthorized'});
            }
        })
        .catch(err => { console.error(err)})

    }
}
</script>
<style scoped>
  img {
    display: block;
  margin-left: auto;
  margin-right: auto;
  width: 50%;
  }
</style>