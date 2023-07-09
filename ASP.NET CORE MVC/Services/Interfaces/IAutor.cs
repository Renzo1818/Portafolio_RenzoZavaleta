using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IAutor
    {
        void AñadirAutor(Autor objAutor);
        IEnumerable<Autor> GetAllAutores();
        IEnumerable<Autor> GetAllAutoresTrue();
        int GetCorrelativoAutor();
        Autor GetIdAutorModificar(int codigo);
        void EditDetails_Autor(Autor objAutor);
        bool EliminarAutor(int codigo);
        Autor Buscar(string buscar);
    }
}
