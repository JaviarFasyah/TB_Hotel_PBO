/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb_main_hotel.UI;

import javax.swing.JOptionPane;
import tb_main_hotel.Database;
import tb_main_hotel.ListUser;
import tb_main_hotel.User;

/**
 *
 * @author Andaresta
 */
public class GUIListUser extends javax.swing.JFrame {
    
     private User currentUser = null;
     private User loginUser = null;
     private ListUser listUser;
     private DialoggantiJUser gantiUser;
     private GUIListHotel guiHotel;
     
    /**
     * Creates new form GUIListUser
     */
    public GUIListUser(GUIListHotel guiHotel, User loginUser) {
        initComponents();
        
        this.listUser = new ListUser(new Database());
        this.guiHotel = guiHotel;
        this.loginUser = loginUser;
        this.clearCurrentUser();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableListUser = new javax.swing.JTable();
        btDelete = new javax.swing.JButton();
        titleListUser = new javax.swing.JLabel();
        btGantiJenis = new javax.swing.JButton();
        btClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tableListUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableListUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableListUser);

        btDelete.setText("Delete");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        titleListUser.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titleListUser.setText("List User");

        btGantiJenis.setText("Ganti Jenis");
        btGantiJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGantiJenisActionPerformed(evt);
            }
        });

        btClose.setText("Kembali");
        btClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleListUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btGantiJenis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btDelete)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleListUser)
                    .addComponent(btGantiJenis)
                    .addComponent(btDelete)
                    .addComponent(btClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     /* cek apa ada user dipilih */
    private boolean hasCurrentUser() {
        
        this.currentUser = null;
        int row = this.tableListUser.getSelectedRow();
        if (row > -1) {
            String name = this.tableListUser.getValueAt(row, 1).toString();
            /* ambil id hotel, nama hotel, update judul review */
            this.currentUser = new User(
                name,
                this.tableListUser.getValueAt(row, 0).toString(),
                this.tableListUser.getValueAt(row, 2).toString(),
                    this.tableListUser.getValueAt(row, 3).toString()
            );
            return true;
        } else {
            return false;
        }
    }
    
     private void clearCurrentUser() {
    

        this.currentUser = null;
    }
    
    private void tableListUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListUserMouseClicked
        // TODO add your handling code here:
        if (this.hasCurrentUser()) {
           this.currentUser.getAkun();
           this.currentUser.getNama();
           this.currentUser.getPassword();
           this.currentUser.getJenis();
        }
    }//GEN-LAST:event_tableListUserMouseClicked

    private void btGantiJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGantiJenisActionPerformed
        // TODO add your handling code here:
        if (this.hasCurrentUser()) {
            if (this.currentUser.getAkun().equals(this.loginUser.getAkun())) {
                JOptionPane.showMessageDialog(this, "Tidak bisa mengubah diri sendiri.");
            } else {
                DialoggantiJUser gantiJenis = new DialoggantiJUser(this, true, this.currentUser);
                gantiJenis.setVisible(true);
                this.listUser.refesh();
                this.listUser.tampil(this.tableListUser);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih salah satu user.");
        }
    }//GEN-LAST:event_btGantiJenisActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
        if (this.hasCurrentUser()) {
            if (this.currentUser.getAkun().equals(this.loginUser.getAkun())) {
                JOptionPane.showMessageDialog(this, "Tidak bisa menghapus diri sendiri.");
            } else {
                if (this.listUser.delete(this.currentUser.getAkun())) {
                    this.listUser.tampil(this.tableListUser);
                    this.clearCurrentUser();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus user.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih salah satu user.");
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        int x = this.listUser.refesh();
        this.listUser.tampil(this.tableListUser);
    }//GEN-LAST:event_formWindowOpened

    private void btCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloseActionPerformed
        // TODO add your handling code here:
        this.guiHotel.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btCloseActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClose;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btGantiJenis;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableListUser;
    private javax.swing.JLabel titleListUser;
    // End of variables declaration//GEN-END:variables
}
