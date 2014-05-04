/**
 *
 */
package com.jet_sys.dt.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.jet_sys.dt.config.DataContextConfig;
import com.jet_sys.dt.repository.UserProfileRepository;


/**
 * @author ethutch
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = {"data"})
@ContextConfiguration(classes = DataContextConfig.class, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserProfileTest {

	@Autowired
	UserProfileRepository userProfileRepository;
	/**
	 * Test method for {@link com.jet_sys.dt.model.UserProfile#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() {

		List<UserProfile> userList = userProfileRepository.findAll();
		UserProfile user = userList.get(0);
		assertTrue("User Not retrieved", "Terry".equals(user.getFirstName()));

		UserProfile u2 = userProfileRepository.findByuserId("terry");
		assertTrue("User Not retrieved", "Terry".equals(u2.getFirstName()));
	}

}
