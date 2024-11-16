import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '5s', target: 100 },
        { duration: '5s', target: 100 },
        { duration: '2s', target: 200 },
        { duration: '2s', target: 0 }
    ],
    thresholds: {
        checks: ['rate > 0.95'],
        http_req_duration: ['p(95) < 200'],
        http_req_failed: ['rate < 0.01'],
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
    sleep(1);
}