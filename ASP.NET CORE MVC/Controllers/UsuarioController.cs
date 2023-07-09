using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class UsuarioController : Controller
    {
        private readonly IUsuario _iusuario;
        private readonly IPersona _ipersona;

        public UsuarioController(IUsuario usuario, IPersona persona)
        {
            _iusuario = usuario;
            _ipersona = persona;
        }
        public IActionResult FormularioUsuario()
        {
            ViewData["idPersona"] = _ipersona.GetCorrelativoPersona();
            return View();
        }

        public IActionResult Login()
        {
            return View();
        }

        public IActionResult AgregarUsuario(Usuario objUsuario)
        {
            int? idTipoPersona = HttpContext.Session.GetInt32("IdTipoPersona");
            TempData["Mensaje"] = "El usuario se ha agregado correctamente.";
            _iusuario.AñadirUsuario(objUsuario);

            HttpContext.Session.SetInt32("IdPersona", _iusuario.GetIdUsuario(objUsuario));

            HttpContext.Session.SetString("sUsuario", JsonConvert.SerializeObject(objUsuario));

            if (idTipoPersona.HasValue)
            {
                if (idTipoPersona == 1)
                {
                    return RedirectToAction("VistaMain_Cliente", "VistaMain");
                }
                else if (idTipoPersona == 2)
                {
                    return RedirectToAction("VistaMain_Admin", "VistaMain");
                }
                else
                {
                    TempData["Mensaje"] = "ERROR FATAL EL ID_TIPO_PERSONA";
                    return RedirectToAction("Login", "Usuario");
                }
            }
            else
            {
                return RedirectToAction("Index", "General");
            }

        }

        public IActionResult Validar(Usuario usuario)
        {
            int? idTipoPersona = HttpContext.Session.GetInt32("IdTipoPersona");
            HttpContext.Session.SetString("sUsuario", JsonConvert.SerializeObject(usuario));

            if (_iusuario.ValidateUser(usuario) == true)
            {
                HttpContext.Session.SetInt32("IdPersona", _iusuario.GetIdUsuario(usuario));

                if (idTipoPersona.HasValue)
                {
                    if (idTipoPersona == 1)
                    {
                        return RedirectToAction("VistaMain_Cliente", "VistaMain");
                    }
                    else if (idTipoPersona == 2)
                    {
                        return RedirectToAction("VistaMain_Admin", "VistaMain");
                    }
                    else
                    {
                        TempData["Mensaje"] = "ERROR FATAL EL ID_TIPO_PERSONA";
                        return RedirectToAction("Index", "General");
                    }
                }
                else
                {
                    return RedirectToAction("Index","General");
                }
            }
            else
            {
                TempData["Mensaje"] = "Username o contraseña incorrecta!!";
                return RedirectToAction("Login","Usuario");
            }
        }

        public IActionResult CerrarSesion()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("Index", "General");
        }
    }
}
