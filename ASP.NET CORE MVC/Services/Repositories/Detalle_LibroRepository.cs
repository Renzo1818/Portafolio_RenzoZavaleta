using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class Detalle_LibroRepository : IDetalle_Libro
    {
        private OperaC conexion = new OperaC();
        public void AñadirSinopsis(DetalleLibro objSinopsis)
        {
            conexion.DetalleLibros.Add(objSinopsis);
            conexion.SaveChanges();
        }
        public void EditDetails_Sinopsis(DetalleLibro objSinopsis)
        {
            var objModificar = (from tbSinopsis in conexion.DetalleLibros where tbSinopsis.IdLibro == objSinopsis.IdLibro select tbSinopsis).FirstOrDefault();
            objModificar.Sinopsis = objSinopsis.Sinopsis;

            conexion.SaveChanges();
        }
    }
}
