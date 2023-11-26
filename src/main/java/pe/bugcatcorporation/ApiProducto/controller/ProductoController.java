package pe.bugcatcorporation.ApiProducto.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.bugcatcorporation.ApiProducto.entity.Producto;
import pe.bugcatcorporation.ApiProducto.service.ProductoService;

@RestController
@RequestMapping("api/v1/producto")
@Slf4j
public class ProductoController {
    
    @Autowired
    private ProductoService ps;
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Producto>> findAll() {
        try {
            log.info("Obteniendo todos los productos");
            return new ResponseEntity<>(ps.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al obtener todos los productos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findPage/page/{page}/size/{size}")
    public ResponseEntity<Page<Producto>> findPage(@PathVariable int page, @PathVariable int size) {
        try {
            log.info("Obteniendo página {} de productos con tamaño {}", page, size);
            return new ResponseEntity<>(ps.findPage(page, size), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al obtener la página de productos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }   
    
    @GetMapping("/findById/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id) {
        try {
            log.info("Buscando producto por ID: {}", id);
            return new ResponseEntity<>(ps.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al buscar el producto por ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByProductoSK/{productoSK}")
    public ResponseEntity<Producto> findByProductoSK(@PathVariable String productoSK) {
        try {
            log.info("Buscando producto por SKU: {}", productoSK);
            return new ResponseEntity<>(ps.findByProductoSK(productoSK), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al buscar el producto por SKU: {}", productoSK, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreProducto/{nombreProducto}")
    public ResponseEntity<Producto> findByNombreProducto(@PathVariable String nombreProducto) {
        try {
            log.info("Buscando producto por nombre: {}", nombreProducto);
            return new ResponseEntity<>(ps.findByNombreProducto(nombreProducto), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al buscar el producto por nombre: {}", nombreProducto, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<Producto> add(@RequestBody Producto producto) {
        try {
            log.info("Agregando nuevo producto: {}", producto);
            return new ResponseEntity<>(ps.add(producto), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error al agregar nuevo producto", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        try {
            log.info("Actualizando producto: {}", producto);
            return new ResponseEntity<>(ps.update(producto), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al actualizar el producto", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            log.info("Eliminando producto con ID: {}", id);
            ps.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error al eliminar el producto", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
