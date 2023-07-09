using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class UsuarioRepository : IUsuario
    {
        private OperaC conexion = new OperaC();
        public void AñadirUsuario(Usuario objUsuario)
        {
            conexion.Usuarios.Add(objUsuario);
            conexion.SaveChanges();
        }

        public bool ValidateUser(Usuario obj)
        {
            var objUsuario = (from tusu in conexion.Usuarios
                              where tusu.Username == obj.Username
                              && tusu.Contrasena == obj.Contrasena
                              select tusu).FirstOrDefault();
            if (objUsuario == null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        public IEnumerable<Usuario> GetAllUsuarios()
        {
            return conexion.Usuarios;
        }

        public int GetIdUsuario(Usuario obj)
        {
            var objUsuario = (from tusu in conexion.Usuarios
                              where tusu.Username == obj.Username
                              && tusu.Contrasena == obj.Contrasena
                              select tusu).FirstOrDefault();

            if(objUsuario != null)
            {
                return objUsuario.IdUsuario;
            }
            else
            {
                return -1;
            }
        }
    }
}
