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
        String c = "";
        String q = String.format("SELECT * FROM table_buku");
        try{
            rs = stmt.executeQuery(q);
            while(rs.next()){
                c = rs.getString("kode_buku");
                if(c.equals(kb)){
                    status = 1;
                }
            }
            if(status == 1){
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
        int status = 0;
        String c = "";
        String q = String.format("SELECT * FROM table_buku");
        try{
            rs = stmt.executeQuery(q);
            while(rs.next()){
                c = rs.getString("kode_buku");
                if(c.equals(kb)){
                    status = 1;
                }
            }
            if(status == 1){
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
        int status = 0;
        String c = "";
        String d = "";
        String q = String.format("SELECT * FROM table_buku");
        try{
            rs = stmt.executeQuery(q);
            while(rs.next()){
                c = rs.getString("kode_buku");
                if(c.equals(kb)){
                    status = 1;
                }
            }
            
            if(status == 1){
                q = String.format("SELECT tanggal_pengembalian FROM table_buku WHERE kode_buku = '%s'", kb);
                rs = stmt.executeQuery(q);
                while(rs.next()){
                    d = rs.getString("tanggal_pengembalian");
                }
                
                q = String.format("SELECT DATE_ADD('%s', INTERVAL %d DAY) AS result", d, date);
                rs = stmt.executeQuery(q);
                while(rs.next()){
                    c = rs.getString("result");
                }

                q = String.format("UPDATE table_buku SET tanggal_pengembalian = '%s' WHERE kode_buku = '%s'", c, kb);
                stmt.execute(q);
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Update Data","Info", JOptionPane.INFORMATION_MESSAGE);
            }
//           
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
}
