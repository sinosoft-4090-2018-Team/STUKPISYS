Vue.prototype.$http = axios.create({
    baseURL: 'http://localhost:8080/',
    // withCredentials: true,
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
        console.log(newData);
        return newData;
    }]
});

