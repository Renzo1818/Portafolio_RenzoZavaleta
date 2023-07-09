using Microsoft.AspNetCore.Mvc;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class GeneralController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult LoginCliente()
        {
            HttpContext.Session.SetInt32("IdTipoPersona", 1);
            return RedirectToAction("Login","Usuario");
        }

        public IActionResult LoginAdmin()
        {
            HttpContext.Session.SetInt32("IdTipoPersona", 2);
            return RedirectToAction("Login", "Usuario");
        }
    }
}
