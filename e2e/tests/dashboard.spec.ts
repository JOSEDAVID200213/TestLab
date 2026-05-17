import { test, expect } from '@playwright/test';

test.describe('Dashboard UI', () => {
  test('should load the dashboard and display key metrics', async ({ page }) => {
    // Navigate to the root URL
    await page.goto('/');

    // Wait for the main title to ensure the dashboard loaded
    await expect(page.getByRole('heading', { name: 'TestLab QA Dashboard' })).toBeVisible();

    // Verify the presence of metric cards
    await expect(page.getByText('Overall Pass Rate')).toBeVisible();
    await expect(page.getByText('Code Coverage')).toBeVisible();
    await expect(page.getByText('Total Tests')).toBeVisible();
    await expect(page.getByText('Last Build')).toBeVisible();

    // Verify the charts and tables sections
    await expect(page.getByText('Build History (Pass Rate)')).toBeVisible();
    await expect(page.getByText('Recent Failures')).toBeVisible();
  });
});
