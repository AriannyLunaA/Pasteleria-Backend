package cl.duoc.milsabores.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private Integer total;

    @Column(nullable = false)
    private String estado; // "Pendiente", "Preparando", "Entregado", "Cancelada"

    @Column(length = 1000)
    private String items;
}