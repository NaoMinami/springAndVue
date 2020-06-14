class SessionInfoRepository {
    sessionStorage = window.sessionStorage;

    saveLoginInfo(userDto) {
        if (!userDto) {
            this.sessionStorage.removeItem('login-info');
        } else {
            this.sessionStorage.setItem('login-info', JSON.stringify(userDto));
        }
    }

    restoreLoginInfo() {
        const value = this.sessionStorage.getItem('login-info');
        if(value) {
            return JSON.parse(value);
        }
        return null;
    }

    isLogin() {
        return this.restoreLoginInfo() != null;
    }
}

export default new SessionInfoRepository();