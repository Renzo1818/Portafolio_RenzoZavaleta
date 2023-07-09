using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface ICategoria
    {
        IEnumerable<Categorium> GetAllCategorias();
        int GetCorrelativoCategoria();
    }
}
