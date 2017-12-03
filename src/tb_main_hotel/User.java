/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tb_main_hotel;

/**
 *
 * @author User
 */
public class User {
    
    private String nama;
    private String akun;
    private String password;
    private String jenis;

    public User(String nama, String akun, String password, String jenis) {
        this.nama = nama;
        this.akun = akun;
        this.password = password;
        this.jenis = jenis;
    }
    
    public String getNama() {
        return nama;
    }

    public String getAkun() {
        return akun;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    public String getJenis() {
        return jenis;
    }
    
    public void setNama(String nama) {
    
        this.nama = nama;
    }
    
    public void setPassword(String password) {
    
        this.password = password;
    }
    
    public void setJenis(String jenis){
        this.jenis = jenis;
    }
    
    public void tampil() {
    
        System.out.println("Nama: " + this.nama + " Akun : " + this.akun);
    }
    
}