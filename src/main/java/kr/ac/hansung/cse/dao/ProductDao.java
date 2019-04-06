package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Product;

@Repository 
public class ProductDao {
	private JdbcTemplate jdbcTemplate;	
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);	//dataSource는 Autowired에서 주입 --> dao-context.xml의 dataSource
	}
	
	public List<Product> getProducts() {
		String sqlStatement = "select * from product";	// record -> object
		
		return jdbcTemplate.query(sqlStatement, new RowMapper<Product> () {
			//query를 통해서 sqlStatement 실행, product에 대한 정보를 읽어옴, record 형태를 객체로 mapping 해주는 부분을 반드시 define 해줘야

			//record 개수 만큼 호출
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));
				
				return product;
			}
			
		});
	}
	
	public boolean addProduct(Product product) {
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "insert into product (name, category, price, manufacturer, unitInStock, description) "
				+ "values (?,?,?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description }) == 1);
	}

	public boolean deleteProduct(int id) {
		String sqlStatement = "delete from product where id=?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);
	}

	public Product getProductById(int id) {
		String sqlStatement = "select * from product where id=?"; // record형태로 넘어오는 것을 object로 매핑

		return jdbcTemplate.queryForObject(sqlStatement,new Object[] { id }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();

				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getInt("price"));
				product.setManufacturer(rs.getString("manufacturer"));
				product.setUnitInStock(rs.getInt("unitInStock"));
				product.setDescription(rs.getString("description"));

				return product;
			}
		});
	}

	public boolean updateProduct(Product product) {
		int id = product.getId();
		String name = product.getName();
		String category = product.getCategory();
		int price = product.getPrice();
		String manufacturer = product.getManufacturer();
		int unitInStock = product.getUnitInStock();
		String description = product.getDescription();

		String sqlStatement = "update product set name=?, category=?, price=?, manufacturer=?, unitInStock=?, description=? "
				+ "where id=?";
				

		return (jdbcTemplate.update(sqlStatement,
				new Object[] { name, category, price, manufacturer, unitInStock, description, id }) == 1);
	}
}
 