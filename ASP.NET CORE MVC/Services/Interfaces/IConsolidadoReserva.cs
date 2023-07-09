using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IConsolidadoReserva
    {
        IEnumerable<ConsolidadoReserva> GetAllConsolidadoReservaFalse(int IdLibro);
        IEnumerable<ConsolidadoReserva> GetAllConsolidadoReservaTrue(int IdLibro);
    }
}
