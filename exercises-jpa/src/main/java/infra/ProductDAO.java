package infra;

import model.Product;

public class ProductDAO extends DAO<Product> {

	public ProductDAO() {
		super(Product.class);
	}

	@Override
	public Product getById(long id) {
		Product product = em.find(Product.class, id);
		if (product != null) {
			System.out.println("\nID -> " + product.getId() + "\nName -> " + product.getName() + "\nPrice -> "
					+ product.getPrice());
		}else {
			System.out.println("This product doesn't exists");
		}
		
		return product;
	}

}
