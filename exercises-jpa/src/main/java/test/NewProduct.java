package test;

import java.util.Scanner;

import infra.DAO;
import model.Product;

public class NewProduct {

	public static void main(String[] args) {

		DAO<Product> productDAO = new DAO<>(Product.class);

//		productDAO.openTransaction().insert(product).closeTransaction().close();
//		productDAO.insertAtomic(product).close();

		Scanner input = new Scanner(System.in);
		System.out.print("How many: ");
		int qtd = input.nextInt();

		for (int i = 0; i < qtd; i++) {
			Product product = new Product();
			System.out.print("Enter the product name: ");
			String productName = input.next();

			System.out.print("Enter the product price: ");
			double price = input.nextDouble();

			product.setName(productName);
			product.setPrice(price);

			productDAO.openTransaction().insert(product).closeTransaction();
		}
		productDAO.close();
		input.close();

	}

}
