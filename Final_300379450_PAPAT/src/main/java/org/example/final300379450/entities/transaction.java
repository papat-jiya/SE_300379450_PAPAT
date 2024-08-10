package org.example.final300379450.entities;
//--------------------------------------------
//Papat Jiyaworanant
//300379450

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class transaction {
    //transaction data structure : id, amount, dot(date of transaction, item, name)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private Date dot;
    private String item;
    private String name;
}

