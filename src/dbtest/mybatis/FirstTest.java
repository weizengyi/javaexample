package dbtest.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

public class FirstTest {
	
	@Test
	public SqlSessionFactory createSqlSessionFactoryFromXML(){
		String resource = "resources/mybatis/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Test
	public SqlSessionFactory createSqlSessionFactoryFromClass(){
		Properties properties = new Properties();
		properties.setProperty("driver", "com.mysql.jdbc.Driver");
		properties.setProperty("url", "jdbc:mysql://127.0.0.1:3306/mybatis");
		properties.setProperty("username", "root");
		properties.setProperty("password", "123456");
		PooledDataSourceFactory pooledDataSourceFactory = new PooledDataSourceFactory();
		pooledDataSourceFactory.setProperties(properties);
		DataSource dataSource = pooledDataSourceFactory.getDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		return sqlSessionFactory;
	}
	
	@Test
	public void getSqlSessionFromXMl(){
		SqlSessionFactory sqlSessionFactory = createSqlSessionFactoryFromXML();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  Blog blog = (Blog) session.selectOne("dbtest.mybatis.BlogMapper.selectBlog", 101);
			} finally {
			  session.close();
			}
	}
	
	@Test
	public void getSqlSessionFromNote(){
		SqlSessionFactory sqlSessionFactory = createSqlSessionFactoryFromXML();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			  BlogMapper mapper = session.getMapper(BlogMapper.class);
			  Blog blog = mapper.selectBlog(101);
			} finally {
			  session.close();
			}
		}
	
	

}
