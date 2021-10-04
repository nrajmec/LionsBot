package com.example.demo.model;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.UUIDBinaryType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="shipments")
public class Shipment {
    @Id
    @Column(name="shipment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipment_id;
    private int order_id;
    private Date shipment_date;
    private UUID method;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "order_id", nullable = false, insertable=false, updatable=false)
    private Order order;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getShipment_id() {
        return shipment_id;
    }

    public void setShipment_id(int shipment_id) {
        this.shipment_id = shipment_id;
    }

    public Date getShipment_date() {
        return shipment_date;
    }

    public void setShipment_date(Date shipment_date) {
        this.shipment_date = shipment_date;
    }

    public UUID getMethod() {
        return method;
    }

    public void setMethod(UUID method) {
        this.method = method;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
