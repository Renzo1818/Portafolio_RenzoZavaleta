using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class Registro_Libro_EditorialController : Controller
    {
        private readonly ILibro _ilibro;
        private readonly ICategoria _icategoria;
        private readonly IAutor _iautor;
        private readonly IEditorial _ieditorial;
        private readonly IDetalle_Libro _idetalle_Libro;
        private readonly IRegistro_Libro_Editorial _iregistro;
        private readonly IConsolidadoLibro _iconsolidado_Libro;

        public Registro_Libro_EditorialController(ILibro libro, IEditorial editorial, ICategoria categoria, IAutor autor, IDetalle_Libro detalle_Libro, 
                                                  IRegistro_Libro_Editorial registro, IConsolidadoLibro consolidadoLibro)
        {
            _iautor = autor;
            _ilibro = libro;
            _icategoria = categoria;
            _ieditorial = editorial;
            _idetalle_Libro = detalle_Libro;
            _iregistro = registro;
            _iconsolidado_Libro = consolidadoLibro;
        }

        public IActionResult ListadoCatalogo()
        {
            return View(_iconsolidado_Libro.GetAllCatalogo());
        }
        public IActionResult FormularioRegistro()
        {
            ViewBag.Autor = _iautor.GetAllAutores();
            ViewBag.Categoria = _icategoria.GetAllCategorias();
            ViewBag.Editorial = _ieditorial.GetAllEditoriales();
            return View();
        }
        public IActionResult Registrar(int IdCategoria, int IdAutor, int IdEditorial, string txtTitulo, int txtCantidad, DateTime txtFechaPubli, 
                                        string txtSinopsis,IFormFile imagenLibro)
        {
            if(imagenLibro != null && imagenLibro.Length > 0)
            {
                int? idPersona = HttpContext.Session.GetInt32("IdPersona");

                Libro objlibro = new Libro();
                objlibro.IdAutor = IdAutor;
                objlibro.IdEditorial = IdEditorial;
                objlibro.IdCategoria = IdCategoria;
                objlibro.Titulo = txtTitulo;
                objlibro.Stock = txtCantidad;
                objlibro.Estado = true;
                objlibro.FechaPublicacion = txtFechaPubli;
                _ilibro.AñadirLibro(imagenLibro, objlibro);

                DetalleLibro objDetalle = new DetalleLibro();
                objDetalle.IdLibro = _ilibro.GetCorrelativoLibro();
                objDetalle.Sinopsis = txtSinopsis;
                _idetalle_Libro.AñadirSinopsis(objDetalle);

                RegistroLibrosEditorial objRegistro = new RegistroLibrosEditorial();
                objRegistro.IdPersona = (int)idPersona;
                objRegistro.IdLibro = _ilibro.GetCorrelativoLibro();
                objRegistro.CantidadLibros = txtCantidad;
                objRegistro.FechaRegistro = DateTime.Now;
                _iregistro.AñadirRegistro_Libro_Editorial(objRegistro);

                TempData["Mensaje"] = "Se añadio correctamente el libro!!!";
                return RedirectToAction("FormularioRegistro");
            }
            else
            {
                TempData["Mensaje"] = "Error al añadir el libro!!!";
                return RedirectToAction("FormularioRegistro");
            }
        }

        public IActionResult FormularioEditRegistro()
        {
            return View(_iconsolidado_Libro.GetAllCatalogoEdit());
        }


        [Route("Registro_Libro_Editorial/FormularioModificar/{IdLibro}")]
        public IActionResult FormularioModificar(int IdLibro)
        {
            ViewBag.Autor = _iautor.GetAllAutores();
            ViewBag.Categoria = _icategoria.GetAllCategorias();
            ViewBag.Editorial = _ieditorial.GetAllEditoriales();
            var objLibro = _iconsolidado_Libro.GetIdLibro(IdLibro);

            return View(objLibro);
        }

        public IActionResult ModificarRegistro(int IdLibro, string txtTitulo, int IdCategoria, int IdAutor, int IdEditorial,
                                               int txtCantidad, DateTime txtFechaPubli, IFormFile imagenLibro, string txtSinopsis)
        {
            if(imagenLibro != null && imagenLibro.Length > 0)
            {
                RegistroLibrosEditorial objRegistro = new RegistroLibrosEditorial();
                objRegistro.IdLibro = IdLibro;
                objRegistro.CantidadLibros = txtCantidad;
                objRegistro.FechaRegistro = DateTime.Now;

                _iregistro.EditDetails_Registro(objRegistro);


                Libro objLibro = new Libro();
                objLibro.IdLibro = IdLibro;
                objLibro.Titulo = txtTitulo;
                objLibro.IdCategoria = IdCategoria;
                objLibro.IdAutor = IdAutor;
                objLibro.IdEditorial = IdEditorial;
                objLibro.FechaPublicacion = txtFechaPubli;

                var objIdObj = _iregistro.GetIdRegistroModificar(objLibro.IdLibro);

                _ilibro.EditDetails_LibroIMG(imagenLibro,objLibro, objIdObj);

                DetalleLibro objDetalleLibro = new DetalleLibro();
                objDetalleLibro.IdLibro = IdLibro;
                objDetalleLibro.Sinopsis = txtSinopsis;

                _idetalle_Libro.EditDetails_Sinopsis(objDetalleLibro);


                TempData["Mensaje"] = "Se modifico correctamente!!";
                return RedirectToAction("FormularioEditRegistro");
            }
            else
            {
                RegistroLibrosEditorial objRegistro = new RegistroLibrosEditorial();
                objRegistro.IdLibro = IdLibro;
                objRegistro.CantidadLibros = txtCantidad;
                objRegistro.FechaRegistro = DateTime.Now;

                _iregistro.EditDetails_Registro(objRegistro);


                Libro objLibro = new Libro();
                objLibro.IdLibro = IdLibro;
                objLibro.Titulo = txtTitulo;
                objLibro.IdCategoria = IdCategoria;
                objLibro.IdAutor = IdAutor;
                objLibro.IdEditorial = IdEditorial;
                objLibro.FechaPublicacion = txtFechaPubli;


                var objIdObj = _iregistro.GetIdRegistroModificar(objLibro.IdLibro);

                _ilibro.EditDetails_Libro(objLibro, objIdObj);

                DetalleLibro objDetalleLibro = new DetalleLibro();
                objDetalleLibro.IdLibro = IdLibro;
                objDetalleLibro.Sinopsis = txtSinopsis;

                _idetalle_Libro.EditDetails_Sinopsis(objDetalleLibro);
                

                TempData["Mensaje"] = "Se modifico correctamente!!";
                return RedirectToAction("FormularioEditRegistro");
            }
        }

        [Route("Registro_Libro_Editorial/EliminarRegistro/{IdLibro}")]
        public IActionResult EliminarRegistro(int IdLibro)
        {
            bool flag = _ilibro.EliminarLibro(IdLibro);
            if (flag == true)
            {
                TempData["Mensaje"] = "Se elimino correctamente!!";
                return RedirectToAction("FormularioEditRegistro");
            }
            else
            {
                TempData["Mensaje"] = "Hubo un error al momento de elimnar";
                return RedirectToAction("FormularioEditRegistro");
            }
        }

    }
}
