package com.uan.optica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "historiaclinica")
public class Historiaclinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idhistoriaclinica;
}
