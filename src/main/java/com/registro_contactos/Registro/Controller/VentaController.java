package com.registro_contactos.Registro.Controller;

import com.registro_contactos.Registro.DTO.Request.VentaCancelacionRequest;
import com.registro_contactos.Registro.DTO.Request.VentaRequest;
import com.registro_contactos.Registro.DTO.Response.VentaResponse;
import com.registro_contactos.Registro.Service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "http://localhost:3005")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    @Operation(description = "Registrar la venta del cliente")
    public ResponseEntity<VentaResponse> registrarVenta(@RequestBody VentaRequest ventaRequest){
        VentaResponse ventaResponse = ventaService.registrarVenta(ventaRequest);
        return ResponseEntity.ok(ventaResponse);
    }

    @GetMapping("/ventas_realizadas")
    @Operation(description = "Lista de ventas realizadas")
    public ResponseEntity<List<VentaResponse>> listaVentasRealizadas(){
        return ResponseEntity.ok(ventaService.listarVentasRealizadas());
    }

    @PutMapping("/cancelarVenta")
    @Operation(description = "Cancelar venta por DNI y fecha de compra")
    public ResponseEntity<VentaResponse> cancelarVenta(@RequestBody VentaCancelacionRequest ventaCancelacionRequest){
        return ResponseEntity.ok(ventaService.cancelarVenta(ventaCancelacionRequest));
    }
}
