/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import server._main.entity.ProductWithPackSize;
import server.mbr.entity.MBR;

/**
 *
 * @author maine
 */
@Stateless
public class MBRFacade {

    @PersistenceContext(unitName = "PharmaWebServerPU")
    private EntityManager em;

    public List<MBR> findAll() {
        return em.createQuery("select m from MBR m order by m.id desc").getResultList();
    }

    public void create(int productId, int packSizeId, MBR mbr) {
        List<ProductWithPackSize> l = em.createQuery("select p from ProductWithPackSize p where p.packagingSizeId.id = :packaging_size_id and p.productId.id = :product_id")
                .setParameter("product_id", productId)
                .setParameter("packaging_size_id", packSizeId)
                .getResultList();
        ProductWithPackSize productWithPackSize = l.get(0);
        mbr.setProductWithPackSizeId(productWithPackSize);
        em.persist(mbr);
    }
}
