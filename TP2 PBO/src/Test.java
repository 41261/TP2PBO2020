/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author AR_GI
 */
public class Test {
    
    private Connection koneksi;
    private Statement stmt;
    private ResultSet rs;
    
    private void init(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_buku", "root", "");
            stmt = koneksi.createStatement();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Koneksi Gagal","Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void insertBuku(String kb, String nb, String np, String tp, String tk){
        
        init();
        int status = 0;
        String q = String.format("SELECT COUNT(*) FROM table_buku WHERE kode_buku = '%s'", kb);
        try{
            rs = stmt.executeQuery(q);
            if(rs.next()){
               JOptionPane.showMessageDialog(null, "Gagal Insert Data","Info", JOptionPane.INFORMATION_MESSAGE);
            }else{
                q = String.format("INSERT INTO table_buku(kode_buku, nama_buku, nama_peminjam, tanggal_pinjam, tanggal_pengembalian) VALUE('%s', '%s', '%s', '%s', '%s')", kb, nb, np, tp, tk);
                stmt.executeUpdate(q);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
 
    }
    
    public void deleteBuku(String kb){
        
        init();
        String q = String.format("SELECT COUNT(*) FROM table_buku WHERE kode_buku = '%s'", kb);
        try{
            rs = stmt.executeQuery(q);
            if(rs.next()){
                q = String.format("DELETE FROM table_buku WHERE kode_buku = '%s'", kb);
                stmt.execute(q);
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Delete Data","Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
 
    }
    
    public void updateBuku(String kb, int date){
        
        init();
        String q3 = String.format("SELECT COUNT(*) FROM table_buku WHERE kode_buku = '%s'", kb);
        try{
            rs = stmt.executeQuery(q3);
//            if(rs.next()){
//                q3 = String.format("UPDATE table_buku SET tanggal_pengembalian =  WHERE kode_buku = '%s'", date, kb);
//                stmt.executeUpdate(q3);
//            }else{
//                JOptionPane.showMessageDialog(null, "Gagal Update Data","Info", JOptionPane.INFORMATION_MESSAGE);
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
