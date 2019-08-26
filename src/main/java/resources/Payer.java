
package resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Payer {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("entity_type")
    @Expose
    private Object entityType;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identification")
    @Expose
    private Identification_ identification;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("operator_id")
    @Expose
    private Object operatorId;
    @SerializedName("phone")
    @Expose
    private Phone phone;
    @SerializedName("type")
    @Expose
    private String type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getEntityType() {
        return entityType;
    }

    public void setEntityType(Object entityType) {
        this.entityType = entityType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Identification_ getIdentification() {
        return identification;
    }

    public void setIdentification(Identification_ identification) {
        this.identification = identification;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Object operatorId) {
        this.operatorId = operatorId;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("email", email).append("entityType", entityType).append("firstName", firstName).append("id", id).append("identification", identification).append("lastName", lastName).append("operatorId", operatorId).append("phone", phone).append("type", type).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(lastName).append(phone).append(operatorId).append(email).append(identification).append(type).append(firstName).append(entityType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Payer) == false) {
            return false;
        }
        Payer rhs = ((Payer) other);
        return new EqualsBuilder().append(id, rhs.id).append(lastName, rhs.lastName).append(phone, rhs.phone).append(operatorId, rhs.operatorId).append(email, rhs.email).append(identification, rhs.identification).append(type, rhs.type).append(firstName, rhs.firstName).append(entityType, rhs.entityType).isEquals();
    }

}
