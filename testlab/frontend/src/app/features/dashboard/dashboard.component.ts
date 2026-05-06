import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TestResultsService } from '../../core/services/test-results.service';
import { BaseChartDirective } from 'ng2-charts';
import { ChartConfiguration, ChartData, ChartType, Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, BaseChartDirective],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  private testService = inject(TestResultsService);
  
  latestRun = this.testService.latestRun;
  history = this.testService.history;

  // Chart data
  public lineChartData: ChartData<'line'> = {
    datasets: [],
    labels: []
  };

  public lineChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      y: { min: 0, max: 100 }
    }
  };

  ngOnInit() {
    this.testService.fetchLatestRun().subscribe();
    this.testService.fetchHistory().subscribe(history => {
      this.updateChart(history);
    });
  }

  private updateChart(history: any[]) {
    this.lineChartData = {
      labels: history.map(h => new Date(h.timestamp).toLocaleDateString()).reverse(),
      datasets: [
        {
          data: history.map(h => (h.passedTests / h.totalTests) * 100).reverse(),
          label: 'Pass Rate %',
          borderColor: '#10b981',
          backgroundColor: 'rgba(16, 185, 129, 0.1)',
          fill: true,
        }
      ]
    };
  }
}
