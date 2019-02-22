package app.entities;

import app.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "packages")
public class Package extends UUIDEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "shipping_address", nullable = false)
    @NotNull
    private String shippingAddress;

    @Column(name = "status", nullable = false)
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;

    @ManyToOne
    @JoinColumn(name = "recepient_id", referencedColumnName = "uuid")
    private User recipient;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}

