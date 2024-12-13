package com.example.venta.controller;
import com.example.venta.entity.Venta;
import com.example.venta.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> obtenerTodasLasVentas() {
        return ventaService.obtenerTodasLasVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        return (venta != null) ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Venta crearVenta(@RequestBody Venta venta) {
        return ventaService.guardarVenta(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long id, @RequestBody Venta ventaDetalles) {
        Venta ventaExistente = ventaService.obtenerVentaPorId(id);
        if (ventaExistente != null) {
            ventaExistente.setFecha(ventaDetalles.getFecha());
            ventaExistente.setMonto(ventaDetalles.getMonto());
            ventaExistente.setDescripcion(ventaDetalles.getDescripcion());
            return ResponseEntity.ok(ventaService.guardarVenta(ventaExistente));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        if (venta != null) {
            ventaService.eliminarVenta(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
