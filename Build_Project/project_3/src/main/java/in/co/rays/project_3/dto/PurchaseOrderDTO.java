package in.co.rays.project_3.dto;

import java.util.Date;

public class PurchaseOrderDTO extends BaseDTO {

	private Integer totalquantity;
	private String product;
	private Date orderdate;
	private Double totalCost;

	public Integer getTotalquantity() {
		return totalquantity;
	}

	public void setTotalquantity(Integer totalquantity) {
		this.totalquantity = totalquantity;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
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
