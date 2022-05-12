package com.example.repositorys;

import com.example.model.entities.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AL_I01971
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
    @Query("SELECT t FROM #{#entityName} t "
            + " WHERE t.type = :type")
    public List<Producto> findByType(String type);
    
    @Query("SELECT t FROM #{#entityName} t "
            + " WHERE t.parentId = :parentId")
    public List<Producto> findByParentId(Long parentId);
}
