/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb_main_hotel;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andaresta
 */
public class TableUser extends AbstractTableModel {
    private String[] columnNames = { "Akun", "Nama", "Password", "Jenis" };
    private ListUser listUser;

    public TableUser(ListUser listUser) {
    
        this.listUser = listUser;
    }
    
    public int getColumnCount() {
        
        return this.columnNames.length;
    }

    public int getRowCount() {
        
        return this.listUser.count();
    }

    public String getColumnName(int col) {
        
        return this.columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        
        User user = this.listUser.get(row);
        if (user != null) {
            switch (col) {
                case 0: return user.getAkun();
                case 1: return user.getNama();
                case 2: return user.getPassword();
                case 3: return user.getJenis();
            }
        }
        return "";
    }
}
