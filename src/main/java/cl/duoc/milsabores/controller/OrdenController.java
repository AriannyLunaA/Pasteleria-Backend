package cl.duoc.milsabores.controller;

import cl.duoc.milsabores.model.Orden;
import cl.duoc.milsabores.service.OrdenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Órdenes de Compra", description = "Registro de ventas desde el Checkout y actualización de estados de despacho")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    @Operation(summary = "Listar todas las órdenes", description = "Obtiene el historial completo de pedidos realizados en la pastelería.")
    @ApiResponse(responseCode = "200", description = "Lista de órdenes obtenida exitosamente")
    public List<Orden> listar() {
        return ordenService.obtenerTodas();
    }

    @PostMapping
    @Operation(summary = "Crear nueva orden de compra", description = "Registra un nuevo pedido generado desde el Checkout con fecha actual y estado Pendiente.")
    @ApiResponse(responseCode = "201", description = "Orden registrada correctamente")
    public ResponseEntity<Orden> crear(@RequestBody Orden orden) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenService.crear(orden));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de orden", description = "Cambia el estado del pedido (Pendiente, En Preparación, Entregado, Cancelado).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado de la orden actualizado"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    public ResponseEntity<Orden> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ordenService.actualizarEstado(id, body.get("estado"))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}