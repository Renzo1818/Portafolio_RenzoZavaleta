using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class AutorController : Controller
    {
        private readonly IAutor _iautor;

        public AutorController(IAutor autor)
        {
            _iautor = autor;
        }
        public IActionResult ListadoAutor()
        {
            return View(_iautor.GetAllAutores());
        }

        public IActionResult FormularioAutor()
        {
            return View();
        }

        public IActionResult AñadirAutor(Autor objAutor)
        {
            _iautor.AñadirAutor(objAutor);
            TempData["Mensaje"] = "Se añadio correctamente el autor!!!";
            return RedirectToAction("FormularioAutor");
        }

        public IActionResult FormularioEditAutor()
        {
            return View(_iautor.GetAllAutoresTrue());
        }

        [Route("Autor/FormularioModificar/{IdAutor}")]
        public IActionResult FormularioModificar(int IdAutor)
        {
            var objAutor = _iautor.GetIdAutorModificar(IdAutor);

            return View(objAutor);
        }

        public IActionResult ModificarAutor(Autor objAutor)
        {
            _iautor.EditDetails_Autor(objAutor);
            TempData["Mensaje"] = "Se modifico correctamente!!";
            return RedirectToAction("FormularioEditAutor");
        }

        [Route("Autor/EliminarAutor/{IdAutor}")]
        public IActionResult EliminarAutor(int IdAutor)
        {
            bool flag = _iautor.EliminarAutor(IdAutor);
            if (flag == true)
            {
                TempData["Mensaje"] = "Se elimino correctamente!!";
                return RedirectToAction("FormularioEditAutor");
            }
            else
            {
                TempData["Mensaje"] = "Hubo un error al momento de elimnar";
                return RedirectToAction("FormularioEditAutor");
            }
        }
    }
}
