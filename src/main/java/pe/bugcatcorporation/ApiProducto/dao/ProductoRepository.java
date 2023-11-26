
package pe.bugcatcorporation.ApiProducto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.bugcatcorporation.ApiProducto.entity.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    Producto findByNombreProducto (String nombreProducto);
    Producto findByProductoSK (String productoSK);
    
}
