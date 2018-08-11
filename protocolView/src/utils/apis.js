import axios from 'axios';

const url = '/api/index';

export const helloWorld = () => axios.get(`${url}/helloWorld`);
