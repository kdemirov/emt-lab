import axios from "axios";

const instance = axios.create({
    baseURL : 'http://localhost:8080/api',
    headers : {
        'Access-Controle-Allow-Origin' : '*'
    }
})
export default instance;