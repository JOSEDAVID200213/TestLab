import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

export interface TestRun {
  id: number;
  timestamp: string;
  totalTests: number;
  passedTests: number;
  failedTests: number;
  coveragePercentage: number;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class TestResultsService {
  private apiUrl = '/api/test-results';
  
  latestRun = signal<TestRun | null>(null);
  history = signal<TestRun[]>([]);

  constructor(private http: HttpClient) {}

  fetchLatestRun(): Observable<TestRun> {
    return this.http.get<TestRun>(`${this.apiUrl}/latest`).pipe(
      tap(run => this.latestRun.set(run))
    );
  }

  fetchHistory(): Observable<TestRun[]> {
    return this.http.get<TestRun[]>(`${this.apiUrl}/history`).pipe(
      tap(runs => this.history.set(runs))
    );
  }
}
