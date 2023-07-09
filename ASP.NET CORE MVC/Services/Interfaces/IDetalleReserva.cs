using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IDetalleReserva
    {
        IEnumerable<DetalleReserva> GetAllDetalleReservas();
        void Agregar(DetalleReserva objDetalle);
        void DevolverLibro(int IdReserva, int IdLibro);
        bool ValidarLibroPrestado(int IdPersona, int IdLibro);
        void ValidarReservaCompleta(int IdReserva);
    }
}
