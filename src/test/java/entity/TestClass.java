package entity;

import annotations.Column;
import annotations.Table;

@Table("test")
public class TestClass {
    @Column(value = "id" , isPK=true)
    int a;
}
