using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IUsuario
    {
        void AñadirUsuario(Usuario objUsuario);
        bool ValidateUser(Usuario obj);
        IEnumerable<Usuario> GetAllUsuarios();
        int GetIdUsuario(Usuario obj);
    }
}
