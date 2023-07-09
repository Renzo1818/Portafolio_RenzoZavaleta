using API_BIBLIO.Models;

namespace API_BIBLIO.Services.Interfaces
{
    public interface ILibros
    {
        IEnumerable<ConsolidadoLibro> getAllConsolidado();
        ConsolidadoLibro getId(int id);
        void add(Libro libro);
        void update(Libro libro);
        void delete(int id);
    }
}
