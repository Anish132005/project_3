package in.co.rays.project_3.dto;

import java.util.Date;

public class CartOverviewDto extends BaseDTO {
	private String CustomerName;
	private String Product;
	private Date TransationDate;
	private Integer QuantityOrdered;

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	public Date getTransationDate() {
		return TransationDate;
	}

	public void setTransationDate(Date transationDate) {
		TransationDate = transationDate;
	}

	public Integer getQuantityOrdered() {
		return QuantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		QuantityOrdered = quantityOrdered;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
