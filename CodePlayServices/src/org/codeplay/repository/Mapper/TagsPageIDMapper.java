/**
 * 
 */
package org.codeplay.repository.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codeplay.repository.BObjects.TagPage;
import org.codeplay.repository.BObjects.User;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author surabh
 *
 */
public class TagsPageIDMapper implements RowMapper<TagPage>  {
	public TagPage mapRow(ResultSet rs, int rowNum) throws SQLException {
		TagPage tagPageObj = new TagPage();

		tagPageObj.setPageIds(rs.getString("page_ids"));
		tagPageObj.setTags(rs.getString("tags"));

		return tagPageObj;

	}
}
