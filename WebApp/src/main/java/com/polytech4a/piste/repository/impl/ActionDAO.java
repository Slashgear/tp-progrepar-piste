package com.polytech4a.piste.repository.impl;

import com.polytech4a.piste.beans.Action;
import com.polytech4a.piste.repository.IActionDAO;
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
public class ActionDAO implements IActionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Action> findAll() {
        final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Action> lCriteriaQuery = lCriteriaBuilder.createQuery(Action.class);
        final Root<Action> lRoot = lCriteriaQuery.from(Action.class);
        lCriteriaQuery.select(lRoot);
        final TypedQuery<Action> lTypedQuery = entityManager.createQuery(lCriteriaQuery);
        return lTypedQuery.getResultList();
    }
}
