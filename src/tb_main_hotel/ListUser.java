/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb_main_hotel;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Andaresta
 */
public class ListUser {
    private Database db;
    private ArrayList<User> userArray;
    
    public ListUser(Database db) {
    
        this.db = db;
        this.userArray = new ArrayList();
    }
    
   public int refesh() {
    
        try{
            ResultSet rs = this.db.getData("select akun, user, password, jenis from user");
            
            this.userArray.clear();
            while(rs.next()){    
                User user = new User(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(4));
                this.userArray.add(user);   
            }
            rs.close();
        }catch(Exception e){
            System.out.println("Error refresh" +e.getMessage());
        }
        return this.userArray.size();
    }
   
    public int cari(String akun) {
    
        int out = -1;
        if (this.userArray.size() > 0) {
            for (int i = 0; i < this.userArray.size(); i++) {
                User user = this.userArray.get(i);
                System.out.println("vs " + akun + " a " + user.getAkun());
                if (user.getAkun().equals(akun)) {
                    out = i;
                    break;
                }
            }
        }
        return out;
    }
    
    public User get(int index) {

         if ((index > -1) && (index < this.userArray.size())) {
            return this.userArray.get(index);
        } else {
            return null;
        }
    }
    
    public User get(String akun) {
    
        int index = this.cari(akun);
        if ((index > -1) && (index < this.userArray.size())) {
            return this.userArray.get(index);
        } else {
            return null;
        }
    }

    public int count() {
    
        return this.userArray.size();
    }
    
    public User login(String akun, String password) {
    
        //System.out.println("select akun, user, password, jenis from user where (akun = '" + akun + "') " + password);
        try{
            ResultSet rs = this.db.getData("select akun, user, password, jenis from user where (akun = '" + akun + "')");
            if(rs.next()){    
                User user = new User(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(4));
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
            rs.close();
        }catch(Exception e){
            System.out.println("Error login " +e.getMessage());
        }
        return null;
    }
    
    public boolean update(String nama, String akun, String password){
        try{
            return this.db.query("update user set password = '"+password+"', user = '"+nama+"' where akun = '"+akun+"'");
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean updateJenis(String jenis, String akun){
        try{
            return this.db.query("update user set jenis = '"+jenis+"' where akun = '"+akun+"'");
        } catch (Exception e){
            return false;
        }
    }
    
    
    public int tambah(String akun, String nama,  String password){
         int hasil = 0;
        
        if (this.cari(akun) > -1) {
            hasil = 1;
        } else {
            String jenis = "STANDAR";
            /* simpan ke db */
            if (this.db.query("insert into user (akun, user, password, jenis) values ('" + akun + "', '" + nama + "', '" + password + "', '" + jenis + "')")) {
                /* add ke list */
                User user = new User(nama, akun, password, jenis);
                this.userArray.add(user);              
            } else {
                hasil = 2;
            }
        }
        return hasil;      
    }
    
    
      public boolean delete(String akun) {
    
        int i = this.cari(akun);
        if (i > -1) {
            try {
                if (this.db.query("delete from user where akun = '" + akun + "'")) {
                    this.userArray.remove(i);
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("user tidak ada.");
            return false;
        }
    }
    public void tampil() {
    
        if (this.userArray.size() > 0) {
            for (int i = 0; i < this.userArray.size(); i++) {
                User user = this.userArray.get(i);
                //System.out.println("#" + (i+1) + " " + hotel.getId() + " " + hotel.getNama());
                user.tampil();
            }
        
        } else {
            System.out.println("Tidak ada data user");
        }
    }
    
     public void tampil(JTable jtable) {
        
        TableUser data = new TableUser(this);
        jtable.setModel(data);
        /*
        if (this.hotelArray.size() > 0) {
            for (int j = 0; j < this.hotelArray.size(); j++) {
                jtable.setValueAt(this.hotelArray.get(j).getId(), j, 0);
                jtable.setValueAt(this.hotelArray.get(j).getNama(), j, 1);
                jtable.setValueAt(this.hotelArray.get(j).getInformasi(), j, 2);
            }
        }
        */
    }
}
