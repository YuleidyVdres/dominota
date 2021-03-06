package modelo;
// Generated Feb 24, 2018 10:50:50 AM by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * PartidosEquipos generated by hbm2java
 */
public class PartidosEquipos  implements java.io.Serializable {


     private BigDecimal id;
     private Partidas partidas;
     private Equipos equipos;

    public PartidosEquipos() {
    }

    public PartidosEquipos(Partidas partidas, Equipos equipos) {
       this.partidas = partidas;
       this.equipos = equipos;
    }
   
    public BigDecimal getId() {
        return this.id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public Partidas getPartidas() {
        return this.partidas;
    }
    
    public void setPartidas(Partidas partidas) {
        this.partidas = partidas;
    }
    public Equipos getEquipos() {
        return this.equipos;
    }
    
    public void setEquipos(Equipos equipos) {
        this.equipos = equipos;
    }




}


