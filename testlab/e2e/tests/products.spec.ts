import { test, expect } from '@playwright/test';
import { ProductsPage } from '../pages/ProductsPage';

test.describe('Product Management', () => {
  test('should create and delete a product', async ({ page }) => {
    const productsPage = new ProductsPage(page);
    await productsPage.goto();

    const productData = {
      name: 'Mechanical Keyboard',
      price: '150',
      stock: '20',
      minStock: '5',
      category: 'Electronics'
    };

    await productsPage.createProduct(productData);
    
    const row = await productsPage.getProductRow('Mechanical Keyboard');
    await expect(row).toBeVisible();
    await expect(row.getByText('150')).toBeVisible();

    await productsPage.deleteProduct('Mechanical Keyboard');
    await expect(row).not.toBeVisible();
  });

  test('should show validation error for missing name', async ({ page }) => {
    const productsPage = new ProductsPage(page);
    await productsPage.goto();
    await productsPage.newProductButton.click();
    await productsPage.submitButton.click();
    
    await expect(page.getByText('Name is required')).toBeVisible();
  });
});
