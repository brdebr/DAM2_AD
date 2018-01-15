package Hibernate.Classes;
// Generated 15-ene-2018 11:32:36 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pedido generated by hbm2java
 */
@Entity
@Table(name = "pedido",
         catalog = "dbprueba"
)
public class Pedido implements java.io.Serializable {

    private Long id;
    private Cliente cliente;
    private Date fecha;
    private BigDecimal importe;
    private Set<Pedidolinea> pedidolineas = new HashSet<Pedidolinea>(0);

    public Pedido() {
    }

    public Pedido(Cliente cliente, Date fecha) {
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public Pedido(Cliente cliente, Date fecha, BigDecimal importe, Set<Pedidolinea> pedidolineas) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.importe = importe;
        this.pedidolineas = pedidolineas;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente", nullable = false)
    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false, length = 19)
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "importe", precision = 10)
    public BigDecimal getImporte() {
        return this.importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
    public Set<Pedidolinea> getPedidolineas() {
        return this.pedidolineas;
    }

    public void setPedidolineas(Set<Pedidolinea> pedidolineas) {
        this.pedidolineas = pedidolineas;
    }

    @Override
    public String toString() {
//        return super.toString(); //To change body of generated methods, choose Tools | Templates.
        String lineas = "Pedido: ["+String.valueOf(this.getId())+"]"
                +"\n-Fecha: " + this.getFecha().toString() +"\n"
                +"-Cliente: " + this.getCliente().toString();
        ArrayList<Pedidolinea> articulos = new ArrayList<>(this.getPedidolineas());
        if (!articulos.isEmpty()) {
            lineas+="\n-Articulos: \n";
            lineas+=String.format("%-6s%-15s%-6s%-6s%-6s", "ID:","Articulo:","Uds:","BI:","Total:");
            lineas+="\n";
            for (int i = 0; i < articulos.size(); i++) {
                Pedidolinea linea = articulos.get(i);
                lineas+=String.format("%-6s%-15s%-6s%-6s%-6s\n",
                        String.valueOf(linea.getArticulo().getId()),
                        linea.getArticulo().getNombre(),
                        linea.getUnidades(),
                        linea.getPrecio(),
                        linea.getImporte());
//                lineas+="\n";
            }
        }
        return lineas;
    }

}
