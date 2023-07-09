using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class EditorialController : Controller
    {
        private readonly IEditorial _ieditorial;

        public EditorialController(IEditorial editorial)
        {
            _ieditorial = editorial;
        }

        public IActionResult ListadoEditorial()
        {
            return View(_ieditorial.GetAllEditoriales());
        }
        public IActionResult FormularioEditorial()
        {
            return View();
        }
        public IActionResult AñadirEditorial(Editorial objEditorial)
        {
            _ieditorial.AñadiEditorial(objEditorial);
            TempData["Mensaje"] = "Se añadio correctamente la editorial!!!";
            return RedirectToAction("FormularioEditorial");
        }
        public IActionResult FormularioEditEditorial()
        {
            return View(_ieditorial.GetAllEditorialesTrue());
        }

        [Route("Editorial/FormularioModificar/{IdEditorial}")]
        public IActionResult FormularioModificar(int IdEditorial)
        {
            var objAutor = _ieditorial.GetIdEditorialModificar(IdEditorial);

            return View(objAutor);
        }

        public IActionResult ModificarEditorial(Editorial objEditorial)
        {
            _ieditorial.EditDetails_Editorial(objEditorial);
            TempData["Mensaje"] = "Se modifico correctamente!!";
            return RedirectToAction("FormularioEditEditorial");
        }

        [Route("Editorial/EliminarEditorial/{IdEditorial}")]
        public IActionResult EliminarEditorial(int IdEditorial)
        {
            bool flag = _ieditorial.EliminarEditorial(IdEditorial);
            if (flag == true)
            {
                TempData["Mensaje"] = "Se elimino correctamente!!";
                return RedirectToAction("FormularioEditEditorial");
            }
            else
            {
                TempData["Mensaje"] = "Hubo un error al momento de elimnar";
                return RedirectToAction("FormularioEditEditorial");
            }
        }
    }
}
