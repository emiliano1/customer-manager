package com.test.project.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Column(columnDefinition = "text")
    private String bio;

    public Customer() {

    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
