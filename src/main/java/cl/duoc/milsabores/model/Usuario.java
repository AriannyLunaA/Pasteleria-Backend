package cl.duoc.milsabores.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String nombre;

        @Column(nullable = false, unique = true)
        private String correo;

        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Rol rol; // Guarda "ADMIN", "VENDEDOR" o "CLIENTE" en MySQL
    }

