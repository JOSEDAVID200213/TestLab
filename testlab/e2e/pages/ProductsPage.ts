import { Page, Locator, expect } from '@playwright/test';

export interface ProductData {
  name: string;
  price: string;
  stock: string;
  minStock: string;
  category: string;
}

export class ProductsPage {
  readonly page: Page;
  readonly newProductButton: Locator;
  readonly nameInput: Locator;
  readonly priceInput: Locator;
  readonly stockInput: Locator;
  readonly minStockInput: Locator;
  readonly categorySelect: Locator;
  readonly submitButton: Locator;

  constructor(page: Page) {
    this.page = page;
    this.newProductButton = page.getByRole('button', { name: 'New Product' });
    this.nameInput = page.getByLabel('Name');
    this.priceInput = page.getByLabel('Price');
    this.stockInput = page.getByLabel('Initial Stock');
    this.minStockInput = page.getByLabel('Min Threshold');
    this.categorySelect = page.getByLabel('Category');
    this.submitButton = page.getByRole('button', { name: 'Save Product' });
  }

  async goto() {
    await this.page.goto('/inventory/products');
  }

  async createProduct(data: ProductData) {
    await this.newProductButton.click();
    await this.nameInput.fill(data.name);
    await this.priceInput.fill(data.price);
    await this.stockInput.fill(data.stock);
    await this.minStockInput.fill(data.minStock);
    await this.categorySelect.selectOption({ label: data.category });
    await this.submitButton.click();
  }

  async getProductRow(name: string) {
    return this.page.locator('tr').filter({ hasText: name });
  }

  async deleteProduct(name: string) {
    const row = await this.getProductRow(name);
    await row.getByRole('button', { name: 'Delete' }).click();
    await this.page.getByRole('button', { name: 'Confirm' }).click();
  }
}
