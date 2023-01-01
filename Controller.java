/*CLASE CONEXION A LA DB*/

package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "root";
    private String nombreDB = "DB_Ventas";
    private String url="jdbc:mysql://localhost:3306/DB_Ventas?useUnicode=true&use" + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&" + "serverTimezone=UTC";
    
    Connection conectar = null;
    
    public Conexion() {

        try {
            Class.forName(driver);
            conectar = DriverManager.getConnection(url, user, password);
            if (conectar != null) {
                System.out.println("Conexion exitosa a la DB: " + nombreDB);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("ocurre una classnotfounts exocetion" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("ocurre un SQLException" + e.getMessage());
        }

    }
    
    public Connection getConnection(){
        return conectar;
    }

    public void desconectar() {
        conectar = null;
    }
    
}


/*CLASE ARRAY TRABAJADOR*/

package Controller;

import Modelo.Admin;
import Modelo.Trabajador;
import Modelo.Vendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArrayTrabajador {
    ArrayList<Trabajador> arrayTrabajador = new ArrayList<>();
    ArrayList<Admin> arrayAdmin = new ArrayList<>();
    ArrayList<Vendedor> arrayVendedor = new ArrayList<>();
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;

    public ArrayTrabajador() {
        leer_Trabajador();
        leer_Vendedor();
        leer_Admin();
        
    }
    public int correlativoTrabajador(){
        if(arrayTrabajador.size() == 0){
            return 1;
        }
        else{
            return arrayTrabajador.get(arrayTrabajador.size() - 1).getCodigo()+1;
        }
    }
    
    public int correlativoVendedor(){
        if(arrayVendedor.size() == 0){
            return 1;
        }
        else{
            return arrayVendedor.get(arrayVendedor.size() - 1).getCodigo()+1;
        }
    }
    
    public int correlativoAdmin(){
        if(arrayAdmin.size() == 0){
            return 1;
        }
        else{
            return arrayAdmin.get(arrayAdmin.size() - 1).getCodigo()+1;
        }
    }
    
    public void agregarDatosTraba(Trabajador obj){
        arrayTrabajador.add(obj);
    }
    
    public void agregarDatosVen(Vendedor obj){
        arrayVendedor.add(obj);
    }
    
    public void agregarDatosAdmin(Admin obj){
        arrayAdmin.add(obj);
    }
    
    public int rowsTraba(){
        return arrayTrabajador.size();
    }
    
    public int rowsVen(){
        return arrayVendedor.size();
    }
    
    public int rowsAdmin(){
        return arrayAdmin.size();
    }
    
    public Trabajador getT(int pos){
        return arrayTrabajador.get(pos);
    }
    
    public Vendedor getV(int pos){
        return arrayVendedor.get(pos);
    }
    
    public Admin getA(int posi){
        return arrayAdmin.get(posi);
    }
    
    public Trabajador buscar_PorDniTraba(String dni){
        Trabajador t = new Trabajador();
        t.setDni("Error");
        for(Trabajador x:arrayTrabajador){
            if(dni.equals(x.getDni())){
                return x;
            }
        }
        return t;
    }
    
    public Vendedor buscar_PorDniVen(String dni){
        Vendedor v = new Vendedor();
        v.setDni("Error");
        for(Vendedor x:arrayVendedor){
            if(dni.equals(x.getDni())){
                return x;
            }
        }
        return v;
    }
    
    public Admin buscar_PorDniAdmin(String dni){
        Admin ad = new Admin();
        ad.setDni("Error");
        for(Admin x:arrayAdmin){
            if(dni.equals(x.getDni())){
                return x;
            }
        }
        return ad;
    }
    
    public boolean modificarTraba(Trabajador t, String email, String direcc, String distri,String provin, double suel, String sistema, double seguro){
        for(int i = 0; i < rowsTraba(); i++){
            if(getT(i).getCodigo() == t.getCodigo()){
                arrayTrabajador.get(i).setEmail(email);
                arrayTrabajador.get(i).setDireccion(direcc);
                arrayTrabajador.get(i).setDistrito(distri);
                arrayTrabajador.get(i).setProvincia(provin);
                arrayTrabajador.get(i).setSueldo(suel);
                arrayTrabajador.get(i).setSistema_pension(sistema);
                arrayTrabajador.get(i).setSeguro_Salud(seguro);
                
                return true;
                
            }
            
        }
        return false;
    
    }
    
    public boolean modificarVen(Vendedor ven, String email, String direcc, String distri,String provin, double suel, String sistema, double seguro, int nH, double boni){
        for(int i = 0; i < rowsVen(); i++){
            if(getV(i).getCodigo() == ven.getCodigo()){
                arrayVendedor.get(i).setEmail(email);
                arrayVendedor.get(i).setDireccion(direcc);
                arrayVendedor.get(i).setDistrito(distri);
                arrayVendedor.get(i).setProvincia(provin);
                arrayVendedor.get(i).setSueldo(suel);
                arrayVendedor.get(i).setSistema_pension(sistema);
                arrayVendedor.get(i).setSeguro_Salud(seguro);
                arrayVendedor.get(i).setnHijos(nH);
                arrayVendedor.get(i).setBoniHijos(boni);
                
                return true;
                
            }
            
        }
        return false;
    
    }
    
    public void eliminarTraba(Trabajador t){
        arrayTrabajador.remove(getposarrayTraba(t));
    }
    
    public void eliminarVen(Vendedor v){
        arrayVendedor.remove(getposarrayVe(v));
    }
    
    public void eliminarAdmin(Admin ad){
        arrayAdmin.remove(getposarrayAdmin(ad));
    }
    
    public int getposarrayTraba(Trabajador t){
       for(int i=0;i<rowsTraba();i++){
            if(t.getDni() == arrayTrabajador.get(i).getDni()){
                return i;
            }
        }
       return -1;
    }
    
    public int getposarrayVe(Vendedor ve){
       for(int i=0;i<rowsVen();i++){
            if(ve.getDni() == arrayVendedor.get(i).getDni()){
                return i;
            }
        }
       return -1;
    }
    
    public int getposarrayAdmin(Admin ad){
       for(int i=0; i< rowsAdmin(); i++){
            if(ad.getDni() == arrayAdmin.get(i).getDni()){
                return i;
            }
        }
       return -1;
    }
    
    public void leer_Trabajador(){
        try{
            String sql = "select * from Trabajador";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Trabajador t = new Trabajador();
                
                t.setCodigo(rs.getInt("CodTrabajador"));
                t.setNombre(rs.getString("Nombre"));
                t.setApellido(rs.getString("Apellido"));
                t.setDni(rs.getString("DNI"));
                t.setSexo(rs.getString("Sexo"));
                t.setFecha_nacimiento(String.valueOf(rs.getDate("Fecha_Nacimiento")));
                t.setEmail(rs.getString("Email"));
                t.setDireccion(rs.getString("Direccion"));
                t.setDistrito(rs.getString("Distrito"));
                t.setProvincia(rs.getString("Provincia"));
                t.setPais(rs.getString("Pais"));
                t.setSueldo(rs.getDouble("Sueldo"));
                t.setSistema_pension(rs.getString("Sistema_pension"));
                t.setSeguro_Salud(rs.getDouble("Seguro_salud"));
                t.setFecha_ingreso(String.valueOf(rs.getDate("Fecha_ingreso")));
                t.setTipo_traba(rs.getString("Tipo_trabajador"));
                
                agregarDatosTraba(t);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void leer_Vendedor(){
        try{
            String sql = "select * from Vendedor_Consolidado";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Vendedor v = new Vendedor();
                
                v.setCodigo(rs.getInt("CodVendedor"));
                v.setNombre(rs.getString("Nombre"));
                v.setApellido(rs.getString("Apellido"));
                v.setDni(rs.getString("DNI"));
                v.setSexo(rs.getString("Sexo"));
                v.setFecha_nacimiento(String.valueOf(rs.getDate("Fecha_Nacimiento")));
                v.setEmail(rs.getString("Email"));
                v.setDireccion(rs.getString("Direccion"));
                v.setDistrito(rs.getString("Distrito"));
                v.setProvincia(rs.getString("Provincia"));
                v.setPais(rs.getString("Pais"));
                v.setSueldo(rs.getDouble("Sueldo"));
                v.setSistema_pension(rs.getString("Sistema_pension"));
                v.setSeguro_Salud(rs.getDouble("Seguro_salud"));
                v.setnHijos(rs.getInt("NHijos"));
                v.setBoniHijos(rs.getDouble("boniHijos"));
                v.setFecha_ingreso(String.valueOf(rs.getDate("Fecha_ingreso")));
                v.setTipo_traba(rs.getString("Tipo_trabajador"));
                
                agregarDatosVen(v);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void leer_Admin(){
        try{
            String sql = "select * from Admin_Consolidado";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Admin ad = new Admin();
                
                ad.setCodigo(rs.getInt("CodAdmin"));
                ad.setNombre(rs.getString("Nombre"));
                ad.setApellido(rs.getString("Apellido"));
                ad.setDni(rs.getString("DNI"));
                ad.setSexo(rs.getString("Sexo"));
                ad.setFecha_nacimiento(String.valueOf(rs.getDate("Fecha_Nacimiento")));
                ad.setEmail(rs.getString("Email"));
                ad.setDireccion(rs.getString("Direccion"));
                ad.setDistrito(rs.getString("Distrito"));
                ad.setProvincia(rs.getString("Provincia"));
                ad.setPais(rs.getString("Pais"));
                ad.setSueldo(rs.getDouble("Sueldo"));
                ad.setSistema_pension(rs.getString("Sistema_pension"));
                ad.setSeguro_Salud(rs.getDouble("Seguro_salud"));
                ad.setImpuesto(rs.getDouble("Monto_Impuesto"));
                ad.setUtilidades(rs.getDouble("Utilidades"));
                ad.setFecha_ingreso(String.valueOf(rs.getDate("Fecha_ingreso")));
                ad.setTipo_traba(rs.getString("Tipo_trabajador"));
                
                agregarDatosAdmin(ad);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Grabar_Trabajador(Trabajador t){
        try{
            if (t.getNombre().equals(null)) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Trabajador(" + t.getCodigo() + ",'" + t.getNombre() + "','" + t.getApellido() + "','" + t.getDni() + "','" + t.getSexo()+ "','" + t.getFecha_nacimiento()
                        +"','" + t.getEmail() + "','" + t.getDireccion() + "','" + t.getDistrito() + "','" + t.getProvincia() + "','" + t.getPais() + "'," + t.getSueldo()
                        + ",'"+ t.getSistema_pension() + "'," + t.getSeguro_Salud() + ",'" + t.getFecha_ingreso() + "','" + t.getTipo_traba() +"');";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Trabajador Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    public void Grabar_Vendedor(Vendedor v){
        try{
            if (v.getNombre().equals(null)) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Vendedor(" + v.getCodigo() + "," + v.getnHijos() + "," + v.getBoniHijos() + ");";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Vendedor Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    
    public void Grabar_Admin(Admin ad){
        try{
            if (ad.getNombre().equals(null)) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Administrador(" + ad.getCodigo() + "," + ad.getImpuesto() + "," + ad.getUtilidades() + ");";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Admin Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    public void grabarModificarTrabajador(int codT, String email, String direcc, String distri, String provin, double suel, String sistema, double seguro){
        try{
            String sql = "Call Modificar_Trabajador('" + email + "','" + direcc + "','" + provin + "','" + distri + "'," + suel + ",'" + sistema + "'," + seguro + "," + codT + ");";
            if (email != null && direcc != null) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Trabajador Modificado");
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
    }
    
    public void grabarModificarVendedor(int codV, int nH, double boni){
        try{
            String sql = "Call Modificar_Vendedor(" + nH + "," + boni + "," + codV + ");";
            if (nH != 0 && boni != 0) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Vendedor Modificado");
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
    }
    
    public void grabarEliminarTrabajador(int codT){
        try{
            String sql = "Call Eliminar_Trabajador(" + codT + ");";
            if (codT != -1) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Trabajador Eliminado");
                
              
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
    }
    
    public void grabarEliminarVendedor(int codV){
        try{
            String sql = "Call Eliminar_Vendedor(" + codV + ");";
            if (codV != -1) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Vendedor Eliminado");
                
              
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
    }
    
}


/*CLASE ARRAY USUARIO*/


package Controller;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArrayUsuario {

    ArrayList<Usuario> arrayUsuario = new ArrayList<>();
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;
    
    
    public ArrayUsuario() {
      leer();
    }
    
    public String validacion(String usuario, String contrasena){
        for(int i = 0; i < rows(); i++){
            if(arrayUsuario.get(i).getNombUsu().equalsIgnoreCase(usuario) && arrayUsuario.get(i).getContrasena().equalsIgnoreCase(contrasena) && arrayUsuario.get(i).getTipo_Usu().equalsIgnoreCase("Vendedor")){
                return "Vendedor";
                
            }
            else if(arrayUsuario.get(i).getNombUsu().equalsIgnoreCase(usuario) && arrayUsuario.get(i).getContrasena().equalsIgnoreCase(contrasena) && arrayUsuario.get(i).getTipo_Usu().equalsIgnoreCase("Admin")){
                return "Admin";
            }
        }
        return "Error";
    }
    
    public int buscarCodUsuario(String usu){
        for(int i = 0; i < rows(); i++){
            if(get(i).getNombUsu().equalsIgnoreCase(usu)){
                return get(i).getCodUsu();
            }
        }
        return -1;
    }
    public void agregarDatos(Usuario obj){
        arrayUsuario.add(obj);
    }
    
    public int correlativo(){
        if(arrayUsuario.size() == 0){
            return 1;
        }
        else{
            return arrayUsuario.get(arrayUsuario.size() - 1).getCodUsu()+1;
        }
    }
    
    public Usuario get(int posi){
        return arrayUsuario.get(posi);
    }
    
    public int rows(){
        return arrayUsuario.size();
    }
    
    public Usuario buscar_PorCod(int cod){
        Usuario u = new Usuario();
        u.setNombUsu("Error");
        for(Usuario x:arrayUsuario){
            if(cod == x.getCodUsu()){
                return x;
            }
        }
        return u;
    }
    
    public boolean modificar(Usuario u, String n){
        for(int i = 0; i < rows(); i++){
            if(u.getCodUsu() == get(i).getCodUsu()){
                arrayUsuario.get(i).setNombUsu(n);
                return true;
            }
        }
        return false;
    
    }
    
    public void eliminar(int cod){
       arrayUsuario.remove(getposarray(cod));
    }
    
    
    public int getposarray(int cod){
       for(int i=0;i<rows();i++){
            if(cod == arrayUsuario.get(i).getCodUsu()){
                return i;
            }
        }
       return -1;
    }
    
    public void leer(){
        try{
            String sql = "select * from Usuario";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Usuario u = new Usuario();
                
                u.setCodUsu(rs.getInt("CodUsuario"));
                u.setNombUsu(rs.getString("Nombre_Usu"));
                u.setContrasena(rs.getString("Contraseña"));
                u.setTipo_Usu(rs.getString("Tipo_Usu"));
                
                agregarDatos(u);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Grabar_Usuario(Usuario u){
        try{
            if (u.getNombUsu().equals(null)) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Usuario(" + u.getCodUsu()+ ",'" + u.getNombUsu() + "','" + u.getContrasena() + "','" + u.getTipo_Usu() + "');";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Usuario Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    public void grabarEliminarUsuario(int codU){
        try{
            String sql = "Call Eliminar_Usuario(" + codU + ");";
            if (codU != -1) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Usuario Eliminado");
                
              
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
    }
    
}



/*CLASE ARRAY CLIENTE*/


package Controller;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArrayCliente 
{
    ArrayList<Cliente> cl = new ArrayList();
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;
    
    
    public ArrayCliente()
    {
        CargarDatos();
    }
    
    public void AgregarArray(Cliente c)
    {
        cl.add(c);
    }
    
    public int Correlativo()
    {
        if(cl.isEmpty())
        {
            return 1;
        }
        else
        {
            return cl.get(cl.size()-1).getCode()+1;
        }
    }
    
    public int Filas(){
      return cl.size();
    }
  
    public Cliente get(int pos){
        return cl.get(pos);
    }

    public Cliente buscarDni(String dni)
    {
        Cliente c=new Cliente();
        c.setNombre("Error");
        for(Cliente x:cl){
            if(dni.equals(x.getDni()))
            {
                return x;
            }
        }
        return c;
    }
    public Cliente buscarCode(int cod)
    {
        Cliente c=new Cliente();
        c.setDni("Error");
        for(Cliente x:cl){
            if(cod == x.getCode())
            {
                return x;
            }
        }
        return c;
    }
    public Cliente buscarCodeV(int cod)
    {
        Cliente c=new Cliente();
        
        for(Cliente x:cl)
        {
            if(cod==x.getCode())
            {
                return x;
            }
        }
        return c;
    }
    public void eliminar(Cliente c)
     {
         cl.remove(getposarray(c));
     }
    public int getposarray(Cliente c){
         for(int i=0;i<Filas();i++){
             if(c.getCode()==cl.get(i).getCode()){
                 return i;
             }
         }

         return -1;
    }
    public boolean ModificarCode(Cliente c,String r, String e,String d,String di,String p){
         for(int i=0;i<Filas();i++){
             if(c.getCode()==get(i).getCode())
             {
                 cl.get(i).setRuc(r);
                 cl.get(i).setEmail(e);
                 cl.get(i).setDireccion(d);
                 cl.get(i).setDistrito(di);
                 cl.get(i).setProvincia(p);
                 return true;
             }
         }
         return false;
     }
    public boolean ModificarDni(Cliente c,String r,String e,String d,String di,String p,String pa)
     {
         for(int i=0;i<Filas();i++)
         {
             if(c.getDni()==get(i).getDni())
             {
                 cl.get(i).setRuc(r);
                 cl.get(i).setEmail(e);
                 cl.get(i).setDireccion(d);
                 cl.get(i).setDistrito(di);
                 cl.get(i).setProvincia(p);
                 cl.get(i).setPais(pa);
                 return true;
             }
         }
         return false;
    }
    
    public void CargarDatos(){
        try{
            String sql = "select * from Cliente";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Cliente c = new Cliente();
                
                c.setCode(rs.getInt("CodCliente"));
                c.setNombre(rs.getString("Nombre"));
                c.setApe(rs.getString("Apellido"));
                c.setDni(rs.getString("DNI"));
                c.setRuc(rs.getString("RUC"));
                c.setSexo(rs.getString("Sexo"));
                c.setFechaN(String.valueOf(rs.getDate("Fecha_Nacimiento")));
                c.setEmail(rs.getString("Email"));
                c.setDireccion(rs.getString("Direccion"));
                c.setDistrito(rs.getString("Distritos"));
                c.setProvincia(rs.getString("Provincia"));
                c.setPais(rs.getString("Pais"));
                
                AgregarArray(c);
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    public void Grabar_Cliente(Cliente c){
        try
        {
            if (c.getNombre().equals(null)) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Cliente(" + c.getCode() + ",'" + c.getNombre() + "','" + c.getApe() + "','" + c.getDni() + "','" + c.getRuc() + "','" + c.getSexo()+ "','" + c.getFechaN()
                        + "','" + c.getEmail() + "','" + c.getDireccion() + "','" + c.getDistrito() + "','" + c.getProvincia() + "','" + c.getPais() + "');";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Cliente Registrado con Exito");
                
            }
        } catch ( Exception e ){
            System.out.println(e);
        }
    }

    public void grabarModificarCliente(int codC, String ruc, String email, String direcc, String distri, String provin){
        try{
            String sql = "Call Modificar_Cliente('" + ruc + "','" + email + "','" + direcc + "','" + provin + "','" + distri + "'," + codC + ");";
            if (email != null && direcc != null) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Cliente Modificado");
            }
              
          }
          catch ( Exception e )
          {
              System.out.println(e);
          }
    }
    
    public void grabarEliminarCliente(int codC){
        try{
            String sql = "Call Eliminar_Cliente(" + codC + ");";
            if (codC != -1) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Cliente Eliminado");
                
              
        }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
    }


}


/*CLASE ARRAY DISTRIBUIDOR*/

package Controller;

import Modelo.Distribuidor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ArrayDistribuidor {
    ArrayList<Distribuidor> arrayDistribuidor = new ArrayList<>();
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;

    public ArrayDistribuidor() {
        leer();
    }
    
    public void agregarDatos(Distribuidor obj){
        arrayDistribuidor.add(obj);
    }
    
    public int correlativo(){
        if(arrayDistribuidor.size() == 0){
            return 1;
        }
        else{
            return arrayDistribuidor.get(arrayDistribuidor.size() - 1).getCodigo()+1;
        }
    }
    
    public Distribuidor get(int posi){
        return arrayDistribuidor.get(posi);
    }
    
    public int rows(){
        return arrayDistribuidor.size();
    }
    
    public Distribuidor buscar_PorCod(int cod){
        Distribuidor d = new Distribuidor();
        d.setRuc("Error");
        for(Distribuidor x:arrayDistribuidor){
            if(cod == x.getCodigo()){
                return x;
            }
        }
        return d;
    }
    public Distribuidor buscar_PorRuc(String ruc)
    {
        Distribuidor d = new Distribuidor();
        d.setDireccion("Error");
        for (Distribuidor x: arrayDistribuidor)
        {
            if (ruc == x.getRuc())
            {
                return x;
            }
        }
        return d;
    }
    
    public boolean modificar(Distribuidor d,String ra, String direc, String corr, String n, String p, String s){
        for(int i = 0; i < rows(); i++){
            if(d.getCodigo() == get(i).getCodigo()){
                arrayDistribuidor.get(i).setRazon_social(ra);
                arrayDistribuidor.get(i).setDireccion(direc);
                arrayDistribuidor.get(i).setCorreoEle_Contacto(corr);
                arrayDistribuidor.get(i).setTelefono_contacto(n);
                arrayDistribuidor.get(i).setPagina_web(p);
                arrayDistribuidor.get(i).setSucursal(s);
                
                return true;
            }
        }
        return false;
    
    }
    
    public void eliminar(Distribuidor d){
       arrayDistribuidor.remove(getposarray(d));
    }
    
    public int getposarray(Distribuidor d){
       for(int i=0;i<rows();i++){
            if(d.getCodigo() == arrayDistribuidor.get(i).getCodigo()){
                return i;
            }
        }
       return -1;
    }
    
    public void leer(){
        try{
            String sql = "select * from Distribuidor";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Distribuidor d = new Distribuidor();
                
                d.setCodigo(rs.getInt("CodDistribuidor"));
                d.setRazon_social(rs.getString("Razon_Social"));
                d.setRuc(rs.getString("RUC"));
                d.setSucursal(rs.getString("Surcursal"));
                d.setDireccion(rs.getString("Direccion"));
                d.setCorreoEle_Contacto(rs.getString("Correo_Contacto"));
                d.setPagina_web(rs.getString("Pagina_Web"));
                d.setTelefono_contacto(rs.getString("Telefono_Contacto"));
                
                agregarDatos(d);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Grabar_Distribuidor(Distribuidor d){
        try{
            if (d.getRazon_social().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Distribuidor(" + d.getCodigo() + ",'" + d.getRazon_social() + "','" + d.getRuc() + "','" + d.getSucursal() 
                        + "','" + d.getDireccion() + "','" + d.getCorreoEle_Contacto() + "','" + d.getPagina_web() + "','" + d.getTelefono_contacto() + "');";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Distribuidor Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    public void grabar_ModificarDistribuidor(int code, String rz, String surcu, String direc, String correo, String pag, String tele){
        try{
            String sql = "Call Modificar_Distribuidor(" + code + ",'" + rz + "','" + surcu + "','" + direc + "','" + correo + "','" + pag + "','" + tele + "');";
            if (rz != null && direc != null) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Distribuidor Modificado");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
            
        
    }
    
    public void grabar_EllminarDistribuidor(int code){
        try{
            String sql = "Call Eliminar_Distribuidor(" + code + ");";
            if (code != -1) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Distribuidor Eliminado");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}



/*CLASE ARRAY PRODUCTO*/

package Controller;

import Modelo.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ArrayProducto
{
    ArrayList<Producto> pr = new ArrayList();
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;
    
    public ArrayProducto()
    {
        CargarDatos();
    }
   
    public void AgregarArray(Producto p)
    {
        pr.add(p);
    }
   
    
    public int Correlativo()
    {
        if(pr.isEmpty())
        {
            return 1;
        }
        else
        {
            return pr.get(pr.size()-1).getCodigo()+1;
        }
    }
    
    public int Filas()
    {
      return pr.size();
    }
  
    public Producto get(int pos){
        return pr.get(pos);
    }

    public Producto buscarNombre(String nombre)
    {
        Producto p = new Producto();
        p.setCodigo(0);
        for(Producto x:pr){
            if(nombre.equalsIgnoreCase(x.getNombreProduc()))
            {
                return x;
            }
        }
        return p;
    }
    public Producto buscarCode(int cod)
    {
        Producto p = new Producto();
        p.setNombreProduc("Error");
        for(Producto x:pr){
            if(cod == x.getCodigo())
            {
                return x;
            }
        }
        return p;
    }

    public boolean ModificarCode(Producto p, String co, String t,int s, double c,double pre, String fi){
         for(int i=0;i<Filas();i++){
             if(p.getCodigo() == get(i).getCodigo())
             {
                 pr.get(i).setColor(co);
                 pr.get(i).setTamaño(t);
                 pr.get(i).setStock(s);
                 pr.get(i).setCosto(c);
                 pr.get(i).setPrecio(pre);
                 pr.get(i).setFechaIngreso(fi);
                 return true;
             }
         }
         return false;
     }
     public boolean ModificarNombre(Producto p, String co, String t,int s, double c,double pre, String fi)
     {
         for(int i=0;i<Filas();i++)
         {
             if(p.getNombreProduc()==get(i).getNombreProduc())
             {
                 
                 pr.get(i).setColor(co);
                 pr.get(i).setTamaño(t);
                 pr.get(i).setStock(s);
                 pr.get(i).setCosto(c);
                 pr.get(i).setPrecio(pre);
                 pr.get(i).setFechaIngreso(fi);
                 return true;
             }
         }
         return false;
     }
     public void modificarStock(Producto p, int cod, int n){
        for(int i=0;i<Filas();i++){
            if(p.getCodigo() == get(i).getCodigo()){
                pr.get(i).setStock(pr.get(i).getStock() - n);
                
                
            }
            if(cod ==get(i).getCodigo()){
                pr.get(i).setStock(pr.get(i).getStock() - n);
                
            }
        }
        
     }
     public void actualizarStock(int cod, int n){
        for(int i=0;i<Filas();i++){
            if(cod == get(i).getCodigo()){
                pr.get(i).setStock(pr.get(i).getStock() + n);
                
            }
        }
        
     }
     
     public void CargarDatos()
    {
        
        try
        {
            String sql = "select * from Producto_Consolidado";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Producto p = new Producto();
                
                p.setCodigo(rs.getInt("CodProducto"));
                p.setNombreProduc(rs.getString("Nombre_Producto"));
                p.setColor(rs.getString("Color"));
                p.setTamaño(rs.getString("Tamaño"));
                p.setStock(rs.getInt("Stock"));
                p.setCosto(rs.getDouble("Costo_Prod"));
                p.setPrecio(rs.getDouble("Precio_Prod"));
                p.setFechaIngreso(rs.getString("Fecha_Ingreso"));
                p.setFechaVencimiento(rs.getString("Fecha_Vencimiento"));
                p.setDistribuidor(rs.getString("Razon_Social"));
                
                AgregarArray(p);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
    }

     public void grabar_Producto(Producto p, int codeD){
          try
          {
            if (p.getNombreProduc().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Producto(" + p.getCodigo() + "," + codeD + ",'" + p.getNombreProduc() + "','" + p.getColor()
                        + "','" + p.getTamaño() + "'," + p.getStock() + "," + p.getCosto() + "," + p.getPrecio() + ",'" + p.getFechaIngreso() + "','" + p.getFechaVencimiento() + "');";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Producto Registrado con Exito");
                
            }
          }
          catch ( Exception e )
          {
              System.out.println(e);
          }
     }
     
     public void Grabar_ModificarProducto(int codP, String color, String tamaño, int stock, double costo, double precio, String fechaI){
          try{
            String sql = "Call Modificar_Producto(" + codP + ",'" + color + "','" + tamaño + "'," + stock + "," + costo + "," + precio + ",'" + fechaI + "');";
            
            if (color != null && tamaño != null) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Producto Modificado");
            }
              
          }
          catch ( Exception e )
          {
              System.out.println(e);
          }
     }
     
     public void Grabar_ModificarProducto_Stock(int codP, int cantidad){
          try{
            String sql = "Call Modificar_Stock(" + codP + "," + cantidad + ");";
            
            if (codP != -1 && cantidad != -1) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Stock Modificado");
            }
              
          }
          catch ( Exception e )
          {
              System.out.println(e);
          }
     }
     
     public void Grabar_EliminarProducto(int codP){
          try{
            String sql = "Call Eliminar_Producto(" + codP + ");";
            
            if (codP != -1) {
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Producto Eliminado");
            }
              
          }
          catch ( Exception e )
          {
              System.out.println(e);
          }
     }
     
     
     public void eliminar(Producto p)
     {
         pr.remove(getposarray(p));
     }
     public int getposarray(Producto p){
         for(int i=0;i<Filas();i++){
             if(p.getCodigo()==pr.get(i).getCodigo()){
                 return i;
             }
         }

         return -1;
     }
}



/*CLASE ARRAY DETALLE VENTA*/

package Controller;

import Modelo.Boleta;
import Modelo.DetalleVenta;
import Modelo.Factura;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ArrayDetalleVenta {
    ArrayList<DetalleVenta> arrayDetalleVenta = new ArrayList<>();
    ArrayList<Boleta> arrayBoleta = new ArrayList<>();
    ArrayList<Factura> arrayFactura = new ArrayList<>();
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;

    public ArrayDetalleVenta() {
        leerDetalle();
        leerBoleta();
        leerFactura();
    }
    
    public void agregarDatosDetalle(DetalleVenta obj){
        arrayDetalleVenta.add(obj);
    }
    
    public void agregarDatosBoleta(Boleta obj){
        arrayBoleta.add(obj);
    }
    
    public void agregarDatosFactura(Factura obj){
        arrayFactura.add(obj);
    }
    
    public int correlativoDetalleVenta(){
        if(arrayDetalleVenta.size() == 0){
            return 1;
        }
        else{
            return arrayDetalleVenta.get(arrayDetalleVenta.size() - 1).getID()+1;
        }
    }
    
    public int correlativoBoleta(){
        if(arrayBoleta.size() == 0){
            return 1;
        }
        else{
            return arrayBoleta.get(arrayBoleta.size() - 1).getIdVenta()+1;
        }
    }
    
    public int correlativoFactura(){
        if(arrayFactura.size() == 0){
            return 1;
        }
        else{
            return arrayFactura.get(arrayFactura.size() - 1).getIdVenta()+1;
        }
    }
    
    public DetalleVenta getDetalleVenta(int posi){
        return arrayDetalleVenta.get(posi);
    }
    
    public Boleta getBoleta(int posi){
        return arrayBoleta.get(posi);
    }
    
    public Factura getFactura(int posi){
        return arrayFactura.get(posi);
    }
    
    public int rowsDetalleVenta(){
        return arrayDetalleVenta.size();
    }
    
    public int rowsBoleta(){
        return arrayBoleta.size();
    }
    
    public int rowsFactura(){
        return arrayFactura.size();
    }
    
    public DetalleVenta buscar_PorCod(int cod){
        DetalleVenta dv = new DetalleVenta();
        dv.setIdVenta(-1);
        for(DetalleVenta x:arrayDetalleVenta){
            if(cod == x.getIdVenta()){
                return x;
            }
        }
        return dv;
    }
    
    public Boleta buscar_PorCodBoleta(int cod){
        Boleta b = new Boleta();
        b.setIdVenta(-1);
        for(Boleta x:arrayBoleta){
            if(cod == x.getIdVenta()){
                return x;
            }
        }
        return b;
    }
    
    public Factura buscar_PorCodFactura(int cod){
        Factura f = new Factura();
        f.setIdVenta(-1);
        for(Factura x:arrayFactura){
            if(cod == x.getIdVenta()){
                return x;
            }
        }
        return f;
    }
    
    public void leerDetalle(){
        try{
            String sql = "select * from DetalleVenta";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                DetalleVenta dv = new DetalleVenta();
                
                dv.setID(rs.getInt("ID"));
                dv.setIdVenta(rs.getInt("CodDetalleVenta"));
                dv.setIdVendedor(rs.getInt("CodVendedor"));
                dv.setIdProducto(rs.getInt("CodProducto"));
                dv.setCantidadPro(rs.getInt("CantidadProducto"));
                dv.setTipo(rs.getString("Tipo"));
                
                agregarDatosDetalle(dv);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void leerBoleta(){
        try{
            String sql = "select * from Boleta_Consolidado";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Boleta b = new Boleta();
                
                b.setID(rs.getInt("ID"));
                b.setIdVenta(rs.getInt("CodDetalleVenta"));
                b.setIdVendedor(rs.getInt("CodVendedor"));
                b.setIdProducto(rs.getInt("CodProducto"));
                b.setCantidadPro(rs.getInt("CantidadProducto"));
                b.setPrecioPro(rs.getDouble("Precio_Prod"));
                b.setTotal(rs.getDouble("Total"));
                b.setTipo(rs.getString("Tipo"));
                
                agregarDatosBoleta(b);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void leerFactura(){
        try{
            String sql = "select * from Factura_Consolidado";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Factura f = new Factura();
                
                f.setID(rs.getInt("ID"));
                f.setIdVenta(rs.getInt("CodDetalleVenta"));
                f.setIdVendedor(rs.getInt("CodVendedor"));
                f.setIdProducto(rs.getInt("CodProducto"));
                f.setCantidadPro(rs.getInt("CantidadProducto"));
                f.setPrecioPro(rs.getDouble("Precio_Prod"));
                f.setSubtotal(rs.getDouble("Subtotal"));
                f.setTotal(rs.getDouble("Total"));
                f.setTipo(rs.getString("Tipo"));
                
                agregarDatosFactura(f);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void Grabar_DetalleVenta(DetalleVenta dv){
        try{
            if (dv.getTipo().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_DetalleVenta(" + dv.getID() + "," + dv.getIdVenta() + "," + dv.getIdVendedor() + "," + dv.getIdProducto() + "," + dv.getCantidadPro()
                        + ",'" + dv.getTipo() + "');";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "DetalleVenta Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    public void Grabar_Boleta(Boleta b){
        try{
            if (b.getTipo().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Boleta(" + b.getID() + "," + b.getIdVenta() + "," + b.getTotal() + ");";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Boleta Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
    public void Grabar_Factura(Factura f){
        try{
            if (f.getTipo().equals("")) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Factura(" + f.getID() + "," + f.getIdVenta() + "," + f.getSubtotal() + "," + f.getTotal() + ");";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Boleta Registrado con Exito");
                
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
  
  
  /*CLASE ARRAY VENTA*/

package Controller;

import Modelo.Venta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ArrayVenta {
    ArrayList<Venta> arrayVenta = new ArrayList<>();
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;

    public ArrayVenta() {
        leer();
    }
    
    public void agregarDatos(Venta obj){
        arrayVenta.add(obj);
    }
    
    public int correlativoBoleta(){
        if(arrayVenta.size() == 0){
            return 1;
        }
        else{
            return arrayVenta.get(arrayVenta.size() - 1).getIdVenta()+1;
        }
    }
    
    public int correlativoFactura(){
        if(arrayVenta.size() == 0){
            return 1;
        }
        else{
            return arrayVenta.get(arrayVenta.size() - 1).getIdVenta()+1;
        }
    }
    
    public Venta get(int posi){
        return arrayVenta.get(posi);
    }
    
    public int rows(){
        return arrayVenta.size();
    }
    
    public Venta buscar_PorCod(int cod){
        Venta v = new Venta();
        v.setIdVenta(-1);
        for(Venta x:arrayVenta){
            if(cod == x.getIdVenta()){
                return x;
            }
        }
        return v;
    }
    
    public boolean modificar(Venta v, String n){
        for(int i = 0; i < rows(); i++){
            if(v.getIdVenta() == get(i).getIdVenta()){
                arrayVenta.get(i).setFecha(n);
                
                return true;
            }
        }
        return false;
    
    }
    
    public void eliminar(Venta v){
       arrayVenta.remove(getposarray(v));
    }
    
    
    public int getposarray(Venta v){
       for(int i=0;i<rows();i++){
            if(v.getIdVenta() == arrayVenta.get(i).getIdVenta()){
                return i;
            }
        }
       return -1;
    }

    public void leer(){
        try{
            String sql = "select * from Venta";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
            while(rs.next()){
                
                Venta v = new Venta();
                
                v.setIdVenta(rs.getInt("CodVenta"));
                v.setIdVendedor(rs.getInt("CodVendedor"));
                v.setIdCliente(rs.getInt("CodCliente"));
                v.setMontoTotal(rs.getDouble("Monto_Total"));
                v.setFecha(String.valueOf(rs.getDate("Fecha_Venta")));
                
                agregarDatos(v);
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Grabar_Venta(Venta v){
        try{
            if (v.getIdVenta() == -1) {
                JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");             
            } else {
                String sql = "Call Insertar_Venta(" + v.getIdVenta() + "," + v.getIdVendedor() + "," + v.getIdCliente() + "," + v.getMontoTotal()
                        + ",'" + v.getFecha() + "');";
                con = cn.getConnection();
                st = con.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Venta Registrado con Exito");
                
            }
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
}


/*CLASE ARRAY CARRITO*/

package Controller;

import Modelo.Carrito;
import java.util.ArrayList;


public class ArrayCarrito {
    ArrayList<Carrito> arrayCarrito = new ArrayList<>();

    public ArrayCarrito() {
    }
    
    public void agregarDatos(Carrito obj){
        arrayCarrito.add(obj);
    }
    
    public int rows(){
        return arrayCarrito.size();
    }
    
    public Carrito get(int posi){
        return arrayCarrito.get(posi);
    }
    
    public void eliminarTodo(){
        arrayCarrito.clear();
       
    }
    public void eliminar(int posiCod){
        arrayCarrito.remove(getposarray(posiCod));
    }
    public int getposarray(int posiCod){
         for(int i=0;i<rows();i++){
             if(posiCod== arrayCarrito.get(i).getCod()){
                 return i;
             }
         }

         return -1;
     }
    
    public Carrito buscarCode(int cod)
    {
        Carrito c = new Carrito();
        c.setNombrePr("Error");
        for(Carrito x:arrayCarrito){
            if(cod == x.getCod())
            {
                return x;
            }
        }
        return c;
    }
    
    public boolean modificar(int posiCod, int n){
        for(int i = 0; i < rows(); i++){
            if(posiCod == get(i).getCod()){
                arrayCarrito.get(i).setCantidad(n);
                
                return true;
            }
        }
        return false;
    }
    
    public double acumuladorTotal(){
        double Acumulador = 0;
        for(int i = 0; i < rows(); i++){
            Acumulador += arrayCarrito.get(i).Subtotal();
        }
        return Acumulador;
    }
    
    
}

