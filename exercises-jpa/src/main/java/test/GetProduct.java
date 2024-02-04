package test;

import infra.ProductDAO;

public class GetProduct {

	public static void main(String[] args) {
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.getById(1L);

	}

}
