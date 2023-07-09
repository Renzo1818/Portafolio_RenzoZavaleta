using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class CategoriaRepository : ICategoria
    {
        private OperaC conexion = new OperaC();
        public IEnumerable<Categorium> GetAllCategorias()
        {
            return conexion.Categoria;
        }

        public int GetCorrelativoCategoria()
        {
            return conexion.Categoria.Max(c => c.IdCategoria);
        }
    }
}
