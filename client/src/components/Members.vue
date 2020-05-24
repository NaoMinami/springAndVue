<template>
    <div>
        <div v-for="(member, i) in members" :key="i">
            {{member.name}}
        </div>
    </div>
</template>
<script>
import ky from 'ky';

const api = ky.create({prefixUrl: 'http://localhost:8081'})

export default {
    name: "Members",

    data() {
        return {
            members: []
        }
    },

    methods: {
        refresh: async function () {
            const res = await api.get('members').json();
            this.members = res;
        }
    },

    mounted: async function () {
        await this.refresh()
    }
}
</script>
<style scoped>

</style>