package org.example.automaticed.entity;

import jakarta.persistence.*;
import lombok.Data;
// import org.example.automaticed.service.SensitiveConverter;
import org.example.automaticed.service.AESSensitiveConverter;

import java.io.Serializable;

/**
 * Entity class representing the structure of the 't_order' table in the database.
 * This class manages the automatic encryption and decryption of sensitive fields using the
 * SensitiveConverter class.
 *
 * Done by Yan
 */
@Data
@Table(name = "t_order")
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer"}) // Uncomment if needed for lazy loading
@Entity
public class OrderEntity implements Serializable {

    /**
     * Primary key for the 't_order' table, representing the order ID.
     * This field is auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    /**
     * Represents the user ID associated with this order.
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * Sensitive field representing the user's name.
     * This field is automatically encrypted and decrypted using the SensitiveConverter.
     */
    @Column(name = "user_name")
    @Convert(converter = AESSensitiveConverter.class) // Automatically applies encryption/decryption
    private String userName;

    /**
     * Represents the name of the order.
     */
    @Column(name = "order_name")
    private String orderName;

    // Lombok @Data annotation automatically generates getters and setters for all fields.
    // Below are custom getter and setter methods in case future validation or processing logic is needed.

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        // This method sets the user's name. Future validation logic can be added here if necessary.
        this.userName = userName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    // Additional methods for validation or processing can be added here if needed in the future.
}