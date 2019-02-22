package app.model.service;

public class ReceiptServiceModel {

    private String uuid;

    private String fee;

    private String issuedOn;

    private String recipient;

    private PackageServiceModel packageServiceModel;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public PackageServiceModel getPackageServiceModel() {
        return packageServiceModel;
    }

    public void setPackageServiceModel(PackageServiceModel packageServiceModel) {
        this.packageServiceModel = packageServiceModel;
    }
}
