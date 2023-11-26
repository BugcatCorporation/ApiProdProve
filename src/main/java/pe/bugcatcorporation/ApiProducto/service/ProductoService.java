
package pe.bugcatcorporation.ApiProducto.service;

import java.util.List;
import org.springframework.data.domain.Page;
import pe.bugcatcorporation.ApiProducto.entity.Producto;

public interface ProductoService {
    
    public List<Producto> findAll();
    public Page<Producto> findPage(int page, int size);
    public Producto findById(Long id);
    public Producto findByNombreProducto(String nombreProducto);
    public Producto findByProductoSK(String productoSK);
    public Producto add(Producto producto);
    public Producto update(Producto producto);
    public void delete(Long id);
    
}
