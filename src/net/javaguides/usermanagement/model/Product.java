package net.javaguides.usermanagement.model;

/**
 * Product.java
 * This is a model class represents a Product entity
 * @author Ramesh Fadatare
 *
 */
public class Product {
	protected int IDProducto;
	protected String NombreProducto;
	protected String Descripcion;
	protected float Precio;
	
	public Product() {
	}
	
	public Product(String NombreProducto, String Descripcion, float Precio) {
		super();
		this.NombreProducto = NombreProducto;
		this.Descripcion = Descripcion;
		this.Precio = Precio;
	}

	public Product(int IDProducto, String NombreProducto, String Descripcion, float Precio) {
		super();
		this.IDProducto = IDProducto;
		this.NombreProducto = NombreProducto;
		this.Descripcion = Descripcion;
		this.Precio = Precio;
	}

	public int getId() {
		return IDProducto;
	}
	public void setId(int IDProducto) {
		this.IDProducto = IDProducto;
	}
	public String getNombre() {
		return NombreProducto;
	}
	public void setNombre(String NombreProducto) {
		this.NombreProducto = NombreProducto;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String Descripcion) {
		this.Descripcion = Descripcion;
	}
	public float getPrecio() {
		return Precio;
	}
	public void setPrecio(float Precio) {
		this.Precio = Precio;
	}
}
