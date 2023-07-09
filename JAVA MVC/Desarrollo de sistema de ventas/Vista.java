/*FRAME MENU INICIAL*/

package Vista.FrmGeneral;

import Vista.FrmGeneral.LoginGeneral;


public class Menu_Inicial extends javax.swing.JFrame {

    
    public Menu_Inicial() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    
    @SuppressWarnings("unchecked")
  
  private void BSalirActionPerformed(java.awt.event.ActionEvent evt) {                                       
        System.exit(0);
    }
  
  private void BFrmRegistrarMouseClicked(java.awt.event.MouseEvent evt) {                                           
        Menu_Registrar m2 = new Menu_Registrar();
        m2.setVisible(true);
        this.dispose();
    }                                          

    private void BFrmLoginMouseClicked(java.awt.event.MouseEvent evt) {                                       
        LoginGeneral lg1 = new LoginGeneral();
        lg1.setVisible(true);
        this.dispose();
    }                                      

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Inicial().setVisible(true);
            }
        });
    }
  
    private javax.swing.JButton BFrmLogin;
    private javax.swing.JButton BFrmRegistrar;
    private javax.swing.JButton BSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
                  
}





/*FRAME MENU REGISTRAR USUARIO*/

package Vista.FrmGeneral;

import Controller.ArrayTrabajador;
import Vista.FrmGeneral.LoginGeneral;
import Controller.ArrayUsuario;
import Controller.Conexion;
import Modelo.Admin;
import Modelo.Trabajador;
import Modelo.Usuario;
import Modelo.Vendedor;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


public class Menu_Registrar extends javax.swing.JFrame {

    ArrayUsuario arrayUsu = new ArrayUsuario();
    ArrayTrabajador arrayTraba = new ArrayTrabajador();
    
    Date fecha = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
    
    String fecha_ingresoActual;
    
    public Menu_Registrar() {
        Conexion con = new Conexion();
        initComponents();
        this.setLocationRelativeTo(null);
        txtNombre.requestFocus();
        this.OCULTAR.setVisible(false);
        
        
        fecha_ingresoActual = formato.format(fecha);
        
    }
    public void Boton()
    {
        if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtDni.getText().isEmpty() || txtUsuario.getText().isEmpty() || JPContrasena.getText().isEmpty() || txtCorreoElec.getText().isEmpty() || txtDireccion.getText().isEmpty())
        {
            BRegistrar.setEnabled(false);
        }
        else
        {
            BRegistrar.setEnabled(true);
        }
    }
    
    @SuppressWarnings("unchecked")


private void BRegistrarMouseClicked(java.awt.event.MouseEvent evt) {                                        
        int cod2 = 0;
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String dni = txtDni.getText();
        String fecha_nacimiento = formato.format(JDtFechaNaci.getDate());
        String sexo = null;
        String usuario = txtUsuario.getText();
        String contrasena = JPContrasena.getText();
        String correo_elec = txtCorreoElec.getText();
        String direccion = txtDireccion.getText();
        String pais = JCPais.getSelectedItem().toString();
        String provincia = JCProvincia.getSelectedItem().toString();
        String distrito = JCDistrito.getSelectedItem().toString();
        String pension;
        String tipo_usu = null;
        int nHijos = Integer.parseInt(SHijos.getValue().toString());
        
        if(JRSexoM.isSelected()){
            sexo = "Masculino";
        }else{
            sexo = "Femenino";
        }
        
        if(JROnp.isSelected()){
            pension = "ONP";
        }else{
            pension = "AFP";
        }
        
        if(JRAdmin.isSelected()){
            tipo_usu = "Admin";
          
        }else{
            tipo_usu = "Vendedor";
        }
        
        
        cod2 = arrayTraba.correlativoTrabajador();
        
        
        if(tipo_usu == "Admin"){
            
            Admin ad = new Admin(cod2,nombre,apellido,dni,sexo,fecha_nacimiento,correo_elec,direccion,distrito,provincia,pais,2500.00,pension,150,0.15,0.3,fecha_ingresoActual,tipo_usu);
            ad.descSistema_pension();
            ad.operaciónSueldo();
            
            arrayTraba.agregarDatosAdmin(ad);
            
            Trabajador tra = new Trabajador(cod2,nombre,apellido,dni,sexo,fecha_nacimiento,correo_elec,direccion,distrito,provincia,pais,ad.operaciónSueldo(),pension,150,fecha_ingresoActual,tipo_usu);
            arrayTraba.agregarDatosTraba(tra);
            arrayTraba.Grabar_Trabajador(tra);
            
            arrayTraba.Grabar_Admin(ad);
            
            
            Usuario usu = new Usuario(cod2, usuario, contrasena, tipo_usu);
        
            arrayUsu.agregarDatos(usu);
            arrayUsu.Grabar_Usuario(usu);
            
        }else{
            
            Vendedor ve = new Vendedor(cod2,nombre,apellido,dni,sexo,fecha_nacimiento,correo_elec,direccion,distrito,provincia,pais,1100.00,pension,150,nHijos,0.1,fecha_ingresoActual,tipo_usu);
            ve.descSistema_pension();
            ve.operaciónSueldo();
            
            arrayTraba.agregarDatosVen(ve);
            
            
            Trabajador tra = new Trabajador(cod2,nombre,apellido,dni,sexo,fecha_nacimiento,correo_elec,direccion,distrito,provincia,pais,ve.operaciónSueldo(),pension,150,fecha_ingresoActual,tipo_usu);
            arrayTraba.agregarDatosTraba(tra);
            arrayTraba.Grabar_Trabajador(tra);
            
            arrayTraba.Grabar_Vendedor(ve);
            
            Usuario usu = new Usuario(cod2, usuario, contrasena, tipo_usu);
        
            arrayUsu.agregarDatos(usu);
            arrayUsu.Grabar_Usuario(usu);
        }
        
        
        
        LoginGeneral lg01 = new LoginGeneral();
        lg01.setVisible(true);
        this.dispose();
        
    }                                       
    public void limpiar(){
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtDni.setText(null);
        JDtFechaNaci.setDate(null);
        buttonGroup1.clearSelection();
        txtUsuario.setText(null);
        JPContrasena.setText(null);
        txtCorreoElec.setText(null);
        txtDireccion.setText(null);
        JCPais.setSelectedIndex(0);
        JCProvincia.setSelectedIndex(0);
        JCDistrito.setSelectedIndex(0);
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        SHijos.setValue(0);
        
    }
    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {                                
        if(txtDni.getText().length() >=8){
            evt.consume();
        }
        Boton();
    }                                                                       

    private void JRVendedorMouseClicked(java.awt.event.MouseEvent evt) {                                        
        SHijos.setEnabled(true);
    }                                       

    private void JRAdminMouseClicked(java.awt.event.MouseEvent evt) {                                     
        SHijos.setEnabled(false);
    }                                                                                

    private void VERMouseClicked(java.awt.event.MouseEvent evt) {                                 
        VER.setVisible(false);
        OCULTAR.setVisible(true);
        JPContrasena.setEchoChar((char)0);
    }                                

    private void OCULTARMouseClicked(java.awt.event.MouseEvent evt) {                                     
        VER.setVisible(true);
        OCULTAR.setVisible(false);
        JPContrasena.setEchoChar('●');
    }                                    

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Menu_Inicial ni = new Menu_Inicial();
        ni.setVisible(true);
        this.dispose();
    }                                        

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {                                   
        Boton();
        Validar();
    }                                  

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {                                     
        Boton();
        Validar();
    }                                    

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {                                    
        Boton();
        Validar();
    }                                   

    private void JPContrasenaKeyTyped(java.awt.event.KeyEvent evt) {                                      
        Boton();
        Validar();
    }                                     

    private void txtCorreoElecKeyTyped(java.awt.event.KeyEvent evt) {                                       
        Boton();
        Validar();
    }                                      

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {                                      
        Boton();
        Validar();
    }                                     
                                          

    private void JCProvinciaItemStateChanged(java.awt.event.ItemEvent evt) {                                             
       if(evt.getStateChange() == ItemEvent.SELECTED){
            if(JCProvincia.getSelectedIndex() > 0){
                JCDistrito.setModel(new DefaultComboBoxModel(this.getDistritos(JCProvincia.getSelectedItem().toString())));
            }
        }
    }                                            

                                             
    public void Validar()
    {
        if(txtNombre.getText().isEmpty())
        {
            jLabel1.setText("*Campo Vacio*");
        }
        else
        {
            jLabel1.setText("");
        }
        
        if(txtApellido.getText().isEmpty())
        {
            jLabel2.setText("*Campo Vacio*");
        }
        else
        {
            jLabel2.setText("");
        }
        
        if(txtDni.getText().isEmpty())
        {
            jLabel3.setText("*Campo Vacio*");
        }
        else
        {
            jLabel3.setText("");
        }
        
        if(txtUsuario.getText().isEmpty())
        {
            jLabel8.setText("*Campo Vacio*");
        }
        else
        {
            jLabel8.setText("");
        }
        
        if(JPContrasena.getText().isEmpty())
        {
            jLabel9.setText("*Campo Vacio*");
        }
        else
        {
            jLabel9.setText("");
        }
        
        if(txtCorreoElec.getText().isEmpty())
        {
            jLabel10.setText("*Campo Vacio*");
        }
        else
        {
            jLabel10.setText("");
        }
        
        if(txtDireccion.getText().isEmpty())
        {
            jLabel12.setText("*Campo Vacio*");
        }
        else
        {
            jLabel12.setText("");
        }
        
    }
    public String[] getDistritos(String pais){
        String[] distritos = null;
        BufferedReader lector;
        if(pais.equalsIgnoreCase("Lima Metropolitana")){
            distritos = new String[44];
            try{
                File archivoTXT = new File("DistritosLima.txt");
                FileReader fr = new FileReader(archivoTXT);
                lector = new BufferedReader(fr);
            
                String linea;
                int i = 0;
                while((linea = lector.readLine()) != null){
                    distritos[i] = linea;    
                    i++;

                }
                lector.close();
            
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return distritos;
        }
        
        if(pais.equalsIgnoreCase("Cañete")){
            distritos = new String[17];
            try{
                File archivoTXT = new File("DistritosCañete.txt");
                FileReader fr = new FileReader(archivoTXT);
                lector = new BufferedReader(fr);
            
                String linea;
                int i = 0;
                while((linea = lector.readLine()) != null){
                    distritos[i] = linea;    
                    i++;

                }
                lector.close();
            
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return distritos;
        }
        return distritos;
    }
    
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Registrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Registrar().setVisible(true);
            }
        });
    }
                
    private javax.swing.JButton BRegistrar;
    private javax.swing.JComboBox<String> JCDistrito;
    private javax.swing.JComboBox<String> JCPais;
    private javax.swing.JComboBox<String> JCProvincia;
    private com.toedter.calendar.JDateChooser JDtFechaNaci;
    private javax.swing.JPasswordField JPContrasena;
    private javax.swing.JRadioButton JRAdmin;
    private javax.swing.JRadioButton JRAfp;
    private javax.swing.JRadioButton JROnp;
    private javax.swing.JRadioButton JRSexoF;
    private javax.swing.JRadioButton JRSexoM;
    private javax.swing.JRadioButton JRVendedor;
    private javax.swing.JLabel OCULTAR;
    private javax.swing.JSpinner SHijos;
    private javax.swing.JLabel VER;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreoElec;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
                     
}





/*FRAME LOGIN GENERAL*/

package Vista.FrmGeneral;

import Controller.ArrayUsuario;
import Modelo.Usuario;
import Vista.FrmAdmin.Login_IngresoAdmin;
import Vista.FrmVendedor.Login_IngresoVendedor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoginGeneral extends javax.swing.JFrame {
    
    ArrayUsuario archi = new ArrayUsuario();
    
    public LoginGeneral() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtUsuario.requestFocus();
        
        
    }

    @SuppressWarnings("unchecked")

  
private void BSalirActionPerformed(java.awt.event.ActionEvent evt) {                                       
        Menu_Inicial ni = new Menu_Inicial();
        ni.setVisible(true);
        this.dispose();
        
    }                                      

    private void txtContrasenaKeyTyped(java.awt.event.KeyEvent evt) {                                       
        Validar();
        Boton_Validar();
    }                                                                                  

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {                                    
        Validar();
        Boton_Validar();
    }                                                                             

    private void INGRESARActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        String rpta;

        rpta = archi.validacion(usuario, contrasena);
        int cod = archi.buscarCodUsuario(usuario);

        if(rpta == "Vendedor"){
            Login_IngresoVendedor Lv = new Login_IngresoVendedor(cod);
            Lv.setVisible(true);
            this.dispose();
        }
        else if (rpta == "Admin"){
            Login_IngresoAdmin Li = new Login_IngresoAdmin();
            Li.setVisible(true);
            this.dispose();

        }
        else{
            LbError.setText("USUARIO O CONTRASENA INCORRECTA");
        }

        txtUsuario.setText("");
        txtContrasena.setText("");
    }                                        
    public void Validar(){
        if(txtUsuario.getText().isEmpty()){
            jLabelUsuario.setText("*CAMPO VACIO*");
        }
        else{
            jLabelUsuario.setText("");
        }

        if(txtContrasena.getText().isEmpty())
        {
            contra.setText("*CAMPO VACIO*");
        }
        else{
            contra.setText("");
        }
    }
    public void Boton_Validar(){
        if(txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty()){
            INGRESAR.setEnabled(false); 
        }
        else{
            INGRESAR.setEnabled(true);
        }
    }
    
   
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGeneral().setVisible(true);
            }
        });
    }

                     
    private javax.swing.JButton BSalir;
    private javax.swing.JButton INGRESAR;
    private javax.swing.JLabel LbError;
    private javax.swing.JLabel contra;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtUsuario;
                      
}




/*FRAME MENU MANTENIMIENTO GENERAL ADMIN*/

package Vista.FrmAdmin;

import javax.swing.JOptionPane;
import Vista.FrmVendedor.Login_IngresoVendedor;

public class Login_IngresoAdmin extends javax.swing.JFrame {

    public Login_IngresoAdmin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")                     

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int salida = JOptionPane.showConfirmDialog(null,"¿Estas seguro que deseas salir?","Salida",JOptionPane.YES_NO_OPTION);
        if(salida==0)
        {
            System.exit(0);
        }
    }                                        

    private void VENDEDORActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Mantenimiento_Vendedor m2 = new Mantenimiento_Vendedor();
        m2.setVisible(true);
        this.dispose();
    }                                        

    private void CLIENTEActionPerformed(java.awt.event.ActionEvent evt) {                                        
        Mantenimiento_Cliente m = new Mantenimiento_Cliente();
        m.setVisible(true);
        this.dispose();
    }                                       

    private void PROVEEDORActionPerformed(java.awt.event.ActionEvent evt) {                                          
        MantenimientoProveedor mp = new MantenimientoProveedor();
        mp.setVisible(true);
        this.dispose();
    }                                         

    private void PRODUCTOSActionPerformed(java.awt.event.ActionEvent evt) {                                          
        Mantenimiento_Producto lp = new Mantenimiento_Producto();
        lp.setVisible(true);
        this.dispose();
    }                                         

    private void VENTAActionPerformed(java.awt.event.ActionEvent evt) {                                      
        int c=0;
        Login_IngresoVendedor v = new Login_IngresoVendedor(c);
       v.setVisible(true);
       this.dispose();
    }                                     

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login_IngresoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_IngresoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_IngresoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_IngresoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_IngresoAdmin().setVisible(true);
            }
        });
    }

                    
    private javax.swing.JButton CLIENTE;
    private javax.swing.JButton PRODUCTOS;
    private javax.swing.JButton PROVEEDOR;
    private javax.swing.JButton VENDEDOR;
    private javax.swing.JButton VENTA;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
                     
}



/*FRAME MANTENIMIENTO VENDEDOR*/


package Vista.FrmAdmin;

import Controller.ArrayTrabajador;
import Controller.ArrayUsuario;
import Controller.Conexion;
import Modelo.Admin;
import Modelo.Trabajador;
import Modelo.Usuario;
import Modelo.Vendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Mantenimiento_Vendedor extends javax.swing.JFrame {
    
    
    String nombre;
    String apellido;
    String dni;
    String fecha_nacimiento;
    String sexo = null;
    String usuario;
    String contrasena;
    String correo_elec;
    String direccion;
    String pais;
    String provincia;
    String distrito;
    String pension;
    int nHijos;
    
    boolean senial0 = false;
    boolean senial = false;
    boolean senial2 = false;
    boolean senial3 = false;
    
    ArrayUsuario arrayUsu = new ArrayUsuario();
    ArrayTrabajador arrayTraba = new ArrayTrabajador();
    DefaultTableModel modelTable = new DefaultTableModel();
    Trabajador modi2 = null;
    Vendedor modi = null;
    Trabajador Eli2 = null;
    Vendedor Eli = null;
    Date fecha = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
    
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;
    
    String fecha_ingresoActual;
    int siguiente = 0;
    public Mantenimiento_Vendedor() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtNombre.requestFocus();
        this.BOcultar1.setVisible(false);
        JCPais.setSelectedItem(null);
        JCProvincias.setSelectedItem(null);
        JCDistritos.setSelectedItem(null);
        
        fecha_ingresoActual = formato.format(fecha);
        
        modelTable.addColumn("CodVendedor");
        modelTable.addColumn("NOMBRE");
        modelTable.addColumn("APELLIDO");
        modelTable.addColumn("DNI");
        modelTable.addColumn("SEXO");
        modelTable.addColumn("F.NACIMIENTO");
        modelTable.addColumn("E-MAIL");
        modelTable.addColumn("DIRECCION");
        modelTable.addColumn("DISTRITO");
        modelTable.addColumn("PROVINCIA");
        modelTable.addColumn("PAIS");
        modelTable.addColumn("SUELDO");
        modelTable.addColumn("SISTEMA PENSION");
        modelTable.addColumn("SEGURO DE SALUD");
        modelTable.addColumn("N.HIJOS");
        modelTable.addColumn("BONI");
        modelTable.addColumn("FECHA_INGRESO");
        
        
        this.TbVendedor.setModel(modelTable);
        
        ListarTabla();
    }
  
    @SuppressWarnings("unchecked")

private void BMostrar1ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        BMostrar1.setVisible(false);
        BOcultar1.setVisible(true);
        JPContrasena.setEchoChar((char)0);
    }                                         

    private void BAnadirActionPerformed(java.awt.event.ActionEvent evt) {                                        
        int cod2 = 0;
        nombre = txtNombre.getText();
        apellido = txtApellidos.getText();
        dni = txtDni.getText();
        fecha_nacimiento = formato.format(JDFecha_nacimiento.getDate());
        sexo = null;
        usuario = txtUsuario.getText();
        contrasena = JPContrasena.getText();
        correo_elec = txtCorreo.getText();
        direccion = txtDireccion.getText();
        pais = JCPais.getSelectedItem().toString();
        provincia = JCProvincias.getSelectedItem().toString();
        distrito = JCDistritos.getSelectedItem().toString();
        pension=null;
        
        nHijos = Integer.parseInt(SHijos.getValue().toString());
        
        if(JRMasculino.isSelected()){
            sexo = "Masculino";
        }else{
            sexo = "Femenino";
        }
        
        if(JROnp.isSelected()){
            pension = "ONP";
        }else{
            pension = "AFP";
        }
        
        cod2 = arrayTraba.correlativoTrabajador();
        
        
        Vendedor ve = new Vendedor(cod2,nombre,apellido,dni,sexo,fecha_nacimiento,correo_elec,direccion,distrito,provincia,pais,1100.00,pension,150,nHijos,0.1,fecha_ingresoActual,"Vendedor");
        ve.descSistema_pension();
        ve.operaciónSueldo();
            
        arrayTraba.agregarDatosVen(ve);
            
        Trabajador tra = new Trabajador(cod2,nombre,apellido,dni,sexo,fecha_nacimiento,correo_elec,direccion,distrito,provincia,pais,ve.operaciónSueldo(),pension,150,fecha_ingresoActual,"Vendedor");
        arrayTraba.agregarDatosTraba(tra);
        arrayTraba.Grabar_Trabajador(tra);
            
        arrayTraba.Grabar_Vendedor(ve);
            
          
        Usuario usu = new Usuario(cod2, usuario, contrasena, "Vendedor");
        
        arrayUsu.agregarDatos(usu);
        arrayUsu.Grabar_Usuario(usu);
        
        senial0 = true;
        
        
    }                                       

    private void BOcultar1ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        BMostrar1.setVisible(true);
        BOcultar1.setVisible(false);
        JPContrasena.setEchoChar('●');
    }                                         

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {                                
        if(txtDni.getText().length() >=8){
            evt.consume();
        }
    }                               

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        
        if(senial0 == true){
            LimpiarTabla();
            ListarTabla();
        }
        else if(senial == true){
            LimpiarTabla();
            ListarTabla();
        }else if(senial2 == true){
            LimpiarTabla();
            ListarTabla();
        }else if(senial3 == true){
            LimpiarTabla();
            ListarTabla();
        }else{
            
        }
        
        
      
    }                                           

    private void lookforActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
            String dnia = dnione.getText();
            Vendedor ven = arrayTraba.buscar_PorDniVen(dnia);

            if (!"Error".equals(ven.getNombre())) {
                codeone.setText(Integer.toString(ven.getCodigo()));
                namess.setText(ven.getNombre());
                apee.setText(ven.getApellido());
                txtSeguro_Salud.setText(String.valueOf(ven.getSeguro_Salud()));
                txtSueldo.setText(String.valueOf(ven.getSueldo()));
                txtN_Hijos.setText(String.valueOf(ven.getnHijos()));
                txtBonificacion.setText(String.valueOf(ven.getBoniHijos()));
                txtFecha_Ingreso.setText(ven.getFecha_ingreso());
                age.setText(ven.getFecha_nacimiento());
                sex.setText(ven.getSexo());
                email.setText(ven.getEmail());
                direcc.setText(ven.getDireccion());
                country.setText(ven.getPais());
                province.setText(ven.getProvincia());
                district.setText(ven.getDistrito());
                system.setText(ven.getSistema_pension());
                JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
            } else {
                LimpiarBtn();
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
          
        
    }                                       
    public void LimpiarBtn()
    {
        codeone.setText(null);
        namess.setText("");
        apee.setText("");
        age.setText("");
        sex.setText("");
        email.setText("");
        direcc.setText("");
        country.setText("");
        province.setText("");
        district.setText("");
        system.setText("");
    }
    private void dni1ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        
        if(dni1.isSelected())
        {
            dnione.setText(null);
            dnione.setEditable(true);
            codeone.setEnabled(false);
        }
        else
        {
            dnione.setEditable(false);
            codeone.setEnabled(true);
        }
    }                                                                      

    private void lookfor1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        String dnii = dnione1.getText();
        modi2 = arrayTraba.buscar_PorDniTraba(dnii);
        modi = arrayTraba.buscar_PorDniVen(dnii);

        if (!"Error".equals(modi.getNombre())) {
            codeone1.setText(Integer.toString(modi.getCodigo()));
            namess1.setText(modi.getNombre());
            apee1.setText(modi.getApellido());
            txtSeguro1.setText(String.valueOf(modi.getSeguro_Salud()));
            txtSueldo1.setText(String.valueOf(modi.getSueldo()));
            txtNHijos1.setText(String.valueOf(modi.getnHijos()));
            txtBoni1.setText(String.valueOf(modi.getBoniHijos()));
            txtFechaIngreso1.setText(modi.getFecha_ingreso());
            age1.setText(modi.getFecha_nacimiento());
            sex1.setText(modi.getSexo());
            email1.setText(modi.getEmail());
            direcc1.setText(modi.getDireccion());
            country1.setText(modi.getPais());
            province1.setText(modi.getProvincia());
            district1.setText(modi.getDistrito());
            system1.setText(modi.getSistema_pension());
            
            txtSeguro1.setEditable(true);
            txtSueldo1.setEditable(true);
            txtNHijos1.setEditable(true);
            txtBoni1.setEditable(true);
            email1.setEditable(true);
            direcc1.setEditable(true);
            province1.setEditable(true);
            district1.setEditable(true);
            system1.setEditable(true);
            JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
        } else {
            LimpiarBtn();
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }                                        
    public void LimpiarModify()
    {
        codeone1.setText(null);
        namess1.setText(null);
        dnione1.setText(null);
        apee1.setText(null);
        txtSeguro1.setText(null);
        txtSueldo1.setText(null);
        txtNHijos1.setText(null);
        txtBoni1.setText(null);
        txtFechaIngreso1.setText(null);
        age1.setText(null);
        sex1.setText(null);
        email1.setText(null);
        direcc1.setText(null);
        country1.setText(null);
        province1.setText(null);
        district1.setText(null);
        system1.setText(null);
    }
    private void lookfor2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        
        int rpt1 = JOptionPane.showConfirmDialog(null,"¿DESEA MODIFICAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt1==0)
            {
                int cod;
                double seguroSalud1;
                double sueldo1;
                int nHijos1;
                double boni1;
                String emailemail;
                String direcdirec;
                String provinceprovince;
                String districtdistrict;
                String systemsystem;
                
                cod = Integer.valueOf(codeone1.getText());
                seguroSalud1 = Double.valueOf(txtSeguro1.getText());
                sueldo1 = Double.valueOf(txtSueldo1.getText());
                nHijos1 = Integer.valueOf(txtNHijos1.getText());
                boni1 = Double.valueOf(txtBoni1.getText());
                emailemail = email1.getText();
                direcdirec = direcc1.getText();
                provinceprovince = province1.getText();
                districtdistrict = district1.getText();
                systemsystem = system1.getText();
                
                boolean mood2 = arrayTraba.modificarTraba(modi2, emailemail, direcdirec, districtdistrict, provinceprovince, sueldo1, systemsystem, seguroSalud1);
                boolean mood = arrayTraba.modificarVen(modi, emailemail, direcdirec, districtdistrict, provinceprovince, sueldo1, systemsystem, seguroSalud1, nHijos1, boni1);
                
                if(mood2 == true && mood == true)
                {
                    
                    arrayTraba.grabarModificarTrabajador(cod, emailemail, direcdirec, districtdistrict, provinceprovince, sueldo1, systemsystem, seguroSalud1);
                    arrayTraba.grabarModificarVendedor(cod, nHijos1, boni1);
                    LimpiarModify();
                    
                    
                    senial = true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "REGISTRO NO MODIFICADO");
                }
            }
    }                                        

    private void dni3ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        
    }                                    

    private void lookfor3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        String dnitwo = dnione2.getText();
        Eli2 = arrayTraba.buscar_PorDniTraba(dnitwo);
        Eli = arrayTraba.buscar_PorDniVen(dnitwo);

        if (!"Error".equals(Eli.getNombre())) {
            codeone2.setText(Integer.toString(Eli.getCodigo()));
            namess2.setText(Eli.getNombre());
            apee2.setText(Eli.getApellido());
            txtSeguroS2.setText(String.valueOf(Eli.getSeguro_Salud()));
            txtSueldo2.setText(String.valueOf(Eli.getSueldo()));
            txtNHijos2.setText(String.valueOf(Eli.getnHijos()));
            txtBoni2.setText(String.valueOf(Eli.getBoniHijos()));
            txtFechaIng2.setText(Eli.getFecha_ingreso());
            age2.setText(Eli.getFecha_nacimiento());
            sex2.setText(Eli.getSexo());
            email2.setText(Eli.getEmail());
            direcc2.setText(Eli.getDireccion());
            country2.setText(Eli.getPais());
            province2.setText(Eli.getProvincia());
            district2.setText(Eli.getDistrito());
            system2.setText(Eli.getSistema_pension());
            
            namess2.setEditable(true);
            apee2.setEditable(true);
            age2.setEditable(true);
            sex2.setEditable(true);
            email2.setEditable(true);
            direcc2.setEditable(true);
            country2.setEditable(true);
            province2.setEditable(true);
            district2.setEditable(true);
            system2.setEditable(true);
            JOptionPane.showMessageDialog(null, "REGISTRO ENCONTRADO");
            
        } else {
            LimpiarBtn();
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }                                        
    public void LimpiarEli()
    {
        codeone2.setText(null);
        namess2.setText(null);
        dnione2.setText(null);
        apee2.setText(null);
        txtSeguroS2.setText(null);
        txtSueldo2.setText(null);
        txtNHijos2.setText(null);
        txtBoni2.setText(null);
        txtFechaIng2.setText(null);
        age2.setText(null);
        sex2.setText(null);
        email2.setText(null);
        direcc2.setText(null);
        country2.setText(null);
        province2.setText(null);
        district2.setText(null);
        system2.setText(null);
    }
    private void lookfor4ActionPerformed(java.awt.event.ActionEvent evt) {                                         

        int cod = Integer.valueOf(codeone2.getText());
        
        int rpt3 = JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt3==0)
            {
                if(!"Error".equals(Eli.getNombre()) && !"Error".equals(Eli2.getNombre()))
                {
                    arrayUsu.eliminar(cod);
                    arrayUsu.grabarEliminarUsuario(cod);
                    arrayTraba.eliminarTraba(Eli2);
                    arrayTraba.eliminarVen(Eli);
                    arrayTraba.grabarEliminarVendedor(cod);
                    arrayTraba.grabarEliminarTrabajador(cod);
                    
                    LimpiarEli();
                    JOptionPane.showMessageDialog(null,"REGISTRO ELIMINADO SATISFACTORIAMENTE","AVISO",JOptionPane.OK_OPTION);
                    senial3 = true;
                }
                else
                {
                     JOptionPane.showMessageDialog(null,"NO EXISTE EL CODIGO DEL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
         int rp = JOptionPane.showConfirmDialog(null, "DESEA CANCELAR EL REGISTRO?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) {
            JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", JOptionPane.WARNING_MESSAGE);
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {                                   
        
        Validar();
    }                                  

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {                                      
        
        Validar();
    }                                     

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {                                    
        
        Validar();
    }                                   

    private void JPContrasenaKeyTyped(java.awt.event.KeyEvent evt) {                                      
        
        Validar();
    }                                     

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {                                   
        
        Validar();
    }                                  

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {                                      
        
        Validar();
    }                                     

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

                                             
    public void ListarTabla(){
        
        String sql = "select * from Vendedor_Consolidado";
        try {
            con = cn.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            String[] filas = new String[17];
            
            modelTable = (DefaultTableModel) TbVendedor.getModel();
            
            while (rs.next()) {
                filas[0] = String.valueOf(rs.getInt("CodVendedor"));
                filas[1] = rs.getString("Nombre");
                filas[2] = rs.getString("Apellido");
                filas[3] = rs.getString("DNI");
                filas[4] = rs.getString("Sexo");
                filas[5] = String.valueOf(rs.getDate("Fecha_Nacimiento"));
                filas[6] = rs.getString("Email");
                filas[7] = rs.getString("Direccion");
                filas[8] = rs.getString("Distrito");
                filas[9] = rs.getString("Provincia");
                filas[10] = rs.getString("Pais");
                filas[11] = String.valueOf(rs.getDouble("Sueldo"));
                filas[12] = rs.getString("Sistema_pension");
                filas[13] = String.valueOf(rs.getDouble("Seguro_salud"));
                filas[14] = String.valueOf(rs.getInt("NHijos"));
                filas[15] = String.valueOf(rs.getDouble("boniHijos"));
                filas[16] = String.valueOf(rs.getDate("Fecha_ingreso"));
                
                modelTable.addRow(filas);
            }
            TbVendedor.setModel(modelTable);

        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }
    public void LimpiarTabla(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) TbVendedor.getModel();
            int filas = TbVendedor.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }

    }
    public void Validar()
    {
        if(txtNombre.getText().isEmpty() || txtApellidos.getText().isEmpty() || txtDni.getText().isEmpty() || txtUsuario.getText().isEmpty() || JPContrasena.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtDireccion.getText().isEmpty())
        {
            CAMPO.setText("*Hay Campos Vacio!");
            BAnadir.setEnabled(false);
        }
        else
        {
            CAMPO.setText("");
            BAnadir.setEnabled(true);
        }
    }
   
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Vendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Vendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Vendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Vendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mantenimiento_Vendedor().setVisible(true);
            }
        });
    }
                      
}




/*FRAME MANTENIMIENTO*/

package Vista.FrmAdmin;

import Controller.ArrayCliente;
import Controller.Conexion;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Mantenimiento_Cliente extends javax.swing.JFrame {

    int code = 0;
    String ruc = null;
    String dni;
    String nombre;
    String ape;
    String fecha;
    String sexo;
    String correo;
    String direccion;
    String distrito;
    String provincia;
    String pais;
    
    
    boolean senial = false;
    boolean senial2 = false;
    boolean senial3 = false;
    

    SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
    ArrayCliente ar = new ArrayCliente();
    Cliente cModificar = new Cliente();
    Cliente cEliminar = new Cliente();

    DefaultTableModel table = new DefaultTableModel();

    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;
    
    public Mantenimiento_Cliente() {
        initComponents();
        this.setLocationRelativeTo(null);
        table.addColumn("CODIGO");
        table.addColumn("NOMBRE");
        table.addColumn("APELLIDO");
        table.addColumn("DNI");
        table.addColumn("RUC");
        table.addColumn("SEXO");
        table.addColumn("F.NACIMIENTO");
        table.addColumn("E-MAIL");
        table.addColumn("DIRECCION");
        table.addColumn("DISTRITO");
        table.addColumn("PROVINCIA");
        table.addColumn("PAIS");
        
        
        this.TABLE.setModel(table);
        ListarClientes();
        
        

    }

    @SuppressWarnings("unchecked")
  
  private void REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {                                          

        code = ar.Correlativo();
        String c = Integer.toString(code);
        CODE.setText(c);
        ruc = txtRuc1.getText();
        int rpt;
        dni = DNI.getText();
        nombre = NOMBRE.getText();
        ape = APELLIDO.getText();
        fecha = sd.format(CALENDER.getDate());
        correo = CORREO.getText();
        direccion = DIRECCION.getText();
        distrito = DISTRITO.getSelectedItem().toString();
        provincia = PROVINCIA.getSelectedItem().toString();
        pais = PAIS.getSelectedItem().toString();

        if (HOMBRE.isSelected()) {
            sexo = HOMBRE.getText();
        } else {
            sexo = MUJER.getText();
        }

        rpt = JOptionPane.showConfirmDialog(null, "DESEA REGISTRAR ESTE CLIENTE?", "AVISO", JOptionPane.YES_NO_OPTION);
        if (rpt == 0) {
            
            Cliente b = new Cliente();
            b.setCode(code);
            b.setDni(dni);
            b.setRuc(ruc);
            b.setNombre(nombre);
            b.setApe(ape);
            b.setFechaN(fecha);
            b.setSexo(sexo);
            b.setEmail(correo);
            b.setDireccion(direccion);
            b.setDistrito(distrito);
            b.setProvincia(provincia);
            b.setPais(pais);

            ar.AgregarArray(b);
            ar.Grabar_Cliente(b);
            senial = true;

            JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO", "AVISO", JOptionPane.OK_OPTION);
            Limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "ERROR", JOptionPane.WARNING_MESSAGE);
        }


    }                                         

    private void CANCELARActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int rp = JOptionPane.showConfirmDialog(null, "DESEA CANCELAR EL REGISTRO?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) {
            JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", JOptionPane.WARNING_MESSAGE);
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

    private void REGRESARActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }

    }                                        

    private void DNIKeyTyped(java.awt.event.KeyEvent evt) {                             
        if (DNI.getText().length() >= 8) {
            evt.consume();
        }
        boton();
        Validar();
    }                            

    private void NOMBREKeyTyped(java.awt.event.KeyEvent evt) {                                
        boton();
        Validar();
    }                               

    private void APELLIDOKeyTyped(java.awt.event.KeyEvent evt) {                                  
        boton();
        Validar();
    }                                 

    private void HOMBREKeyTyped(java.awt.event.KeyEvent evt) {                                
        boton();
        Validar();
    }                               

    private void MUJERKeyTyped(java.awt.event.KeyEvent evt) {                               
        boton();
        Validar();
    }                              

    private void CORREOKeyTyped(java.awt.event.KeyEvent evt) {                                
        boton();
        Validar();
    }                               

    private void DIRECCIONKeyTyped(java.awt.event.KeyEvent evt) {                                   
        boton();
        Validar();
    }                                  

    private void DNI1KeyTyped(java.awt.event.KeyEvent evt) {                              
       if (DNI1.getText().length() >= 8) {
            evt.consume();
        }
        botonDni();
        Validar();
    }                                                                    

    private void BUSCARActionPerformed(java.awt.event.ActionEvent evt) {                                       

        if (DNIB.isSelected()) {
            dni = DNI1.getText();
            Cliente c = ar.buscarDni(dni);

            if (c.getNombre() != "Error") {
                CODE1.setText(Integer.toString(c.getCode()));
                NOMBRE1.setText(c.getNombre());
                APELLIDO1.setText(c.getApe());
                NACIMIENTO1.setText(c.getFechaN());
                txtRuc.setText(c.getRuc());
                SEXO1.setText(c.getSexo());
                CORREO1.setText(c.getEmail());
                DIRECCION1.setText(c.getDireccion());
                DISTRITO1.setText(c.getDistrito());
                PROVINCIA1.setText(c.getProvincia());
                PAIS1.setText(c.getPais());
                AVISO.setText("REGISTRO ENCONTRADO!!!");
            } else {
                LimpiarB();
                AVISO.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);

            }
        } else if (CODIGOB.isSelected()) {
            code = Integer.parseInt(CODE1.getText());
            Cliente c1 = ar.buscarCode(code);
            
            if (c1.getDni() != "Error") {
                DNI1.setText(c1.getDni());
                NOMBRE1.setText(c1.getNombre());
                APELLIDO1.setText(c1.getApe());
                txtRuc.setText(c1.getRuc());
                NACIMIENTO1.setText(c1.getFechaN());
                SEXO1.setText(c1.getSexo());
                CORREO1.setText(c1.getEmail());
                DIRECCION1.setText(c1.getDireccion());
                DISTRITO1.setText(c1.getDistrito());
                PROVINCIA1.setText(c1.getProvincia());
                PAIS1.setText(c1.getPais());
                AVISO.setText("REGISTRO ENCONTRADO!!!");
            } else {
                LimpiarB();
                AVISO.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);

            }
        }

    }                                      

    private void REGRESAR1ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int rpt = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.OK_OPTION);
        if (rpt == 0) {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                         

    private void CODIGOBActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if (CODIGOB.isSelected())
        {
            CODE1.setText(null);
            CODE1.setEditable(true);
            DNIB.setEnabled(false);
            
        }
        else
        {
            CODE1.setEditable(false);
            DNIB.setEnabled(true);
        }

    }                                       

    private void DNIBActionPerformed(java.awt.event.ActionEvent evt) {                                     
        if (DNIB.isSelected()) 
        {
            DNI1.setText(null);
            DNI1.setEditable(true);
            CODIGOB.setEnabled(false);
            
        } 
        else 
        {
            DNI1.setEditable(false);
            CODIGOB.setEnabled(true);
            
        }
    }                                    


    private void BUSCAREActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if (DNI2.isSelected()) {
            dni = DNIE.getText();
            cEliminar = ar.buscarDni(dni);
            if (cEliminar.getNombre() != "Error") 
            {
                CODEE.setText(Integer.toString(cEliminar.getCode()));
                NOMBREE.setText(cEliminar.getNombre());
                APELLIDOE.setText(cEliminar.getApe());
                txtRuc2.setText(cEliminar.getRuc());
                FECHAE.setText(cEliminar.getFechaN());
                SEXOE.setText(cEliminar.getSexo());
                CORREOE.setText(cEliminar.getEmail());
                DIRECCIONE.setText(cEliminar.getDireccion());
                DISTRITOE.setText(cEliminar.getDistrito());
                PROVINCIAE.setText(cEliminar.getProvincia());
                PAISE.setText(cEliminar.getPais());
                AVISO1.setText("REGISTRO ENCONTRADO!!!");
            }
            else 
            {
                LimpiarE();
                AVISO1.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
                
            }
        } else if (CODIGOE.isSelected()) 
        {
            code = Integer.parseInt(CODEE.getText());
            cEliminar = ar.buscarCode(code);
            if (cEliminar.getDni() != "Error") 
            {
                DNIE.setText(cEliminar.getDni());
                NOMBREE.setText(cEliminar.getNombre());
                APELLIDOE.setText(cEliminar.getApe());
                txtRuc2.setText(cEliminar.getRuc());
                FECHAE.setText(cEliminar.getFechaN());
                SEXOE.setText(cEliminar.getSexo());
                CORREOE.setText(cEliminar.getEmail());
                DIRECCIONE.setText(cEliminar.getDireccion());
                DISTRITOE.setText(cEliminar.getDistrito());
                PROVINCIAE.setText(cEliminar.getProvincia());
                PAISE.setText(cEliminar.getPais());
                AVISO1.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
                LimpiarE();
                AVISO1.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }

        }
    }                                       

    private void CODIGOEActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if (CODIGOE.isSelected()) 
        {
            CODEE.setText(null);
            CODEE.setEditable(true);
            DNI2.setEnabled(false);
        } else 
        {
            CODEE.setEditable(false);
            DNI2.setEnabled(true);
        }
    }                                       

    private void DNI2ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        if (DNI2.isSelected())
        {
            DNIE.setText(null);
            DNIE.setEditable(true);
            CODIGOE.setEnabled(false);
        } 
        else
        {
            DNIE.setEditable(false);
            CODIGOE.setEnabled(true);
        }
    }                                                                 

    private void CODE1KeyTyped(java.awt.event.KeyEvent evt) {                               
        botonCode();
    }                              

    private void DNIEKeyTyped(java.awt.event.KeyEvent evt) {                              
       if (DNIE.getText().length() >= 8) {
            evt.consume();
        }
        botonDniE();
    }                                                           

    private void CODEEKeyTyped(java.awt.event.KeyEvent evt) {                               
        botonCodeE();
    }                              

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        if(DNI2.isSelected())
        {
            int rpt1 = JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt1==0)
            {
                if(cEliminar.getNombre() != "Error")
                {
                    ar.eliminar(cEliminar);
                    ar.grabarEliminarCliente(cEliminar.getCode());
                    JOptionPane.showMessageDialog(null,"REGISTRO ELIMINADO SATISFACTORIAMENTE","AVISO",JOptionPane.OK_OPTION);
                    LimpiarE();
                    AVISO1.setText("");
                    senial3 = true;
                    
                }
                else
                {
                    AVISO1.setText("");
                     JOptionPane.showMessageDialog(null,"NO EXISTE EL CODIGO DEL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else if(CODIGOE.isSelected())
        {
            int rpt = JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt==0)
            {
                if(cEliminar.getDni() != "Error")
                {
                    ar.eliminar(cEliminar);
                    ar.grabarEliminarCliente(cEliminar.getCode());
                    JOptionPane.showMessageDialog(null,"REGISTRO ELIMINADO SATISFACTORIAMENTE","AVISO",JOptionPane.OK_OPTION);
                    LimpiarE();
                    AVISO1.setText("");
                    senial3 = true;
                }
                else
                {
                    AVISO1.setText("");
                     JOptionPane.showMessageDialog(null,"NO EXISTE EL CODIGO DEL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }                                        

    private void BRegresar03ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
        
    }                                           

    private void BRegresar04ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                                                              

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(senial == true){
            LimpiarTabla();
            ListarClientes();
        }else if(senial2 == true){
            LimpiarTabla();
            ListarClientes();
        }else if(senial3 == true){
            LimpiarTabla();
            ListarClientes();
        }else{
            
        }
    }                                           

    private void MODIFICARActionPerformed(java.awt.event.ActionEvent evt) {                                          
        if(CODIGOM.isSelected())
        {
            int rpt1 = JOptionPane.showConfirmDialog(null,"DESEA MODIFICAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt1==0)
            {
                String ruc,correo,direccion,distrito,provincia,pais;
                int codeb = Integer.valueOf(CODEM.getText());
                ruc = txtRuc3.getText();
                correo = CORREOM.getText();
                direccion = DIRECCIONM.getText();
                distrito = DISTRITOM.getText();
                provincia = PROVINCIAM.getText();
                pais = PAISM.getText();
                boolean estado = ar.ModificarCode(cModificar, ruc, correo,direccion,distrito,provincia);
                if(estado==true)
                {
                    LimpiarM();
                    AVISO2.setText("");
                    JOptionPane.showMessageDialog(null,"REGISTRO MODIFICADO","MODIFICADO",JOptionPane.OK_OPTION);
                    ar.grabarModificarCliente(codeb, ruc, correo, direccion, distrito, provincia);

                    senial2 = true;
                }
                else
                {

                    JOptionPane.showMessageDialog(null,"NO EXISTE EL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else if(DNIM.isSelected())
        {
            int rpt1 = JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt1==0)
            {
                String nombre,ape,correo,direccion,distrito,provincia,pais;
                int codeb = Integer.valueOf(CODEM.getText());
                nombre = NOMBREM.getText();
                ape = APELLIDOM.getText();
                String ruc = txtRuc3.getText();
                correo = CORREOM.getText();
                direccion = DIRECCIONM.getText();
                distrito = DISTRITOM.getText();
                provincia = PROVINCIAM.getText();
                pais = PAISM.getText();
                boolean estado = ar.ModificarCode(cModificar, ruc, correo,direccion,distrito,provincia);
                if(estado==true)
                {
                    LimpiarM();
                    AVISO2.setText("");
                    JOptionPane.showMessageDialog(null,"REGISTRO MODIFICADO","MODIFICADO",JOptionPane.OK_OPTION);
                    ar.grabarModificarCliente(codeb, ruc, correo, direccion, distrito, provincia);

                    senial2 = true;
                }
                else
                {

                    JOptionPane.showMessageDialog(null,"NO EXISTE EL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }                                         

    private void BRegresar02ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                                                      

    private void DNIMActionPerformed(java.awt.event.ActionEvent evt) {                                     
        if(DNIM.isSelected())
        {
            DNI3.setText("");
            DNI3.setEditable(true);
            CODIGOM.setEnabled(false);
        }
        else
        {
            DNI3.setEditable(false);
            CODIGOM.setEnabled(true);
        }
    }                                    
                               

    private void CODIGOMActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(CODIGOM.isSelected())
        {
            CODEM.setText(null);
            CODEM.setEditable(true);
            DNIM.setEnabled(false);
        }
        else
        {
            CODEM.setEditable(false);
            DNIM.setEnabled(true);
        }
    }                                       

    private void CODEMKeyTyped(java.awt.event.KeyEvent evt) {                               
        botonCodeM();
    }                              

    private void BUSCARMActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(CODIGOM.isSelected())
        {

            code = Integer.parseInt(CODEM.getText());
            cModificar = ar.buscarCode(code);
            if(cModificar.getDni() != null)
            {
                DNI3.setText(cModificar.getDni());
                NOMBREM.setText(cModificar.getNombre());
                APELLIDOM.setText(cModificar.getApe());
                NACIMIENTOM.setText(cModificar.getFechaN());
                txtRuc3.setText(cModificar.getRuc());
                SEXOM.setText(cModificar.getSexo());
                CORREOM.setText(cModificar.getEmail());
                DIRECCIONM.setText(cModificar.getDireccion());
                DISTRITOM.setText(cModificar.getDistrito());
                PROVINCIAM.setText(cModificar.getProvincia());
                PAISM.setText(cModificar.getPais());

                
                CORREOM.setEditable(true);
                DIRECCIONM.setEditable(true);
                DISTRITOM.setEditable(true);
                PROVINCIAM.setEditable(true);
                txtRuc3.setEditable(true);

                AVISO2.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
                LimpiarM();
                AVISO2.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(DNIM.isSelected())
        {
            dni = DNI3.getText();
            cModificar = ar.buscarDni(dni);

            if(cModificar.getNombre() != "Error")
            {
                CODEM.setText(Integer.toString(cModificar.getCode()));
                NOMBREM.setText(cModificar.getNombre());
                APELLIDOM.setText(cModificar.getApe());
                txtRuc3.setText(cModificar.getRuc());
                NACIMIENTOM.setText(cModificar.getFechaN());
                SEXOM.setText(cModificar.getSexo());
                CORREOM.setText(cModificar.getEmail());
                DIRECCIONM.setText(cModificar.getDireccion());
                DISTRITOM.setText(cModificar.getDistrito());
                PROVINCIAM.setText(cModificar.getProvincia());
                PAISM.setText(cModificar.getPais());

                
                txtRuc3.setEditable(true);
                CORREOM.setEditable(true);
                DIRECCIONM.setEditable(true);
                DISTRITOM.setEditable(true);
                PROVINCIAM.setEditable(true);

                AVISO2.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
                LimpiarM();
                AVISO2.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
    }                                       

    private void DNI3KeyTyped(java.awt.event.KeyEvent evt) {                              
        if(DNI3.getText().length() >=8)
        {
            evt.consume();
        }
        botonDniM();
    }                             

    private void txtRuc1KeyTyped(java.awt.event.KeyEvent evt) {                                 
        if(txtRuc1.getText().length() >=11)
        {
            evt.consume();
        }
        
    }                                
    public void boton()
    {
        if (NOMBRE.getText().isEmpty() || DNI.getText().isEmpty() || APELLIDO.getText().isEmpty() || HOMBRE.getText().isEmpty() || MUJER.getText().isEmpty() || CORREO.getText().isEmpty() || DIRECCION.getText().isEmpty()) {
            REGISTRAR.setEnabled(false);
        } 
        else 
        {
            REGISTRAR.setEnabled(true);
        }
    }

    public void botonDni() {
        if (DNI1.getText().length() >= 0) 
        {
            BUSCAR.setEnabled(true);
        } else {
            BUSCAR.setEnabled(false);
        }
    }
    public void botonCode()
    {
        if(CODE1.getText().length() >= 0)
        {
            BUSCAR.setEnabled(true);
        }
        else
        {
            BUSCAR.setEnabled(false);
        }
    }
    public void botonCodeE()
    {
        if(CODEE.getText().length() >= 0)
        {
            BUSCARE.setEnabled(true);
        }
        else 
        {
            BUSCARE.setEnabled(false);
        }
    }
    public void botonDniE()
    {
        if(DNIE.getText().length() >= 0)
        {
            BUSCARE.setEnabled(true);
        }
        else
        {
            BUSCARE.setEnabled(false);
        }
    }
    public void botonDniM()
    {
        if(DNI3.getText().length() >= 0)
        {
            BUSCARM.setEnabled(true);
        }
        else
        {
            BUSCARM.setEnabled(false);
        }
    }
    public void botonCodeM()
    {
        if(CODEM.getText().length() >= 0)
        {
            BUSCARM.setEnabled(true);
        }
        else
        {
            BUSCARM.setEnabled(false);
        }
    }
    public void Limpiar()
    {
        DNI.setText(null);
        NOMBRE.setText(null);
        APELLIDO.setText(null);
        CORREO.setText(null);
        DIRECCION.setText(null);
    }

    public void ListarClientes()
    {
      
      String sql = "select * from cliente";
      
        try {
            con = cn.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            String[] filas = new String[12];
            
            table = (DefaultTableModel) TABLE.getModel();
            
            while (rs.next()) {
                filas[0] = String.valueOf(rs.getInt("CodCliente"));
                filas[1] = rs.getString("Nombre");
                filas[2] = rs.getString("Apellido");
                filas[3] = rs.getString("DNI");
                filas[4] = rs.getString("RUC");
                filas[5] = rs.getString("Sexo");
                filas[6] = String.valueOf(rs.getDate("Fecha_Nacimiento"));
                filas[7] = rs.getString("Email");
                filas[8] = rs.getString("Direccion");
                filas[9] = rs.getString("Distritos");
                filas[10] = rs.getString("Provincia");
                filas[11] = rs.getString("Pais");
                
                
                table.addRow(filas);
            }
            TABLE.setModel(table);

        } catch (Exception e) {
            System.out.println(e);
        }
    
    }
    
    public void LimpiarTabla(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) TABLE.getModel();
            int filas = TABLE.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }

    }

    public void LimpiarB() {
        CODE1.setText(null);
        DNI1.setText(null);
        NOMBRE1.setText(null);
        APELLIDO1.setText(null);
        APELLIDO.setText(null);
        NACIMIENTO1.setText(null);
        SEXO1.setText(null);
        CORREO1.setText(null);
        DIRECCION1.setText(null);
        DISTRITO1.setText(null);
        PROVINCIA1.setText(null);
        PAIS1.setText(null);
    }
    
    public void LimpiarE() 
    {
        CODEE.setText(null);
        DNIE.setText(null);
        NOMBREE.setText(null);
        APELLIDOE.setText(null);
        APELLIDOE.setText(null);
        FECHAE.setText(null);
        SEXOE.setText(null);
        CORREOE.setText(null);
        DIRECCIONE.setText(null);
        DISTRITOE.setText(null);
        PROVINCIAE.setText(null);
        PAISE.setText(null);
    }
    public void LimpiarM()
    {
       CODEM.setText(null);
       DNI3.setText(null);
       NOMBREM.setText(null);
       APELLIDOM.setText(null);
       NACIMIENTOM.setText(null);
       SEXOM.setText(null);
       CORREOM.setText(null);
       DIRECCIONM.setText(null);
       DISTRITOM.setText(null);
       PROVINCIAM.setText(null);
       PAISM.setText(null);  
    }
    public void Validar()
    {
        if(MUJER.getText().isEmpty() || HOMBRE.getText().isEmpty() || NOMBRE.getText().isEmpty() || APELLIDO.getText().isEmpty() || DNI.getText().isEmpty() || CORREO.getText().isEmpty() || DIRECCION.getText().isEmpty())
        {
            CAMPO.setText("*Hay Campos Vacios!");
        }
        else
        {
            CAMPO.setText("");
        }
    }
   
   
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mantenimiento_Cliente().setVisible(true);
            }
        });
    }
                   
}




/*FRAME MANTENIMIENTO DISTRIBUIDOR*/


package Vista.FrmAdmin;

import Controller.ArrayDistribuidor;
import Modelo.Distribuidor;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;


public class Mantenimiento_Distribuidor extends javax.swing.JFrame {

    int code = 0;
    String ruc;
    String razonSocial;
    String direccion;
    String correoElectronico;
    String numerotelefonico;
    String paginaWeb;
    String sucursal;
    
    boolean senail = false;
    boolean senail2 = false;
    boolean senail3 = false;
    
    ArrayDistribuidor ad = new ArrayDistribuidor();
    Distribuidor d = new Distribuidor();
    Distribuidor dis = new Distribuidor();
    Distribuidor eli = new Distribuidor();
    
    
    DefaultTableModel jtablePro = new DefaultTableModel();
    
    
    public Mantenimiento_Distribuidor() {
        initComponents();
        this.setLocationRelativeTo(null);
        jtablePro.addColumn("Codigo");
        jtablePro.addColumn("Ruc");
        jtablePro.addColumn("Razon Social");
        jtablePro.addColumn("Direccion");
        jtablePro.addColumn("Correo Electronico");
        jtablePro.addColumn("Numero de Telefono");
        jtablePro.addColumn("Pagina Web");
        jtablePro.addColumn("Sucursal");
        
        jTableProveedor.setModel(jtablePro);
        
        ListarTabla();
        
    }

    @SuppressWarnings("unchecked")
  
  private void AñadirProveedorActionPerformed(java.awt.event.ActionEvent evt) {                                                
        
        code = ad.correlativo();
        CodigoAñadir.setText("D" + code);
        ruc = RucAñadir.getText();
        razonSocial = RazonSocialAñadir.getText();
        direccion = DireccionAñadir.getText();
        correoElectronico = CorreoContactoAñadir.getText();
        numerotelefonico = NumTel1Añadir.getText();
        paginaWeb = PaginaWebAñadir.getText();
        sucursal = SucursalAñadir.getText();
        
        int rpt = JOptionPane.showConfirmDialog(null, "DESEA REGISTRAR ESTE PROVEEDOR?", "AVISO", JOptionPane.YES_NO_OPTION);
        if (rpt == 0)
        {
            d.setCodigo(code);
            d.setRazon_social(razonSocial);
            d.setRuc(ruc);
            d.setSucursal(sucursal);
            d.setDireccion(direccion);
            d.setCorreoEle_Contacto(correoElectronico);
            d.setPagina_web(paginaWeb);
            d.setTelefono_contacto(numerotelefonico);
        
            ad.agregarDatos(d);
            
          
            ad.Grabar_Distribuidor(d);
            
                
            JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO", "AVISO", JOptionPane.OK_OPTION);
            Limpiar();
            
            senail = true;
           
            
            
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", JOptionPane.OK_OPTION);
            Limpiar();
        }
        
    }                                               

    private void CancelarRegistroAñadirActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        Limpiar();
    }                                                      

    private void RegresarAñadirActionPerformed(java.awt.event.ActionEvent evt) {                                               
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                              

    private void RegresarMenuListarActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                                                                                  

    private void RegresarMenuModificarActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                                     

    private void BusRucModificarActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if (BusRucModificar.isSelected())
        {
           RucModificar.setText(null);
           BusCodigoModificar.setEnabled(false);
           RucModificar.setEditable(true);
           
        }
        else 
        {
            BusCodigoModificar.setEnabled(true);
            RucModificar.setEditable(false);
        }
    }                                               

    private void BusCodigoModificarActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        if (BusCodigoModificar.isSelected())
        {
           CodigoModificar.setText(null);
           BusRucModificar.setEnabled(false);
           CodigoModificar.setEditable(true);
           
        }
        else 
        {
            BusRucModificar.setEnabled(true);
            CodigoModificar.setEditable(false);
        }
    }                                                                                               

    private void BuscarProveedorBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        if (BusCodigoBuscar.isSelected())
        {
            int codigo = Integer.parseInt(CodigoBuscar.getText());
            Distribuidor dr = new Distribuidor();
            dr = ad.buscar_PorCod(codigo);
            
            if (dr.getRuc() != "Error")
            {
                CodigoBuscar.setText(Integer.toString(dr.getCodigo()));
                RucBuscar.setText(dr.getRuc());
                RazonSocialBuscar.setText(dr.getRazon_social());
                DireccionBuscar.setText(dr.getDireccion());
                CorreoContactoBuscar.setText(dr.getCorreoEle_Contacto());
                NumTel1Buscar.setText(dr.getTelefono_contacto());
                PaginaWebBuscar.setText(dr.getPagina_web());
                SucursalBuscar.setText(dr.getSucursal());
            }
        }
        else if (BusRucBuscar.isSelected())
        {
            String ruc = RucBuscar.getText();
            Distribuidor dro = new Distribuidor();
            dro = ad.buscar_PorRuc(ruc);
            if (dro.getDireccion() != "Error")
            {
                CodigoBuscar.setText(Integer.toString(dro.getCodigo()));
                RucBuscar.setText(dro.getRuc());
                RazonSocialBuscar.setText(dro.getRazon_social());
                DireccionBuscar.setText(dro.getDireccion());
                CorreoContactoBuscar.setText(dro.getCorreoEle_Contacto());
                NumTel1Buscar.setText(dro.getTelefono_contacto());
                PaginaWebBuscar.setText(dro.getPagina_web());
                SucursalBuscar.setText(dro.getSucursal());
            }
            
        }
    }                                                                                                    

    private void RegresarBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                               
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                              

    private void BusCodigoBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if(BusCodigoBuscar.isSelected())
        {
            CodigoBuscar.setText(null);
            BusRucBuscar.setEnabled(false);
            CodigoBuscar.setEditable(true);
        }
        else 
        {
            CodigoBuscar.setEditable(false);
            BusRucBuscar.setEnabled(true);
        }
    }                                               

    private void BusRucBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(BusRucBuscar.isSelected())
        {
            RucBuscar.setText(null);
            BusCodigoBuscar.setEnabled(false);
            RucBuscar.setEditable(true);
        }
        else 
        {
            RucBuscar.setEditable(false);
            BusCodigoBuscar.setEnabled(true);
            
        }
    }                                                                                           

    private void BuscarProveedorModificarActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        if (BusCodigoModificar.isSelected())
        {
            int codigo = Integer.parseInt(CodigoModificar.getText());
            dis = ad.buscar_PorCod(codigo);
            
            if (dis.getRuc() != "Error")
            {
                CodigoModificar.setText(Integer.toString(dis.getCodigo()));
                RucModificar.setText(dis.getRuc());
                RazonSocialModificar.setText(dis.getRazon_social());
                DireccionModificar.setText(dis.getDireccion());
                CorreoContactoModificar.setText(dis.getCorreoEle_Contacto());
                NumTel1Modificar.setText(dis.getTelefono_contacto());
                PaginaWebModificar.setText(dis.getPagina_web());
                SucursalModificar.setText(dis.getSucursal());
                
                CodigoModificar.setEditable(false);
                
                RazonSocialModificar.setEditable(true);
                DireccionModificar.setEditable(true);
                CorreoContactoModificar.setEditable(true);
                NumTel1Modificar.setEditable(true);
                PaginaWebModificar.setEditable(true);
                SucursalModificar.setEditable(true);
            }
        }
        else if (BusRucModificar.isSelected())
        {
            String ruc = RucModificar.getText();
            dis = ad.buscar_PorRuc(ruc);
            if (dis.getDireccion() != "Error")
            {
                CodigoModificar.setText(Integer.toString(dis.getCodigo()));
                RucModificar.setText(dis.getRuc());
                RazonSocialModificar.setText(dis.getRazon_social());
                DireccionModificar.setText(dis.getDireccion());
                CorreoContactoModificar.setText(dis.getCorreoEle_Contacto());
                NumTel1Modificar.setText(dis.getTelefono_contacto());
                PaginaWebModificar.setText(dis.getPagina_web());
                SucursalModificar.setText(dis.getSucursal());
                
                RucModificar.setEditable(false);
                
                RazonSocialModificar.setEditable(true);
                DireccionModificar.setEditable(true);
                CorreoContactoModificar.setEditable(true);
                NumTel1Modificar.setEditable(true);
                PaginaWebModificar.setEditable(true);
                SucursalModificar.setEditable(true);
            }
            
        }
    }                                                        

    private void ModificarProveedorModificarActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        int cod = Integer.valueOf(CodigoModificar.getText());
        String raz = RazonSocialModificar.getText();
        String dir = DireccionModificar.getText();
        String correo = CorreoContactoModificar.getText();
        String num = NumTel1Modificar.getText();
        String pagina = PaginaWebModificar.getText();
        String sucur = SucursalModificar.getText();
        
        boolean estado = ad.modificar(dis, raz, dir, correo, num, pagina, sucur);
        
        if (estado == true)
        {
            ad.grabar_ModificarDistribuidor(cod, raz, sucur, dir, correo, pagina, num);
            JOptionPane.showMessageDialog(null, "MODIFICACION COMPLETADA", "AVISO", JOptionPane.OK_OPTION);
            LimpiarMod();
            
            senail2 = true;
            
        }
    
    }                                                                                                         

    private void BuscarProveedorEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        if (BusCodigoEliminar.isSelected())
        {
            int codigo = Integer.parseInt(CodigoEliminar.getText());
            eli = ad.buscar_PorCod(codigo);
            
            if (eli.getRuc() != "Error")
            {
                CodigoEliminar.setText(Integer.toString(eli.getCodigo()));
                RucEliminar.setText(eli.getRuc());
                RazonSocialEliminar.setText(eli.getRazon_social());
                DireccionEliminar.setText(eli.getDireccion());
                CorreoContactoEliminar.setText(eli.getCorreoEle_Contacto());
                NumTel1Eliminar.setText(eli.getTelefono_contacto());
                PaginaWebEliminar.setText(eli.getPagina_web());
                SucursalEliminar.setText(eli.getSucursal());
            }
        }
        else if (BusRucEliminar.isSelected())
        {
            String ruc = RucEliminar.getText();
            eli = ad.buscar_PorRuc(ruc);
            if (eli.getDireccion() != "Error")
            {
                CodigoEliminar.setText(Integer.toString(eli.getCodigo()));
                RucEliminar.setText(eli.getRuc());
                RazonSocialEliminar.setText(eli.getRazon_social());
                DireccionEliminar.setText(eli.getDireccion());
                CorreoContactoEliminar.setText(eli.getCorreoEle_Contacto());
                NumTel1Eliminar.setText(eli.getTelefono_contacto());
                PaginaWebEliminar.setText(eli.getPagina_web());
                SucursalEliminar.setText(eli.getSucursal());
            }
            
        }
    }                                                       

    private void EliminarProveedorEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                                          
        
        ad.eliminar(eli); 
        ad.grabar_EllminarDistribuidor(eli.getCodigo());
        JOptionPane.showMessageDialog(null, "ELIMINACION SATISFACTORIA", "AVISO", JOptionPane.OK_OPTION);
        LimpiarEli();
        
        senail3 = true;
    }                                                         

    private void BusCodigoEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        if (BusCodigoEliminar.isSelected())
        {
           CodigoEliminar.setText(null);
           BusRucEliminar.setEnabled(false);
           CodigoEliminar.setEditable(true);
           
        }
        else 
        {
            BusRucEliminar.setEnabled(true);
            CodigoEliminar.setEditable(false);
        }
    }                                                 

    private void BusRucEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                               
        if (BusRucEliminar.isSelected())
        {
           RucEliminar.setText(null);
           BusCodigoEliminar.setEnabled(false);
           RucEliminar.setEditable(true);
           
        }
        else 
        {
            BusCodigoEliminar.setEnabled(true);
            RucEliminar.setEditable(false);
        }
    }                                              

    private void RegresarMenuEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                                    

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {                                            
       
        if(senail == true){
            LimpiarTabla();
            ListarTabla();
        }else if(senail2 == true){
            LimpiarTabla();
            ListarTabla();
        }else if(senail3 == true){
            LimpiarTabla();
            ListarTabla();
        }else{
            
        }
        
        
    }                                           

    private void RucAñadirKeyTyped(java.awt.event.KeyEvent evt) {                                   
        if(RucAñadir.getText().length() >=11)
        {
            evt.consume();
        }
        Validar();
    }                                  

    private void RazonSocialAñadirKeyTyped(java.awt.event.KeyEvent evt) {                                          
        Validar();
    }                                          

    private void DireccionAñadirKeyTyped(java.awt.event.KeyEvent evt) {                                         
        Validar();
    }                                        

    private void CorreoContactoAñadirKeyTyped(java.awt.event.KeyEvent evt) {                                              
        Validar();
    }                                             

    private void NumTel1AñadirKeyTyped(java.awt.event.KeyEvent evt) {                                       
        Validar();
    }                                      

    private void PaginaWebAñadirKeyTyped(java.awt.event.KeyEvent evt) {                                         
        Validar();
    }                                        

    private void SucursalAñadirKeyTyped(java.awt.event.KeyEvent evt) {                                        
        Validar();
    }                                       

    
    public void Limpiar()
    {
        
        CodigoAñadir.setText(null);
        RucAñadir.setText(null);
        RazonSocialAñadir.setText(null);
        DireccionAñadir.setText(null);
        CorreoContactoAñadir.setText(null);
        NumTel1Añadir.setText(null);
        PaginaWebAñadir.setText(null);
        SucursalAñadir.setText(null);
    }
    
    public void LimpiarMod()
    {
        
        CodigoModificar.setText(null);
        RucModificar.setText(null);
        RazonSocialModificar.setText(null);
        DireccionModificar.setText(null);
        CorreoContactoModificar.setText(null);
        NumTel1Modificar.setText(null);
        PaginaWebModificar.setText(null);
        SucursalModificar.setText(null);
    }
    
    public void LimpiarEli()
    {
        
        CodigoEliminar.setText(null);
        RucEliminar.setText(null);
        RazonSocialEliminar.setText(null);
        DireccionEliminar.setText(null);
        CorreoContactoEliminar.setText(null);
        NumTel1Eliminar.setText(null);
        PaginaWebEliminar.setText(null);
        SucursalEliminar.setText(null);
    }
    
    public void ListarTabla()
    {
      String [] filas = new String[8];
           
      for(int i=0;i<ad.rows();i++)
      {
               filas[0]=Integer.toString(ad.get(i).getCodigo());
               filas[1]=ad.get(i).getRuc();
               filas[2]=ad.get(i).getRazon_social();
               filas[3]=ad.get(i).getDireccion();
               filas[4]=ad.get(i).getCorreoEle_Contacto();
               filas[5]=ad.get(i).getTelefono_contacto();
               filas[6]=ad.get(i).getPagina_web();
               filas[7]=ad.get(i).getSucursal();
             
               
               jtablePro.addRow(filas);
       }
    }
    
    public void LimpiarTabla(){
        try {
            DefaultTableModel modelo =(DefaultTableModel) jTableProveedor.getModel();
            int filas = jTableProveedor.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }

    }
    public void Validar()
    {
        if(RucAñadir.getText().isEmpty() || RazonSocialAñadir.getText().isEmpty() || DireccionAñadir.getText().isEmpty() || CorreoContactoAñadir.getText().isEmpty() || NumTel1Añadir.getText().isEmpty() || PaginaWebAñadir.getText().isEmpty() || SucursalAñadir.getText().isEmpty())
        {
            CAMPO.setText("*Hay Campos Vacios!");
            AñadirProveedor.setEnabled(false);
        }
        else
        {
            CAMPO.setText("");
            AñadirProveedor.setEnabled(true);
        }
    }
    
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Distribuidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Distribuidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Distribuidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Distribuidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mantenimiento_Distribuidor().setVisible(true);
            }
        });
    }
                  
}




/*FRAME MANTENIMIENTO PRODUCTO*/


package Vista.FrmAdmin;

import Controller.ArrayProducto;
import Controller.Conexion;
import Modelo.Distribuidor;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Mantenimiento_Producto extends javax.swing.JFrame {
    int code;
    int codeD;
    String nombre;
    String color;
    String tamaño;
    int stock;
    double costo;
    double precio;
    String fechaI;
    String fechaV;
    String dis;
    String ruc;
    
    boolean senial = false;
    boolean senial2 = false;
    boolean senial3 = false;
    
    SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
    ArrayProducto pr = new ArrayProducto();
    Producto pModificar = new Producto();
    Producto pEliminar = new Producto();
    
    DefaultTableModel table = new DefaultTableModel();
    
    ArrayList<Distribuidor> distribuidores = new ArrayList<>();
    
    Conexion cn = new Conexion();
    Connection con;
    Statement st;
    ResultSet rs;
    
   
    public Mantenimiento_Producto() {
        initComponents();
        this.setLocationRelativeTo(null);
        table.addColumn("CODIGO");
        table.addColumn("NOMBRE");
        table.addColumn("COLOR");
        table.addColumn("TAMAÑO");
        table.addColumn("STOCK");
        table.addColumn("COSTO");
        table.addColumn("PRECIO");
        table.addColumn("FECHA I.");
        table.addColumn("FECHA V.");
        table.addColumn("DISTRIBUIDOR");
        
        this.TABLE.setModel(table);
        ListarProductos();
        BotonRe();
        BotonBuC();
        BotonBuN();
        BotonBuCE();
        BotonBuNE();
        BotonBuCM();
        BotonBuNM();
        getDistritos();
        
    }

    @SuppressWarnings("unchecked")

  private void REGISTROActionPerformed(java.awt.event.ActionEvent evt) {                                         
        code = pr.Correlativo();
        String co = Integer.toString(code);
        CODE.setText("P-"+co);
        int rpt;
        nombre = NOMBRE.getText();
        color = COLOR.getText();
        tamaño = TAMAÑO.getText();
        stock = Integer.parseInt(STOCK.getValue().toString());
        costo = Double.parseDouble(COSTO.getValue().toString());
        precio = Double.parseDouble(PRECIO.getValue().toString());
        fechaI = sd.format(FECHAI.getDate());
        fechaV = sd.format(FECHAV.getDate());
        
        dis = DISTRIBUIDOR.getSelectedItem().toString();
        
        codeD = buscar_PorRazon(dis).getCodigo();
        
        
        
        
        rpt = JOptionPane.showConfirmDialog(null,"DESEA REGISTRAR ESTE PRODUCTO?","AVISO",JOptionPane.YES_NO_OPTION);
        if(rpt==0)
        {
            Producto p = new Producto();
            p.setCodigo(code);
            p.setNombreProduc(nombre);
            p.setColor(color);
            p.setTamaño(tamaño);
            p.setStock(stock);
            p.setCosto(costo);
            p.setPrecio(precio);
            p.setFechaIngreso(fechaI);
            p.setFechaVencimiento(fechaV);
            p.setDistribuidor(dis);
            
            pr.AgregarArray(p);
            pr.grabar_Producto(p, codeD);
           
            JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO", "AVISO", JOptionPane.OK_OPTION);
            Limpiar();
            
            senial = true;
            
        }
        else
        {
             JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        
    }                                        

    public Distribuidor buscar_PorRazon(String rz)
    {
        Distribuidor d = new Distribuidor();
        d.setDireccion("Error");
        for (Distribuidor x: distribuidores)
        {
            if (rz == x.getRazon_social())
            {
                return x;
            }
        }
        return d;
    }
    private void CANCELARActionPerformed(java.awt.event.ActionEvent evt) {                                         
       int rp = JOptionPane.showConfirmDialog(null, "DESEA CANCELAR EL REGISTRO?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) {
            JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "AVISO", JOptionPane.WARNING_MESSAGE);
            Login_IngresoAdmin la = new Login_IngresoAdmin();
            la.setVisible(true);
            this.dispose();
        }
    }                                        

    private void REGRESARActionPerformed(java.awt.event.ActionEvent evt) {                                         
         int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0) 
        {
            Login_IngresoAdmin la = new Login_IngresoAdmin();
            la.setVisible(true);
            this.dispose();
        }
    }                                                                              

    private void MODIFICARActionPerformed(java.awt.event.ActionEvent evt) {                                          
       if(CODIGOM.isSelected())
       {
          int rpt1 = JOptionPane.showConfirmDialog(null,"DESEA MODIFICAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
          if(rpt1==0)
          {
              String color, tamaño, fechaI;
              int stock;
              double costo, precio;
              color = COLORM.getText();
              tamaño = TAMAÑOM.getText();
              fechaI = FECHAIM.getText();
              stock = Integer.parseInt(STOCKM.getText());
              costo = Double.parseDouble(COSTOM.getText());
              precio = Double.parseDouble(PRECIOM.getText());
              boolean estado = pr.ModificarCode(pModificar, color, tamaño, stock, costo, precio, fechaI);
              if(estado==true)
              {
                LimpiarM();
                AVISO2.setText("");
                JOptionPane.showMessageDialog(null,"REGISTRO MODIFICADO","MODIFICADO",JOptionPane.OK_OPTION);
                pr.Grabar_ModificarProducto(pModificar.getCodigo(), color, tamaño, stock, costo, precio, fechaI);
                
                senial2 =true;
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"NO EXISTE EL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
              }
          }
       }
       else if(NOMBREM.isSelected())
       {
            int rpt1 = JOptionPane.showConfirmDialog(null,"DESEA MODIFICAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt1==0)
            {
                String nombre, color, tamaño, fechaI;
                int stock;
                double costo, precio;
                nombre = NOMBREM1.getText();
                color = COLORM.getText();
                tamaño = TAMAÑOM.getText();
                fechaI = FECHAIM.getText();
                stock = Integer.parseInt(STOCKM.getText());
                costo = Double.parseDouble(COSTOM.getText());
                precio = Double.parseDouble(PRECIOM.getText());
                boolean estado = pr.ModificarNombre(pModificar, color, tamaño, stock, costo, precio, fechaI);
                if(estado==true)
                {
                  LimpiarM();
                  AVISO2.setText("");
                  JOptionPane.showMessageDialog(null,"REGISTRO MODIFICADO","MODIFICADO",JOptionPane.OK_OPTION);
                  pr.Grabar_ModificarProducto(pModificar.getCodigo(), color, tamaño, stock, costo, precio, fechaI);
                  
                  senial2 =true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"NO EXISTE EL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"NO EXISTE EL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
            }
       }
        
    }                                         

    private void BUSCARPActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(CODEB.isSelected())
        {
            code =Integer.parseInt(CODE3.getText());
            Producto p = pr.buscarCode(code);
            
            if(p.getNombreProduc() != "Error")
            {
                NOMBRE1.setText(p.getNombreProduc());
                COLORB.setText(p.getColor());
                TAMAÑOB.setText(p.getTamaño());
                STOCKB.setText(Integer.toString(p.getStock()));
                COSTOB.setText(Double.toString(p.getCosto()));
                PRECIOB.setText(Double.toString(p.getPrecio()));
                FECHAIB.setText(p.getFechaIngreso());
                FECHAVB.setText(p.getFechaVencimiento());
                DISTRIBUIDORB.setText(p.getDistribuidor());
                AVISO.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
                AVISO.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
                LimpiarBB();
            }
            
            
        }
        else if(NOMBREB.isSelected())
        {
            nombre = NOMBRE1.getText();
            Producto p1 = pr.buscarNombre(nombre);
            if(p1.getCodigo() != 0)
            {
                CODE3.setText(Integer.toString(p1.getCodigo()));
                COLORB.setText(p1.getColor());
                TAMAÑOB.setText(p1.getTamaño());
                STOCKB.setText(Integer.toString(p1.getStock()));
                COSTOB.setText(Double.toString(p1.getCosto()));
                PRECIOB.setText(Double.toString(p1.getPrecio()));
                FECHAIB.setText(p1.getFechaIngreso());
                FECHAVB.setText(p1.getFechaVencimiento());
                DISTRIBUIDORB.setText(p1.getDistribuidor());
                 AVISO.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
                AVISO.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
                LimpiarBB();
            }
            
            
        }
    }                                       

    private void CODEBActionPerformed(java.awt.event.ActionEvent evt) {                                      
         if (CODEB.isSelected())
        {
            CODE3.setText(null);
            CODE3.setEditable(true);
            NOMBREB.setEnabled(false);
        }
        else
        {
            CODE3.setEditable(false);
            NOMBREB.setEnabled(true);
        }
    }                                     

    private void NOMBREBActionPerformed(java.awt.event.ActionEvent evt) {                                        
         if (NOMBREB.isSelected())
        {
            NOMBRE1.setText(null);
            NOMBRE1.setEditable(true);
            CODEB.setEnabled(false);
        }
        else
        {
            NOMBRE1.setEditable(false);
            CODEB.setEnabled(true);
        }
    }                                       

    private void NOMBREKeyTyped(java.awt.event.KeyEvent evt) {                                
       BotonRe();
       Validar();
    }                               

    private void COLORKeyTyped(java.awt.event.KeyEvent evt) {                               
        BotonRe();
        Validar();
    }                              

    private void TAMAÑOKeyTyped(java.awt.event.KeyEvent evt) {                                
        BotonRe();
        Validar();
    }                               

    private void CODE3KeyTyped(java.awt.event.KeyEvent evt) {                               
       BotonBuC();
    }                              

    private void NOMBRE1KeyTyped(java.awt.event.KeyEvent evt) {                                 
       BotonBuN();
    }                                

    private void BUSCARMActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(CODIGOM.isSelected())
        {
            code = Integer.parseInt(CODEM.getText());
            
            pModificar = pr.buscarCode(code);
            if(pModificar.getNombreProduc() != "Error")
            {
                NOMBREM1.setText(pModificar.getNombreProduc());
                COLORM.setText(pModificar.getColor());
                TAMAÑOM.setText(pModificar.getTamaño());
                STOCKM.setText(Integer.toString(pModificar.getStock()));
                COSTOM.setText(Double.toString(pModificar.getCosto()));
                PRECIOM.setText(Double.toString(pModificar.getPrecio()));
                FECHAIM.setText(pModificar.getFechaIngreso());
                FECHAVM.setText(pModificar.getFechaVencimiento());
                DISTRIBUIDORM.setText(pModificar.getDistribuidor());
                
                 COLORM.setEditable(true);
                 TAMAÑOM.setEditable(true);
                 STOCKM.setEditable(true);
                 COSTOM.setEditable(true);
                 PRECIOM.setEditable(true);
                 FECHAIM.setEditable(true);
                 
                AVISO2.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
               LimpiarM();
               AVISO2.setText("");
               JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            
        }
        else if(NOMBREM.isSelected())
        {
            nombre = NOMBREM1.getText();
            pModificar = pr.buscarNombre(nombre);
            
            if(pModificar.getCodigo() != 0)
            {
                NOMBREM1.setText(pModificar.getNombreProduc());
                COLORM.setText(pModificar.getColor());
                TAMAÑOM.setText(pModificar.getTamaño());
                STOCKM.setText(Integer.toString(pModificar.getStock()));
                COSTOM.setText(Double.toString(pModificar.getCosto()));
                PRECIOM.setText(Double.toString(pModificar.getPrecio()));
                FECHAIM.setText(pModificar.getFechaIngreso());
                FECHAVM.setText(pModificar.getFechaVencimiento());
                DISTRIBUIDORM.setText(pModificar.getDistribuidor());
                
                 COLORM.setEditable(true);
                 TAMAÑOM.setEditable(true);
                 STOCKM.setEditable(true);
                 COSTOM.setEditable(true);
                 PRECIOM.setEditable(true);
                 FECHAIM.setEditable(true);
                AVISO2.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
               LimpiarM();
               AVISO2.setText("");
               JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        
    }                                       

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                         

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                         

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {                                            
       if(senial == true){
            LimpiarTabla();
            ListarProductos();
        }else if(senial2 == true){
            LimpiarTabla();
            ListarProductos();
        }else if(senial3 == true){
            LimpiarTabla();
            ListarProductos();
        }else{
            
        }
    }                                           

    private void CODIGOMActionPerformed(java.awt.event.ActionEvent evt) {                                        
       if(CODIGOM.isSelected())
       {
           CODEM.setText("");
           CODEM.setEditable(true);
           NOMBREM.setEnabled(false);
       }
       else
       {
           CODEM.setEditable(false);
           NOMBREM.setEnabled(true);
       }
    }                                       

    private void NOMBREMActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(NOMBREM.isSelected())
       {
           NOMBREM1.setText("");
           NOMBREM1.setEditable(true);
           CODIGOM.setEnabled(false);
       }
       else
       {
           NOMBREM1.setEditable(false);
           CODIGOM.setEnabled(true);
       }
    }                                       

    private void CODEMKeyTyped(java.awt.event.KeyEvent evt) {                               
        BotonBuCM();
    }                              

    private void NOMBREM1KeyTyped(java.awt.event.KeyEvent evt) {                                  
        BotonBuNM();
    }                                 

    private void BUSCARActionPerformed(java.awt.event.ActionEvent evt) {                                       
        if(CODE2.isSelected())
        {
            code = Integer.parseInt(CODEE.getText());

            pEliminar = pr.buscarCode(code);
            if(pEliminar.getNombreProduc()!= "Error")
            {
                NOMBREE.setText(pEliminar.getNombreProduc());
                COLORE.setText(pEliminar.getColor());
                TAMAÑOE.setText(pEliminar.getTamaño());
                STOCKE.setText(Integer.toString(pEliminar.getStock()));
                COSTOE.setText(Double.toString(pEliminar.getCosto()));
                PRECIOE.setText(Double.toString(pEliminar.getPrecio()));
                FECHAIE.setText(pEliminar.getFechaIngreso());
                FECHAVE.setText(pEliminar.getFechaVencimiento());
                DISTRIBUIDORE.setText(pEliminar.getDistribuidor());
                AVISO1.setText("REGISTRO ENCONTRADO!!!");
            }

            else
            {
                LimpiarE();
                AVISO1.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(NOMBRE2.isSelected())
        {
            nombre = NOMBREE.getText();
            pEliminar = pr.buscarNombre(nombre);
            if(pEliminar.getCodigo() != 0)
            {
                CODEE.setText(Integer.toString(pEliminar.getCodigo()));
                NOMBREE.setText(pEliminar.getNombreProduc());
                COLORE.setText(pEliminar.getColor());
                TAMAÑOE.setText(pEliminar.getTamaño());
                STOCKE.setText(Integer.toString(pEliminar.getStock()));
                COSTOE.setText(Double.toString(pEliminar.getCosto()));
                PRECIOE.setText(Double.toString(pEliminar.getPrecio()));
                FECHAIE.setText(pEliminar.getFechaIngreso());
                FECHAVE.setText(pEliminar.getFechaVencimiento());
                DISTRIBUIDORE.setText(pEliminar.getDistribuidor());
                AVISO1.setText("REGISTRO ENCONTRADO!!!");
            }
            else
            {
                LimpiarE();
                AVISO1.setText("");
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
    }                                      

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                        

    private void NOMBRE2ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if(NOMBRE2.isSelected())
        {
            NOMBREE.setText(null);
            NOMBREE.setEditable(true);
            CODE2.setEnabled(false);
        }
        else
        {
            NOMBREE.setEditable(false);
            CODE2.setEnabled(true);
        }
    }                                       

    private void CODE2ActionPerformed(java.awt.event.ActionEvent evt) {                                      
        if(CODE2.isSelected())
        {
            CODEE.setText(null);
            CODEE.setEditable(true);
            NOMBRE2.setEnabled(false);
        }
        else
        {
            CODEE.setEditable(false);
            NOMBRE2.setEnabled(true);
        }
    }                                     

    private void ELIMINARActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(CODE2.isSelected())
        {
            int rpt1 = JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt1==0)
            {
                if(pEliminar.getNombreProduc() != "Error")
                {
                    pr.eliminar(pEliminar);
                    JOptionPane.showMessageDialog(null,"REGISTRO ELIMINADO SATISFACTORIAMENTE","AVISO",JOptionPane.OK_OPTION);
                    pr.Grabar_EliminarProducto(pEliminar.getCodigo());
                    LimpiarE();
                    AVISO1.setText("");

                    senial3 = true;
                }
            }
            else
            {
                AVISO1.setText("");
                JOptionPane.showMessageDialog(null,"NO EXISTE EL CODIGO DEL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(NOMBRE2.isSelected())
        {
            int rpt1 = JOptionPane.showConfirmDialog(null,"DESEA ELIMINAR ESTE REGISTRO?","AVISO",JOptionPane.YES_NO_OPTION);
            if(rpt1==0)
            {
                pr.eliminar(pEliminar);
                JOptionPane.showMessageDialog(null,"REGISTRO ELIMINADO SATISFACTORIAMENTE","AVISO",JOptionPane.OK_OPTION);
                pr.Grabar_EliminarProducto(pEliminar.getCodigo());
                LimpiarE();
                AVISO1.setText("");
                senial3 = true;
            }
            else
            {
                AVISO1.setText("");
                JOptionPane.showMessageDialog(null,"NO EXISTE EL CODIGO DEL REGISTRO","ERROR",JOptionPane.WARNING_MESSAGE);
            }
        }
    }                                        
                                      

    private void REGRESARPActionPerformed(java.awt.event.ActionEvent evt) {                                          
        int rp = JOptionPane.showConfirmDialog(null, "DESEA REGRESAR AL MENU?", "AVISO", JOptionPane.YES_NO_OPTION);

        if (rp == 0)
        {
            Login_IngresoAdmin li = new Login_IngresoAdmin();
            li.setVisible(true);
            this.dispose();
        }
    }                                         
    public void ListarProductos()
    {
        
         String [] filas = new String[10];
        
        for(int i=0;i<pr.Filas();i++)
        {
            filas[0] = "P-"+Integer.toString(pr.get(i).getCodigo());
            filas[1] = pr.get(i).getNombreProduc();
            filas[2] = pr.get(i).getColor();
            filas[3] = pr.get(i).getTamaño();
            filas[4] = Integer.toString(pr.get(i).getStock());
            filas[5] = Double.toString(pr.get(i).getCosto());
            filas[6] = Double.toString(pr.get(i).getPrecio());
            filas[7] = pr.get(i).getFechaIngreso();
            filas[8] = pr.get(i).getFechaVencimiento();
            filas[9] = pr.get(i).getDistribuidor();
            
            table.addRow(filas);
            
            
        }
    }
    
    
    
    public void BotonRe()
    {
        if(NOMBRE.getText().isEmpty() || COLOR.getText().isEmpty() || TAMAÑO.getText().isEmpty() || DISTRIBUIDOR.getSelectedItem().toString().isEmpty())
        {
            REGISTRO.setEnabled(false);
        }
        else
        {
            REGISTRO.setEnabled(true);
        }
    }
    public void BotonBuC()
    {
        if(CODE3.getText().length() >= 0)
        {
            BUSCARP.setEnabled(true);
        }
        else
        {
            BUSCARP.setEnabled(false);
        }
    }
    public void BotonBuN()
    {
        if(NOMBRE1.getText().length() >= 0)
        {
            BUSCARP.setEnabled(true);
        }
        else
        {
            BUSCARP.setEnabled(false);
        }
    }
    public void BotonBuCE()
    {
        if(CODEE.getText().length() >= 0)
        {
            BUSCAR.setEnabled(true);
        }
        else
        {
            BUSCAR.setEnabled(false);
        }
    }
    public void BotonBuNE()
    {
        if(NOMBREE.getText().length() >= 0)
        {
            BUSCAR.setEnabled(true);
        }
        else
        {
            BUSCAR.setEnabled(false);
        }
    }
    public void BotonBuCM()
    {
        if(CODEM.getText().length() >= 0)
        {
            BUSCARM.setEnabled(true);
        }
        else
        {
            BUSCARM.setEnabled(false);
        }
    }
    public void BotonBuNM()
    {
        if(NOMBREM1.getText().length() >= 0)
        {
            BUSCARM.setEnabled(true);
        }
        else
        {
            BUSCARM.setEnabled(false);
        }
    }
    public void Limpiar()
    {
        NOMBRE.setText(null);
        COLOR.setText(null);
        TAMAÑO.setText(null);
        STOCK.setValue(0);
        COSTO.setValue(0);
        PRECIO.setValue(0);
        FECHAI.setDate(null);
        FECHAV.setDate(null);
        DISTRIBUIDOR.setSelectedIndex(0);
    }
    public void LimpiarBB()
    {
        CODE3.setText(null);
        NOMBRE1.setText(null);
        COLORB.setText(null);
        TAMAÑOB.setText(null);
        STOCKB.setToolTipText(null);
        COSTOB.setText(null);
        PRECIOB.setText(null);
        FECHAIB.setText(null);
        FECHAVB.setText(null);
        DISTRIBUIDORB.setText(null);
    }
    public void LimpiarM()
    {
        CODEM.setText(null);
        NOMBREM1.setText(null);
        COLORM.setText(null);
        TAMAÑOM.setText(null);
        STOCKM.setText(null);
        COSTOM.setText(null);
        PRECIOM.setText(null);
        FECHAIM.setText(null);
        FECHAVM.setText(null);
        DISTRIBUIDORM.setText(null);
    }
    public void LimpiarE()
    {
        CODEE.setText(null);
        NOMBREE.setText(null);
        COLORE.setText(null);
        TAMAÑOE.setText(null);
        STOCKE.setText(null);
        COSTOE.setText(null);
        PRECIOE.setText(null);
        FECHAIE.setText(null);
        FECHAVE.setText(null);
        DISTRIBUIDORE.setText(null);
    }
    
    public void LimpiarTabla(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) TABLE.getModel();
            int filas = TABLE.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }

    }
    public void Validar()
    {
        if(NOMBRE.getText().isEmpty() || COLOR.getText().isEmpty() || TAMAÑO.getText().isEmpty() || DISTRIBUIDOR.getSelectedItem().toString().isEmpty())
        {
            CAMPO.setText("*Hay Campos Vacios!");
        }
        else
        {
            CAMPO.setText("");
        }
    }
    
    public void getDistritos(){
        
        ArrayList<String> rz = new ArrayList<>();
        
            try{
                String sql = "select * from distribuidor";
                con = cn.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);
                
                while(rs.next()){
                    Distribuidor d = new Distribuidor();
                    d.setCodigo(rs.getInt("CodDistribuidor"));
                    d.setRazon_social(rs.getString("Razon_Social"));
                    
                    distribuidores.add(d);
                    rz.add(d.getRazon_social());

                }
            
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            DISTRIBUIDOR.setModel(new DefaultComboBoxModel(rz.toArray()));
    }
    
   
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mantenimiento_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mantenimiento_Producto().setVisible(true);
            }
        });
    }

                
}




/*FRAME MENU DE VENTA*/


package Vista.FrmVendedor;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Controller.ArrayProducto;
import Controller.ArrayCarrito;
import Modelo.Carrito;
import Modelo.Producto;
import java.util.ArrayList;
import Controller.ArrayCliente;
import Modelo.Cliente;
import Controller.ArrayDetalleVenta;
import Controller.ArrayVenta;
import Modelo.Boleta;
import Modelo.DetalleVenta;
import Modelo.Factura;
import Modelo.Venta;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import Vista.FrmGeneral.LoginGeneral;
import javax.swing.DefaultComboBoxModel;

public class Menu_Venta extends javax.swing.JFrame {

    ArrayProducto arrayProducto = new ArrayProducto();
    ArrayCliente arrayCliente = new ArrayCliente();
    ArrayCarrito Acarrito = new ArrayCarrito();
    ArrayDetalleVenta arrayDetalleV = new ArrayDetalleVenta();
    ArrayVenta arrayVenta = new ArrayVenta();
    
    
    Producto pBusca = new Producto();
    
    int codeCliente;
    int codVendedor;
    double TotalVenta;
    double TotalCarro;
    String ruc;
    
    Date fecha = new Date();
    String fecha_ingresoActual;
    
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    DefaultTableModel modelTable = new DefaultTableModel();
    DefaultTableModel modelTable02 = new DefaultTableModel();
    DefaultTableModel modelTable03 = new DefaultTableModel();
    
    
    public Menu_Venta(int codVen) {
        initComponents();
        this.setLocationRelativeTo(null);
        codVendedor = codVen;
        columnasProductos();
        columnasCarrito();
        columnasVenta();
        
        ListarProductos();
        fecha_ingresoActual = sd.format(fecha);
        txtFechaVenta.setText(fecha_ingresoActual);
        txtCodVendedor.setText(Integer.toString(codVen));
        mostrarCodCliente();
    }

    @SuppressWarnings("unchecked")

  private void BBuscarProActionPerformed(java.awt.event.ActionEvent evt) {                                           
        
        if(txtNombrePro.getText().isEmpty()){
            int code =Integer.parseInt(txtCodigo.getText());
            pBusca = arrayProducto.buscarCode(code);
            
            if(pBusca.getNombreProduc() != "Error")
            {
                txtNombrePro.setText(pBusca.getNombreProduc());
                txtFechaVen.setText(pBusca.getFechaVencimiento());
                txtStock.setText(Integer.toString(pBusca.getStock()));
                txtPrecioVenta.setText(Double.toString(pBusca.getPrecio()));
            }
            else
            {
                
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
                
            }
        }
        if(txtCodigo.getText().isEmpty()){
            String nombre = txtNombrePro.getText();
            Producto p1 = arrayProducto.buscarNombre(nombre);
            
            if(p1.getCodigo() != 0)
            {
                txtCodigo.setText(Integer.toString(p1.getCodigo()));
                txtFechaVen.setText(p1.getFechaVencimiento());
                txtStock.setText(Integer.toString(p1.getStock()));
                txtPrecioVenta.setText(Double.toString(p1.getPrecio()));
            }
            else
            {
                
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO EL REGISTRO", "ERROR", JOptionPane.WARNING_MESSAGE);
                
            }
        }
        
            
    }                                          
    public void mostrarCodCliente(){
        codeCliente = arrayCliente.Correlativo();
        CODE.setText(Integer.toString(codeCliente));
    }
    private void TablaProducMouseClicked(java.awt.event.MouseEvent evt) {                                         
        int filaSeleccionada = TablaProduc.getSelectedRow();
        
        String cod = TablaProduc.getValueAt(filaSeleccionada, 0).toString();
        String nombreP = TablaProduc.getValueAt(filaSeleccionada, 1).toString();
        String FechaV = TablaProduc.getValueAt(filaSeleccionada, 2).toString();
        String Precio = TablaProduc.getValueAt(filaSeleccionada, 3).toString();
        String Stock = TablaProduc.getValueAt(filaSeleccionada, 4).toString();
        
        txtCodigo.setText(cod);
        txtNombrePro.setText(nombreP);
        txtFechaVen.setText(FechaV);
        txtPrecioVenta.setText(Precio);
        txtStock.setText(Stock);
        
        
    }                                        
    public void buscarIgualdad(int cod,int cantidad){
        int cant;
        int cantidadActual;
        for(int i = 0; i < Acarrito.rows(); i++){
            if(cod == Acarrito.get(i).getCod()){
                cant = Acarrito.get(i).getCantidad();
                cantidadActual =  cant + cantidad;
                Acarrito.modificar(cod, cantidadActual);
            }
        }
        
    }
    private void BAgregarActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Carrito ca = new Carrito();
        int cod = Integer.parseInt(txtCodigo.getText());
        String nombrePro = txtNombrePro.getText();
        String fecha_ven = txtFechaVen.getText();
        double precioP = Double.parseDouble(txtPrecioVenta.getText());
        int Stock = Integer.parseInt(txtStock.getText());
        int cantidad = Integer.parseInt(SPCantidadIngre.getValue().toString());
        int codB;
        double subtotalPorProducto;
        
        Carrito cb = Acarrito.buscarCode(cod);
        codB = cb.getCod();
                
        ca.setCod(cod);
        ca.setNombrePr(nombrePro);
        ca.setFechaVen(fecha_ven);
        ca.setPrecioPro(precioP);
        ca.setCantidad(cantidad);
        
        if(Stock >= cantidad && cantidad != 0){
            
            
            if(codB == cod){
                buscarIgualdad(cod,cantidad);
                
            }else{
                Acarrito.agregarDatos(ca);
            }
            subtotalPorProducto = ca.Subtotal();
            
            TotalCarro = Acarrito.acumuladorTotal();
            arrayProducto.modificarStock(pBusca, cod, cantidad);
            LimpiarTablaProductos();
            ListarProductos();
            actualizarStockBusca(cod);
            
            LimpiarTablaCarrito();
            ListarCarrito();
            LimpiarTablaVenta();
            ListarVenta();
            JOptionPane.showMessageDialog(null, "EL PRODUCTO SE INGRESO AL CARRITO");
            TotalCarrito.setText("Monto: "+ Double.toString(TotalCarro));
            
        }else{
            JOptionPane.showMessageDialog(null, "LA CANTIDAD INGRESADA SUPERA EL STOCK DEL PRODUCTO O NO SE INGRESO CORRECTAMENTE LA CANTIDAD");
        }
        SPCantidadIngre.setValue(0);
    }                                        
    public void actualizarStockBusca(int code){
        Producto p = arrayProducto.buscarCode(code);
        txtStock.setText(Integer.toString(p.getStock()));
    }                                  

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        modificar();
        LimpiarTablaCarrito();
        ListarCarrito();
        LimpiarTablaProductos();
        ListarProductos();
        LimpiarTablaVenta();
        ListarVenta();
    }                                           

    private void BEliminarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
            int filaSeleccionada = TablaCarrito.getSelectedRow();
            int posiCod = Integer.parseInt(TablaCarrito.getValueAt(filaSeleccionada, 0).toString());
            int cantidadE = Integer.parseInt(TablaCarrito.getValueAt(filaSeleccionada, 4).toString());
        
            if(filaSeleccionada >= 0){
                modelTable02.removeRow(filaSeleccionada);
                Acarrito.eliminar(posiCod);
                TotalCarro = Acarrito.acumuladorTotal();
                TotalCarrito.setText("Monto: "+ Double.toString(TotalCarro));
                
                arrayProducto.actualizarStock(posiCod, cantidadE);
                LimpiarTablaProductos();
                ListarProductos();
                LimpiarTablaVenta();
                ListarVenta();

                JOptionPane.showMessageDialog(null, "SE ELIMINO EL PRODUCTO SELECCIONADO DEL CARRITO");
                

            }else{
                JOptionPane.showMessageDialog(null, "NO SE SELECCIONO NINGUN PRODUCTO");
            }
            
       
                
    }                                         

    private void DNIKeyTyped(java.awt.event.KeyEvent evt) {                             
        if (DNI.getText().length() >= 8) {
            evt.consume();
        }
        boton();
        Validar();
    }                            

    private void NOMBREKeyTyped(java.awt.event.KeyEvent evt) {                                
        boton();
        Validar();
    }                               

    private void APELLIDOKeyTyped(java.awt.event.KeyEvent evt) {                                  
        boton();
        Validar();
    }                                 

    private void HOMBREKeyTyped(java.awt.event.KeyEvent evt) {                                
        boton();
        Validar();
    }                               

    private void MUJERKeyTyped(java.awt.event.KeyEvent evt) {                               
        boton();
        Validar();
    }                              

    private void CORREOKeyTyped(java.awt.event.KeyEvent evt) {                                
        boton();
        Validar();
    }                                                                   

    private void DIRECCIONKeyTyped(java.awt.event.KeyEvent evt) {                                   
        boton();
        Validar();
    }                                  
    public void boton()
        {
            if (NOMBRE.getText().isEmpty() || DNI.getText().isEmpty() || APELLIDO.getText().isEmpty() || HOMBRE.getText().isEmpty() || MUJER.getText().isEmpty() || CORREO.getText().isEmpty() || DIRECCION.getText().isEmpty()) {
                REGISTRAR.setEnabled(false);
            } 
            else 
            {
                REGISTRAR.setEnabled(true);
            }
        }
    private void REGISTRARActionPerformed(java.awt.event.ActionEvent evt) {                                          

        codeCliente = arrayCliente.Correlativo();
        int rpt;
        String dni = DNI.getText();
        String nombre = NOMBRE.getText();
        String ape = APELLIDO.getText();
        String fecha = sd.format(CALENDARIO.getDate());
        String correo = CORREO.getText();
        String direccion = DIRECCION.getText();
        String distrito = DISTRITO.getSelectedItem().toString();
        String provincia = PROVINCIA.getSelectedItem().toString();
        String pais = PAIS.getSelectedItem().toString();
        String sexo;
        ruc = txtRucRegis.getText();

        if (HOMBRE.isSelected()) {
            sexo = HOMBRE.getText();
        } else {
            sexo = MUJER.getText();
        }

        rpt = JOptionPane.showConfirmDialog(null, "DESEA REGISTRAR ESTE CLIENTE?", "AVISO", JOptionPane.YES_NO_OPTION);
        if (rpt == 0) {
            Cliente b = new Cliente();
            b.setCode(codeCliente);
            b.setDni(dni);
            b.setNombre(nombre);
            b.setApe(ape);
            b.setFechaN(fecha);
            b.setSexo(sexo);
            b.setEmail(correo);
            b.setDireccion(direccion);
            b.setDistrito(distrito);
            b.setProvincia(provincia);
            b.setPais(pais);
            b.setRuc(ruc);

            arrayCliente.AgregarArray(b);
            try
            {
                arrayCliente.Grabar_Cliente(b);
                txtCodCliente.setText(Integer.toString(codeCliente));

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "OCURRIO UN PROBLEMA AL GRABAR EN EL ARCHIVO" + e);

            }

            JOptionPane.showMessageDialog(null, "REGISTRO COMPLETADO", "AVISO", JOptionPane.OK_OPTION);
            Limpiar();
            
            CODE.setText(Integer.toString(codeCliente+1));
            
            
        } else {
            JOptionPane.showMessageDialog(null, "REGISTRO CANCELADO", "ERROR", JOptionPane.WARNING_MESSAGE);
        }

    }                                         

    private void BRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        registrarDetalleVenta();
        LimpiarMenuVenta();
        
    }                                               
    
    private void registrarDetalleVenta(){
        int codigoCliente = Integer.parseInt(txtCodCliente.getText());
        Venta ven = new Venta();
        
        int codProduc;
        int cantidadProducto;
        double precioProducto;
        String tipoVenta;
        
        
        if (RBoleta.isSelected()) {
            tipoVenta = RBoleta.getText();
        } else {
            tipoVenta = RFactura.getText();
        }
        
        if(tipoVenta.equals("Boleta")){
            int codDetalleV = arrayDetalleV.correlativoBoleta();
            
        
        
            for(int i = 0; i < Acarrito.rows(); i++){
                int codCorrela = arrayDetalleV.correlativoDetalleVenta();
                
                codProduc = Acarrito.get(i).getCod();
                cantidadProducto = Acarrito.get(i).getCantidad();
                precioProducto = Acarrito.get(i).getPrecioPro();
                
                
                DetalleVenta dv = new DetalleVenta(codCorrela, codDetalleV, codVendedor, codProduc, cantidadProducto, precioProducto, tipoVenta);
                Boleta b = new Boleta();
                
                b.setID(codCorrela);
                b.setTipo(tipoVenta);
                b.setIdVenta(codDetalleV);
                b.setIdVendedor(codVendedor);
                b.setIdProducto(codProduc);
                b.setCantidadPro(cantidadProducto);
                b.setPrecioPro(precioProducto);
                b.setTotal(b.Total());
                
                
                arrayDetalleV.agregarDatosDetalle(dv);
                arrayDetalleV.agregarDatosBoleta(b);
                arrayDetalleV.Grabar_DetalleVenta(dv);
                arrayDetalleV.Grabar_Boleta(b);

            }
            ven.setIdVenta(codDetalleV);
            ven.setFecha(fecha_ingresoActual);
            ven.setIdCliente(codigoCliente);
            ven.setIdVendedor(codVendedor);
            ven.setMontoTotal(TotalCarro);
            
            arrayVenta.agregarDatos(ven);
            arrayVenta.Grabar_Venta(ven);
            
        }else{
            int codDetalleV = arrayDetalleV.correlativoFactura();
            
        
            for(int i = 0; i < Acarrito.rows(); i++){
                int codCorrela = arrayDetalleV.correlativoDetalleVenta();
                
                codProduc = Acarrito.get(i).getCod();
                cantidadProducto = Acarrito.get(i).getCantidad();
                precioProducto = Acarrito.get(i).getPrecioPro();
                

                DetalleVenta dv = new DetalleVenta(codCorrela, codDetalleV, codVendedor, codProduc, cantidadProducto, precioProducto, tipoVenta);
                Factura f = new Factura();
                
                f.setID(codCorrela);
                f.setIdVenta(codDetalleV);
                f.setIdVendedor(codVendedor);
                f.setIdProducto(codProduc);
                f.setCantidadPro(cantidadProducto);
                f.setPrecioPro(precioProducto);
                f.setTipo(tipoVenta);
                f.setSubtotal(f.SubTotal());
                f.setTotal(f.Total());
                
                arrayDetalleV.agregarDatosDetalle(dv);
                arrayDetalleV.agregarDatosFactura(f);
                arrayDetalleV.Grabar_DetalleVenta(dv);
                arrayDetalleV.Grabar_Factura(f);

            }
            ven.setIdVenta(codDetalleV);
            ven.setFecha(fecha_ingresoActual);
            ven.setIdCliente(codigoCliente);
            ven.setIdVendedor(codVendedor);
            ven.setMontoTotal(TotalVenta);
            
            arrayVenta.agregarDatos(ven);
            arrayVenta.Grabar_Venta(ven);
        }
        
        for(int i = 0; i < Acarrito.rows(); i++){
            arrayProducto.Grabar_ModificarProducto_Stock(Acarrito.get(i).getCod(), Acarrito.get(i).getCantidad());
        }
        
        
        JOptionPane.showMessageDialog(null, "VENTA REGISTRADA CORRECTAMENTE");
    }
    
    
    
    private void BProcesarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        SeleccionTipoVenta();
    }                                         
    public void borrarDatosCarrito(){
        Acarrito.eliminarTodo();
    }
  
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int r = JOptionPane.showConfirmDialog(null,"DESEA VOLVER AL LOGIN?","AVISO",JOptionPane.YES_NO_OPTION);
        if(r==0)
        {
        LoginGeneral lo = new LoginGeneral();
        lo.setVisible(true);
        this.dispose();
        }
        
    }                                        
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int r = JOptionPane.showConfirmDialog(null,"DESEA VOLVER AL LOGIN?","AVISO",JOptionPane.YES_NO_OPTION);
        if(r==0)
        {
        LoginGeneral lo = new LoginGeneral();
        lo.setVisible(true);
        this.dispose();
        }
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int r = JOptionPane.showConfirmDialog(null,"DESEA VOLVER AL LOGIN?","AVISO",JOptionPane.YES_NO_OPTION);
        if(r==0)
        {
        LoginGeneral lo = new LoginGeneral();
        lo.setVisible(true);
        this.dispose();
        }
    }                                        

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       int r = JOptionPane.showConfirmDialog(null,"DESEA VOLVER AL LOGIN?","AVISO",JOptionPane.YES_NO_OPTION);
        if(r==0)
        {
        LoginGeneral lo = new LoginGeneral();
        lo.setVisible(true);
        this.dispose();
        }
    }                                        

    private void ROpcionNoActionPerformed(java.awt.event.ActionEvent evt) {                                          
           
    }                                         

    private void ROpcionSiActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
    }                                         

    private void PROVINCIAItemStateChanged(java.awt.event.ItemEvent evt) {                                           
        if(evt.getStateChange() == ItemEvent.SELECTED){
            if(PROVINCIA.getSelectedIndex() > 0){
                DISTRITO.setModel(new DefaultComboBoxModel(this.getDistritos(PROVINCIA.getSelectedItem().toString())));
            }
        }
    }                                          

    private void BUSCARActionPerformed(java.awt.event.ActionEvent evt) {                                       
        if(txtCodCliente.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "DIGITE UN CODIGO","AVISO",JOptionPane.YES_OPTION);
        }
        else
        {
            try
                {
                    int c = Integer.parseInt(txtCodCliente.getText());
                    Cliente c1 = arrayCliente.buscarCodeV(c);
                    
                    JOptionPane.showMessageDialog(null, "SE ENCONTRO AL CLIENTE","AVISO",JOptionPane.YES_OPTION);
                    txtRuc.setText(c1.getRuc());
                    
                    if(txtRuc.getText().equals("-"))
                    {
                        RBoleta.setSelected(true);
                        RBoleta.setEnabled(true);
                        RFactura.setEnabled(false);
                    }
                    else
                    {
                        RFactura.setSelected(true);
                        RFactura.setEnabled(true);
                        RBoleta.setEnabled(false);
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "NO SE ENCONTRO AL CLIENTE","AVISO",JOptionPane.YES_OPTION);
                }
        }
    }                                      

                             

    private void ROpcionSiMouseClicked(java.awt.event.MouseEvent evt) {                                       
        if(ROpcionSi.isSelected()){
            txtRucRegis.setEditable(true);
            txtRucRegis.setText(null);
            RBoleta.setEnabled(false);
            RFactura.setEnabled(true);
        }else{
            txtRucRegis.setEditable(false);
            txtRucRegis.setText("-");
            RFactura.setEnabled(false);
        }
    }                                      

    private void ROpcionNoMouseClicked(java.awt.event.MouseEvent evt) {                                       
        if(ROpcionNo.isSelected()){
            txtRucRegis.setEditable(false);
            txtRucRegis.setText("-");
            RFactura.setEnabled(false);
            RBoleta.setEnabled(true);
        }else{
            txtRucRegis.setEditable(true);
            RBoleta.setEnabled(false);
        }
    }                                      

    private void RBoletaMouseClicked(java.awt.event.MouseEvent evt) {                                     
        if(RBoleta.isSelected()){
            txtRuc.setText("-");
        }else{
            txtRuc.setText(ruc);
        }
    }                                    

    private void RFacturaMouseClicked(java.awt.event.MouseEvent evt) {                                      
        if(RFactura.isSelected()){
            txtRuc.setText(ruc);
        }else{
            txtRuc.setText("-");
        }
    }                                     

    private void txtRucRegisKeyTyped(java.awt.event.KeyEvent evt) {                                     
        if (txtRucRegis.getText().length() >= 11) {
            evt.consume();
        }
        boton();
    }                                    
    
    public void SeleccionTipoVenta()
    {
        double igv = 0.18;
       
            if(RBoleta.isSelected())
            {
                txtSubTotal.setText(Double.toString(TotalCarro));
                txtTotal.setText(Double.toString(TotalCarro));
            }
            else if(RFactura.isSelected())
            {
                TotalVenta = TotalCarro + (TotalCarro * igv);
                txtSubTotal.setText(Double.toString(TotalCarro));
                txtTotal.setText(Double.toString(TotalVenta));
            }
       
    }
    
    public void Limpiar()
    {
        DNI.setText(null);
        NOMBRE.setText(null);
        APELLIDO.setText(null);
        CALENDARIO.setDate(null);
        
        buttonGroup2.clearSelection();
        
        CORREO.setText(null);
        DIRECCION.setText(null);
        DISTRITO.setSelectedItem("Distrito");
        PROVINCIA.setSelectedItem("Provincia");
        PAIS.setSelectedItem("Pais");
        
        buttonGroup3.clearSelection();
        
        txtRucRegis.setText(null);
    }
    public void modificar(){
        int filaSeleccionada = TablaCarrito.getSelectedRow();
        int posiCod = Integer.parseInt(TablaCarrito.getValueAt(filaSeleccionada, 0).toString());
        int cantidadM = Integer.parseInt(TablaCarrito.getValueAt(filaSeleccionada, 4).toString());
        
        Carrito ca = Acarrito.buscarCode(posiCod);
        int cantPro = ca.getCantidad();
        
        boolean estado = Acarrito.modificar(posiCod, cantidadM);
        
        if(estado == true){
            TotalCarro = Acarrito.acumuladorTotal();
            TotalCarrito.setText("Monto: "+ Double.toString(TotalCarro));
            residuo(posiCod, cantidadM, cantPro);
            
            
            JOptionPane.showMessageDialog(null, "SE ACTUALIZO EL CARRITO");
        }else{
            JOptionPane.showMessageDialog(null, "NO SE ACTUALIZO EL CARRITO");
        }
        
    }
    
    public void residuo(int posiCod,int cantidadM, int cantPro){
        int residuo;
        
        if(cantidadM < cantPro){
            residuo = cantPro - cantidadM;
            arrayProducto.actualizarStock(posiCod, residuo);
            
        }
        if(cantidadM > cantPro){
           residuo = cantidadM - cantPro;
           arrayProducto.modificarStock(pBusca, posiCod, residuo);
           
        }
    }
    
    public void columnasCarrito(){
        modelTable02.addColumn("CODIGO");
        modelTable02.addColumn("NOMBRE");
        modelTable02.addColumn("FECHA VENCIMIENTO");
        modelTable02.addColumn("PRECIO VENTA");
        modelTable02.addColumn("CANTIDAD");
        modelTable02.addColumn("SUBTOTAL");
        
        this.TablaCarrito.setModel(modelTable02);
    }
    public void columnasProductos(){
        modelTable.addColumn("CODIGO");
        modelTable.addColumn("NOMBRE");
        modelTable.addColumn("FECHA VENCIMIENTO");
        modelTable.addColumn("PRECIO VENTA");
        modelTable.addColumn("STOCK");
        
        
        this.TablaProduc.setModel(modelTable);
    }
    public void columnasVenta(){
        modelTable03.addColumn("CODIGO");
        modelTable03.addColumn("NOMBRE");
        modelTable03.addColumn("FECHA VENCIMIENTO");
        modelTable03.addColumn("PRECIO VENTA");
        modelTable03.addColumn("CANTIDAD");
        modelTable03.addColumn("SUBTOTAL");
        
        this.TablaVenta.setModel(modelTable03);
    }
    public void ListarVenta(){
        String[] filas = new String[6];
        for(int i = 0; i < Acarrito.rows(); i++){
           filas[0] = Integer.toString(Acarrito.get(i).getCod());
           filas[1] = Acarrito.get(i).getNombrePr();
           filas[2] = Acarrito.get(i).getFechaVen();
           filas[3] = Double.toString(Acarrito.get(i).getPrecioPro());
           filas[4] = Integer.toString(Acarrito.get(i).getCantidad());
           filas[5] = Double.toString(Acarrito.get(i).Subtotal());
           
           modelTable03.addRow(filas);
        }
    }
    public void ListarCarrito(){
        String [] filas = new String[6];
        

          for(int i = 0; i < Acarrito.rows(); i++){
           filas[0] = Integer.toString(Acarrito.get(i).getCod());
           filas[1] = Acarrito.get(i).getNombrePr();
           filas[2] = Acarrito.get(i).getFechaVen();
           filas[3] = Double.toString(Acarrito.get(i).getPrecioPro());
           filas[4] = Integer.toString(Acarrito.get(i).getCantidad());
           filas[5] = Double.toString(Acarrito.get(i).Subtotal());
                   

            modelTable02.addRow(filas);

          }
    }
    public void ListarProductos(){
        String [] filas = new String[5];
        

          for(int i = 0; i < arrayProducto.Filas(); i++){
           filas[0] = Integer.toString(arrayProducto.get(i).getCodigo());
           filas[1] = arrayProducto.get(i).getNombreProduc();
           filas[2] = arrayProducto.get(i).getFechaVencimiento();
           filas[3] = Double.toString(arrayProducto.get(i).getPrecio());
           filas[4] = Integer.toString(arrayProducto.get(i).getStock());
                   

            modelTable.addRow(filas);

          }
    }
    public void LimpiarTablaVenta(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) TablaVenta.getModel();
            int filas = TablaVenta.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }

    }
    public void LimpiarTablaCarrito(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) TablaCarrito.getModel();
            int filas = TablaCarrito.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }

    }
    public void LimpiarTablaProductos(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) TablaProduc.getModel();
            int filas = TablaProduc.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }

    }
    
    
    public void LimpiarMenuVenta(){
        borrarDatosCarrito();
        txtCodigo.setText(null);
        txtNombrePro.setText(null);
        txtFechaVen.setText(null);
        txtPrecioVenta.setText(null);
        txtStock.setText(null);
        SPCantidadIngre.setValue(0);
        
        LimpiarTablaCarrito();
        txtCodCliente.setText(null);
        buttonGroup1.clearSelection();
        txtRuc.setText(null);
        txtSubTotal.setText(null);
        txtTotal.setText(null);
        LimpiarTablaVenta();
        TotalCarrito.setText(null);
        
        
    }
     public String[] getDistritos(String pais){
        String[] distritos = null;
        BufferedReader lector;
        if(pais.equalsIgnoreCase("Lima Metropolitana")){
            distritos = new String[44];
            try{
                File archivoTXT = new File("DistritosLima.txt");
                FileReader fr = new FileReader(archivoTXT);
                lector = new BufferedReader(fr);
            
                String linea;
                int i = 0;
                while((linea = lector.readLine()) != null){
                    distritos[i] = linea;    
                    i++;

                }
                lector.close();
            
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return distritos;
        }
        
        if(pais.equalsIgnoreCase("Cañete")){
            distritos = new String[17];
            try{
                File archivoTXT = new File("DistritosCañete.txt");
                FileReader fr = new FileReader(archivoTXT);
                lector = new BufferedReader(fr);
            
                String linea;
                int i = 0;
                while((linea = lector.readLine()) != null){
                    distritos[i] = linea;    
                    i++;

                }
                lector.close();
            
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            return distritos;
        }
        return distritos;
    }
    public void Validar()
    {
        if(DNI.getText().isEmpty() || NOMBRE.getText().isEmpty() || APELLIDO.getText().isEmpty() || HOMBRE.getText().isEmpty() || MUJER.getText().isEmpty() || CORREO.getText().isEmpty() || txtRucRegis.getText().isEmpty())
        {
            CAMPO.setText("*HAY CAMPOS VACIOS");
           
        }
        else
        {
            CAMPO.setText("");
            
        }
    }
    
   
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Venta(0).setVisible(true);
            }
        });
    }

                    
}
