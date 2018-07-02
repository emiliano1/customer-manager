package com.test.project.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@Getter
@Setter
@EqualsAndHashCode
public class Authority implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String authority;

    public Authority() {

    }
}
