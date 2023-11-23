package com.digital.art.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {

    @Column(name = "iid")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "code")
    private String itemCode;

    @Column(name = "name")
    private String itemName;

    @Column(name = "filename")
    private String filename;

    @Lob
    private byte[] content;
}
