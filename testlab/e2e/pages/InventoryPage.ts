import { Page, Locator } from '@playwright/test';

export class InventoryPage {
  readonly page: Page;

  constructor(page: Page) {
    this.page = page;
  }

  async goto() {
    await this.page.goto('/inventory/stock');
  }

  async addStock(productName: string, quantity: string) {
    const row = this.page.locator('tr').filter({ hasText: productName });
    await row.getByRole('button', { name: 'Stock In' }).click();
    await this.page.getByLabel('Quantity').fill(quantity);
    await this.page.getByRole('button', { name: 'Submit' }).click();
  }

  async removeStock(productName: string, quantity: string) {
    const row = this.page.locator('tr').filter({ hasText: productName });
    await row.getByRole('button', { name: 'Stock Out' }).click();
    await this.page.getByLabel('Quantity').fill(quantity);
    await this.page.getByRole('button', { name: 'Submit' }).click();
  }
}
