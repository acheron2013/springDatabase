/**
 *
 */
package com.jet_sys.dt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jet_sys.dt.model.UserProfile;

/**
 * @author ethutch
 *
 */

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

	@Transactional(isolation=Isolation.REPEATABLE_READ)
	List<UserProfile> findAll();

	UserProfile findByuserId(String userId);

}
