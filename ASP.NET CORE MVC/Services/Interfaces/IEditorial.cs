using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IEditorial
    {
        void AñadiEditorial(Editorial objEditorial);
        IEnumerable<Editorial> GetAllEditoriales();
        IEnumerable<Editorial> GetAllEditorialesTrue();
        int GetCorrelativoEditorial();
        Editorial GetIdEditorialModificar(int codigo);
        void EditDetails_Editorial(Editorial objEditorial);
        bool EliminarEditorial(int codigo);
        Editorial Buscar(string buscar);
    }
}
