package entity;

import annotations.Column;
import annotations.Table;
import enums.DBEngine;

@Table(value = "test")
public class TestClass {
    @Column(value = "id" , isPK=true)
    int a;

    double b;
}
