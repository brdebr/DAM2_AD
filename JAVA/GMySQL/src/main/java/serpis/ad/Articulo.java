package serpis.ad;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Articulo {

	private long id;
	private String nombre;
	private Double precio;
	private long categoria_fk;

	public Articulo(String id, String nombre, String precio, String categoria_fk) {
		this.id = Long.parseLong(id);
		this.nombre = nombre;
		this.precio = Double.parseDouble(precio);
		this.categoria_fk = Long.parseLong(categoria_fk);
	}

	public String getId_String() {
		return String.valueOf(this.id);
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio_String() {
		return String.valueOf(this.precio);
	}

	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getCategoria_fk_String() {
		return String.valueOf(this.categoria_fk);
	}

	public long getCategoria_fk() {
		return this.categoria_fk;
	}

	public void setCategoria_fk(long categoria_fk) {
		this.categoria_fk = categoria_fk;
	}

	public static void insert(Articulo articulo) {

		try {

			Connection connection = SingletonConnection.getInstancia().getConnection();
			String sql = "INSERT INTO articulo " + "(nombre, precio, categoria) VALUES" + "(?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, articulo.getNombre());
			stmt.setDouble(2, articulo.getPrecio());
			stmt.setLong(3, articulo.getId());

			stmt.execute();

			stmt.close();

			connection.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void update(Articulo articulo, Articulo nuevo) {

		try {

			Connection connection = SingletonConnection.getInstancia().getConnection();
			String sql = "UPDATE articulo SET nombre = ? , precio = ?, categoria = ?  WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, nuevo.getNombre());
			stmt.setDouble(2, nuevo.getPrecio());
			stmt.setLong(3, nuevo.getCategoria_fk());
			stmt.setLong(4, articulo.getId());

			stmt.execute();

			stmt.close();

			connection.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void delete(Articulo articulo) {

		try {

			Connection connection = SingletonConnection.getInstancia().getConnection();
			String sql = "DELETE FROM articulo WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, articulo.getId());

			stmt.execute();

			stmt.close();

			connection.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static Articulo select(String id) {
		Articulo aux=null;
		try {

			Connection connection = SingletonConnection.getInstancia().getConnection();
			String sql = "SELECT * FROM articulo WHERE id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, id);

			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
				String id_aux = String.valueOf(resultSet.getBigDecimal("id"));
				String nombre = resultSet.getString("nombre");
				String precio = String.valueOf(resultSet.getDouble("precio"));
				String categoria = String.valueOf(resultSet.getBigDecimal("categoria"));

				aux =  new Articulo(id_aux, nombre, precio, categoria);

			stmt.close();

			connection.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return aux;

	}

	public static Articulo[] select() {
		Articulo[] aux = null;
		try {

			Connection connection = SingletonConnection.getInstancia().getConnection();
			String sql = "SELECT * FROM articulo";
			PreparedStatement stmt = connection.prepareStatement(sql);

			ArrayList<Articulo> lista = new ArrayList<Articulo>();

			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				String id = String.valueOf(resultSet.getBigDecimal("id"));
				String nombre = resultSet.getString("nombre");
				String precio = String.valueOf(resultSet.getDouble("precio"));
				String categoria = String.valueOf(resultSet.getBigDecimal("categoria"));

				lista.add(new Articulo(id, nombre, precio, categoria));

			}

			stmt.close();

			connection.close();

			aux = lista.toArray(new Articulo[lista.size()]);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return aux;

	}

	public static void print(Articulo articulo) {
		System.out.println(articulo.getId_String() + "\t" + articulo.getNombre() + "\t\t" + articulo.getPrecio_String()
				+ "\t" + articulo.getCategoria_fk_String());
	}

}
