package cl.duoc.milsabores.service;

import cl.duoc.milsabores.model.Orden;
import cl.duoc.milsabores.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  cl.duoc.milsabores.service.OrdenService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> obtenerTodas() {
        return ordenRepository.findAll();
    }

    public Orden crear(Orden orden) {
        if (orden.getFecha() == null || orden.getFecha().isEmpty()) {
            orden.setFecha(LocalDate.now().toString());
        }
        if (orden.getEstado() == null || orden.getEstado().isEmpty()) {
            orden.setEstado("Pendiente");
        }
        return ordenRepository.save(orden);
    }

    public Optional<Orden> actualizarEstado(Long id, String nuevoEstado) {
        return ordenRepository.findById(id).map(orden -> {
            orden.setEstado(nuevoEstado);
            return ordenRepository.save(orden);
        });
    }
}
