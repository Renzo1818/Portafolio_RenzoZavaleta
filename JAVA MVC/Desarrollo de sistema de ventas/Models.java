/*CLASE TRABAJADOR*/

package Modelo;


public class Trabajador {
    protected int codigo;
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String sexo;
    protected String fecha_nacimiento;
    protected String email;
    protected String direccion;
    protected String distrito;
    protected String provincia;
    protected String pais;
    protected double sueldo;
    protected String sistema_pension;
    protected double seguro_salud;
    protected String fecha_ingreso;
    protected String tipo_traba;

    public Trabajador(int codigo, String nombre, String apellido, String dni, String sexo, String fecha_nacimiento, String email, String direccion, String distrito, String provincia, String pais, double sueldo, String sistema_pension, double seguro_salud, String fecha_ingreso, String tipo_traba) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.direccion = direccion;
        this.distrito = distrito;
        this.provincia = provincia;
        this.pais = pais;
        this.sueldo = sueldo;
        this.sistema_pension = sistema_pension;
        this.seguro_salud = seguro_salud;
        this.fecha_ingreso = fecha_ingreso;
        this.tipo_traba = tipo_traba;
    }

    

    public Trabajador() {
        
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public void setSistema_pension(String sistema_pension) {
        this.sistema_pension = sistema_pension;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setSeguro_Salud(double seguro_salud) {
        this.seguro_salud = seguro_salud;
    }

    public void setTipo_traba(String tipo_traba) {
        this.tipo_traba = tipo_traba;
    }


    public int getCodigo() {
        return codigo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPais() {
        return pais;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getSistema_pension() {
        return sistema_pension;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public double getSeguro_Salud() {
        return seguro_salud;
    }

    public String getTipo_traba() {
        return tipo_traba;
    }

    
    public double descSistema_pension(){
        if(sistema_pension == "ONP"){
            return 0.13;
        }else{
            return 0.1;
        }
        
    }
    
    public double operaciónSueldo(){
        return sueldo - (sueldo * descSistema_pension());
        
    }
    
}


/*CLASE VENDEDOR*/

package Modelo;


public class Vendedor extends Trabajador{
    private int nHijos;
    private double boniHijos;

    public Vendedor(int codigo, String nombre, String apellido, String dni, String sexo, String fecha_nacimiento, String email, String direccion, String distrito, String provincia, String pais, double sueldo, String sistema_pension, double seguro_salud, int nHijos, double boniHijos, String fecha_ingreso, String tipo_traba) {
        super(codigo, nombre, apellido, dni, sexo, fecha_nacimiento, email, direccion, distrito, provincia, pais, sueldo, sistema_pension, seguro_salud, fecha_ingreso, tipo_traba);
        this.nHijos = nHijos;
        this.boniHijos = boniHijos;
    }

    public Vendedor() {
    }


    public void setnHijos(int nHijos) {
        this.nHijos = nHijos;
    }

    public void setBoniHijos(double boniHijos) {
        boniHijos = 0.1;
        this.boniHijos = boniHijos;
    }

    public int getnHijos() {
        return nHijos;
    }

    public double getBoniHijos() {
        return boniHijos;
    }

    public double bonificacion(){
        return nHijos * boniHijos;
    }


    
    @Override
    public double operaciónSueldo() {
        return sueldo  - (sueldo * descSistema_pension()) + (sueldo * bonificacion());
        
    }
    
    
}



/*CLASE ADMINISTRADOR*/

package Modelo;


public class Admin extends Trabajador{
    private double impuesto;
    private double utilidades;

    public Admin(int codigo, String nombre, String apellido, String dni, String sexo, String fecha_nacimiento, String email, String direccion, String distrito, String provincia, String pais, double sueldo, String sistema_pension, double seguro_salud, double impuesto, double utilidades, String fecha_ingreso, String tipo_traba) {
        super(codigo, nombre, apellido, dni, sexo, fecha_nacimiento, email, direccion, distrito, provincia, pais, sueldo, sistema_pension, seguro_salud, fecha_ingreso, tipo_traba);
        this.impuesto = impuesto;
        this.utilidades = utilidades;
    }

    

    public Admin() {
    }


    public void setImpuesto(double impuesto) {
        impuesto = 0.15;
        this.impuesto = impuesto;
    }

    public void setUtilidades(double utilidades) {
        utilidades = 0.3;
        this.utilidades = utilidades;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getUtilidades() {
        return utilidades;
    }
    
    
    @Override
    public double operaciónSueldo() {
        return sueldo - ((sueldo * descSistema_pension()) + (sueldo * impuesto)) + (sueldo * utilidades);
        
    }
}



/*CLASE USUARIO*/

package Modelo;

public class Usuario {
    private int codUsu;
    private String nombUsu;
    private String contrasena;
    private String tipo_Usu;

    public Usuario(int codUsu, String nombUsu, String contrasena, String tipo_Usu) {
        this.codUsu = codUsu;
        this.nombUsu = nombUsu;
        this.contrasena = contrasena;
        this.tipo_Usu = tipo_Usu;
    }

    public Usuario() {
    }
    

    public void setCodUsu(int codUsu) {
        this.codUsu = codUsu;
    }

    public void setNombUsu(String nombUsu) {
        this.nombUsu = nombUsu;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public void setTipo_Usu(String tipo_Usu) {
        this.tipo_Usu = tipo_Usu;
    }

    public int getCodUsu() {
        return codUsu;
    }

    public String getNombUsu() {
        return nombUsu;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    public String getTipo_Usu() {
        return tipo_Usu;
    }

}


/*CLASE CLIENTE*/

package Modelo;

public class Cliente 
{
    private int code;
    private String dni;
    private String nombre;
    private String ape;
    private String fechaN;
    private String sexo;
    private String email;
    private String direccion;
    private String distrito;
    private String provincia;
    private String pais;
    private String ruc;
    
    public Cliente(int code, String dni, String nombre, String ape, String fechaN, String sexo, String email, String direccion, String distrito, String provincia, String pais,String ruc) {
        this.code=code;
        this.dni = dni;
        this.nombre = nombre;
        this.ape = ape;
        this.fechaN = fechaN;
        this.sexo = sexo;
        this.email = email;
        this.direccion = direccion;
        this.distrito = distrito;
        this.provincia = provincia;
        this.pais = pais;
        this.ruc = ruc;
    }
    
    public Cliente(){
        
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getFechaN() {
        return fechaN;
    }

    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
        
    }
    
    
    
}



/*CLASE DISTRIBUIDOR*/

package Modelo;


public class Distribuidor {
    private int codigo;
    private String ruc;
    private String razon_social;
    private String sucursal;
    private String correoEle_Contacto;
    private String pagina_web;
    private String telefono_contacto;
    private String direccion;

    public Distribuidor(int codigo, String ruc, String razon_social, String sucursal, String correoEle_Contacto, String pagina_web, String telefono_contacto, String direccion) {
        this.codigo = codigo;
        this.ruc = ruc;
        this.razon_social = razon_social;
        this.sucursal = sucursal;
        this.correoEle_Contacto = correoEle_Contacto;
        this.pagina_web = pagina_web;
        this.telefono_contacto = telefono_contacto;
        this.direccion = direccion;
    }

    public Distribuidor() {
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public void setCorreoEle_Contacto(String correoEle_Contacto) {
        this.correoEle_Contacto = correoEle_Contacto;
    }

    public void setPagina_web(String pagina_web) {
        this.pagina_web = pagina_web;
    }

    public void setTelefono_contacto(String telefono_contacto) {
        this.telefono_contacto = telefono_contacto;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getRuc() {
        return ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public String getSucursal() {
        return sucursal;
    }

    public String getCorreoEle_Contacto() {
        return correoEle_Contacto;
    }

    public String getPagina_web() {
        return pagina_web;
    }

    public String getTelefono_contacto() {
        return telefono_contacto;
    }

    public String getDireccion() {
        return direccion;
    }

   
    
    
}



/*CLASE PRODUCTO*/

package Modelo;


public class Producto 
{
    private int codigo;
    private String nombreProduc;
    private String color;
    private String tamaño;
    private int stock;
    private double costo;
    private double precio;
    private String fechaIngreso;
    private String fechaVencimiento;
    private String distribuidor;

    public Producto(int codigo, String nombreProduc, String color, String tamaño, int stock, double costo, double precio, String fechaIngreso, String fechaVencimiento, String distribuidor) {
        this.codigo = codigo;
        this.nombreProduc = nombreProduc;
        this.color = color;
        this.tamaño = tamaño;
        this.stock = stock;
        this.costo = costo;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.fechaVencimiento = fechaVencimiento;
        this.distribuidor = distribuidor;
    }

    public Producto() {
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombreProduc(String nombreProduc) {
        this.nombreProduc = nombreProduc;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setDistribuidor(String distribuidor) {
        this.distribuidor = distribuidor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombreProduc() {
        return nombreProduc;
    }

    public String getColor() {
        return color;
    }

    public String getTamaño() {
        return tamaño;
    }

    public int getStock() {
        return stock;
    }

    public double getCosto() {
        return costo;
    }

    public double getPrecio() {
        return precio;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getDistribuidor() {
        return distribuidor;
    }

}



/*CLASE DETALLE VENTA*/

package Modelo;


public class DetalleVenta {
    protected int ID;
    protected int idVenta;
    protected int idVendedor;
    protected int idProducto;
    protected int cantidadPro;
    protected double precioPro;
    protected String tipo;

    public DetalleVenta(int ID, int idVenta, int idVendedor, int idProducto, int cantidadPro, double precioPro, String tipo) {
        this.ID = ID;
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.idProducto = idProducto;
        this.cantidadPro = cantidadPro;
        this.precioPro = precioPro;
        this.tipo = tipo;
    }

    public DetalleVenta() {
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidadPro(int cantidadPro) {
        this.cantidadPro = cantidadPro;
    }

    public void setPrecioPro(double precioPro) {
        this.precioPro = precioPro;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getID() {
        return ID;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getCantidadPro() {
        return cantidadPro;
    }

    public double getPrecioPro() {
        return precioPro;
    }

    public String getTipo() {
        return tipo;
    }
    
    
    public double SubTotal() {
        if(tipo.equals("Factura")){
            return (precioPro * cantidadPro);
        }else{
            return 0;
        }
        
    }
    public double Total(){
        if(tipo.equals("Factura")){
            return (precioPro * cantidadPro) + (precioPro * cantidadPro)*0.18;
        }else{
            return precioPro * cantidadPro;
        }
    }
    
    
}


/*CLASE BOLETA*/

package Modelo;

public class Boleta extends DetalleVenta{
    private double Total;

    public Boleta(int ID, int idVenta, int idVendedor, int idProducto, int cantidadPro, double precioPro, double Total, String tipo) {
        super(ID, idVenta, idVendedor, idProducto, cantidadPro, precioPro, tipo);
        this.Total = Total;
    }

    public Boleta() {
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public double getTotal() {
        return Total;
    }

    @Override
    public double Total() {
        return precioPro * cantidadPro;
    }
    
    
}



/*CLASE FACTURA*/

package Modelo;


public class Factura extends DetalleVenta{
    private double Subtotal;
    private double Total;

    public Factura(int ID, int idVenta, int idVendedor, int idProducto, int cantidadPro, double precioPro, double Subtotal, double Total, String tipo) {
        super(ID, idVenta, idVendedor, idProducto, cantidadPro, precioPro, tipo);
        this.Subtotal = Subtotal;
        this.Total = Total;
    }

    public Factura() {
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public double getTotal() {
        return Total;
    }
    
    @Override
    public double SubTotal() {
        return precioPro * cantidadPro;
    }
    
    @Override
    public double Total() {
        return (precioPro * cantidadPro) + (precioPro * cantidadPro)*0.18;
    }
    
    
    
}



/*CLASE VENTA*/

package Modelo;


public class Venta {
    private int idVenta;
    private String fecha;
    private int idCliente;
    private int idVendedor;
    private double montoTotal;

    public Venta(int idVenta, String fecha, int idCliente, int idVendedor, double montoTotal) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.montoTotal = montoTotal;
    }

    public Venta() {
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public double getMontoTotal() {
        return montoTotal;
    }
    
    
}



/*CLASE CARRITO*/


package Modelo;

public class Carrito {
    private int cod;
    private String nombrePr;
    private String FechaVen;
    private double precioPro;
    private int cantidad;

    public Carrito(int cod, String nombrePr, String FechaVen, double precioPro, int cantidad) {
        this.cod = cod;
        this.nombrePr = nombrePr;
        this.FechaVen = FechaVen;
        this.precioPro = precioPro;
        this.cantidad = cantidad;
    }

    public Carrito() {
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNombrePr(String nombrePr) {
        this.nombrePr = nombrePr;
    }

    public void setFechaVen(String FechaVen) {
        this.FechaVen = FechaVen;
    }

    public void setPrecioPro(double precioPro) {
        this.precioPro = precioPro;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCod() {
        return cod;
    }

    public String getNombrePr() {
        return nombrePr;
    }

    public String getFechaVen() {
        return FechaVen;
    }

    public double getPrecioPro() {
        return precioPro;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public double Subtotal(){
        return precioPro * cantidad;
    }

    
    
}



/*CLASE MAIN*/

package Modelo;


import Vista.FrmGeneral.Menu_Inicial;


public class ProyectoFinal_SQL {

    public static void main(String[] args) {
        Menu_Inicial m1 = new Menu_Inicial();
        m1.setVisible(true);
    }
    
}
