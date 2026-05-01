import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 20 },
    { duration: '3m', target: 50 },
    { duration: '1m', target: 0 },
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'],
    http_req_failed: ['rate<0.01'],
  },
};

export default function () {
  const responses = http.batch([
    ['GET', 'http://localhost:8080/api/products'],
    ['GET', 'http://localhost:8080/api/inventory/low-stock'],
  ]);
  
  check(responses[0], { 'status is 200': (r) => r.status === 200 });
  sleep(1);
}
