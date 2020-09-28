package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author oscar_lopez
 */
public class Producto {
    private int id_prod,existencia,idMarca;
    private float pcosto,pventa;
    private String producto, descripcion;
    Conexion cn;
    public Producto(){
        
    }
    public Producto(int id_prod, int existencia, float pcosto, float pventa, String producto, String descripcion,int idMarca){
        this.id_prod = id_prod;
        this.existencia = existencia;
        this.pcosto = pcosto;
        this.pventa = pventa;
        this.producto = producto;
        this.descripcion = descripcion;
        this.idMarca=idMarca;
       
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
    
    

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getPcosto() {
        return pcosto;
    }

    public void setPcosto(float pcosto) {
        this.pcosto = pcosto;
    }

    public float getPventa() {
        return pventa;
    }

    public void setPventa(float pventa) {
        this.pventa = pventa;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    public DefaultTableModel tablaleer(){
        DefaultTableModel tabla = new DefaultTableModel();
         try{
     cn = new Conexion();
     cn.abrir_conexion();
      String query = "select p.idProducto as id,p.producto,m.marca,p.descripcion,p.precio_costo,p.precio_venta,p.existencia,m.idMarca FROM productos as p inner join marcas as m on p.idMarca = m.idMarca;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","producto","marca","descripcion","precio_costo","precio_venta","existencia","idMarca"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[8];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("producto");
          datos[2] = consulta.getString("marca");
          datos[3] = consulta.getString("descripcion");
          datos[4] = consulta.getString("precio_costo");
          datos[5] = consulta.getString("precio_venta");
          datos[6] = consulta.getString("existencia");
            datos[7] = consulta.getString("idMarca");
          tabla.addRow(datos);
      }
     cn.cerrar_conexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
        return tabla;
    }
    
    public int agregar() {
       int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into productos(producto,idMarca,descripcion,precio_costo,precio_venta,existencia) values(?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getProducto());
            parametro.setInt(2,getIdMarca());
            parametro.setString(3,getDescripcion());
            parametro.setFloat(4,getPcosto());
            parametro.setFloat(5,getPventa());
            parametro.setInt(6, getExistencia());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
     public int actualizar() {
         int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE productos set producto=?,idMarca=?,descripcion=?,precio_costo=?,precio_venta=?,existencia=? WHERE idProducto=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getProducto());
            parametro.setInt(2,getIdMarca());
            parametro.setString(3,getDescripcion());
            parametro.setFloat(4,getPcosto());
            parametro.setFloat(5,getPventa());
            parametro.setInt(6, getExistencia());
             parametro.setInt(7,this.getId_prod());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
     
     public int eliminar() {
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM productos WHERE idProducto=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,this.getId_prod());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
}
