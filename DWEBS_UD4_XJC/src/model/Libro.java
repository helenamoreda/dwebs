//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.12.04 a las 07:01:28 PM CET 
//


package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fecha",
    "titulo",
    "autor"
})
@XmlRootElement(name = "Libro")
public class Libro {

	@XmlElement(name = "id", required = true)
    protected int id;
    public int getId() {
		return id;
	}

	public Libro() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "Fecha", required = true)
    protected String fecha;
    @XmlElement(name = "Titulo", required = true)
    protected String titulo;
    @XmlElement(name = "Autor", required = true)
    protected String autor;

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    public Libro(int id, String fecha, String titulo, String autor) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.titulo = titulo;
		this.autor = autor;
	}

	/**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    public Libro(String fecha, String titulo, String autor) {
		super();
		this.fecha = fecha;
		this.titulo = titulo;
		this.autor = autor;
	}

	/**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Obtiene el valor de la propiedad autor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define el valor de la propiedad autor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutor(String value) {
        this.autor = value;
    }

}
