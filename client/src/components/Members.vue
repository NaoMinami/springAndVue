<template>
    <div>
        <div v-for="(member, i) in members" :key="i">
            {{member.name}}
        </div>
    </div>
</template>
<script>
import {withApi,api} from "@/api";

export default {
    name: "Members",

    data() {
        return {
            members: []
        }
    },

    methods: {
        async refresh() {
            await withApi(
                this,
                async () => {
                    const res = await api.get('members').json();
                    this.members = res;
                }
            )
        }
    },

    mounted() {
        this.refresh();
    }
}
</script>
<style scoped>

</style>