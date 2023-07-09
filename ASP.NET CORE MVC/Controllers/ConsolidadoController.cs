using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace BIBLIOTECA_VIRTUAL.Controllers
{
    public class ConsolidadoController : Controller
    {
        private readonly IConsolidadoLibro _iconsolidado;
        private readonly ICategoria _icategoria;
        private readonly IAutor _iautor;

        public ConsolidadoController(IConsolidadoLibro consolidadoLibro, ICategoria categoria, IAutor autor)
        {
            _iconsolidado = consolidadoLibro;
            _icategoria = categoria;
            _iautor = autor;
        }
        [Route("Consolidado/DetalleLibro/{IdLibro}")]
        public IActionResult DetalleLibro(int IdLibro)
        {
            var objEncrontrado = _iconsolidado.GetIdLibro(IdLibro);
            return View(objEncrontrado);
        }

        public IActionResult Catalogo(IEnumerable<ConsolidadoLibro>? autores, IEnumerable<ConsolidadoLibro>? categorias, IEnumerable<ConsolidadoLibro>? cateAutor)
        {
            ViewBag.Categorias1 = _icategoria.GetAllCategorias();
            ViewBag.Autores = _iautor.GetAllAutoresTrue();
            if(autores.Count() == 0 && categorias.Count() == 0 && cateAutor.Count() == 0)
            {
                return View(_iconsolidado.GetAllCatalogoView());
            }
            else if(autores.Count() > 0 && categorias.Count() == 0 && cateAutor.Count() == 0)
            {
                return View(autores);
            }
            else if(categorias.Count() > 0 && autores.Count() == 0 && cateAutor.Count() == 0)
            {
                return View(categorias);
            }
            else
            {
                return View(cateAutor);
            }
            
        }
        public IActionResult Buscar(string titulo, string categoria, string autores)
        {

            if (titulo != null && categoria != null && autores != null)
            {
                int idLibro = _iconsolidado.GetTituCateAutoIdLibro(titulo, categoria, autores);
                if (idLibro != -1)
                {
                    return RedirectToAction("DetalleLibro", new { id = idLibro });
                }
                else
                {
                    return RedirectToAction("LibroNoFound");
                }
            }
            else if (categoria != null && autores != null)
            {
                if (_iconsolidado.GetCateAutorIdLibro(categoria, autores).Count() > 0)
                {
                    return RedirectToAction("Catalogo", new { cateAutor = _iconsolidado.GetCateAutorIdLibro(categoria, autores) });
                }
                else
                {
                    return RedirectToAction("LibroNoFound");
                }
            }
            else if(titulo != null && autores == null && categoria == null)
            {
                int idLibro = _iconsolidado.GetTituloIdLibro(titulo);
                if (idLibro != -1)
                {
                    return RedirectToAction("DetalleLibro", new { id = idLibro });
                }
                else
                {
                    return RedirectToAction("LibroNoFound");
                }
            }
            else if(autores != null && categoria == null && titulo == null)
            {
                if (_iconsolidado.GetAutorLibro(autores).Count() > 0)
                {
                    return RedirectToAction("Catalogo", new { autores = _iconsolidado.GetAutorLibro(autores)});
                }
                else
                {
                    return RedirectToAction("LibroNoFound");
                }
            }
            else if(categoria != null && titulo == null && autores == null)
            {
                if (_iconsolidado.GetCategoriaIdLibro(categoria).Count() > 0)
                {
                    return RedirectToAction("Catalogo", new { categorias = _iconsolidado.GetCategoriaIdLibro(categoria)});
                }
                else
                {
                    return RedirectToAction("LibroNoFound");
                }
            }
            else
            {
                return RedirectToAction("LibroNoFound");
            }
        }
        public IActionResult LibroNoFound()
        {
            ViewBag.Categorias1 = _icategoria.GetAllCategorias();
            ViewBag.Autores = _iautor.GetAllAutoresTrue();
            return View();
        }
    }
}
