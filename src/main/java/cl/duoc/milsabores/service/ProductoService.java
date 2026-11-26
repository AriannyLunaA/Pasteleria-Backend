package cl.duoc.milsabores.service;

import cl.duoc.milsabores.model.Producto;
import cl.duoc.milsabores.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizar(Long id, Producto detalles) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(detalles.getNombre());
            producto.setDescripcion(detalles.getDescripcion());
            producto.setImagen(detalles.getImagen());
            producto.setCategoria(detalles.getCategoria());
            producto.setPrecio(detalles.getPrecio());
            producto.setStock(detalles.getStock());
            return productoRepository.save(producto);
        });
    }

    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}