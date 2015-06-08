package com.polytech4a.piste.repository.impl;

import com.polytech4a.piste.beans.Apprenant;
import com.polytech4a.piste.repository.IApprenantDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Antoine CARON on 04/06/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
@Repository
public class ApprenantDAO implements IApprenantDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Apprenant> findAll() {
        final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Apprenant> lCriteriaQuery = lCriteriaBuilder.createQuery(Apprenant.class);
        final Root<Apprenant> lRoot = lCriteriaQuery.from(Apprenant.class);
        lCriteriaQuery.select(lRoot);
        final TypedQuery<Apprenant> lTypedQuery = entityManager.createQuery(lCriteriaQuery);
        return lTypedQuery.getResultList();
    }

    /**
     * Persist an Apprenant.
     *
     * @param apprenant
     */
    @Override
    public void createApprenant(Apprenant apprenant) {
        entityManager.persist(apprenant);
    }

    @Override
    public Apprenant findById(int id) {
        final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Apprenant> lCriteriaQuery = lCriteriaBuilder.createQuery(Apprenant.class);
        final Root<Apprenant> lRoot = lCriteriaQuery.from(Apprenant.class);
        lCriteriaQuery.select(lRoot).where(lCriteriaBuilder.equal(lRoot.get("numapprenant"), id));
        final TypedQuery<Apprenant> lTypedQuery = entityManager.createQuery(lCriteriaQuery);
        List<Apprenant> resultList = lTypedQuery.getResultList();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }
}
