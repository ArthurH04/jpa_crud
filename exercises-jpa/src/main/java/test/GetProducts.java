package test;

import java.util.List;

import infra.ProductDAO;
import model.Product;

public class GetProducts {

	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		List<Product> products = productDAO.getAll(15, 2);

		for (Product product : products) {
			System.out.println("\nID -> " + product.getId() + "\nName -> " + product.getName() + "\nPrice -> "
					+ product.getPrice());
		}

		double totalPrice = products.stream()
				.map(p -> p.getPrice())
				.reduce(0.0, (t, p) -> t + p)
				.doubleValue();
		
		System.out.println("The value is: R$ " + totalPrice);
		
		productDAO.close();
	}
}
