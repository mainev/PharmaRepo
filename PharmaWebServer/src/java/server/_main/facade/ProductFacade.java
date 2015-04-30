/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server._main.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server._main.entity.Product;

/**
 *
 * @author Maine
 */
@Stateless
public class ProductFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createQuery("select p from Product p").getResultList();
    }
/*
    public Product findByProductCode(String code) {
        return em.find(Product.class, code);
    }
    */
    public Product findById(int id){
        return em.find(Product.class, id);
    }
}
