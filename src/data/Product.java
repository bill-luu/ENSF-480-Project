package data;

/**
 * 
 */
public class Product {

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * The ID of the product
     */
    private int productId_;

    /**
     * The name of the product
     */
    private String productName_;

    /**
     * The description of the product
     */
    private String productDescription;

	public int getProductId_() {
		return productId_;
	}

	public void setProductId_(int productId_) {
		this.productId_ = productId_;
	}

	public String getProductName_() {
		return productName_;
	}

	public void setProductName_(String productName_) {
		this.productName_ = productName_;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}