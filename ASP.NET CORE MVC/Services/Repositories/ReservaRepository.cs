using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class ReservaRepository : IReserva
    {
        private OperaC conexion = new OperaC();
        public void Agregar(ReservaVirtual objReserva)
        {
            conexion.ReservaVirtuals.Add(objReserva);
            conexion.SaveChanges();
        }

        public IEnumerable<ReservaVirtual> GetAllReservas()
        {
            return conexion.ReservaVirtuals;
        }

        public int GetCorrelativo()
        {
            return conexion.ReservaVirtuals.Max(r => r.IdReserva);
        }
    }
}
