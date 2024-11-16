import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '40s', target: 200 },
        { duration: '60s', target: 200 },
        { duration: '20s', target: 0 }
    ],
    thresholds: {
        checks: ['rate > 0.95'],
        http_req_duration: ['p(95) < 200'],
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
