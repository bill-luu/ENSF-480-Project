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
     * 
     */
    private int productId_;

    /**
     * 
     */
    private String productName_;

    /**
     * 
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