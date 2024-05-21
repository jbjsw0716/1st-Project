package com.keduit.SangWook;

public class ProductionCompany {
	protected int productionCompany_id;
	protected String productionCompanyName;
	
	protected ProductionCompany(int productionCompany_id) {
		this.productionCompany_id = productionCompany_id;
	}
	
	protected ProductionCompany(String productionCompanyName) {
		this.productionCompanyName = productionCompanyName;
	}

	protected int getProductionCompany() {
		return productionCompany_id;
	}

	protected void setProductionCompanyID(int productionCompany_id) {
		this.productionCompany_id = productionCompany_id;
	}

	protected String getProductionCompanyName() {
		return productionCompanyName;
	}

	protected void setProductionCompanyName(String productionCompanyName) {
		this.productionCompanyName = productionCompanyName;
	}

	@Override
	public String toString() {
		return "ProductionCompany [productionCompany_id=" + productionCompany_id + ", productionCompanyName="
				+ productionCompanyName + "]";
	}
}
