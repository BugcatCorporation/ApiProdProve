package pe.bugcatcorporation.ApiProducto.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pe.bugcatcorporation.ApiProducto.dao.ProductoRepository;
import pe.bugcatcorporation.ApiProducto.entity.Producto;

@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    private ProductoRepository pr;

    @Override
    public List<Producto> findAll() {
        try {
            log.info("Obteniendo todos los productos");
            return pr.findAll();
        } catch (Exception e) {
            log.error("Error al obtener todos los productos", e);
            throw e;
        }
    }

    @Override
    public Page<Producto> findPage(int page, int size) {
        try {
            log.info("Obteniendo página {} de productos con tamaño {}", page, size);
            return pr.findAll(PageRequest.of(page, size));
        } catch (Exception e) {
            log.error("Error al obtener la página de productos", e);
            throw e;
        }
    }

    @Override
    public Producto findById(Long id) {
        try {
            log.info("Buscando producto por ID: {}", id);
            return pr.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con la id: " + id.toString()));
        } catch (Exception e) {
            log.error("Error al buscar el producto por ID: {}", id, e);
            throw e;
        }
    }

    @Override
    public Producto findByNombreProducto(String nombreProducto) {
        try {
            log.info("Buscando producto por nombre: {}", nombreProducto);
            return pr.findByNombreProducto(nombreProducto);
        } catch (Exception e) {
            log.error("Error al buscar el producto por nombre: {}", nombreProducto, e);
            throw e;
        }
    }

    @Override
    public Producto add(Producto producto) {
        try {
            log.info("Agregando nuevo producto: {}", producto);
            return pr.save(producto);
        } catch (Exception e) {
            log.error("Error al agregar nuevo producto", e);
            throw e;
        }
    }

    @Override
    public Producto update(Producto producto) {
        try {
            log.info("Actualizando producto: {}", producto);
            var productoDB = pr.findById(producto.getId()).get();
            productoDB.setNombreProducto(producto.getNombreProducto());
            productoDB.setDesripcion(producto.getDesripcion());
            productoDB.setStock(producto.getStock());
            productoDB.setPrecio(producto.getPrecio());
            return pr.save(productoDB);
        } catch (Exception e) {
            log.error("Error al actualizar el producto", e);
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            log.info("Eliminando producto con ID: {}", id);
            var productoDB = pr.findById(id).get();
            pr.delete(productoDB);
        } catch (Exception e) {
            log.error("Error al eliminar el producto", e);
            throw e;
        }
    }

    @Override
    public Producto findByProductoSK(String productoSK) {
        try {
            log.info("Buscando producto por SKU: {}", productoSK);
            return pr.findByProductoSK(productoSK);
        } catch (Exception e) {
            log.error("Error al buscar el producto por SKU: {}", productoSK, e);
            throw e;
        }
    }
}
