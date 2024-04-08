package com.practiceistad.mvc_taskwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 30,name = "category_name",unique = true)
    private String name;
    @Column(columnDefinition = "TEXT",name = "category_description")
    private String description;
}
