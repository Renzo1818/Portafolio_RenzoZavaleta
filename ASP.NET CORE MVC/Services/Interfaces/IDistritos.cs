using BIBLIOTECA_VIRTUAL.Models;

namespace BIBLIOTECA_VIRTUAL.Services.Interfaces
{
    public interface IDistritos
    {
        IEnumerable<Distrito> GetAllDistritos();
    }
}
