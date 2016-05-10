/**
 * 
 */
package org.codeplay.repository.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codeplay.repository.BObjects.User;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author surabh
 *
 */

public class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User userObj = new User();

		userObj.setAge(String.valueOf(rs.getInt("age")));
		userObj.setAgeGroup(String.valueOf(rs.getInt("agegroup")));
		userObj.setAppName(rs.getString("app_name"));
		userObj.setCreatedDate(String.valueOf(rs.getString("created_date")));
		userObj.setCurrentlyAt(rs.getString("currently_at"));
		userObj.setCurrentlyAtId(rs.getString("currently_at_id"));
		userObj.setDateOfBirth(String.valueOf(rs.getString("date_of_birth")));
		userObj.setFbId(rs.getString("fbId"));
		userObj.setGender(rs.getString("gender"));
		userObj.setHomeTown(rs.getString("home_town"));
		userObj.setHomeTownCountry(rs.getString("home_town_country"));
		userObj.setHomeTownId(rs.getString("home_town_id"));
		userObj.setId(Integer.toString(rs.getInt("id")));
		userObj.setLivesIn(rs.getString("lives_in"));
		userObj.setLivesInId(rs.getString("live_in_id"));
		userObj.setLivesInCountry(rs.getString("lives_in_country"));
		userObj.setName(rs.getString("name"));
		userObj.setProfilePicture(rs.getString("profile_picture"));
		userObj.setRawData(rs.getString("raw_data"));
		userObj.setTags(rs.getString("tags"));
		userObj.setEmail(rs.getString("email"));
		userObj.setActive(rs.getString("active"));
		return userObj;
	}

}
