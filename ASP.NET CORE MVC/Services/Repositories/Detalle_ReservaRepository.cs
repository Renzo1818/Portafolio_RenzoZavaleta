using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class Detalle_ReservaRepository : IDetalleReserva
    {
        private OperaC conexion = new OperaC();
        public void Agregar(DetalleReserva objDetalle)
        {
            conexion.DetalleReservas.Add(objDetalle);
            conexion.SaveChanges();
        }

        public IEnumerable<DetalleReserva> GetAllDetalleReservas()
        {
            return conexion.DetalleReservas;
        }
        public void DevolverLibro(int IdReserva, int IdLibro)
        {
            var objEncontrado = (from tbDetalleReserva in conexion.DetalleReservas 
                                where tbDetalleReserva.IdReserva == IdReserva && tbDetalleReserva.IdLibro == IdLibro
                                select tbDetalleReserva).FirstOrDefault();
            objEncontrado.Estado = true;
            conexion.SaveChanges();
        }
        public bool ValidarLibroPrestado(int IdPersona, int IdLibro)
        {
            var objEncontrado = (from tbReserva in conexion.ReservaVirtuals join tbDetalleReserva in conexion.DetalleReservas
                                 on tbReserva.IdReserva equals tbDetalleReserva.IdReserva
                                 select new
                                 {
                                     IdReserva = tbReserva.IdReserva,
                                     IdPersona = tbReserva.IdPersona,
                                     IdLibro = tbDetalleReserva.IdLibro,
                                     Estado = tbDetalleReserva.Estado
                                 }).ToList();

            var objValidar = (from ConsoDevu in objEncontrado where ConsoDevu.IdPersona == IdPersona && ConsoDevu.IdLibro == IdLibro
                              && ConsoDevu.Estado == false select ConsoDevu).ToList();

            if(objValidar.Count() > 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        public void ValidarReservaCompleta(int IdReserva)
        {
            var objEncontrado = (from tbDetalleReserva in conexion.DetalleReservas
                                 where tbDetalleReserva.IdReserva == IdReserva
                                 select tbDetalleReserva).ToList();

            var contadorFalse = 0;
            for (int i = 0; i < objEncontrado.Count(); i++)
            {
                if (objEncontrado[i].Estado == false)
                {
                    contadorFalse++;
                }
            }

            if (contadorFalse == 0)
            {
                var objModificar = (from tbReserva in conexion.ReservaVirtuals
                                   where tbReserva.IdReserva == IdReserva && 
                                   tbReserva.Estado == false select tbReserva).FirstOrDefault();

                objModificar.Estado = true;
                conexion.SaveChanges();
            }
        }
    }   
}
