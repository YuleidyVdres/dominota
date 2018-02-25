/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.BorderLayout;
import controlador.Operaciones;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import modelo.Equipos;
import modelo.Jugadores;
import modelo.Partidas;
import modelo.PartidosEquipos;

public class EquiposVista extends javax.swing.JPanel {

    Operaciones oper;
    protected ArrayList<Equipos> equipo;
    protected DefaultListModel DLM;
    Date fecha;

    public EquiposVista() {
        initComponents();

        oper = new Operaciones();
        DLM = new DefaultListModel();
        fecha = new Date();
        this.equipo = oper.ConsultarEquiposes();
        for (Equipos equipos : this.equipo) {
            DLM.addElement(equipos.getNombre());
        }
        ListaEquipos.setModel(DLM);
        ListaEquipos.setSelectionModel(new LimitedSelectionModel(ListaEquipos, 2));

    }

    private static class LimitedSelectionModel extends DefaultListSelectionModel {

        private JList list;
        private int maxCount;

        private LimitedSelectionModel(JList list, int maxCount) {
            this.list = list;
            this.maxCount = maxCount;
        }

        @Override
        public void setSelectionInterval(int index0, int index1) {
            if (index1 - index0 >= maxCount) {
                index1 = index0 + maxCount - 1;
            }
            super.setSelectionInterval(index0, index1);
        }

        @Override
        public void addSelectionInterval(int index0, int index1) {
            int selectionLength = list.getSelectedIndices().length;
            if (selectionLength >= maxCount) {
                return;
            }

            if (index1 - index0 >= maxCount - selectionLength) {
                index1 = index0 + maxCount - 1 - selectionLength;
            }
            if (index1 < index0) {
                return;
            }
            super.addSelectionInterval(index0, index1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        PuntosMaximos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaEquipos = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        label1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        label1.setText("Juego en Equipo");

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel1.setText("Puntos Maximos");
        jLabel1.setToolTipText("");

        PuntosMaximos.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        PuntosMaximos.setText("100");
        PuntosMaximos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PuntosMaximosActionPerformed(evt);
            }
        });

        ListaEquipos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(ListaEquipos);

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Crear Partida");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PuntosMaximos, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(PuntosMaximos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PuntosMaximosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PuntosMaximosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PuntosMaximosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List<String> file = ListaEquipos.getSelectedValuesList();
        Set<Equipos> equipos = new HashSet<Equipos>();
        System.out.println(file.get(0));
        Partidas partida=null;
        if (file.size() != 2) {
            JOptionPane.showMessageDialog(null, "El juego debe tener 2 Equipos");
        } else {
            try{
            Integer.parseInt(PuntosMaximos.getText());
            equipos.add(oper.ObjetoEquipos(file.get(0)));
            equipos.add(oper.ObjetoEquipos(file.get(1)));
            partida=oper.crearPartida(equipos,Integer.parseInt(PuntosMaximos.getText()));
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "El puntaje máximo no es válido");
            }

        }
        JuegoEquipo juego= new JuegoEquipo(partida);
        juego.setSize(800, 800);
        jPanel1.setSize(800,800);
        juego.setLocation(1, 1);
        jPanel1.removeAll();
        jPanel1.add(juego, BorderLayout.CENTER);
        jPanel1.revalidate();
        jPanel1.repaint();
        //PartidosEquipos equipos= new PartidosEquipos(partida, equipos);
        //oper.AgregarUsuario(juegador);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaEquipos;
    private javax.swing.JTextField PuntosMaximos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables
}
