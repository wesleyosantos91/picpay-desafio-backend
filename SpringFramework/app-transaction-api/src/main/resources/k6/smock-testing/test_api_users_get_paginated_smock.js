import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 100,
    duration: '30s',
    thresholds: {
        checks: ['rate > 0.99']
    },
}

export default function () {
    const baseUrl  = __ENV.BASE_URL;
    const url = `${baseUrl }/v1/users`;
    const params = {
        headers: {
            'Content-Type': 'application/json',
            'x-correlationID': '85081701-3324-4134-9883-31f38a6c804d'
        },
    }
    let resp = http.get(url, params);
    check(resp, {
        'status is 200': (r) => r.status === 200,
    });
}
