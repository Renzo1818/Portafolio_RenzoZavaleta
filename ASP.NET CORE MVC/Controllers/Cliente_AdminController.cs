using Microsoft.AspNetCore.Mvc;
using BIBLIOTECA_VIRTUAL.Services;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class Cliente_AdminController : Controller
    {
        private readonly IPersona _ipersona;
        private readonly IDistritos _idistritos;

        public Cliente_AdminController(IPersona persona, IDistritos distritos)
        {
            _ipersona = persona;
            _idistritos = distritos;
        }
        public IActionResult FormularioCliente_Admin()
        {
            int? idTipoPersona = HttpContext.Session.GetInt32("IdTipoPersona");

            if (idTipoPersona.HasValue)
            {
                ViewData["idTipoPersona"] = idTipoPersona;
                ViewBag.Distritos = _idistritos.GetAllDistritos();
                return View();
            }
            else
            {
                return RedirectToAction("Index", "General");
            }


           
        }

        public IActionResult AgregarCliente_Admin(Persona objPersona)
        {
            TempData["Mensaje"] = "El cliente se ha agregado correctamente.";
            _ipersona.AñadirPersona(objPersona);
            return RedirectToAction("FormularioUsuario", "Usuario");
            
        }

        public IActionResult Listar()
        {
            return View(_ipersona.GetAllPersonas());
        }
    }
}
