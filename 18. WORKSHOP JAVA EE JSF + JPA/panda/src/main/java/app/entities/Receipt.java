package app.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receipts")
public class Receipt extends UUIDEntity {

    @Column(name = "fee", nullable = false)
    private BigDecimal fee;

    @Column(name = "issued_on")
    private LocalDate issuedOn;

    @ManyToOne
    @JoinColumn(name = "recepient_id", referencedColumnName = "uuid")
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "uuid")
    private Package packageName;

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public LocalDate getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDate issuedOn) {
        this.issuedOn = issuedOn;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Package getPackageName() {
        return packageName;
    }

    public void setPackageName(Package packageName) {
        this.packageName = packageName;
    }
}
