using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface ILibro
    {
        void AñadirLibro(IFormFile imagenLibro, Libro objLibro);
        IEnumerable<Libro> GetAllLibros();
        int GetCorrelativoLibro();
        Libro GetIdLibro_Modificar(int codigo);
        void EditDetails_LibroIMG(IFormFile imagenLibro, Libro objLibro, RegistroLibrosEditorial objRegistro);
        void EditDetails_Libro(Libro objLibro, RegistroLibrosEditorial objRegistro);
        bool EliminarLibro(int codigo);
        void StockLibro(int IdLibro);
        void SinStock(int IdLibro);
        void StockDevolver(int IdLibro);

    }
}
