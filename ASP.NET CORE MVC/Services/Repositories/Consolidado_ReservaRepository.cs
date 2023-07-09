using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class Consolidado_ReservaRepository:IConsolidadoReserva
    {
        private OperaC conexion = new OperaC();
        public IEnumerable<ConsolidadoReserva> GetAllConsolidadoReservaFalse(int IdPersona)
        {
            var objEncontrado = from tbConsoReserva in conexion.ConsolidadoReservas
                                where tbConsoReserva.IdPersona == IdPersona && tbConsoReserva.Estado == false
                                select tbConsoReserva;

            return objEncontrado.ToList();
        }
        public IEnumerable<ConsolidadoReserva> GetAllConsolidadoReservaTrue(int IdPersona)
        {
            var objEncontrado = from tbConsoReserva in conexion.ConsolidadoReservas
                                where tbConsoReserva.IdPersona == IdPersona && tbConsoReserva.Estado == true
                                select tbConsoReserva;

            return objEncontrado.ToList();
        }

    }
}
