<template>
    <div id="login">
        <form v-on:submit.prevent="onSubmit">
            <div class="field">
                <label class="label">ログインID</label>
                <input type="text" class="input" placeholder="ログインID" v-model="loginId"/>
            </div>
            <div class="field">
                <label class="label">パスワード</label>
                <input type="password" class="input" placeholder="パスワード" v-model="password"/>
            </div>
            <button type="submit" class="button">ログイン</button>
        </form>
    </div>
</template>

<script>
import ky from 'ky';
const commonApi = ky.create({prefixUrl: 'http://localhost:8081'});

export default {
    name: "Login",
    data: () => {
        return {
            loginId: null,
            password: null,
            errors: [],
        };
    },
    methods: {
        async onSubmit() {
            console.log(commonApi);
            const loginResult = await commonApi
                .post('login', {
                    json: {
                        loginId: this.loginId,
                        password: this.password,
                    },
                })
                .json();
            console.log(loginResult);
        }
    }
}
</script>

<style scoped>

</style>