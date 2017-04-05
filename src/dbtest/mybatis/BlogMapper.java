package dbtest.mybatis;

import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
	
	@Select("SELECT * FROM blog WHERE ID =#{id}")
	Blog selectBlog(int id);
	
}
