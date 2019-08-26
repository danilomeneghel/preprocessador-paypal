
package resources;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Payment {

    @SerializedName("acquirer")
    @Expose
    private Object acquirer;
    @SerializedName("acquirer_reconciliation")
    @Expose
    private List<Object> acquirerReconciliation = null;
    @SerializedName("additional_info")
    @Expose
    private AdditionalInfo additionalInfo;
    @SerializedName("authorization_code")
    @Expose
    private String authorizationCode;
    @SerializedName("binary_mode")
    @Expose
    private Boolean binaryMode;
    @SerializedName("call_for_authorize_id")
    @Expose
    private Object callForAuthorizeId;
    @SerializedName("captured")
    @Expose
    private Boolean captured;
    @SerializedName("card")
    @Expose
    private Card card;
    @SerializedName("collector_id")
    @Expose
    private Integer collectorId;
    @SerializedName("counter_currency")
    @Expose
    private Object counterCurrency;
    @SerializedName("coupon_amount")
    @Expose
    private Integer couponAmount;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;
    @SerializedName("date_approved")
    @Expose
    private String dateApproved;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_last_updated")
    @Expose
    private String dateLastUpdated;
    @SerializedName("date_of_expiration")
    @Expose
    private Object dateOfExpiration;
    @SerializedName("deduction_schema")
    @Expose
    private Object deductionSchema;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("differential_pricing_id")
    @Expose
    private Object differentialPricingId;
    @SerializedName("external_reference")
    @Expose
    private String externalReference;
    @SerializedName("fee_details")
    @Expose
    private List<Object> feeDetails = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("installments")
    @Expose
    private Integer installments;
    @SerializedName("issuer_id")
    @Expose
    private String issuerId;
    @SerializedName("live_mode")
    @Expose
    private Boolean liveMode;
    @SerializedName("merchant_account_id")
    @Expose
    private Object merchantAccountId;
    @SerializedName("merchant_number")
    @Expose
    private Object merchantNumber;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("money_release_date")
    @Expose
    private String moneyReleaseDate;
    @SerializedName("money_release_schema")
    @Expose
    private Object moneyReleaseSchema;
    @SerializedName("notification_url")
    @Expose
    private Object notificationUrl;
    @SerializedName("operation_type")
    @Expose
    private String operationType;
    @SerializedName("order")
    @Expose
    private Order order;
    @SerializedName("payer")
    @Expose
    private Payer payer;
    @SerializedName("payment_method_id")
    @Expose
    private String paymentMethodId;
    @SerializedName("payment_type_id")
    @Expose
    private String paymentTypeId;
    @SerializedName("pos_id")
    @Expose
    private String posId;
    @SerializedName("processing_mode")
    @Expose
    private String processingMode;
    @SerializedName("refunds")
    @Expose
    private List<Object> refunds = null;
    @SerializedName("shipping_amount")
    @Expose
    private Integer shippingAmount;
    @SerializedName("sponsor_id")
    @Expose
    private Object sponsorId;
    @SerializedName("statement_descriptor")
    @Expose
    private String statementDescriptor;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_detail")
    @Expose
    private String statusDetail;
    @SerializedName("store_id")
    @Expose
    private Object storeId;
    @SerializedName("taxes_amount")
    @Expose
    private Integer taxesAmount;
    @SerializedName("transaction_amount")
    @Expose
    private Integer transactionAmount;
    @SerializedName("transaction_amount_refunded")
    @Expose
    private Integer transactionAmountRefunded;
    @SerializedName("transaction_details")
    @Expose
    private TransactionDetails transactionDetails;

    public Object getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(Object acquirer) {
        this.acquirer = acquirer;
    }

    public List<Object> getAcquirerReconciliation() {
        return acquirerReconciliation;
    }

    public void setAcquirerReconciliation(List<Object> acquirerReconciliation) {
        this.acquirerReconciliation = acquirerReconciliation;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public Boolean getBinaryMode() {
        return binaryMode;
    }

    public void setBinaryMode(Boolean binaryMode) {
        this.binaryMode = binaryMode;
    }

    public Object getCallForAuthorizeId() {
        return callForAuthorizeId;
    }

    public void setCallForAuthorizeId(Object callForAuthorizeId) {
        this.callForAuthorizeId = callForAuthorizeId;
    }

    public Boolean getCaptured() {
        return captured;
    }

    public void setCaptured(Boolean captured) {
        this.captured = captured;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
    }

    public Object getCounterCurrency() {
        return counterCurrency;
    }

    public void setCounterCurrency(Object counterCurrency) {
        this.counterCurrency = counterCurrency;
    }

    public Integer getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(String dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(String dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public Object getDateOfExpiration() {
        return dateOfExpiration;
    }

    public void setDateOfExpiration(Object dateOfExpiration) {
        this.dateOfExpiration = dateOfExpiration;
    }

    public Object getDeductionSchema() {
        return deductionSchema;
    }

    public void setDeductionSchema(Object deductionSchema) {
        this.deductionSchema = deductionSchema;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDifferentialPricingId() {
        return differentialPricingId;
    }

    public void setDifferentialPricingId(Object differentialPricingId) {
        this.differentialPricingId = differentialPricingId;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public List<Object> getFeeDetails() {
        return feeDetails;
    }

    public void setFeeDetails(List<Object> feeDetails) {
        this.feeDetails = feeDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public Boolean getLiveMode() {
        return liveMode;
    }

    public void setLiveMode(Boolean liveMode) {
        this.liveMode = liveMode;
    }

    public Object getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(Object merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public Object getMerchantNumber() {
        return merchantNumber;
    }

    public void setMerchantNumber(Object merchantNumber) {
        this.merchantNumber = merchantNumber;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getMoneyReleaseDate() {
        return moneyReleaseDate;
    }

    public void setMoneyReleaseDate(String moneyReleaseDate) {
        this.moneyReleaseDate = moneyReleaseDate;
    }

    public Object getMoneyReleaseSchema() {
        return moneyReleaseSchema;
    }

    public void setMoneyReleaseSchema(Object moneyReleaseSchema) {
        this.moneyReleaseSchema = moneyReleaseSchema;
    }

    public Object getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(Object notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public String getProcessingMode() {
        return processingMode;
    }

    public void setProcessingMode(String processingMode) {
        this.processingMode = processingMode;
    }

    public List<Object> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<Object> refunds) {
        this.refunds = refunds;
    }

    public Integer getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(Integer shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public Object getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Object sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getStatementDescriptor() {
        return statementDescriptor;
    }

    public void setStatementDescriptor(String statementDescriptor) {
        this.statementDescriptor = statementDescriptor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public Object getStoreId() {
        return storeId;
    }

    public void setStoreId(Object storeId) {
        this.storeId = storeId;
    }

    public Integer getTaxesAmount() {
        return taxesAmount;
    }

    public void setTaxesAmount(Integer taxesAmount) {
        this.taxesAmount = taxesAmount;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getTransactionAmountRefunded() {
        return transactionAmountRefunded;
    }

    public void setTransactionAmountRefunded(Integer transactionAmountRefunded) {
        this.transactionAmountRefunded = transactionAmountRefunded;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("acquirer", acquirer).append("acquirerReconciliation", acquirerReconciliation).append("additionalInfo", additionalInfo).append("authorizationCode", authorizationCode).append("binaryMode", binaryMode).append("callForAuthorizeId", callForAuthorizeId).append("captured", captured).append("card", card).append("collectorId", collectorId).append("counterCurrency", counterCurrency).append("couponAmount", couponAmount).append("currencyId", currencyId).append("dateApproved", dateApproved).append("dateCreated", dateCreated).append("dateLastUpdated", dateLastUpdated).append("dateOfExpiration", dateOfExpiration).append("deductionSchema", deductionSchema).append("description", description).append("differentialPricingId", differentialPricingId).append("externalReference", externalReference).append("feeDetails", feeDetails).append("id", id).append("installments", installments).append("issuerId", issuerId).append("liveMode", liveMode).append("merchantAccountId", merchantAccountId).append("merchantNumber", merchantNumber).append("metadata", metadata).append("moneyReleaseDate", moneyReleaseDate).append("moneyReleaseSchema", moneyReleaseSchema).append("notificationUrl", notificationUrl).append("operationType", operationType).append("order", order).append("payer", payer).append("paymentMethodId", paymentMethodId).append("paymentTypeId", paymentTypeId).append("posId", posId).append("processingMode", processingMode).append("refunds", refunds).append("shippingAmount", shippingAmount).append("sponsorId", sponsorId).append("statementDescriptor", statementDescriptor).append("status", status).append("statusDetail", statusDetail).append("storeId", storeId).append("taxesAmount", taxesAmount).append("transactionAmount", transactionAmount).append("transactionAmountRefunded", transactionAmountRefunded).append("transactionDetails", transactionDetails).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(moneyReleaseSchema).append(statementDescriptor).append(captured).append(processingMode).append(moneyReleaseDate).append(liveMode).append(description).append(dateLastUpdated).append(card).append(posId).append(paymentMethodId).append(dateOfExpiration).append(metadata).append(status).append(differentialPricingId).append(transactionDetails).append(installments).append(issuerId).append(taxesAmount).append(operationType).append(merchantNumber).append(dateApproved).append(sponsorId).append(refunds).append(storeId).append(couponAmount).append(paymentTypeId).append(currencyId).append(externalReference).append(binaryMode).append(merchantAccountId).append(feeDetails).append(acquirer).append(callForAuthorizeId).append(authorizationCode).append(notificationUrl).append(id).append(order).append(statusDetail).append(collectorId).append(transactionAmount).append(transactionAmountRefunded).append(additionalInfo).append(shippingAmount).append(payer).append(counterCurrency).append(acquirerReconciliation).append(deductionSchema).append(dateCreated).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Payment) == false) {
            return false;
        }
        Payment rhs = ((Payment) other);
        return new EqualsBuilder().append(moneyReleaseSchema, rhs.moneyReleaseSchema).append(statementDescriptor, rhs.statementDescriptor).append(captured, rhs.captured).append(processingMode, rhs.processingMode).append(moneyReleaseDate, rhs.moneyReleaseDate).append(liveMode, rhs.liveMode).append(description, rhs.description).append(dateLastUpdated, rhs.dateLastUpdated).append(card, rhs.card).append(posId, rhs.posId).append(paymentMethodId, rhs.paymentMethodId).append(dateOfExpiration, rhs.dateOfExpiration).append(metadata, rhs.metadata).append(status, rhs.status).append(differentialPricingId, rhs.differentialPricingId).append(transactionDetails, rhs.transactionDetails).append(installments, rhs.installments).append(issuerId, rhs.issuerId).append(taxesAmount, rhs.taxesAmount).append(operationType, rhs.operationType).append(merchantNumber, rhs.merchantNumber).append(dateApproved, rhs.dateApproved).append(sponsorId, rhs.sponsorId).append(refunds, rhs.refunds).append(storeId, rhs.storeId).append(couponAmount, rhs.couponAmount).append(paymentTypeId, rhs.paymentTypeId).append(currencyId, rhs.currencyId).append(externalReference, rhs.externalReference).append(binaryMode, rhs.binaryMode).append(merchantAccountId, rhs.merchantAccountId).append(feeDetails, rhs.feeDetails).append(acquirer, rhs.acquirer).append(callForAuthorizeId, rhs.callForAuthorizeId).append(authorizationCode, rhs.authorizationCode).append(notificationUrl, rhs.notificationUrl).append(id, rhs.id).append(order, rhs.order).append(statusDetail, rhs.statusDetail).append(collectorId, rhs.collectorId).append(transactionAmount, rhs.transactionAmount).append(transactionAmountRefunded, rhs.transactionAmountRefunded).append(additionalInfo, rhs.additionalInfo).append(shippingAmount, rhs.shippingAmount).append(payer, rhs.payer).append(counterCurrency, rhs.counterCurrency).append(acquirerReconciliation, rhs.acquirerReconciliation).append(deductionSchema, rhs.deductionSchema).append(dateCreated, rhs.dateCreated).isEquals();
    }

}
