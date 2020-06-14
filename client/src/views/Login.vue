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
import {commonApi,withApi} from '@/api';
import sessionInfoRepository from '@/repository/sessionInfoRepositoy';


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
        

            await withApi(
                this,
                async () => {
                    const loginResult = await commonApi
                    .post('login', {
                        json: {
                            loginId: this.loginId,
                            password: this.password,
                        },
                    })
                    .json();
                    sessionInfoRepository.saveLoginInfo(loginResult.user);
                    console.log(loginResult);
                    this.$router.push("/");
                }
            );
        }
    }
}
</script>

<style scoped>

</style>