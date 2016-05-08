package org.codeplay.repository.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.codeplay.repository.BObjects.IndexingInfo;
import org.springframework.jdbc.core.RowMapper;

public class IndexingInfoMapper  implements RowMapper<IndexingInfo>{

	@Override
	public IndexingInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		IndexingInfo infoObj = new IndexingInfo();

		infoObj.setId(rs.getString("id"));
		infoObj.setLastPageId(rs.getString("lastPageId"));
		infoObj.setExecutionDate(rs.getString("executionDate"));
		
		return infoObj;
	}

}
