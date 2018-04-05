Vue.prototype.$user = axios.create({
    baseURL: 'http://localhost:8080/',
    // withCredentials: true,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        // 'token':localStorage.getItem('token')
    },
    transformRequest: [function (data) {
        let newData = '';
        for (let k in data) {
            if (data.hasOwnProperty(k) === true) {
                newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
            }
        }
        // console.log(newData);
        return newData;
    }]
});
Vue.prototype.$http = axios.create({
    baseURL: 'http://localhost:8080/',
    withCredentials: true,
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        'token':localStorage.getItem('token')
    },
    transformRequest: [function (data) {
        let newData = '';
        for (let k in data) {
            if (data.hasOwnProperty(k) === true) {
                newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
            }
        }
        // console.log(newData);
        return newData;
    }]
});
// let http = axios.create({
//     baseURL: 'http://localhost:8080/',
//     withCredentials: true,
//     headers: {
//         'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
//         'token':localStorage.getItem('token')
//     },
//     transformRequest: [function (data) {
//         let newData = '';
//         for (let k in data) {
//             if (data.hasOwnProperty(k) === true) {
//                 newData += encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&';
//             }
//         }
//         return newData;
//     }]
// });
//
// function apiAxios(method, url, params, response) {
//     http({
//         method: method,
//         url: url,
//         data: method === 'POST' || method === 'PUT' ? params : null,
//         params: method === 'GET' || method === 'DELETE' ? params : null,
//     }).then(function (res) {
//         response(res);
//     }).catch(function (err) {
//         // localStorage.removeItem("token");
//         // localStorage.removeItem("email");
//         // http.defaults.headers.common = {Accept: "application/json, text/plain, */*"};
//         // router.push({path: '/'});
//         response(err);
//     })
// }
// Vue.prototype.$http = {
//     get: function (url, params, response) {
//         return apiAxios('GET', url, params, response)
//     },
//     post: function (url, params, response) {
//         return apiAxios('POST', url, params, response)
//     },
//     put: function (url, params, response) {
//         return apiAxios('PUT', url, params, response)
//     },
//     delete: function (url, params, response) {
//         return apiAxios('DELETE', url, params, response)
//     },
//     setToken: function (token) {
//         http.defaults.headers.common['token'] = token;
//     },
//     removeToken: function () {
//         http.defaults.headers.common = {Accept: "application/json, text/plain, */*"};
//     },
//     getToken: function () {
//         return http.defaults.headers.common['token'];
//     }
// }

