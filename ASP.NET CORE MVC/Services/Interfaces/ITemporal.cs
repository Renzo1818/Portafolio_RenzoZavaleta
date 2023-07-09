using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface ITemporal
    {
        void add(TemporalPrestamo temporal);
        IEnumerable<TemporalPrestamo> GetAllTemporaryPrestamo();
        void EliminarLibro(int id);
        bool GetIdTemporal(int id);
        void EliminarTodo();
    }
}
