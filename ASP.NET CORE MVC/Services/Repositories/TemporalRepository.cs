using BIBLIOTECA_VIRTUAL.Models;
using BIBLIOTECA_VIRTUAL.Services.Interfaces;

namespace BIBLIOTECA_VIRTUAL.Services.Repositories
{
    public class TemporalRepository : ITemporal
    {
        private List<TemporalPrestamo> _lst = new List<TemporalPrestamo>();
        public void add(TemporalPrestamo temporal)
        {
            _lst.Add(temporal);
        }

        public IEnumerable<TemporalPrestamo> GetAllTemporaryPrestamo()
        {
            return _lst;
        }

        public void EliminarLibro(int id)
        {
            TemporalPrestamo objremove = _lst.Find(x => x.codigoLibro.Equals(id));
            if (objremove != null)
            {
                _lst.Remove(objremove);
            }
        }

        public bool GetIdTemporal(int id)
        {
            var objbuscar = _lst.Find(x => x.codigoLibro.Equals(id));
            if(objbuscar == null)
            {
                return false;
            }
            else
            {
                return true;
            }
        }

        public void EliminarTodo()
        {
            _lst.Clear();
        }
    }
}
