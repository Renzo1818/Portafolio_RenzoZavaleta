using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class ReservaController : Controller
    {
        private readonly IReserva _ireserva;
        private readonly IDetalleReserva _idetaller;
        private readonly IConsolidadoReserva _iconsolidadoR;
        private readonly ITemporal _itemporal;
        private readonly ILibro _ilibro;

        public ReservaController(IReserva reserva, IDetalleReserva detalleReserva, IConsolidadoReserva consolidadoReserva,ITemporal temporal, ILibro libro)
        {
            _ireserva = reserva;
            _idetaller = detalleReserva;
            _iconsolidadoR = consolidadoReserva;
            _itemporal = temporal;
            _ilibro = libro;
        }
        public IActionResult Agregar()
        {
            int? idPersona = HttpContext.Session.GetInt32("IdPersona");
            IEnumerable<TemporalPrestamo> carrito = _itemporal.GetAllTemporaryPrestamo();

            if (carrito.Count() > 0)
            {
                ReservaVirtual objReserva = new ReservaVirtual();
                objReserva.IdPersona = (int)idPersona;
                objReserva.FechaInicio = DateTime.Now;
                objReserva.FechaFinal = DateTime.Now.AddMinutes(3);
                objReserva.Estado = false;
                _ireserva.Agregar(objReserva);


                foreach (var item in carrito)
                {
                    DetalleReserva objDetalle = new DetalleReserva();
                    objDetalle.IdReserva = _ireserva.GetCorrelativo();
                    objDetalle.IdLibro = item.codigoLibro;
                    objDetalle.Cantidad = item.cantidad;
                    objDetalle.Estado = false;

                    _idetaller.Agregar(objDetalle);
                    _ilibro.StockLibro(objDetalle.IdLibro);
                    _ilibro.SinStock(objDetalle.IdLibro);
                }
                TempData["PROCESAR"] = "SE PROCESO CORRECTAMENTE LA RESERVA";
                _itemporal.EliminarTodo();

                return RedirectToAction("VerReserva");
            }
            else
            {
                TempData["ALERTA"] = "EL CARRITO ESTA VACIO!!";
                return RedirectToAction("VerCarrito","TemporalPrestamo");
            }
        }

        public IActionResult VerReserva()
        {
            int? idPersona = HttpContext.Session.GetInt32("IdPersona");
            return View(_iconsolidadoR.GetAllConsolidadoReservaFalse((int)idPersona));
        }

        [Route("Reserva/Devolver/{IdReserva}/{IdLibro}")]
        public IActionResult Devolver(int IdReserva, int IdLibro)
        {
            _idetaller.DevolverLibro(IdReserva,IdLibro);
            _ilibro.StockDevolver(IdLibro);
            TempData["PROCESAR"] = "LIBRO DEVUELTO!!";
            _idetaller.ValidarReservaCompleta(IdReserva);
            return RedirectToAction("VerReserva");
        }
        public IActionResult VerHistorial()
        {
            int? idPersona = HttpContext.Session.GetInt32("IdPersona");
            return View(_iconsolidadoR.GetAllConsolidadoReservaTrue((int)idPersona));
        }
    }
}
