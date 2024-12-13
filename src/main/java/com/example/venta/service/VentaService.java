package com.example.venta.service;
import com.example.venta.entity.Venta;
import com.example.venta.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
