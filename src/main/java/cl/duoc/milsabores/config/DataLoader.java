package cl.duoc.milsabores.config;

import cl.duoc.milsabores.model.Producto;
import cl.duoc.milsabores.model.Rol;
import cl.duoc.milsabores.model.Usuario;
import cl.duoc.milsabores.repository.ProductoRepository;
import cl.duoc.milsabores.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public DataLoader(ProductoRepository productoRepository, UsuarioRepository usuarioRepository) {
        this.productoRepository = productoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        // 1. Cargar los 3 usuarios base si no existen
        if (usuarioRepository.count() == 0) {
            usuarioRepository.saveAll(List.of(
                    new Usuario(null, "Admin Mil Sabores", "admin@milsabores.cl", "admin123", Rol.ADMIN),
                    new Usuario(null, "Vendedor Mil Sabores", "vendedor@milsabores.cl", "vendedor123", Rol.VENDEDOR),
                    new Usuario(null, "Cliente Mil Sabores", "cliente@milsabores.cl", "cliente123", Rol.CLIENTE)
            ));
        }

        // 2. Cargar tus 8 tortas exactas de inventarioInicial
        if (productoRepository.count() == 0) {
            productoRepository.saveAll(List.of(
                    new Producto(null, "AriLu", "Una torta de red velvet con cerezas y un toque de flor de durazno.", "/img/torta-arilu.jpg", "circular", 48000, 10),
                    new Producto(null, "Torta Cuadrada de Chocolate", "Bizcocho de chocolate intenso, relleno de crema de chocolate y trufa, cubierta de fondant.", "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=500&q=80", "cuadrada", 45000, 10),
                    new Producto(null, "Torta Circular de Vainilla", "Torta clásica con bizcocho de vainilla, relleno a elección y cubierta de crema chantilly.", "https://images.unsplash.com/photo-1464349095431-e9a21285b5f3?w=500&q=80", "circular", 40000, 10),
                    new Producto(null, "Torta de Manjar y Nuez", "El clásico sabor chileno. Bizcocho de nuez con abundante manjar y nueces picadas.", "/img/torta-manjar.jpg", "circular", 42000, 10),
                    new Producto(null, "Torta Vegana de Semillas", "Bizcocho preparado con leche vegetal de linaza y sésamo, incorporando semillas de maravilla tostadas.", "https://images.unsplash.com/photo-1571115177098-24ec42ed204d?w=500&q=80", "cuadrada", 43000, 10),
                    new Producto(null, "Torta Mi Zoe 🧡", "Bizcocho suave relleno de crema de fresas y frutos rojos, diseñada con un perfil dulce y colorido.", "https://images.unsplash.com/photo-1565958011703-44f9829ba187?w=500&q=80", "circular", 46000, 10),
                    new Producto(null, "Torta Tres Leches", "Clásico bizcocho esponjoso bañado en nuestra mezcla artesanal de tres leches, cubierta con un suave merengue suizo.", "/img/torta-tresleche.jpg", "Cuadrada", 30000, 10),
                    new Producto(null, "Torta Besos de Mora", "Esponjoso bizcocho de vainilla relleno con crema pastelera y una reducción artesanal de moras silvestres, cubierta con crema chantilly y fruta fresca.", "/img/torta-mora.jpg", "Cuadrada", 17500, 10)
            ));
        }
    }
}