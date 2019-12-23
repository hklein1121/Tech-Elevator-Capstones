<template>
    <div>
        <div class="container">
            <h1>Set Your Availability</h1>
        </div>

        <div class="container">    
            <a href="#" v-on:click="$emit('showAvailabilities')">
                <i>Show Scheduled Availability</i>
            </a>
        </div>  
        <div class="container">
            <form>
                <span class="label">Date To Host:</span>
                <input 
                    type="date" 
                    class="form-input"
                    id="date"
                    name="date"
                    v-model="availability.date"
                    min="2018-01-01" 
                    max="2030-12-31"
                />
                <span class="label">Earliest/Lastest Time Available to Host:</span>
                <input 
                    type="time" 
                    class="form-input"
                    name="earliestStart"
                    v-model="availability.earliestStart"
                />
                <input 
                    type="time" 
                    class="form-input"
                    name="latestStart"
                    v-model="availability.latestStart"
                />    

                <input type="radio" id="true" value="True" v-model="availability.home">
                <label for="True">Yes</label>
                <br>
                <input type="radio" id="false" value="False" v-model="availability.home">
                <label for="False">No</label>
                <br>
                <span>Available for home?</span>
                
                <br><br>

                <input type="radio" id="true" value="True" v-model="availability.away">
                <label for="True">Yes</label>
                <br>
                <input type="radio" id="false" value="False" v-model="availability.away">
                <label for="False">No</label>
                <br>
                <span>Available for away?</span>


                <input
                    type="hidden"
                    id="teamId"
                    :value="this.teamId"
                    required
                />
            </form>
            <router-link :to="{name:'availability-list', params:{teamId:this.teamId}}"><button :disabled="!isValidForm" v-on:click="saveAvailability">Save Availability></button></router-link>
        </div> 
    </div>        
</template>

<script>
export default {
    name: "AvailabilityChanges",
    props: {
        apiUrl: String,
        availabilityId: Number
    },
    data() {
        return {
            availability: {
                home: '',
                away: '',
                date: '',
                earliestStart: '',
                latestStart: '',
                teamId: 0
            }
        }
    },
    methods: {
        saveAvailability() {
            this.availability.teamId = this.teamId;
            this.availabilityId === 0 ? this.createAvailability() : this.editAvailability();
        },
        createAvailability() {
            fetch(this.apiUrl, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.availability)
            })
                .then(response => {
                    if(response.ok) {
                        this.$emit('showAvailabilities');
                }
            })
            .catch (err => console.error(err));
        },
        editAvailability() {
            // need api url pathing
            fetch(`${this.apiUrl}/${this.availabilityId}`, {
                method: 'PUT',
                headers: {
                "Content-Type": "application/json"
                },
                body: JSON.stringify(this.availability)
            })
            .then( response => {
                if (response.ok ) {
                this.$emit('showAvailabilities');
                }
            })
            .catch( err => console.error(err) );

        }
    }, 
    computed: {
        isValidForm() {
            return this.availability.date != '';
        }
    },
    created() {
        this.teamId = this.$route.params.teamId;
        if(this.availabilityId != 0 && this.availabilityId != null) {
        fetch(`${this.apiUrl}/${this.teamId}/${this.availabilityId}`)
        .then( response => {
            return response.json()
        })
        .then ( data => {
            this.availability = data;
            console.log(this.availability);
            }) 
        }  
    }
    
}
</script>

<style>

</style>