using BIBLIOTECA_VIRTUAL.Models;
namespace BIBLIOTECA_VIRTUAL.Services
{
    public class PersonaRepository:IPersona
    {
        private OperaC conexion = new OperaC();

        public void AñadirPersona(Persona objPersona)
        {
            conexion.Personas.Add(objPersona);
            conexion.SaveChanges();
        }
        public int GetCorrelativoPersona()
        {
            return conexion.Personas.Max(p => p.IdPersona);
        }

        public IEnumerable<Persona> GetAllPersonas()
        {
            return conexion.Personas;
        }

    }
}
