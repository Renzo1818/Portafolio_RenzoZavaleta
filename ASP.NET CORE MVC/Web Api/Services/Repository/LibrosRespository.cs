using API_BIBLIO.Models;
using API_BIBLIO.Services.Interfaces;

namespace API_BIBLIO.Services.Repository
{
    public class LibrosRespository:ILibros
    {
        OperaC conexion = new OperaC();
        public IEnumerable<ConsolidadoLibro> getAllConsolidado()
        {
            return conexion.ConsolidadoLibros;
        }

        public ConsolidadoLibro getId(int id)
        {
            var obj = conexion.ConsolidadoLibros.Where(x => x.IdLibro == id).FirstOrDefault();
            return obj;
        }

        public void add(Libro libro)
        {
            conexion.Libros.Add(libro);
            conexion.SaveChanges();
        }

        public void delete(int id)
        {
            conexion.Remove(getId(id));
        }

        public void update(Libro libro)
        {
            var objModificar = (from tbLibro in conexion.Libros where tbLibro.IdLibro == libro.IdLibro select tbLibro).FirstOrDefault();
            objModificar.Titulo = libro.Titulo;
            objModificar.IdCategoria = libro.IdCategoria;
            objModificar.IdAutor = libro.IdAutor;
            objModificar.IdEditorial = libro.IdEditorial;
            objModificar.Stock += libro.Stock;
            objModificar.FechaPublicacion = libro.FechaPublicacion;

            conexion.SaveChanges();
        }
    }   
}
