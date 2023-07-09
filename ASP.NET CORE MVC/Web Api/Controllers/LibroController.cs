using API_BIBLIO.Models;
using API_BIBLIO.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace API_BIBLIO.Controllers
{
    [Route("Api/[Controller]")]
    [ApiController]
    public class LibroController : Controller
    {

        private readonly ILibros _libros;
        public LibroController(ILibros libros)
        {
            _libros = libros;
        }


        [HttpGet]
        public IActionResult Get()
        {
            return Ok(_libros.getAllConsolidado());
        }


        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var obj = _libros.getId(id);
            if (obj == null)
            {
                return NotFound("El Libro(" + id.ToString() + ") no existe");
            }
            else
            {
                return Ok(obj);
            }
        }


        [HttpPost("agregar")]
        public IActionResult add(Libro libro)
        {
            _libros.add(libro);
            return CreatedAtAction(nameof(add), libro);
        }


        [HttpDelete("eliminar/{id}")]
        public IActionResult delete(int id)
        {
            _libros.delete(id);
            return NoContent();
        }


        [HttpPut("modificar")]
        public IActionResult modificar(Libro libro)
        {
            _libros.update(libro);
            return CreatedAtAction(nameof(modificar), libro);
        }
    }
}
