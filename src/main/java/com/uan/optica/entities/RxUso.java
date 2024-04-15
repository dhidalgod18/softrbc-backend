package com.uan.optica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "rxuso")

public class RxUso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrxuso;
    private String od;
    private String oi;
    private String addicion;
    private int idhistoriaclinica;
    String fecha;
    


}
