using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class DistritoRepository : IDistritos
    {
        private OperaC conexion = new OperaC();
        public IEnumerable<Distrito> GetAllDistritos()
        {
            return conexion.Distritos;
        }
    }
}
