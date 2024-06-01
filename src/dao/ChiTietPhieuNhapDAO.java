/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.JDBCUtil;
import model.ChiTietPhieu;
import model.ChiTietPhieu;
import model.MayTinh;
import model.Phieu;

public class ChiTietPhieuNhapDAO implements DAOInterface<ChiTietPhieu> {

    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public int insert(ChiTietPhieu t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO ChiTietPhieuNhap (maPhieu, maMay, soLuong, donGia) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setString(2, t.getMayTinh().getMaMay());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getDonGia());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(ChiTietPhieu t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE ChiTietPhieuNhap SET maPhieu=?, maMay=?, soLuong=?, donGia = ?  WHERE maPhieu=? AND maMay=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            pst.setString(2, t.getMayTinh().getMaMay());
            pst.setInt(3, t.getSoLuong());
            pst.setDouble(4, t.getDonGia());
            pst.setString(5, t.getMaPhieu());
            pst.setString(6, t.getMayTinh().getMaMay());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(ChiTietPhieu t) {
        int ketQua = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM ChiTietPhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieu());
            ketQua = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<ChiTietPhieu> selectAll(String t) {
        ArrayList<ChiTietPhieu> ketQua = new ArrayList<ChiTietPhieu>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ChiTietPhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maMay = rs.getString("maMay");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                String sqlMayTinh = "SELECT * FROM MayTinh WHERE maMay = ?";
                PreparedStatement pstMayTinh = con.prepareStatement(sqlMayTinh);
                pstMayTinh.setString(1, maMay);
                MayTinh mayTinh = null;
                ResultSet rsMayTinh = pstMayTinh.executeQuery();
                if(rsMayTinh.next()) {
                    String tenMay = rsMayTinh.getString("tenMay");
                    int soLuongMT = rsMayTinh.getInt("soLuong");
                    double gia = rsMayTinh.getDouble("gia");
                    String tenCpu = rsMayTinh.getString("tenCpu");
                    String ram = rsMayTinh.getString("ram");
                    String Rom = rsMayTinh.getString("Rom");
                    mayTinh = new MayTinh(maMay, tenMay, soLuongMT, gia, tenCpu, ram, Rom);
                }
                ChiTietPhieu ctp = new ChiTietPhieu(maPhieu, mayTinh, soLuong, donGia);
                ketQua.add(ctp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ArrayList<ChiTietPhieu> selectAll() {
        ArrayList<ChiTietPhieu> ketQua = new ArrayList<ChiTietPhieu>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ChiTietPhieuNhap";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maMay = rs.getString("maMay");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                
                String sqlMayTinh = "SELECT * FROM MayTinh WHERE maMay = ?";
                PreparedStatement pstMayTinh = con.prepareStatement(sqlMayTinh);
                pstMayTinh.setString(1, maMay);
                MayTinh mayTinh = null;
                ResultSet rsMayTinh = pstMayTinh.executeQuery();
                if(rsMayTinh.next()) {
                    String tenMay = rsMayTinh.getString("tenMay");
                    int soLuongMT = rsMayTinh.getInt("soLuong");
                    double gia = rsMayTinh.getDouble("gia");
                    String tenCpu = rsMayTinh.getString("tenCpu");
                    String ram = rsMayTinh.getString("ram");
                    String Rom = rsMayTinh.getString("Rom");
                    mayTinh = new MayTinh(maMay, tenMay, soLuongMT, gia, tenCpu, ram, Rom);
                }
                ChiTietPhieu ctp = new ChiTietPhieu(maPhieu, mayTinh, soLuong, donGia);
                ketQua.add(ctp);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public ChiTietPhieu selectById(String t) {
        ChiTietPhieu ketQua = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ChiTietPhieuNhap WHERE maPhieu=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                String maMay = rs.getString("maMay");
                int soLuong = rs.getInt("soLuong");
                double donGia = rs.getDouble("donGia");
                String sqlMayTinh = "SELECT * FROM MayTinh WHERE maMay = ?";
                PreparedStatement pstMayTinh = con.prepareStatement(sqlMayTinh);
                pstMayTinh.setString(1, maMay);
                MayTinh mayTinh = null;
                ResultSet rsMayTinh = pstMayTinh.executeQuery();
                if(rsMayTinh.next()) {
                    String tenMay = rsMayTinh.getString("tenMay");
                    int soLuongMT = rsMayTinh.getInt("soLuong");
                    double gia = rsMayTinh.getDouble("gia");
                    String tenCpu = rsMayTinh.getString("tenCpu");
                    String ram = rsMayTinh.getString("ram");
                    String Rom = rsMayTinh.getString("Rom");
                    mayTinh = new MayTinh(maMay, tenMay, soLuongMT, gia, tenCpu, ram, Rom);
                }
                ketQua = new ChiTietPhieu(maPhieu, mayTinh, soLuong, donGia);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
}
