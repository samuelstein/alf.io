/**
 * This file is part of bagarino.
 *
 * bagarino is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * bagarino is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with bagarino.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.bagarino.model.modification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.bagarino.manager.EventManager;
import io.bagarino.model.transaction.PaymentProxy;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
public class EventModification {

    private final Integer id;
    private final String shortName;
    private final int organizationId;
    private final String location;
    private final String description;
    private final DateTimeModification begin;
    private final DateTimeModification end;
    private final BigDecimal price;
    private final String currency;
    private final int availableSeats;
    private final BigDecimal vat;
    private final boolean vatIncluded;
    private final List<PaymentProxy> allowedPaymentProxies;
    private final List<TicketCategoryModification> ticketCategories;
    private final boolean freeOfCharge;

    @JsonCreator
    public EventModification(@JsonProperty("id") Integer id,
                             @JsonProperty("shortName") String shortName,
                             @JsonProperty("organizationId") int organizationId,
                             @JsonProperty("location") String location,
                             @JsonProperty("description") String description,
                             @JsonProperty("begin") DateTimeModification begin,
                             @JsonProperty("end") DateTimeModification end,
                             @JsonProperty("regularPrice") BigDecimal regularPrice,
                             @JsonProperty("currency") String currency,
                             @JsonProperty("availableSeats") int availableSeats,
                             @JsonProperty("vat") BigDecimal vat,
                             @JsonProperty("vatIncluded") boolean vatIncluded,
                             @JsonProperty("allowedPaymentProxies") List<PaymentProxy> allowedPaymentProxies,
                             @JsonProperty("ticketCategories") List<TicketCategoryModification> ticketCategories,
                             @JsonProperty("freeOfCharge") boolean freeOfCharge) {
        this.id = id;
        this.shortName = shortName;
        this.organizationId = organizationId;
        this.location = location;
        this.description = description;
        this.begin = begin;
        this.end = end;
        this.price = regularPrice;
        this.currency = currency;
        this.availableSeats = availableSeats;
        this.vat = vat;
        this.vatIncluded = vatIncluded;
        this.allowedPaymentProxies = Optional.ofNullable(allowedPaymentProxies).orElse(Collections.<PaymentProxy>emptyList());
        this.ticketCategories = ticketCategories;
        this.freeOfCharge = freeOfCharge;
    }

    public int getPriceInCents() {
        return price.multiply(EventManager.HUNDRED).intValueExact();
    }
}
