
package resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TransactionDetails {

    @SerializedName("acquirer_reference")
    @Expose
    private Object acquirerReference;
    @SerializedName("external_resource_url")
    @Expose
    private Object externalResourceUrl;
    @SerializedName("financial_institution")
    @Expose
    private Object financialInstitution;
    @SerializedName("installment_amount")
    @Expose
    private Integer installmentAmount;
    @SerializedName("net_received_amount")
    @Expose
    private Integer netReceivedAmount;
    @SerializedName("overpaid_amount")
    @Expose
    private Integer overpaidAmount;
    @SerializedName("payable_deferral_period")
    @Expose
    private Object payableDeferralPeriod;
    @SerializedName("payment_method_reference_id")
    @Expose
    private String paymentMethodReferenceId;
    @SerializedName("total_paid_amount")
    @Expose
    private Integer totalPaidAmount;

    public Object getAcquirerReference() {
        return acquirerReference;
    }

    public void setAcquirerReference(Object acquirerReference) {
        this.acquirerReference = acquirerReference;
    }

    public Object getExternalResourceUrl() {
        return externalResourceUrl;
    }

    public void setExternalResourceUrl(Object externalResourceUrl) {
        this.externalResourceUrl = externalResourceUrl;
    }

    public Object getFinancialInstitution() {
        return financialInstitution;
    }

    public void setFinancialInstitution(Object financialInstitution) {
        this.financialInstitution = financialInstitution;
    }

    public Integer getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(Integer installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public Integer getNetReceivedAmount() {
        return netReceivedAmount;
    }

    public void setNetReceivedAmount(Integer netReceivedAmount) {
        this.netReceivedAmount = netReceivedAmount;
    }

    public Integer getOverpaidAmount() {
        return overpaidAmount;
    }

    public void setOverpaidAmount(Integer overpaidAmount) {
        this.overpaidAmount = overpaidAmount;
    }

    public Object getPayableDeferralPeriod() {
        return payableDeferralPeriod;
    }

    public void setPayableDeferralPeriod(Object payableDeferralPeriod) {
        this.payableDeferralPeriod = payableDeferralPeriod;
    }

    public String getPaymentMethodReferenceId() {
        return paymentMethodReferenceId;
    }

    public void setPaymentMethodReferenceId(String paymentMethodReferenceId) {
        this.paymentMethodReferenceId = paymentMethodReferenceId;
    }

    public Integer getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(Integer totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("acquirerReference", acquirerReference).append("externalResourceUrl", externalResourceUrl).append("financialInstitution", financialInstitution).append("installmentAmount", installmentAmount).append("netReceivedAmount", netReceivedAmount).append("overpaidAmount", overpaidAmount).append("payableDeferralPeriod", payableDeferralPeriod).append("paymentMethodReferenceId", paymentMethodReferenceId).append("totalPaidAmount", totalPaidAmount).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(overpaidAmount).append(externalResourceUrl).append(acquirerReference).append(totalPaidAmount).append(netReceivedAmount).append(installmentAmount).append(paymentMethodReferenceId).append(payableDeferralPeriod).append(financialInstitution).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TransactionDetails) == false) {
            return false;
        }
        TransactionDetails rhs = ((TransactionDetails) other);
        return new EqualsBuilder().append(overpaidAmount, rhs.overpaidAmount).append(externalResourceUrl, rhs.externalResourceUrl).append(acquirerReference, rhs.acquirerReference).append(totalPaidAmount, rhs.totalPaidAmount).append(netReceivedAmount, rhs.netReceivedAmount).append(installmentAmount, rhs.installmentAmount).append(paymentMethodReferenceId, rhs.paymentMethodReferenceId).append(payableDeferralPeriod, rhs.payableDeferralPeriod).append(financialInstitution, rhs.financialInstitution).isEquals();
    }

}
