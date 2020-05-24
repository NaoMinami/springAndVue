import ky from 'ky';

export const api = ky.create({prefixUrl: 'http://localhost:8081/app'});
export const commonApi = ky.create({prefixUrl: 'http://localhost:8081'});