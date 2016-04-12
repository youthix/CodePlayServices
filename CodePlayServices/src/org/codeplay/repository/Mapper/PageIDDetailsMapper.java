/**
 * 
 */
package org.codeplay.repository.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codeplay.repository.BObjects.Page;
import org.codeplay.repository.BObjects.User;

/**
 * @author surabh
 *
 */
public class PageIDDetailsMapper {

	public Page mapRow(ResultSet rs, int rowNum) throws SQLException {
		Page pageObj = new Page();

		pageObj.setFbIds(rs.getString("fbids"));
		pageObj.setId(rs.getString("page_id"));
		pageObj.setTable(rs.getString("table"));

		return pageObj;
	}
}
