package dto;

public class FeatureDto {
	
	boolean advanceSearch;
	boolean productRating;
	boolean shirtText;
	boolean messages;
	boolean filters;
	boolean privateStamp;
	boolean loginSocialNetwork;
	boolean shareSocialNetwork;
	boolean changePassword;
	boolean changeAddress;
	boolean ratingsReports;
	boolean sellReports;
	
	public FeatureDto()
	{
		advanceSearch = false;
		productRating = false;
		shirtText = false;
		messages = false;
		filters = false;
		privateStamp = false;
		loginSocialNetwork = false;
		shareSocialNetwork = false;
		changePassword = false;
		changeAddress = false;
		ratingsReports = false;
		sellReports= false; 
	}
	
	public boolean isAdvanceSearch() {
		return advanceSearch;
	}
	public void setAdvanceSearch(boolean advanceSearch) {
		this.advanceSearch = advanceSearch;
	}
	public boolean isProductRating() {
		return productRating;
	}
	public void setProductRating(boolean productRating) {
		this.productRating = productRating;
	}
	public boolean isShirtText() {
		return shirtText;
	}
	public void setShirtText(boolean shirtText) {
		this.shirtText = shirtText;
	}
	public boolean isMessages() {
		return messages;
	}
	public void setMessages(boolean messages) {
		this.messages = messages;
	}
	public boolean isFilters() {
		return filters;
	}
	public void setFilters(boolean filters) {
		this.filters = filters;
	}
	public boolean isPrivateStamp() {
		return privateStamp;
	}
	public void setPrivateStamp(boolean privateStamp) {
		this.privateStamp = privateStamp;
	}
	public boolean isLoginSocialNetwork() {
		return loginSocialNetwork;
	}
	public void setLoginSocialNetwork(boolean loginSocialNetwork) {
		this.loginSocialNetwork = loginSocialNetwork;
	}
	public boolean isShareSocialNetwork() {
		return shareSocialNetwork;
	}
	public void setShareSocialNetwork(boolean shareSocialNetwork) {
		this.shareSocialNetwork = shareSocialNetwork;
	}
	public boolean isChangePassword() {
		return changePassword;
	}
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}
	public boolean isChangeAddress() {
		return changeAddress;
	}
	public void setChangeAddress(boolean changeAddress) {
		this.changeAddress = changeAddress;
	}
	public boolean isRatingsReports() {
		return ratingsReports;
	}
	public void setRatingsReports(boolean ratingsReports) {
		this.ratingsReports = ratingsReports;
	}
	public boolean isSellReports() {
		return sellReports;
	}
	public void setSellReports(boolean sellReports) {
		this.sellReports = sellReports;
	}
	
	


}
