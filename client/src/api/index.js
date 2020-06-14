import ky from 'ky';

export const api = ky.create({prefixUrl: '/api/app'});
export const commonApi = ky.create({prefixUrl: '/api'});

export const withApi = async (vue, fn) => {
    await fn();
}