using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IDetalle_Libro
    {
        void AñadirSinopsis(DetalleLibro objSinopsis);
        void EditDetails_Sinopsis(DetalleLibro objSinopsis);
    }
}
