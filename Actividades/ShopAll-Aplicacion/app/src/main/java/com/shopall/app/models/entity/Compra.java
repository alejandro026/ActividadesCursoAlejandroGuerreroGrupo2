package com.shopall.app.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TBL_Compras")
public class Compra  implements Serializable {

    @Id
    @Column(name = "id_Compra", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    @Column(name = "fecha_compra", nullable = false)
    private Date fecha;

    @Column(name = "cantidad_productos", nullable = false)
    private Integer cantidadProductos;

    @Column(name = "total", nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_forma_pago", nullable = false)
    private FormasPago idFormaPago;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<ListaCompras> productosCompra;

}
