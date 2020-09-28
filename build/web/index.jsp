<%-- 
    Document   : index
    Created on : 21/09/2020, 15:53:07
    Author     : oscar_lopez
--%>
<%@page import="modelo.Marca" %>
<%@page import="modelo.Producto"%>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        
    </head>
    <body>
        <h1 align ="center">Lista de Productos</h1>
        <button align ="center" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_producto" onclick="limpiar()">Nuevo</button>
        
    <div class="container">
          <div class="modal fade" id="modal_producto" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
            <form action="sr_productos" method="post" class="form-group">
               <label for="lbl_id" ><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0"  readonly> 
                <label for="lbl_producto" ><b>Producto</b></label>
                <input type="text" name="txt_producto" id="txt_producto" class="form-control" placeholder="Gorra" required>
                <label for="lbl_marca" ><b>Marca</b></label>
                  <select name="drop_producto" id="drop_producto" class="form-control">
                      <%
                          Marca marca = new Marca();
                          HashMap<String,String> drop = marca.drop_Marca();
                          for (String i:drop.keySet()){
                              out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");   
                          }
                      %>
                </select>
                <label for="lbl_descripcion" ><b>Descripcion</b></label>
                <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Talla 11" required>
                <label for="lbl_pcosto" ><b>Precio Costo</b></label>
                <input type="number" name="txt_pcosto" id="txt_pcosto" class="form-control" placeholder="Ejemplo: 500" required>
                <label for="lbl_pventa" ><b>Precio Venta</b></label>
                <input type="number" name="txt_pventa" id="txt_pventa" class="form-control" placeholder="Ejemplo: 1000" required>
                <label for="lbl_existencia" ><b>Existencia</b></label>
                <input type="number"  name="txt_existencia" id="txt_existencia" class="form-control" required>                    
                <br>
                <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_modificar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
                <button type="button" class="btn btn-warning btn-lg" data-dismiss="modal">Cerrar</button>
            </form>
        </div>
      </div>
      
    </div>
  </div>
           
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Id</th>
        <th>Producto</th>
        <th>Marca</th>
        <th>Descripcion</th>
        <th>Precio Costo</th>
        <th>Precio Venta</th>
        <th>Existencia</th>
      </tr>
    </thead>
    <tbody id="tbl_productos">
        <%
            Producto producto = new Producto(); 
            DefaultTableModel tabla = new DefaultTableModel();
            tabla=producto.tablaleer();
            for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id_m=" + tabla.getValueAt(t,7) + ">");
            out.println("<td>" + tabla.getValueAt(t,0) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,3) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
            out.println("</tr>");
        
        }
            %>
      
    </tbody>
  </table>
  </div>
      

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <script type="text/javascript">
        function limpiar(){
        $("#txt_id").val(0);
       $("#txt_producto").val('');
       $("#drop_producto").val('1');
       $("#txt_descripcion").val('');
       $("#txt_pcosto").val('');
       $("#txt_pventa").val('');
       $("#txt_existencia").val('');
    }
    
    $('#tbl_productos').on('click','tr td',function(evt){
       var target,id,id_m,producto,descripcion,precio_costo,precio_venta,existencias; 
       target = $(event.target); 
       id_m = target.parent().data('id_m'); 
       id = target.parent("tr").find("td").eq(0).html();
       producto= target.parent("tr").find("td").eq(1).html();
       descripcion = target.parent("tr").find("td").eq(3).html();
       precio_costo = target.parent("tr").find("td").eq(4).html();
       precio_venta = target.parent("tr").find("td").eq(5).html();
       existencias = target.parent("tr").find("td").eq(6).html();
       $("#txt_id").val(id);
       $("#txt_producto").val(producto);
       $("#drop_producto").val(id_m);
       $("#txt_descripcion").val(descripcion);
       $("#txt_pcosto").val(precio_costo);
       $("#txt_pventa").val(precio_venta);
       $("#txt_existencia").val(existencias);
       $("#modal_producto").modal('show');
    });
    
    </script>
    </body>
</html>
