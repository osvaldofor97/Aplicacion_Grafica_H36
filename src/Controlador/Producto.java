package Controlador;

import Modelo.ConexionBD;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Producto {
    private String idProducto;
    private String Nombre;
    private double Temperatura;
    private double ValorBase;

    public Producto() {
    }
    
    public Producto(String Nombre,String idProducto,double Temperatura, double ValorBase) {
        this.idProducto = idProducto;
        this.Nombre = Nombre;
        this.Temperatura = Temperatura;
        this.ValorBase = ValorBase;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(double Temperatura) {
        this.Temperatura = Temperatura;
    }

    public double getValorBase() {
        return ValorBase;
    }

    public void setValorBase(double ValorBase) {
        this.ValorBase = ValorBase;
    }
    
    public boolean guardarProducto(){
        ConexionBD objConexionBD = new ConexionBD();
        
        String sql="INSERT INTO Producto(idProducto,Nombre,Temperatura,ValorBase)"
                +"VALUES('"+idProducto+"','"+Nombre+"',"+Temperatura+","+ValorBase+")";
        if (objConexionBD.insertarBD(sql)) {
            objConexionBD.cerrarConexion();
            return true;
        }else{
            objConexionBD.cerrarConexion();
            return false;
        }
    }
    
    public List<Producto> listarProductos(){
        List<Producto> listaProductos = new ArrayList<>();
        ConexionBD objConexionBD = new ConexionBD();
        String sql1="SELECT * FROM Producto";
        try {
            ResultSet rs = objConexionBD.consultarBD(sql1);
            
            while(rs.next()){
                listaProductos.add(
                        new Producto(
                                rs.getString("Nombre"),
                                rs.getString("idProducto"),
                                rs.getDouble("Temperatura"),
                                rs.getDouble("ValorBase")));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los productos");
        }
        objConexionBD.cerrarConexion();
        return listaProductos;
    }
    
    public List<Producto> listarProductos(String where){
        List<Producto> listaProductos = new ArrayList<>();
        ConexionBD objConexionBD = new ConexionBD();
        String sql1="SELECT * FROM Producto WHERE "+where;
        try {
            ResultSet rs = objConexionBD.consultarBD(sql1);
            
            while(rs.next()){
                listaProductos.add(
                        new Producto(
                                rs.getString("Nombre"),
                                rs.getString("idProducto"),
                                rs.getDouble("Temperatura"),
                                rs.getDouble("ValorBase")));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los productos");
        }
        objConexionBD.cerrarConexion();
        return listaProductos;
    }
        
    public boolean actualizarProducto(){
        ConexionBD objConexionBD = new ConexionBD();
        String sql="UPDATE Producto set "
                + "Nombre='"+Nombre+"',"
                + "Temperatura='"+Temperatura+"',"
                + "ValorBase="+ValorBase+" "
                + "where idProducto='"+idProducto+"'";
        if (objConexionBD.actualizarBD(sql)) {
            objConexionBD.cerrarConexion();
            return true;
        }else{
            objConexionBD.cerrarConexion();
            return false;
        }
    }
    
    public boolean eliminarProducto(){
        ConexionBD objConexionBD = new ConexionBD();
        String sql="";
        int resp= JOptionPane.showConfirmDialog(null, "¿Seguro que desea elimar el elemento?","Confirmación", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        switch(resp){
            case 0:
                sql="DELETE FROM Producto where idProducto='"+idProducto+"'";
                if (objConexionBD.borrarBD(sql)) {
                objConexionBD.cerrarConexion();
                }
                break;
            case 1:
                objConexionBD.cerrarConexion();  
                break;       
        }
            if(resp==0){return true;} else{return false;}
            
/*        String sql="DELETE FROM Producto where idProducto='"+idProducto+"'";
          if (objConexionBD.borrarBD(sql)) {
              objConexionBD.cerrarConexion();
              return true;
          }else{
              objConexionBD.cerrarConexion();
              return false;
          }*/
    }
    
}
