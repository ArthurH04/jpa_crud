package test;

import infra.DAO;
import model.Product;

public class DeleteProduct {
	
	public static void main(String[] args) {
		DAO<Product> daoo = new DAO<>(Product.class);
		
		
		daoo.remove(15L);
	}
}
