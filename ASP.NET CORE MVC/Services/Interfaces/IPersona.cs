using BIBLIOTECA_VIRTUAL.Models;
namespace BIBLIOTECA_VIRTUAL.Services
{
    public interface IPersona
    {
        void AñadirPersona(Persona objPersona);
        IEnumerable<Persona> GetAllPersonas();
        int GetCorrelativoPersona();
    }
}
