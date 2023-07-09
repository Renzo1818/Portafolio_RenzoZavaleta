using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class VistaMainController : Controller
    {
        private readonly ILibro _ilibro;
        private readonly IAutor _iautor;
        private readonly IEditorial _ieditorial;
        private readonly IConsolidadoLibro _iconsolidado;
        public VistaMainController(ILibro libro, IAutor autor, IEditorial editorial, IConsolidadoLibro consolidadoLibro)
        {
            _ilibro = libro;
            _iautor = autor;
            _ieditorial = editorial;
            _iconsolidado = consolidadoLibro;
        }
        public IActionResult VistaMain_Cliente()
        {
            ViewBag.Reciente = _iconsolidado.GetAllCatalogoRecientemente();
            ViewBag.Infantil = _iconsolidado.GetAllCatalogoInfantil();
            ViewBag.Cuento = _iconsolidado.GetAllCatalogoCuento();
            ViewBag.Novela = _iconsolidado.GetAllCatalogoNovela();
            ViewBag.Poesia = _iconsolidado.GetAllCatalogoPoesia();
            ViewBag.Terror = _iconsolidado.GetAllCatalogoTerror();
            ViewBag.Accion = _iconsolidado.GetAllCatalogoAccion();
            ViewBag.Aventura = _iconsolidado.GetAllCatalogoAventura();
            ViewBag.Romance = _iconsolidado.GetAllCatalogoRomance();


            var objsession = HttpContext.Session.GetString("sUsuario");
            if (objsession != null)
            {
                var obj = JsonConvert.DeserializeObject<Usuario> (HttpContext.Session.GetString("sUsuario"));
                return View();
            }
            else
            {
                return RedirectToAction("Login", "Usuario");
            }
        }
        public IActionResult VistaMain_Admin()
        {
            ViewBag.CountLibros = _ilibro.GetAllLibros().Count();
            ViewBag.CountAutores = _iautor.GetAllAutores().Count();
            ViewBag.CountEditoriales = _ieditorial.GetAllEditoriales().Count();

            var objsession = HttpContext.Session.GetString("sUsuario");
            if (objsession != null)
            {
                var obj = JsonConvert.DeserializeObject<Usuario>(HttpContext.Session.GetString("sUsuario"));
                return View();
            }
            else
            {
                return RedirectToAction("Login", "Usuario");
            }
            
        }
    }
}
