
package resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Phone {

    @SerializedName("area_code")
    @Expose
    private String areaCode;
    @SerializedName("extension")
    @Expose
    private Object extension;
    @SerializedName("number")
    @Expose
    private String number;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("areaCode", areaCode).append("extension", extension).append("number", number).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(extension).append(areaCode).append(number).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Phone) == false) {
            return false;
        }
        Phone rhs = ((Phone) other);
        return new EqualsBuilder().append(extension, rhs.extension).append(areaCode, rhs.areaCode).append(number, rhs.number).isEquals();
    }

}
