using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IConsolidadoLibro
    {
        IEnumerable<ConsolidadoLibro> GetAllCatalogo();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoEdit();
        ConsolidadoLibro GetIdLibro(int codigo);
        IEnumerable<ConsolidadoLibro> GetAllCatalogoRecientemente();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoInfantil();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoCuento();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoNovela();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoPoesia();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoTerror();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoAccion();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoAventura();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoRomance();
        IEnumerable<ConsolidadoLibro> GetAllCatalogoView();
        int GetTituloIdLibro(string titulo);
        IEnumerable<ConsolidadoLibro>? GetAutorLibro(string autor);
        IEnumerable<ConsolidadoLibro>? GetCategoriaIdLibro(string categoria);
        int GetTituCateAutoIdLibro(string titulo, string categoria, string autor);
        IEnumerable<ConsolidadoLibro>? GetCateAutorIdLibro(string categoria, string autor);
    }
}
