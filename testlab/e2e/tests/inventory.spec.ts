import { test, expect } from '@playwright/test';
import { InventoryPage } from '../pages/InventoryPage';

test.describe('Inventory Management', () => {
  test('should increase and decrease stock', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.goto();

    // Assuming a product "Laptop" already exists from seed or previous test
    await inventoryPage.addStock('Laptop', '10');
    const row = page.locator('tr').filter({ hasText: 'Laptop' });
    await expect(row.locator('.stock-quantity')).toHaveText(/10/); // simplified check

    await inventoryPage.removeStock('Laptop', '5');
    await expect(row.locator('.stock-quantity')).toHaveText(/5/);
  });

  test('should show error for insufficient stock', async ({ page }) => {
    const inventoryPage = new InventoryPage(page);
    await inventoryPage.goto();
    
    await inventoryPage.removeStock('Laptop', '1000');
    await expect(page.getByText('Insufficient stock')).toBeVisible();
  });
});
