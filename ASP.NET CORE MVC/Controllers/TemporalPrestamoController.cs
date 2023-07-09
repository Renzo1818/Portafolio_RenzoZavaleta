using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class TemporalPrestamoController : Controller
    {
        private readonly ITemporal _iprestamo;
        private readonly IDetalleReserva _idetaller;

        public TemporalPrestamoController(ITemporal temporal, IDetalleReserva detalleReserva)
        {
            _iprestamo = temporal;
            _idetaller = detalleReserva;
        }
        public IActionResult AgregarCarrito(int codLibro, string txtTitulo, string txtAutor, string txtEditorial, string txtCategoria)
        {
            int? idPersona = HttpContext.Session.GetInt32("IdPersona");
            bool flag = _iprestamo.GetIdTemporal(codLibro);
            bool flag2 = _idetaller.ValidarLibroPrestado((int)idPersona, codLibro);
            
            if(flag2 == true)
            {
                if (flag == true)
                {
                    TempData["ALERTA"] = "NO ES POSIBLE PRESTAR EL MISMO LIBRO BRO!!!";
                    return RedirectToAction("VerCarrito");
                }
                else
                {


                    TemporalPrestamo objTemp = new TemporalPrestamo();
                    objTemp.codigoLibro = codLibro;
                    objTemp.codigoPersona = (int)idPersona;
                    objTemp.titulo = txtTitulo;
                    objTemp.autor = txtAutor;
                    objTemp.editorial = txtEditorial;
                    objTemp.categoria = txtCategoria;
                    objTemp.cantidad = 1;
                    objTemp.fechaInicio = DateTime.Now;
                    objTemp.fechaFin = DateTime.Now.AddDays(5);
                    objTemp.estado = true;

                    _iprestamo.add(objTemp);
                    return RedirectToAction("VerCarrito");
                }
            }
            else
            {
                TempData["ALERTA"] = "EL LIBRO ESTA EN PRESTAMO!!!";
                return RedirectToAction("VerCarrito");
            }
            
        }

        public IActionResult VerCarrito()
        {
            return View(_iprestamo.GetAllTemporaryPrestamo());
        }

        [Route("TemporalPrestamo/EliminarRegistrar/{id}")]
        public IActionResult EliminarRegistrar(int id)
        {
            _iprestamo.EliminarLibro(id);
            return RedirectToAction("VerCarrito");
        }
    }
}
