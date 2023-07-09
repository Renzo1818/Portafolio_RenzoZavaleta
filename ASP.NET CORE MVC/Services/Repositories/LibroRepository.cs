using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;
using System.Diagnostics;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class LibroRepository : ILibro
    {
        private OperaC conexion = new OperaC();
        private readonly IWebHostEnvironment _webHostEnvironment;

        public LibroRepository(IWebHostEnvironment webHostEnvironment)
        {
            _webHostEnvironment = webHostEnvironment;
        }
        public void AñadirLibro(IFormFile imagenLibro, Libro objLibro)
        {
            var webRootPath = _webHostEnvironment.WebRootPath;
            var rutaImagen = Path.Combine("~", "imagenes-libros", imagenLibro.FileName);
            var rutaCompleta = Path.Combine(webRootPath, "imagenes-libros", imagenLibro.FileName);
            
            if (File.Exists(rutaImagen))
            {
                var nombreArchivo = Path.GetFileNameWithoutExtension(imagenLibro.FileName);
                var extension = Path.GetExtension(imagenLibro.FileName);
                var nuevoNombreArchivo = $"{nombreArchivo}_{Guid.NewGuid()}{extension}";

                rutaImagen = Path.Combine("imagenes-libros", nuevoNombreArchivo);
            }

            using (var fileStream = new FileStream(rutaCompleta, FileMode.Create))
            {
                imagenLibro.CopyTo(fileStream);
            }
            objLibro.RutaImgLibro = rutaImagen;
            conexion.Libros.Add(objLibro);
            conexion.SaveChanges();

        }

        public void EditDetails_LibroIMG(IFormFile imagenLibro,Libro objLibro, RegistroLibrosEditorial objRegistro)
        {

            var objModificar = (from tbLibro in conexion.Libros where tbLibro.IdLibro == objLibro.IdLibro select tbLibro).FirstOrDefault();
            objModificar.Titulo = objLibro.Titulo;
            objModificar.IdCategoria = objLibro.IdCategoria;
            objModificar.IdAutor = objLibro.IdAutor;
            objModificar.IdEditorial = objLibro.IdEditorial;
            objModificar.Stock += objRegistro.CantidadLibros;
            objModificar.FechaPublicacion = objLibro.FechaPublicacion;

            var webRootPath = _webHostEnvironment.WebRootPath;
            var rutaImagen = Path.Combine("~", "imagenes-libros", imagenLibro.FileName);
            var rutaCompleta = Path.Combine(webRootPath, "imagenes-libros", imagenLibro.FileName);

            if (File.Exists(rutaImagen))
            {
                var nombreArchivo = Path.GetFileNameWithoutExtension(imagenLibro.FileName);
                var extension = Path.GetExtension(imagenLibro.FileName);
                var nuevoNombreArchivo = $"{nombreArchivo}_{Guid.NewGuid()}{extension}";

                rutaImagen = Path.Combine("imagenes-libros", nuevoNombreArchivo);
            }

            using (var fileStream = new FileStream(rutaCompleta, FileMode.Create))
            {
                imagenLibro.CopyTo(fileStream);
            }

            objModificar.RutaImgLibro = rutaImagen;

            conexion.SaveChanges();
        }

        public void EditDetails_Libro(Libro objLibro, RegistroLibrosEditorial objRegistro)
        {

            var objModificar = (from tbLibro in conexion.Libros where tbLibro.IdLibro == objLibro.IdLibro select tbLibro).FirstOrDefault();
            objModificar.Titulo = objLibro.Titulo;
            objModificar.IdCategoria = objLibro.IdCategoria;
            objModificar.IdAutor = objLibro.IdAutor;
            objModificar.IdEditorial = objLibro.IdEditorial;
            objModificar.Stock += objRegistro.CantidadLibros;
            objModificar.FechaPublicacion = objLibro.FechaPublicacion;

            conexion.SaveChanges();
        }
        public void StockLibro(int IdLibro)
        {
            var objModificar = (from tbLibro in conexion.Libros where tbLibro.IdLibro == IdLibro select tbLibro).FirstOrDefault();
            objModificar.Stock -= 1;

            conexion.SaveChanges();
        }

        public void SinStock(int IdLibro)
        {
            var objModificar = (from tbLibro in conexion.Libros where tbLibro.IdLibro == IdLibro select tbLibro).FirstOrDefault();
            if(objModificar.Stock == 0)
            {
                objModificar.Estado = false;
                conexion.SaveChanges();
            }
        }
        public void StockDevolver(int IdLibro)
        {
            var objModificar = (from tbLibro in conexion.Libros where tbLibro.IdLibro == IdLibro select tbLibro).FirstOrDefault();
            objModificar.Stock += 1;

            conexion.SaveChanges();
        }

        public IEnumerable<Libro> GetAllLibros()
        {
            return conexion.Libros;
        }

        public int GetCorrelativoLibro()
        {
            return conexion.Libros.Max(lb => lb.IdLibro);
        }

        public Libro GetIdLibro_Modificar(int codigo)
        {
            var obj = (from tbLibro in conexion.Libros where tbLibro.IdLibro == codigo select tbLibro).Single();
            return obj;
        }

        public bool EliminarLibro(int codigo)
        {
            var objModificar = (from tbLibro in conexion.Libros where tbLibro.IdLibro == codigo select tbLibro).FirstOrDefault();

            if (objModificar != null)
            {
                objModificar.Estado = false;
                conexion.SaveChanges();

                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
