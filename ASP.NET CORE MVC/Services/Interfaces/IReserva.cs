using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IReserva
    {
        IEnumerable<ReservaVirtual> GetAllReservas();
        void Agregar(ReservaVirtual objReserva);
        int GetCorrelativo();
    }
}
