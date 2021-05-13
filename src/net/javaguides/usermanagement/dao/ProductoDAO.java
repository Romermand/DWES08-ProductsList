package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.User;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table producto in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class ProductoDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/ud08?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_PRODUCTO_SQL = "INSERT INTO producto" + "  (NombreProducto, Descripcion, Precio) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_PRODUCTO_BY_ID = "select IDProducto,NombreProducto,Descripcion,Precio from producto where IDProducto =?";
	private static final String SELECT_ALL_PRODUCTOS = "select * from producto";
	private static final String DELETE_PRODUCTOS_SQL = "delete from producto where IDProducto = ?;";
	private static final String UPDATE_PRODUCTOS_SQL = "update producto set NombreProducto = ?,Descripcion= ?, Precio =? where IDProducto = ?;";

	public ProductoDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertProduct(Product product) throws SQLException {
		System.out.println(INSERT_PRODUCTO_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTO_SQL)) {
			preparedStatement.setString(1, product.getNombre());
			preparedStatement.setString(2, product.getDescripcion());
			//preparedStatement.setInt(3, product.getPrecio());
			preparedStatement.setFloat(3, product.getPrecio());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Product selectProduct(int IDProducto) {
		Product product = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTO_BY_ID);) {
			preparedStatement.setInt(1, IDProducto);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String NombreProducto = rs.getString("NombreProducto");
				String Descripcion = rs.getString("Descripcion");
				//int Precio = rs.getInt("Precio");
				float Precio = rs.getFloat("Precio");
				Product = new Product(IDProducto, NombreProducto, Descripcion, Precio);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return product;
	}

	public List<Product> selectAllProducts() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Product> producto = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTOS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int IDProducto = rs.getInt("IDProducto");
				String NombreProducto = rs.getString("NombreProducto");
				String Descripcion = rs.getString("Descripcion");
				//int Precio = rs.getInt("Precio");
				float Precio = rs.getFloat("Precio");
				producto.add(new Product(IDProducto, NombreProducto, Descripcion, Precio));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return producto;
	}

	public boolean deleteProduct(int IDProducto) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTOS_SQL);) {
			statement.setInt(1, IDProducto);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateProduct(Product product) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTOS_SQL);) {
			statement.setString(1, product.getNombre());
			statement.setString(2, product.getDescripcion());
			//statement.setInt(3, product.getPrecio());
			statement.setFloat(3, product.getPrecio());
			statement.setInt(4, product.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
