package com.polytech4a.piste.dao;

import com.polytech4a.piste.beans.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexandre
 *         08/06/2015
 */
@Transactional
public interface MissionDAO extends JpaRepository<Mission, Integer> {
    List<Mission> findMissionsByNumjeu(Integer numJeu);
}
