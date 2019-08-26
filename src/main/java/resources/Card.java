
package resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Card {

    @SerializedName("cardholder")
    @Expose
    private Cardholder cardholder;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_last_updated")
    @Expose
    private String dateLastUpdated;
    @SerializedName("expiration_month")
    @Expose
    private Integer expirationMonth;
    @SerializedName("expiration_year")
    @Expose
    private Integer expirationYear;
    @SerializedName("first_six_digits")
    @Expose
    private String firstSixDigits;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("last_four_digits")
    @Expose
    private String lastFourDigits;

    public Cardholder getCardholder() {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
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

    public Integer getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Integer expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Integer getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Integer expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getFirstSixDigits() {
        return firstSixDigits;
    }

    public void setFirstSixDigits(String firstSixDigits) {
        this.firstSixDigits = firstSixDigits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cardholder", cardholder).append("dateCreated", dateCreated).append("dateLastUpdated", dateLastUpdated).append("expirationMonth", expirationMonth).append("expirationYear", expirationYear).append("firstSixDigits", firstSixDigits).append("id", id).append("lastFourDigits", lastFourDigits).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(firstSixDigits).append(expirationYear).append(expirationMonth).append(dateLastUpdated).append(lastFourDigits).append(dateCreated).append(cardholder).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Card) == false) {
            return false;
        }
        Card rhs = ((Card) other);
        return new EqualsBuilder().append(id, rhs.id).append(firstSixDigits, rhs.firstSixDigits).append(expirationYear, rhs.expirationYear).append(expirationMonth, rhs.expirationMonth).append(dateLastUpdated, rhs.dateLastUpdated).append(lastFourDigits, rhs.lastFourDigits).append(dateCreated, rhs.dateCreated).append(cardholder, rhs.cardholder).isEquals();
    }

}
