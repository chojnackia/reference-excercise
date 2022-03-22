package com.example.referenceexcercise.module.booking;

import com.example.referenceexcercise.module.apartment.Apartment;
import com.example.referenceexcercise.module.owner.Owner;
import com.example.referenceexcercise.module.tenant.Tenant;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "BOOKINGS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JoinColumn(name = "apartment_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Apartment apartment;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Owner owner;

    @JoinColumn(name = "tenant_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Tenant tenant;

    private long price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        return new EqualsBuilder().append(price, booking.price).append(id, booking.id).append(apartment, booking.apartment).append(checkInDate, booking.checkInDate).append(checkOutDate, booking.checkOutDate).append(owner, booking.owner).append(tenant, booking.tenant).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(apartment).append(checkInDate).append(checkOutDate).append(owner).append(tenant).append(price).toHashCode();
    }
}
